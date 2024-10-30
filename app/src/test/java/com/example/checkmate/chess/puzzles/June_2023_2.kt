package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.BoardPosition.A4
import com.example.checkmate.chess.BoardPosition.A7
import com.example.checkmate.chess.BoardPosition.B4
import com.example.checkmate.chess.BoardPosition.B7
import com.example.checkmate.chess.BoardPosition.E5
import com.example.checkmate.chess.BoardPosition.F6
import com.example.checkmate.chess.BoardPosition.F7
import com.example.checkmate.chess.BoardPosition.H2
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.BLACK
import com.example.checkmate.chess.ChessColor.WHITE
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Rock
import org.assertj.core.api.Assertions.assertThat
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.figures.Queen
import org.junit.Test

class June_2023_2 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(D5, King(BLACK))
            .position(B6, Pawn(BLACK))
            .position(B7, Knight(BLACK))
            .position(B8, Knight(WHITE))
            .position(D4, Pawn(WHITE))
            .position(D3, Queen(WHITE))
            .position(E7, King(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        assertThat(solution.last().move).isEqualTo(MoveCommand(B8, A6))
    }
}