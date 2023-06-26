package com.example.checkmate.chess.commands

import com.example.checkmate.chess.ChessBoard

interface ChessCommand {

    fun execute(board : ChessBoard) : Unit

    fun undo(board: ChessBoard) : Unit
}
