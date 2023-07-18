package com.example.checkmate.chess.commands

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessBoard

data class MoveCommand(
    private val source : BoardPosition,
    private val destination : BoardPosition
) : ChessCommand {

    override fun execute(board: ChessBoard) {
        var figure = board.getFigureOnPosition(source)
        board.setFigureOnPosition(destination, figure)
        board.setFigureOnPosition(source, null)
    }

    override fun undo(board: ChessBoard) {
        var figure = board.getFigureOnPosition(destination)
        board.setFigureOnPosition(destination, null)
        board.setFigureOnPosition(source, figure)
    }
}