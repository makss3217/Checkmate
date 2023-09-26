package com.example.checkmate.chess

import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.figures.ChessPiece
import com.example.checkmate.chess.figures.King
import java.util.Stack

class ChessGame(private var board : ChessBoard) {

    private var currentMove = ChessColor.WHITE
    private var commandHistory : Stack<ChessCommand> = Stack()

    fun isCheckMate(): Boolean {
        return getAllAvailableMoves().isEmpty() && kingIsUnderAttack()
    }

    fun isPat() : Boolean {
        return getAllAvailableMoves().isEmpty() && !opponentKingIsUnderAttack()
    }

    fun getAllAvailableMoves() : Set<ChessCommand> {
        return board.getPositionsWithFigureInColor(currentMove)
            .flatMap { position -> getAvailableMovesFromPosition(position) }
            .toSet()
    }

    fun getAvailableMovesFromPosition(position: BoardPosition) : Set<ChessCommand> {
        val commandsBeforeCheckFilter = getCommands(position)
        val availableCommands = mutableSetOf<ChessCommand>()
        for (command in commandsBeforeCheckFilter) {
            if(afterCommandKingIsNotUnderAttack(command)) {
                availableCommands.add(command)
            }
        }
        return availableCommands
    }

    private fun getCommands(position: BoardPosition) =
        getFigureOnPosition(position)?.getAvailableMoves(this, position) ?: mutableSetOf()

    private fun afterCommandKingIsUnderAttack(command: ChessCommand): Boolean {
        executeCommand(command)
        val kingIsUnderAttack = opponentKingIsUnderAttack()
        undoCommand()
        return kingIsUnderAttack
    }

    private fun afterCommandKingIsNotUnderAttack(command: ChessCommand): Boolean {
      return !afterCommandKingIsUnderAttack(command)
    }

    private fun kingIsUnderAttack() : Boolean {
        changeColor()
        val kingIsUnderAttack = opponentKingIsUnderAttack()
        changeColor()
        return kingIsUnderAttack
    }

    private fun opponentKingIsUnderAttack() : Boolean {
      return getAllMoves().filterIsInstance<CaptureCommand>()
           .any() { c -> c.getCapturedFigure() is King }
    }

    private fun getAllMoves(): Set<ChessCommand> {
        return BoardPosition.values()
            .filter { position -> figureOnPositionHasColor(position, currentMove) }
            .flatMap { position -> getCommands(position) }
            .toSet()
    }

    private fun figureOnPositionHasColor(position: BoardPosition, currentMove: ChessColor) : Boolean {
        return getFigureOnPosition(position)?.color == currentMove
    }


    fun executeCommand(command: ChessCommand) {
        command.execute(this.board)
        commandHistory.push(command)
        changeColor()
    }

    fun undoCommand() {
        val command = commandHistory.pop()
        command.undo(board)
        changeColor()
    }

    private fun changeColor() {
        currentMove = if(currentMove == ChessColor.WHITE) {
            ChessColor.BLACK
        } else {
            ChessColor.WHITE
        }
    }

   fun getFigureOnPosition(position: BoardPosition) : ChessPiece? {
        return board.getFigureOnPosition(position)
    }

}