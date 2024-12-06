package com.example.checkmate.chess

import com.example.checkmate.R

enum class FigureImage {
    WHITE_PAWN,
    BLACK_PAWN,
    WHITE_ROOK,
    BLACK_ROOK,
    WHITE_KNIGHT,
    BLACK_KNIGHT,
    WHITE_BISHOP,
    BLACK_BISHOP,
    WHITE_QUEEN,
    BLACK_QUEEN,
    WHITE_KING,
    BLACK_KING;

    fun getResourceId(): Int {
        return when (this) {
            WHITE_PAWN -> R.drawable.white_pawn
            BLACK_PAWN -> R.drawable.black_pawn
            WHITE_ROOK -> R.drawable.white_rook
            BLACK_ROOK -> R.drawable.black_rook
            WHITE_KNIGHT -> R.drawable.white_knight
            BLACK_KNIGHT -> R.drawable.black_knight
            WHITE_BISHOP ->R.drawable.white_bishop
            BLACK_BISHOP -> R.drawable.black_bishop
            WHITE_QUEEN -> R.drawable.white_queen
            BLACK_QUEEN -> R.drawable.black_queen
            WHITE_KING -> R.drawable.white_king
            BLACK_KING -> R.drawable.black_king
        }
    }
}