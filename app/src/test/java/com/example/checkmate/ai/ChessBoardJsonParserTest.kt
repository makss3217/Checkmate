package com.example.checkmate.ai

import com.example.checkmate.chess.BoardPosition.*
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessColor.BLACK
import com.example.checkmate.chess.ChessColor.WHITE
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Queen
import com.example.checkmate.chess.figures.Rock
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


class ChessBoardJsonParserTest {

    @Test
    fun testParse() {

        val exampleJsonResponse = """
        {
            "chessBoard": [
            {
                "figure": "WHITE_QUEEN",
                "position": "F1"
            },
            {
                "figure": "WHITE_PAWN",
                "position": "F2"
            },
            {
                "figure": "WHITE_ROOK",
                "position": "F3"
            },
            {
                "figure": "WHITE_PAWN",
                "position": "D4"
            },
            {
                "figure": "BLACK_BISHOP",
                "position": "F4"
            },
            {
                "figure": "WHITE_PAWN",
                "position": "H4"
            },
            {
                "figure": "BLACK_PAWN",
                "position": "D5"
            },
            {
                "figure": "WHITE_PAWN",
                "position": "E5"
            },
            {
                "figure": "BLACK_KING",
                "position": "F5"
            },
            {
                "figure": "WHITE_PAWN",
                "position": "G5"
            },
            {
                "figure": "BLACK_PAWN",
                "position": "H5"
            },
            {
                "figure": "WHITE_PAWN",
                "position": "F7"
            },
            {
                "figure": "WHITE_KING",
                "position": "F8"
            }
            ]
        }"""

        val expectedBoard = ChessBoardBuilder()
            .position(F1, Queen(WHITE))
            .position(F2, Pawn(WHITE))
            .position(F3, Rock(WHITE))
            .position(D4, Pawn(WHITE))
            .position(F4, Bishop(BLACK))
            .position(H4, Pawn(WHITE))

            .position(E5, Pawn(WHITE))
            .position(G5, Pawn(WHITE))
            .position(D5, Pawn(BLACK))
            .position(H5, Pawn(BLACK))
            .position(F5, King(BLACK))

            .position(F7, Pawn(WHITE))
            .position(F8, King(WHITE))
            .build()

        val parser = ChessBoardJsonParser()

        assertThat(expectedBoard).isEqualTo(parser.parse(exampleJsonResponse))
    }
}

