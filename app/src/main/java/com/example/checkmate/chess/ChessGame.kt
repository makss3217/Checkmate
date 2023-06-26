package com.example.checkmate.chess

import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.figures.ChessPiece
import java.util.Stack

class ChessGame(private val board : ChessBoard) {

    private var currentMove = ChessColor.WHITE
    private var commandHistory : Stack<ChessCommand> = Stack()

    fun getAvailableMovesFromPosition(position: BoardPosition) : Set<ChessCommand> {
        return getFigureOnPosition(position)?.getAvailableMoves(this, position) ?: mutableSetOf()
    }

    fun getFigureOnPosition(position: BoardPosition) : ChessPiece? {
        return board.getFigureOnPosition(position);
    }

}