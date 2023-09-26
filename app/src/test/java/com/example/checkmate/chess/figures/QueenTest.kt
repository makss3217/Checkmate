package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class QueenTest {

    @Test
    fun testQueenMoves() {
        val game = ChessGame(
            ChessBoardBuilder()
                .position(E7, Queen(BLACK))
                .position(F8, Rock(WHITE))
                .position(G5, Rock(BLACK))
                .position(F7, Rock(BLACK))
                .position(E6, Rock(WHITE))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(E7)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            MoveCommand(E7,F6),
            MoveCommand(E7,D8),
            MoveCommand(E7,D6),
            MoveCommand(E7,C5),
            MoveCommand(E7,B4),
            MoveCommand(E7,A3),
            CaptureCommand(E7,F8, Rock(WHITE)),
            CaptureCommand(E7,E6, Rock(WHITE)),
            MoveCommand(E7,E8),
            MoveCommand(E7,D7),
            MoveCommand(E7,C7),
            MoveCommand(E7,B7),
            MoveCommand(E7,A7))
    }
}