package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.figures.King
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.CheckmateSolution
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Queen

class April_2023_1 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(G5, King(BLACK))
            .position(H7, Pawn(BLACK))
            .position(E3, King(WHITE))
            .position(D1, Bishop(WHITE))
            .position(G2, Knight(WHITE))
            .position(E6, Queen(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        assertThat(solution).containsOnly(createExpectedSolution())
    }

    private fun createExpectedSolution(): CheckmateSolution {
        return CheckmateSolution(
            MoveCommand(G2, H4), setOf(
                CheckmateSolution(
                    MoveCommand(H7, H6),
                    setOf(CheckmateSolution(MoveCommand(E6, E7), setOf()))
                ),
                CheckmateSolution(
                    MoveCommand(H7, H5),
                    setOf(CheckmateSolution(MoveCommand(H4, F3), setOf()))
                ),
                CheckmateSolution(
                    CaptureCommand(G5, H4, Knight(WHITE)),
                    setOf(CheckmateSolution(MoveCommand(E6, G4), setOf()))
                )
            )
        )
    }
}