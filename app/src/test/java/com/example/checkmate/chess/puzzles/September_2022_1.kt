package com.example.checkmate.chess.puzzles

import com.example.checkmate.chess.figures.King
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.CheckmateResolver
import com.example.checkmate.chess.CheckmateSolution
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.commands.CaptureCommand
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Queen
import com.example.checkmate.chess.figures.Rock

class September_2022_1 {

    @Test
    fun resolve() {
        var board = ChessBoardBuilder()
            .position(A4, King(BLACK))
            .position(A7, Bishop(BLACK))
            .position(E3, Pawn(BLACK))
            .position(A2, King(WHITE))
            .position(A6, Bishop(WHITE))
            .position(A8, Rock(WHITE))
            .position(D3, Knight(WHITE))
            .position(B4, Pawn(WHITE))
            .position(E2, Pawn(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 3)

        assertThat(solution.last().move).isEqualTo(MoveCommand(A8,E8))
    }

    private fun createExpectedSolution(): CheckmateSolution {
        return CheckmateSolution(
            MoveCommand(A8, E8), setOf(
                CheckmateSolution(
                    MoveCommand(A7, B6), setOf(
                        CheckmateSolution(
                            MoveCommand(E8, B8), setOf(
                                CheckmateSolution(
                                    MoveCommand(B6, A5), setOf(
                                        CheckmateSolution(MoveCommand(A6, B5), setOf()),
                                        CheckmateSolution(MoveCommand(D3, C5), setOf()),
                                        CheckmateSolution(MoveCommand(D3, B2), setOf())
                                    )
                                ),
                                CheckmateSolution(
                                    MoveCommand(B6, A7), setOf(
                                        CheckmateSolution(MoveCommand(A6, B5), setOf()),
                                        CheckmateSolution(MoveCommand(D3, B2), setOf())
                                    )
                                ),
                                CheckmateSolution(
                                    MoveCommand(B6, C5), setOf(
                                        CheckmateSolution(MoveCommand(A6, B5), setOf()),
                                        CheckmateSolution(
                                            CaptureCommand(D3, C5, Bishop(BLACK)),
                                            setOf()
                                        ),
                                        CheckmateSolution(MoveCommand(D3, B2), setOf())
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )

    }
}