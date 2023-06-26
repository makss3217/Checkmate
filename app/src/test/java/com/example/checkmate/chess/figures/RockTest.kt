package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat


class RockTest {

    @Test
    fun rockCanMoveUpAndDownOnAnyEmptyPosition() {
        val game = ChessGame(ChessBoardBuilder()
            .position(E4, Rock(ChessColor.WHITE))
            .build())

        val availablePositions = game.getAvailableMovesFromPosition(E4)

        assertThat(availablePositions).containsExactlyInAnyOrder(
            MoveCommand(E4, E1), MoveCommand(E4, E2), MoveCommand(E4, E3), MoveCommand(E4, E5), MoveCommand(E4, E6), MoveCommand(E4, E7), MoveCommand(E4, E8)
            ,MoveCommand(E4, A4), MoveCommand(E4, B4), MoveCommand(E4, C4), MoveCommand(E4, D4), MoveCommand(E4, F4), MoveCommand(E4, G4), MoveCommand(E4, H4))
    }

    @Test
    fun rockIsBlockingByFiguresInSameColor() {
        val game = ChessGame(ChessBoardBuilder()
            .position(E4, Rock(ChessColor.WHITE))
            .position(F4, Rock(ChessColor.WHITE))
            .build())

        val availablePosition = game.getAvailableMovesFromPosition(E4)

        assertThat(availablePosition).doesNotContain(MoveCommand(E4, F4), MoveCommand(E4, G4), MoveCommand(E4, H4))
    }

    @Test
    fun rockCanCaptureFigureInOppositeColor() {
        val game = ChessGame(ChessBoardBuilder()
            .position(E4, Rock(ChessColor.WHITE))
            .position(F4, Rock(ChessColor.BLACK))
            .build())

        val availablePosition = game.getAvailableMovesFromPosition(E4)

        assertThat(availablePosition)
            .contains(CaptureCommand(E4, F4, Rock(ChessColor.BLACK)))
            .doesNotContain(MoveCommand(E4, G4), MoveCommand(E4, H4))
    }
}

