package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Queen
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Rock
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
class June_2023_1 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(A7, King(BLACK))
            .position(E5, Knight(BLACK))
            .position(A4, King(WHITE))
            .position(B4, Knight(WHITE))
            .position(B7, Pawn(WHITE))
            .position(F6, Rock(WHITE))
            .position(H2, Bishop(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        assertThat(solution.last().move).isEqualTo(MoveCommand(F6, F7))
    }
}