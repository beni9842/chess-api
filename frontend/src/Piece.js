import "./Piece.css";

const pieceMap = {
    'p': '/img/black_pawn.png',
    'r': '/img/black_rook.png',
    'n': '/img/black_knight.png',
    'b': '/img/black_bishop.png',
    'q': '/img/black_queen.png',
    'k': '/img/black_king.png',
    'P': '/img/white_pawn.png',
    'R': '/img/white_rook.png',
    'N': '/img/white_knight.png',
    'B': '/img/white_bishop.png',
    'Q': '/img/white_queen.png',
    'K': '/img/white_king.png',
}

const Piece = ({pieceKey}) => {
    if (!pieceKey) return null;
    return (
        <img src={pieceMap[pieceKey]} alt={pieceKey} className="piece" />
    )
}

export default Piece;