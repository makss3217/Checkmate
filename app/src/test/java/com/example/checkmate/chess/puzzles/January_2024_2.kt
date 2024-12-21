package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.CheckmateSolution
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.BLACK
import com.example.checkmate.chess.ChessColor.WHITE
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.commands.MoveCommandWrapper
import com.example.checkmate.chess.commands.TransformCommand
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class January_2024_2 {

    @Test
    fun resolve() {
        val board = ChessBoardBuilder()
            .position(H1, King(BLACK))
            .position(F3, Pawn(BLACK))
            .position(H2, Pawn(BLACK))
            .position(C7, Pawn(BLACK))
            .position(F1, King(WHITE))
            .position(C6, Pawn(WHITE))
            .position(A7, Pawn(WHITE))
            .position(B7, Bishop(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 3)

        assertThat(solution.last().move).isEqualTo(MoveCommandWrapper(A7, A8, TransformCommand(A8, Pawn(WHITE), Knight(WHITE))))
    }
}