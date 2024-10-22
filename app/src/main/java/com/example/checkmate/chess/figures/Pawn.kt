package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.commands.MoveCommandWrapper
import com.example.checkmate.chess.commands.TransformCommand

data class Pawn(override val color: ChessColor) : ChessPiece {

    override fun getAvailableMoves(
        game: ChessGame,
        currentPosition: BoardPosition
    ): Set<ChessCommand> {
        val availablePositions = mutableSetOf<ChessCommand>()

        if (nextFieldIsEmpty(game, currentPosition)) {
            if (nextFieldIsTransformRow(currentPosition)) {
                availablePositions.addAll(createTransformCommands(currentPosition))
            } else {
                availablePositions.add(createNextFieldMoveCommand(currentPosition))
                if (pawnStayOnStartedPositions(currentPosition) && secondFieldIsEmpty(
                        game,
                        currentPosition
                    )
                ) {
                    availablePositions.add(createSecondAheadMoveCommand(currentPosition))
                }
            }
        }

        val leftCornerFromPawnPosition = getLeftCornerPosition(currentPosition)
        if (isOppositeFigure(leftCornerFromPawnPosition, game)) {
            if (nextFieldIsTransformRow(currentPosition)) {
                availablePositions.addAll(
                    createCaptureAndTransformCommands(
                        currentPosition,
                        leftCornerFromPawnPosition!!,
                        game))
            } else {
                availablePositions.add(
                    createCaptureCommands(currentPosition, leftCornerFromPawnPosition!!, game)
                )
            }
        }

        val rightCornerFromPawnPosition = getRightCornerPosition(currentPosition)
        if (isOppositeFigure(rightCornerFromPawnPosition, game)) {
            if (nextFieldIsTransformRow(currentPosition)) {
                availablePositions.addAll(
                    createCaptureAndTransformCommands(
                        currentPosition,
                        rightCornerFromPawnPosition!!,
                        game
                    ))
            } else {
                availablePositions.add(
                    createCaptureCommands(currentPosition, rightCornerFromPawnPosition!!, game)
                )
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
        val aheadPosition = getPawnAheadPosition(currentPosition)
        return game.getFigureOnPosition(aheadPosition) == null
    }

    private fun getPawnAheadPosition(currentPosition: BoardPosition) : BoardPosition {
        return if (this.color == ChessColor.WHITE) {
            BoardPosition.from(currentPosition.row + 1, currentPosition.column)
        } else {
            BoardPosition.from(currentPosition.row - 1, currentPosition.column)
        }
    }

    private fun createTransformCommands(currentPosition: BoardPosition): Collection<ChessCommand> {
        val aheadPosition = getPawnAheadPosition(currentPosition)

        return setOf(
            MoveCommandWrapper(
                currentPosition, aheadPosition,
                TransformCommand(aheadPosition, this, Queen(this.color))
            ),
            MoveCommandWrapper(
                currentPosition, aheadPosition,
                TransformCommand(aheadPosition, this, Knight(this.color))
            ),
            MoveCommandWrapper(
                currentPosition, aheadPosition,
                TransformCommand(aheadPosition, this, Bishop(this.color))
            ),
            MoveCommandWrapper(
                currentPosition, aheadPosition,
                TransformCommand(aheadPosition, this, Rock(this.color))
            )
        )
    }

    private fun createCaptureAndTransformCommands(current: BoardPosition, dest : BoardPosition, game: ChessGame): Collection<ChessCommand> {
        return setOf(
            CaptureCommand(current, dest, game.getFigureOnPosition(dest)!!, TransformCommand(current, this, Queen(this.color))),
            CaptureCommand(current, dest, game.getFigureOnPosition(dest)!!, TransformCommand(current, this, Rock(this.color))),
            CaptureCommand(current, dest, game.getFigureOnPosition(dest)!!, TransformCommand(current, this, Bishop(this.color))),
            CaptureCommand(current, dest, game.getFigureOnPosition(dest)!!, TransformCommand(current, this, Knight(this.color))))
    }

}