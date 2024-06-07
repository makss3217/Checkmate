package com.example.checkmate.chess.commands

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessBoard
import com.example.checkmate.chess.figures.ChessPiece

data class TransformCommand(
    private val position : BoardPosition,
    private val transformedFigure : ChessPiece,
    private val figureToTransform : ChessPiece
) : ChessCommand {

    override fun execute(board: ChessBoard) {
        board.setFigureOnPosition(position, figureToTransform)
    }

    override fun undo(board: ChessBoard) {
        board.setFigureOnPosition(position, transformedFigure);
    }
}