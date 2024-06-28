import React, { useState, useEffect } from 'react';
import Modal from 'react-modal';
import api from "./services/api";
import Board from "./Board";
import "./App.css";

const initialFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";

Modal.setAppElement('#root');

const App = () => {
  const [isFlipped, setIsFlipped] = useState(false);
  const [fen, setFEN] = useState(initialFEN);
  const [gameID, setGameID] = useState(-1);
  const [move, setMove] = useState('');
  const [responseMessage, setResponseMessage] = useState('');
  const [moveRecord, setMoveRecord] = useState([]);
  const [modalIsOpen, setModalIsOpen] = useState(true);
  const [playerColor, setPlayerColor] = useState('white');

  const delay = (ms) => new Promise(resolve => setTimeout(resolve, ms));
  const createNewGame = async (color) => {
    try {
      console.log(`Creating new game as ${color}`);
      const response = await api.post('/new', {
        playerColor: color === 'black' ? 'black' : 'white'
      }, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      console.log('Response from createNewGame:', response);
      const id = response.data.gameID;
      setGameID(id);
      setPlayerColor(color);
      if (color === 'black') {
        setIsFlipped(true);
        const botResponse = await api.post(`/bot-move/${id}`, {
          botColor: "white"
        }, {
          headers: {
            'Content-Type': 'application/json',
          },
        })
        const fenResponse = await api.get(`/fen/${id}`);
        setFEN(fenResponse.data.fen);
        setResponseMessage(botResponse.data.message)
      }
      setModalIsOpen(false);
    } catch (error) {
      const responseMessage = "Error creating new game: " + error.response.data.message;
      console.error(responseMessage);
      setResponseMessage(responseMessage);
    }
  };

  const handleMoveChange = (event) => {
    setMove(event.target.value);
  };

  const fetchMoveRecord = async () => {
    try {
      const response = await api.get(`/move-record/${gameID}`);
      setMoveRecord(response.data.moveList);
    } catch (error) {
      console.error("Error fetching move record:", error.details);
    }
  };

  const handleMoveSubmit = async () => {
    try {
      const response = await api.post(`/move/${gameID}`, {
        'notation': 'san',
        'moveString': move
      }, {
        headers: {
          'Content-Type': 'application/json',
        },
      });
      setResponseMessage(response.data.message);
      setMove('');

      // Get the new game state
      const fenResponse = await api.get(`/fen/${gameID}`);
      setFEN(fenResponse.data.fen);
      await fetchMoveRecord();
      await delay(1000);
      if (response.data.message != "Error: illegal move") {
        const botResponse = await api.post(`/bot-move/${gameID}`, {
          botColor: playerColor === "white" ? "black" : "white"
        }, {
          headers: {
            'Content-Type': 'application/json',
          },
        })
        const fenResponse2 = await api.get(`/fen/${gameID}`);
        setFEN(fenResponse2.data.fen);
        await fetchMoveRecord();
        setResponseMessage(botResponse.data.message)
      }
    } catch (error) {
      setResponseMessage("Error posting move");
    }
  };

  const formatMoveRecord = (moves) => {
    const rows = [];
    for (let i = 0; i < moves.length; i += 2) {
      rows.push({
        moveNumber: Math.floor(i / 2) + 1 + ".",
        whiteMove: moves[i],
        blackMove: moves[i + 1] || "..."
      });
    }
    return rows;
  };

  const handleFlip = () => {
    setIsFlipped(!isFlipped);
  };

  return (
      <div className="App">
        <Modal isOpen={modalIsOpen} onRequestClose={() => setModalIsOpen(false)}>
          <h2>Select Your Color</h2>
          <button onClick={() => createNewGame('white')}>White</button>
          <button onClick={() => createNewGame('black')}>Black</button>
        </Modal>
        <div className="sidebar">
          <button className="new-game-button" onClick={() => setModalIsOpen(true)}>
            New Game
          </button>
        </div>
        <div className="board-container">
          <Board fen={fen} isFlipped={isFlipped} />
        </div>
        <div className="sidebar">
          <button className="flip-button" onClick={handleFlip}>
            Flip
          </button>
          <input className="move-input"
                 type="text"
                 value={move}
                 onChange={handleMoveChange}
                 placeholder="Enter move"
          />
          <button onClick={handleMoveSubmit}>Submit Move</button>
          <div className="response-message-container">
            {responseMessage && <div className="response-message">{responseMessage}</div>}
          </div>
        </div>
        <div className="move-record-container">
          <header className="move-record-header">Moves</header>
          <table className="move-record-table">
            <tbody>
            {formatMoveRecord(moveRecord).map((row, index) => (
                <tr key={index}>
                  <td>{row.moveNumber}</td>
                  <td>{row.whiteMove}</td>
                  <td>{row.blackMove}</td>
                </tr>
            ))}
            </tbody>
          </table>
        </div>
      </div>
  );
}

export default App;
