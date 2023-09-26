package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class PawnTest {

    @Test
    fun pawnGoesOneFieldAheadOnEmptyField() {
        val game = ChessGame(
            ChessBoardBuilder()
                .position(E4, Pawn(WHITE))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(E4)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            MoveCommand(E4, E5))
    }

    @Test
    fun pawnCanGoesTwoFieldAheadIfPawnStayOnHisStartedField() {
        val game = ChessGame(
            ChessBoardBuilder()
                .position(E7, Pawn(BLACK))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(E7)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            MoveCommand(E7,E6),
            MoveCommand(E7,E5))
    }

    @Test
    fun pawnCanCaptureOppositeFigureOnDiagonal_1() {
        val game = ChessGame(
            ChessBoardBuilder()
                .position(A2, Pawn(WHITE))
                .position(A3, Pawn(BLACK))
                .position(B3, Pawn(BLACK))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(A2)

        assertThat(availablePositions).containsExactlyInAnyOrder(CaptureCommand(A2,B3, Pawn(BLACK)))
    }

    @Test
    fun pawnCanCaptureOppositeFigureOnDiagonal_2() {
        val game = ChessGame(
            ChessBoardBuilder()
                .position(E2, Pawn(WHITE))
                .position(F3, Pawn(BLACK))
                .position(D3, Pawn(BLACK))
                .build())

        val availablePositions = game.getAvailableMovesFromPosition(E2)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            CaptureCommand(E2,F3, Pawn(BLACK)),
            CaptureCommand(E2,D3, Pawn(BLACK)),
            MoveCommand(E2,E3),
            MoveCommand(E2,E4))
    }
}