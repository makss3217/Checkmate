package com.example.checkmate.chess.commands

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessBoard

data class MoveCommandWrapper(
    private val source : BoardPosition,
    private val destination : BoardPosition,
    private val wrappee : ChessCommand
    ) : ChessCommand {
    override fun execute(board: ChessBoard) {
        val figure = board.getFigureOnPosition(source)
        board.setFigureOnPosition(destination, figure)
        board.setFigureOnPosition(source, null)
        wrappee.execute(board)
    }

    override fun undo(board: ChessBoard) {
        wrappee.undo(board)
        var figure = board.getFigureOnPosition(destination)
        board.setFigureOnPosition(destination, null)
        board.setFigureOnPosition(source, figure)
    }
}