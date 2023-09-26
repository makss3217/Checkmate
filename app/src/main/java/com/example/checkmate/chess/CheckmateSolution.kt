package com.example.checkmate.chess

import com.example.checkmate.chess.commands.ChessCommand

data class CheckmateSolution(
    val move : ChessCommand,
    val nextMoves : Set<CheckmateSolution> = setOf()
) {}