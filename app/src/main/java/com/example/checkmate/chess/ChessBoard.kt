package com.example.checkmate.chess

import com.example.checkmate.chess.figures.ChessPiece

class ChessBoard(private val board: Array<Array<ChessPiece?>>) {

    fun getFigureOnPosition(checkedPosition: BoardPosition): ChessPiece? {
        return board[checkedPosition.row][checkedPosition.column]
    }

}