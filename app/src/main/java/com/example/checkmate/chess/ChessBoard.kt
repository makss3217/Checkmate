package com.example.checkmate.chess

import com.example.checkmate.chess.figures.ChessPiece

class ChessBoard(private val board: Array<Array<ChessPiece?>>) {

    fun getFigureOnPosition(checkedPosition: BoardPosition): ChessPiece? {
        return board[checkedPosition.row][checkedPosition.column]
    }

    fun setFigureOnPosition(position: BoardPosition, figure: ChessPiece?) {
        board[position.row][position.column] = figure
    }

    fun getPositionsWithFigureInColor(color: ChessColor) : Set<BoardPosition> {
        return BoardPosition.values()
            .filter { p -> (getFigureOnPosition(p)?.color ?: false) == color }
            .toSet()


    }

}