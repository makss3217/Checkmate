package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Rock
import org.assertj.core.api.Assertions
import org.junit.Test
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessColor.*


class November_2023_1 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(F5, King(BLACK))
            .position(G3, Bishop(BLACK))
            .position(F6, Pawn(BLACK))
            .position(F7, Pawn(BLACK))

            .position(A1, King(WHITE))
            .position(B2, Rock(WHITE))
            .position(C4, Pawn(WHITE))
            .position(C5, Pawn(WHITE))
            .position(F3, Pawn(WHITE))
            .position(H3, Bishop(WHITE))
            .position(G4, Rock(WHITE))

            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        Assertions.assertThat(solution.last().move)
            .isEqualTo(MoveCommand(B2, G2))
    }
}