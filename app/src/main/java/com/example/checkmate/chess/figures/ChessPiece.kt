package com.example.checkmate.chess.figures

import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessColor
import com.example.checkmate.chess.commands.ChessCommand
import com.example.checkmate.chess.ChessGame
import com.example.checkmate.chess.FigureImage

interface ChessPiece {

    val color: ChessColor

    fun getAvailableMoves(game: ChessGame, currentPosition: BoardPosition) : Set<ChessCommand>

    fun getPreview() : FigureImage
}
