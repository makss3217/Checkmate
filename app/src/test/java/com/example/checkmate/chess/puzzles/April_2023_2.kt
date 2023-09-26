package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.CheckmateSolution
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Queen
import org.assertj.core.api.Assertions
import org.junit.Test

class April_2023_2 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(G5, King(ChessColor.BLACK))
            .position(H6, Pawn(ChessColor.BLACK))
            .position(H2, King(ChessColor.WHITE))
            .position(F2, Bishop(ChessColor.WHITE))
            .position(G1, Knight(ChessColor.WHITE))
            .position(E4, Queen(ChessColor.WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        Assertions.assertThat(solution).containsOnly(createExpectedSolution())
    }

    private fun createExpectedSolution(): CheckmateSolution {
        return CheckmateSolution(
            MoveCommand(E4, E6), setOf(
                CheckmateSolution(
                    MoveCommand(H6, H5),
                    setOf(CheckmateSolution(MoveCommand(G1, H3), setOf()))
                ),
                CheckmateSolution(
                    MoveCommand(G5, F4),
                    setOf(CheckmateSolution(MoveCommand(F2, E3), setOf()))
                ),
                CheckmateSolution(
                    MoveCommand(G5, H5),
                    setOf(CheckmateSolution(MoveCommand(E6, F5), setOf()))
                )
            )
        )
    }
}