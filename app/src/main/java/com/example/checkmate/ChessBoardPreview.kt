package com.example.checkmate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.checkmate.chess.BoardPosition
import com.example.checkmate.chess.ChessBoard
import com.example.checkmate.chess.ChessBoardFactory

@Composable
fun ChessBoardPreview(modifier: Modifier = Modifier) {
    val size = 8 // Size of the chessboard
    val chessBoard = ChessBoardFactory.createInitialChessBoard()
    Column(modifier = modifier) {
        for (row in 0 until size) {
            Row(modifier = Modifier.weight(1f)) { // Each row takes equal height
                for (col in 0 until size) {
                    val color = if ((row + col) % 2 == 0) Color.White else Color.Gray
                    Box(
                        modifier = Modifier
                            .weight(1f) // Each square takes equal width
                            .aspectRatio(1f) // Ensures squares are square-shaped
                            .background(color)
                    ) {
                        val piece = chessBoard.getFigureOnPosition(BoardPosition.from(row, col))
                        piece?.let {
                            Image(
                                painter = painterResource(id = piece.getPreview().getResourceId()),
                                contentDescription = "piece",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }
}