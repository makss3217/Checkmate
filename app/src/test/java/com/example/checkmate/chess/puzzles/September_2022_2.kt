package com.example.checkmate.chess.puzzles

import androidx.compose.ui.graphics.BlurEffect
import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Rock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.CheckmateSolution
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Queen

class September_2022_2 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(F6, King(WHITE))
            .position(G4, Pawn(WHITE))
            .position(F2, Knight(WHITE))
            .position(E3, Rock(WHITE))
            .position(C2, Knight(WHITE))
            .position(B2, Queen(WHITE))
            .position(F4, King(BLACK))
            .position(G5, Pawn(BLACK))
            .position(E4, Pawn(BLACK))
            .position(D5, Pawn(BLACK))
            .position(D4, Rock(BLACK))
            .position(B3, Knight(BLACK))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        assertThat(solution).containsOnly(createExpectedSolution())
    }

    private fun createExpectedSolution(): CheckmateSolution {
        return CheckmateSolution(MoveCommand(F6, E7), setOf(
            CheckmateSolution(MoveCommand(D4, D3), setOf(CheckmateSolution(MoveCommand(B2, F6), setOf()))),
            CheckmateSolution(MoveCommand(D4, D2), setOf(CheckmateSolution(MoveCommand(B2, F6), setOf()))),
            CheckmateSolution(MoveCommand(D4, D1), setOf(CheckmateSolution(MoveCommand(B2, F6), setOf()))),
            CheckmateSolution(move=MoveCommand(source=D4, destination=B4), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=F6), setOf()))),
            CheckmateSolution(move=MoveCommand(source=D4, destination=A4), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=F6), setOf()))),
            CheckmateSolution(move=MoveCommand(source=D4, destination=C4), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=F6), setOf()))),
            CheckmateSolution(move=MoveCommand(source=F4, destination=E5), setOf(CheckmateSolution(move=MoveCommand(source=F2, destination=D3), setOf()))),
            CheckmateSolution(move=MoveCommand(source=B3, destination=C5), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=B8), setOf()))),
            CheckmateSolution(move=MoveCommand(source=B3, destination=A5), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=B8), setOf()))),
            CheckmateSolution(move=MoveCommand(source=B3, destination=C1), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=B8), setOf()))),
            CheckmateSolution(move=MoveCommand(source=B3, destination=A1), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=B8), setOf()))),
            CheckmateSolution(move=MoveCommand(source=B3, destination=D2), setOf(CheckmateSolution(move=MoveCommand(source=B2, destination=B8), setOf())))))
    }
}