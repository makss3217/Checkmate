package com.example.checkmate.chess

import com.example.checkmate.chess.figures.ChessPiece
import com.example.checkmate.chess.figures.King
import com.example.checkmate.chess.figures.Knight
import com.example.checkmate.chess.figures.Rock
import com.example.checkmate.chess.ChessColor.*;
import com.example.checkmate.chess.figures.Bishop
import com.example.checkmate.chess.figures.Pawn
import com.example.checkmate.chess.figures.Queen

class ChessBoardFactory {

    companion object {
        fun createInitialChessBoard(): ChessBoard {
            val initialBoard = Array(8) { arrayOfNulls<ChessPiece>(8) }

            // Set up black pieces
            initialBoard[0] = arrayOf(
                Rock(BLACK),
                Knight(BLACK),
                Bishop(BLACK),
                Queen(BLACK),
                King(BLACK),
                Bishop(BLACK),
                Knight(BLACK),
                Rock(BLACK)
            )
            initialBoard[1] = Array(8) { Pawn(BLACK) }

            // Set up white pieces
            initialBoard[6] = Array(8) { Pawn(WHITE) }
            initialBoard[7] = arrayOf(
                Rock(WHITE),
                Knight(WHITE),
                Bishop(WHITE),
                Queen(WHITE),
                King(WHITE),
                Bishop(WHITE),
                Knight(WHITE),
                Rock(WHITE)
            )

            // Empty squares
            for (i in 2..5) {
                initialBoard[i] = arrayOfNulls(8)
            }

            return ChessBoard(initialBoard)
        }
    }
}
