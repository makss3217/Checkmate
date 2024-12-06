package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.FigureImage
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.commands.MoveCommand

data class King(override val color: ChessColor) : ChessPiece {

    override fun getAvailableMoves(
        game: ChessGame,
        currentPosition: BoardPosition
    ): Set<ChessCommand> {
        val availableCommands = mutableSetOf<ChessCommand>()
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row - 1, currentPosition.column - 1)
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row - 1, currentPosition.column)
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row - 1, currentPosition.column + 1)
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row, currentPosition.column - 1)
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row , currentPosition.column + 1)
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row + 1, currentPosition.column - 1)
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row + 1, currentPosition.column)
        addCommandIfPositionAvailable(availableCommands, game, currentPosition, currentPosition.row + 1, currentPosition.column + 1)

        return availableCommands
    }

    private fun addCommandIfPositionAvailable(
        availableCommands: MutableSet<ChessCommand>,
        game: ChessGame,
        currentPosition: BoardPosition,
        row: Int,
        column: Int
    ) {
        try {
            val destinationPosition = BoardPosition.from(row, column)
            val destinationFigure = game.getFigureOnPosition(destinationPosition)
            if (destinationFigure == null) {
                availableCommands.add(MoveCommand(currentPosition, destinationPosition))
            } else {
                if (destinationFigure.color != this.color) {
                    availableCommands.add(CaptureCommand(currentPosition, destinationPosition, destinationFigure))
                }
            }
        } catch (ex : NoSuchElementException) {
            // Do nothing
        }
    }

    override fun getPreview(): FigureImage {
        return if(this.color == ChessColor.BLACK) {
            FigureImage.BLACK_KING
        } else {
            FigureImage.WHITE_KING
        }
    }

}
