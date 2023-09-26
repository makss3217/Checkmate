package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.commands.MoveCommand

data class Bishop(override val color: ChessColor) : ChessPiece {

    override fun getAvailableMoves(
        game: ChessGame,
        currentPosition: BoardPosition
    ): Set<ChessCommand> {
        val availablePositions = mutableSetOf<ChessCommand>()
        var row : Int
        var column : Int

        row = currentPosition.row - 1
        column = currentPosition.column - 1
        while(row >= 0 && column >= 0) {
            val checkedPosition = BoardPosition.from(row, column)
            val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
            if(figureOnPosition == null) {
                availablePositions.add(MoveCommand(currentPosition, checkedPosition))
            } else {
                if(figureOnPosition.color != this.color) {
                    availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
                }
                break
            }
            row--
            column--
        }

        row = currentPosition.row + 1
        column = currentPosition.column - 1
        while(row <= 7 && column >= 0) {
            val checkedPosition = BoardPosition.from(row, column)
            val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
            if(figureOnPosition == null) {
                availablePositions.add(MoveCommand(currentPosition, checkedPosition))
            } else {
                if(figureOnPosition.color != this.color) {
                    availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
                }
                break
            }
            row++
            column--
        }

        row = currentPosition.row - 1
        column = currentPosition.column + 1
        while(row >= 0 && column <= 7) {
            val checkedPosition = BoardPosition.from(row, column)
            val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
            if(figureOnPosition == null) {
                availablePositions.add(MoveCommand(currentPosition, checkedPosition))
            } else {
                if(figureOnPosition.color != this.color) {
                    availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
                }
                break
            }
            row--
            column++
        }

        row = currentPosition.row + 1
        column = currentPosition.column + 1
        while(row <= 7 && column <= 7) {
            val checkedPosition = BoardPosition.from(row, column)
            val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
            if(figureOnPosition == null) {
                availablePositions.add(MoveCommand(currentPosition, checkedPosition))
            } else {
                if(figureOnPosition.color != this.color) {
                    availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
                }
                break
            }
            row++
            column++
        }


        return availablePositions
    }
}