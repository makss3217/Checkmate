package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.FigureImage
import com.example.checkmate.chess.commands.ChessCommand

data class Queen(override val color: ChessColor) : ChessPiece {

    override fun getAvailableMoves(
        game: ChessGame,
        currentPosition: BoardPosition
    ): Set<ChessCommand> {
        val availableCommands = mutableSetOf<ChessCommand>()
        availableCommands.addAll(Rock(color).getAvailableMoves(game, currentPosition))
        availableCommands.addAll(Bishop(color).getAvailableMoves(game, currentPosition))
        return availableCommands
    }

    override fun getPreview(): FigureImage {
        return if(this.color == ChessColor.BLACK) {
            FigureImage.BLACK_QUEEN
        } else {
            FigureImage.WHITE_QUEEN
        }
    }
}