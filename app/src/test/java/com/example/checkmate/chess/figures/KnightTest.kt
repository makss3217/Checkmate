package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class KnightTest {

    @Test
    fun testKnightMove() {

        val game = ChessGame(
            ChessBoardBuilder()
                .position(E7, Knight(BLACK))
                .position(G6, Rock(WHITE))
                .position(C6, Rock(BLACK))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(E7)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            MoveCommand(E7,F5),
            MoveCommand(E7,D5),
            MoveCommand(E7,C8),
            MoveCommand(E7,G8),
            CaptureCommand(E7,G6, Rock(WHITE)))
    }
}