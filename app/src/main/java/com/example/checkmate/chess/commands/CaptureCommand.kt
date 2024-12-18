package com.example.checkmate.chess.commands

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessBoard
import com.example.checkmate.chess.figures.ChessPiece

data class CaptureCommand(
    private val source : BoardPosition,
    private val destination : BoardPosition,
    private val capturedFigure : ChessPiece,
    private val wrappedCommand : ChessCommand? = null
) : ChessCommand {
    override fun execute(board: ChessBoard) {
        var figure = board.getFigureOnPosition(source)
        board.setFigureOnPosition(destination, figure)
        board.setFigureOnPosition(source, null)
        wrappedCommand?.execute(board)
    }

    override fun undo(board: ChessBoard) {
        wrappedCommand?.undo(board)
        var figure = board.getFigureOnPosition(destination)
        board.setFigureOnPosition(destination, capturedFigure)
        board.setFigureOnPosition(source, figure)
    }

    fun getCapturedFigure() : ChessPiece {
        return capturedFigure
    }


}