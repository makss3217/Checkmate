package com.example.checkmate.chess.commands

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessBoard

data class MoveCommand(
    private val source : BoardPosition,
    private val destination : BoardPosition
) : ChessCommand {

    override fun execute(board: ChessBoard) {
        TODO("Not yet implemented")
    }

    override fun undo(board: ChessBoard) {
        TODO("Not yet implemented")
    }
}