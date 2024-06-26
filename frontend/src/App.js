import Board from "./Board";
import "./App.css"
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const initialFEN = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
const App = () => {
  const [isFlipped, setIsFlipped] = useState(false);
  const [fen, setFEN] = useState(initialFEN);
  const [gameID, setGameID] = useState(-1);
  const [move, setMove] = useState('');
  const [responseMessage, setResponseMessage] = useState('');

  useEffect(() => {
    const createNewGame = async () => {
      try {
        const response = await axios.post('http://localhost:8080/chess/new');
        const id = response.data.split('=')[1];
        setGameID(id);
        setResponseMessage(response.data);
      } catch (error) {
        console.error("Error creating new game:", error);
        setResponseMessage("Error creating new game:", error);
      }
    };

    createNewGame();
  }, []);

  const handleMoveChange = (event) => {
    setMove(event.target.value);
  };

  const handleMoveSubmit = async () => {
    try {
      console.log('Submitting move:', move); // Log move
      const response = await axios.post(`http://localhost:8080/chess/move/${gameID}?notation=san`, move, {
        headers: {
          'Content-Type': 'text/plain',
        }
      });
      console.log('Move Response:', response.data); // Log response
      setResponseMessage(response.data);
    } catch (error) {
      console.error("Error posting move:", error);
      setResponseMessage("Error posting move:", error);
    }

    try {
      const fenResponse = await axios.get(`http://localhost:8080/chess/fen/${gameID}`);
      console.log('FEN Response:', fenResponse.data); // Log response
      setFEN(fenResponse.data);
    } catch (error) {
      console.error("Error getting board state:", error);
      setResponseMessage("Error getting board state:", error);
    }
  };

  const handleFlip = () => {
    setIsFlipped(!isFlipped);
  };

  return (
    <div className="App">
      <div className="board-container">
        <Board fen={fen} isFlipped={isFlipped} />
      </div>
      <div className="sidebar">
        <button className="flip-button" onClick={handleFlip}>
          flip
        </button>
        <input 
          type="text" 
          value={move} 
          onChange={handleMoveChange} 
          placeholder="Enter move in SAN" 
        />
        <button onClick={handleMoveSubmit}>Submit Move</button>
        {responseMessage && <div className="response-message">{responseMessage}</div>}
      </div>
    </div>
  );
}

export default App;
