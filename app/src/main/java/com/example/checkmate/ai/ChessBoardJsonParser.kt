package com.example.checkmate.ai

import com.example.checkmate.chess.ChessBoard
import com.example.checkmate.chess.ChessBoardBuilder
import com.example.checkmate.chess.ChessBoardFactory
import kotlinx.serialization.json.Json

class ChessBoardJsonParser {

    fun parse(json: String) : ChessBoard {
        val chessFromJson = Json.decodeFromString<ChessBoardResponse>(json)
        return mapToChessBoardFromResponse(chessFromJson)
    }

    private fun mapToChessBoardFromResponse(chessBoardResponse: ChessBoardResponse?): ChessBoard {
        chessBoardResponse?.let {
            return mapToChessBoard(it)
        } ?: run {
            return ChessBoardFactory.createInitialChessBoard()
        }
    }

    private fun mapToChessBoard(chessBoardResponse: ChessBoardResponse): ChessBoard {
        val builder = ChessBoardBuilder()
        for (field in chessBoardResponse.chessBoard) {
            builder.position(field.position, field.figure.getFigure())
        }
        return builder.build()
    }

}