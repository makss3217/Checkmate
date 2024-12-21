package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.BoardPosition.*

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
import com.example.checkmate.chess.figures.Queen
import com.example.checkmate.chess.figures.Rock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class January_2024_1 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(G8, King(BLACK))
            .position(H8, Rock(BLACK))
            .position(E5, Pawn(BLACK))
            .position(D6, Pawn(BLACK))
            .position(D8, King(WHITE))
            .position(F7, Rock(WHITE))
            .position(G7, Pawn(WHITE))
            .position(D5, Pawn(WHITE))
            .position(G5, Knight(WHITE))
            .position(G3, Queen(WHITE))
            .position(B2, Bishop(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        assertThat(solution.last().move).isEqualTo(MoveCommand(G5, H7))
    }
}