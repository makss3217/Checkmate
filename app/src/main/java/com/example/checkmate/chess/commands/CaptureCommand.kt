package com.example.checkmate.chess.commands

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessBoard
import com.example.checkmate.chess.figures.ChessPiece

data class CaptureCommand(
    private val source : BoardPosition,
    private val destination : BoardPosition,
    private val capturedFigure : ChessPiece
) : ChessCommand {
    override fun execute(board: ChessBoard) {
        TODO("Not yet implemented")
    }

    override fun undo(board: ChessBoard) {
        TODO("Not yet implemented")
    }


}