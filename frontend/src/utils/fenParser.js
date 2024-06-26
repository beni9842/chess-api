const parseFEN = (fen) => {
    const rows = fen.split(' ')[0].split('/');
    const board = [];
  
    rows.forEach(row => {
      const boardRow = [];
      for (let char of row) {
        if (!isNaN(char)) {
          // It's a number: push that many empty squares
          for (let i = 0; i < parseInt(char); i++) {
            boardRow.push(null);
          }
        } else {
          // It's a piece: push it directly
          boardRow.push(char);
        }
      }
      board.push(boardRow);
    });
  
    return board;
};
export default parseFEN;