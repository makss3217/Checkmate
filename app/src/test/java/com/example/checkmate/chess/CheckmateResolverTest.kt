package com.example.checkmate.chess

import com.example.checkmate.chess.figures.King
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessColor.*
import com.example.checkmate.chess.commands.MoveCommand
import com.example.checkmate.chess.figures.Rock

class CheckmateResolverTest {

    @Test
    fun findCheckMateInOne() {
        var board = ChessBoardBuilder()
            .position(G6, King(WHITE))
            .position(H8, King(BLACK))
            .position(A7, Rock(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 1)

        val expectedSolution = CheckmateSolution(MoveCommand(A7, A8))
        assertThat(solution).containsOnly(expectedSolution)
    }

    @Test
    fun findCheckMateInTwo() {
        var board = ChessBoardBuilder()
            .position(H5, King(WHITE))
            .position(H8, King(BLACK))
            .position(B6, Rock(WHITE))
            .position(A5, Rock(WHITE))
            .build()

        val resolver = CheckmateResolver()
        val solution = resolver.resolve(ChessGame(board), 2)

        val expectedSolution = createExpectedSolution()
        assertThat(solution).contains(expectedSolution)
        assertThat(solution).hasSize(4)
    }

    private fun createExpectedSolution(): CheckmateSolution {
        return CheckmateSolution(
            MoveCommand(A5, A7),
            setOf(
                CheckmateSolution(
                    MoveCommand(H8, G8),
                    setOf(
                        CheckmateSolution(
                            MoveCommand(B6, B8))))))
    }
}