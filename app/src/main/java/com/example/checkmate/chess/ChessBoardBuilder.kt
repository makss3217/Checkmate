package com.example.checkmate.chess

import com.example.checkmate.chess.figures.ChessPiece

class ChessBoardBuilder {

    var board: Array<Array<ChessPiece?>> = Array(8) {
        Array(8) {
            null }}

    fun position(position: BoardPosition, figure: ChessPiece) : ChessBoardBuilder {
        board[position.row][position.column] = figure;
        return this;
    }

    fun build() : ChessBoard {
        return ChessBoard(board);
    }


}