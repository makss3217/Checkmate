package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat


class KingTest {

    @Test
    fun kingCanGoInAnyDirectionOnOneEmptyField() {
        val game = ChessGame(
            ChessBoardBuilder()
            .position(E4, King(WHITE))
            .build())

        val availablePositions = game.getAvailableMovesFromPosition(E4)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            MoveCommand(E4,D3),
            MoveCommand(E4,D4),
            MoveCommand(E4,D5),
            MoveCommand(E4,E3),
            MoveCommand(E4,E5),
            MoveCommand(E4,F3),
            MoveCommand(E4,F4),
            MoveCommand(E4,F5))
    }

    @Test
    fun kingCanCaptureOpponentFigure() {
        val game = ChessGame(
            ChessBoardBuilder()
                .position(A1, King(WHITE))
                .position(A2, Rock(BLACK))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(A1)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            MoveCommand(A1, B1),
            CaptureCommand(A1, A2, Rock(BLACK)));
    }

    @Test
    fun kingIsBlockedByFigureWithSameColor() {
        val game = ChessGame(
            ChessBoardBuilder()
                .position(A1, King(WHITE))
                .position(A2, Rock(WHITE))
                .position(B1, Rock(WHITE))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(A1)

        assertThat(availablePositions).containsExactlyInAnyOrder(MoveCommand(A1, B2))
    }
}