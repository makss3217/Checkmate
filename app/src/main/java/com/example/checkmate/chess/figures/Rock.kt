package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand

data class Rock(override val color: ChessColor) : ChessPiece {

    override fun getAvailableMoves(
        game: ChessGame,
        currentPosition: BoardPosition
    ): Set<ChessCommand> {
       val availablePositions = mutableSetOf<ChessCommand>()

       for(i in currentPosition.row - 1 downTo 0)  {
           val checkedPosition = BoardPosition.from(i, currentPosition.column)
           val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
           if(figureOnPosition == null) {
               availablePositions.add(MoveCommand(currentPosition, checkedPosition))
           } else {
               if(figureOnPosition.color != this.color) {
                   availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
               }
               break
           }
       }

        for(i in currentPosition.row + 1 .. 7)  {
            val checkedPosition = BoardPosition.from(i, currentPosition.column)
            val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
            if(figureOnPosition == null) {
                availablePositions.add(MoveCommand(currentPosition,checkedPosition))
            } else {
                if(figureOnPosition.color != this.color) {
                    availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
                }
                break
            }
        }

        for(i in currentPosition.column -1 downTo 0)  {
            val checkedPosition = BoardPosition.from(currentPosition.row, i)
            val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
            if(figureOnPosition == null) {
                availablePositions.add(MoveCommand(currentPosition,checkedPosition))
            } else {
                if(figureOnPosition.color != this.color) {
                    availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
                }
                break
            }
        }

        for(i in currentPosition.column + 1 .. 7)  {
            val checkedPosition = BoardPosition.from(currentPosition.row, i)
            val figureOnPosition : ChessPiece? =  game.getFigureOnPosition(checkedPosition)
            if(figureOnPosition == null) {
                availablePositions.add(MoveCommand(currentPosition,checkedPosition))
            } else {
                if(figureOnPosition.color != this.color) {
                    availablePositions.add(CaptureCommand(currentPosition,checkedPosition, figureOnPosition))
                }
                break
            }
        }

        return availablePositions


    }

}