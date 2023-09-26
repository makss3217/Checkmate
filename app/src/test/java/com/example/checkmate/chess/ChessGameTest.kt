package com.example.checkmate.chess

import com.example.checkmate.chess.figures.*
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.commands.MoveCommand
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class ChessGameTest {

    @Test
    fun chessMoveIsUnavailableIfKingCouldBeCapturedOnNextOpponentMove() {
        val game = ChessGame(ChessBoardBuilder()
            .position(A1, King(WHITE))
            .position(B8, Rock(BLACK))
            .build())

        val availablePositions = game.getAvailableMovesFromPosition(A1)

        assertThat(availablePositions).containsOnly(MoveCommand(A1,A2))
    }


}