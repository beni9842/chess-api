import "./Board.css"
import Piece from "./Piece";
import parseFEN from "./utils/fenParser";

const Board = ({ fen }) => {
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
            const row = [];
            for (let j = 0; j < 8; j++) {
                row.push(renderSquare(i, j, pieces[i][j]));
            }
            board.push(row);
        }
        return board;
    }
    return (
        <div className="board">{renderBoard()}</div>
    );
}

export default Board;