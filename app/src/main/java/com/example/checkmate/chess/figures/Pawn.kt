package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.commands.MoveCommand

data class Pawn(override val color: ChessColor) : ChessPiece {

    override fun getAvailableMoves(
        game: ChessGame,
        currentPosition: BoardPosition
    ): Set<ChessCommand> {
        val availablePositions = mutableSetOf<ChessCommand>()

        if(nextFieldIsEmpty(game, currentPosition)) {
            if(nextFieldIsTransformRow(currentPosition)) {
                availablePositions.addAll(createTransformCommands(currentPosition))
            }
            else {
                availablePositions.add(createNextFieldMoveCommand(currentPosition))
                if(pawnStayOnStartedPositions(currentPosition) && secondFieldIsEmpty(game, currentPosition)) {
                    availablePositions.add(createSecondAheadMoveCommand(currentPosition))
                }
            }
        }

        val leftCornerFromPawnPosition = getLeftCornerPosition(currentPosition)
        if(isOppositeFigure(leftCornerFromPawnPosition, game)) {
            if(nextFieldIsTransformRow(currentPosition)) {
                //create Capture and transform command
            } else {
                availablePositions.add(
                    createCaptureCommands(currentPosition, leftCornerFromPawnPosition!!, game))
            }
        }

        val rightCornerFromPawnPosition = getRightCornerPosition(currentPosition)
        if(isOppositeFigure(rightCornerFromPawnPosition, game)) {
            if(nextFieldIsTransformRow(currentPosition)) {
                //create Capture and transform command
            } else {
                availablePositions.add(
                    createCaptureCommands(currentPosition, rightCornerFromPawnPosition!!, game))
            }
        }

        return availablePositions
    }

    private fun createCaptureCommands(
        currentPosition: BoardPosition,
        destinationPosition: BoardPosition,
        game: ChessGame
    ): ChessCommand {
        return CaptureCommand(currentPosition, destinationPosition, game.getFigureOnPosition(destinationPosition)!!)
    }

    private fun isOppositeFigure(position: BoardPosition?, game: ChessGame): Boolean {
        return if(position != null) {
            val figure= game.getFigureOnPosition(position)
            if(figure != null) {
                figure.color != this.color
            } else {
                false
            }
        } else {
            false
        }
    }

    private fun getLeftCornerPosition(currentPosition: BoardPosition): BoardPosition? {
        return try {
            if (this.color == ChessColor.WHITE) {
                BoardPosition.from(currentPosition.row + 1, currentPosition.column - 1)
            } else {
                BoardPosition.from(currentPosition.row - 1, currentPosition.column - 1)
            }
        } catch (e: NoSuchElementException) {
            null
        }
    }

    private fun getRightCornerPosition(currentPosition: BoardPosition): BoardPosition? {
        return try {
            if(this.color == ChessColor.WHITE) {
                BoardPosition.from(currentPosition.row + 1, currentPosition.column + 1)
            } else {
                BoardPosition.from(currentPosition.row - 1, currentPosition.column + 1)
            }
        } catch (e: NoSuchElementException) {
            null
        }

    }

    private fun createSecondAheadMoveCommand(currentPosition: BoardPosition): ChessCommand {
        return if(this.color == ChessColor.WHITE) {
            MoveCommand(currentPosition , BoardPosition.from(currentPosition.row + 2, currentPosition.column))
        } else {
            MoveCommand(currentPosition , BoardPosition.from(currentPosition.row - 2, currentPosition.column))
        }
    }

    private fun secondFieldIsEmpty(game: ChessGame, currentPosition: BoardPosition): Boolean {
        val checkedPosition = if (this.color == ChessColor.WHITE) {
            BoardPosition.from(currentPosition.row + 2, currentPosition.column)
        } else {
            BoardPosition.from(currentPosition.row - 2, currentPosition.column)
        }
        return game.getFigureOnPosition(checkedPosition) == null
    }

    private fun createNextFieldMoveCommand(currentPosition: BoardPosition): ChessCommand {
        return if(this.color == ChessColor.WHITE) {
            MoveCommand(currentPosition , BoardPosition.from(currentPosition.row + 1, currentPosition.column))
        } else {
            MoveCommand(currentPosition , BoardPosition.from(currentPosition.row - 1, currentPosition.column))
        }
    }

    private fun pawnStayOnStartedPositions(currentPosition: BoardPosition): Boolean {
        return if(this.color == ChessColor.WHITE) {
            currentPosition.row == 1
        } else {
            currentPosition.row == 6
        }
    }


    private fun nextFieldIsTransformRow(currentPosition: BoardPosition): Boolean {
        return if(this.color == ChessColor.WHITE) {
            currentPosition.row == 6
        } else {
            currentPosition.row == 1
        }
    }

    private fun nextFieldIsEmpty(game: ChessGame, currentPosition: BoardPosition): Boolean {
        val aheadPosition = if (this.color == ChessColor.WHITE) {
            BoardPosition.from(currentPosition.row + 1, currentPosition.column)
        } else {
            BoardPosition.from(currentPosition.row - 1, currentPosition.column)
        }
        return game.getFigureOnPosition(aheadPosition) == null
    }

    private fun createTransformCommands(currentPosition: BoardPosition): Collection<ChessCommand> {
        // TO DO
        return setOf()
    }

}