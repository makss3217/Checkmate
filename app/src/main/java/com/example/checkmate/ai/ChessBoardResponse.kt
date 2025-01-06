package com.example.checkmate.ai

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.FigureImage
import kotlinx.serialization.Serializable

@Serializable
data class ChessBoardResponse(
    val chessBoard: List<ChessField>
)

@Serializable
data class ChessField(
    val figure: FigureImage,
    val position: BoardPosition
)