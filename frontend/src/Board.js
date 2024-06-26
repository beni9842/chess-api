import "./Board.css"
import Piece from "./Piece";
import parseFEN from "./utils/fenParser";

const Board = ({ fen, isFlipped}) => {
    const pieces = parseFEN(fen);
    const renderSquare = (i, j, pieceKey) => {
        const isBlack = (i+j) % 2 === 1;
        const className = isBlack ? 'square black' : 'square white'
        return (
            <div key={`${i}-${j}`} className={className}>
                <Piece pieceKey={pieceKey} />
            </div>
        )
    }
    const renderBoard = () => {
        const board = [];
        for (let i = 0; i < 8; i++) {
            for (let j = 0; j < 8; j++) {
                const row = isFlipped ? 7 - i : i;
                const col = isFlipped ? 7 - j : j;
                board.push(renderSquare(i, j, pieces[row][col]));
            }
        }
        return board;
    }
    return (
        <div className="board">{renderBoard()}</div>
    );
}

export default Board;