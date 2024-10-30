package com.example.checkmate.chess

import com.example.checkmate.chess.commands.ChessCommand

class CheckmateResolver {

    fun resolve(game: ChessGame, moveToMate: Int): Set<CheckmateSolution> {
        val availableMoves = game.getAllAvailableMoves()
        if (moveToMate == 1) {
            return findCheckmateInOne(availableMoves, game)
        } else {
            val solution = mutableSetOf<CheckmateSolution>()
            for (command in availableMoves) {
                game.executeCommand(command)
                if (game.isCheckMate()) {
                    solution.add(CheckmateSolution(command))
                } else {
                    val checkmateSolutionAtOpponentsMoves = getCheckmateSolutionAtOpponentsMoves(game, moveToMate)
                    if (checkmateSolutionAtOpponentsMoves.isNotEmpty()) {
                        solution.add(CheckmateSolution(command, checkmateSolutionAtOpponentsMoves))
                    }
                }
                game.undoCommand()
            }
            return solution
        }
    }

    private fun getCheckmateSolutionAtOpponentsMoves(
        game: ChessGame,
        moveToMate: Int
    ): Set<CheckmateSolution> {
        val opponentMoves = game.getAllAvailableMoves()
        val solutionsOnOpponentsMove = mutableSetOf<CheckmateSolution>()
        for(command in opponentMoves) {
            game.executeCommand(command)
            val checkMateSolutions = resolve(game, moveToMate - 1)
            if(checkMateSolutions.isNotEmpty()) {
                solutionsOnOpponentsMove.add(CheckmateSolution(command, checkMateSolutions))
            } else{
                game.undoCommand() // this else probably redundant
                break
            }
            game.undoCommand()
        }
        return if(solutionsOnOpponentsMove.size == opponentMoves.size) {
            solutionsOnOpponentsMove
        } else {
            setOf()
        }
    }

    private fun findCheckmateInOne(
        availableMoves: Set<ChessCommand>,
        game: ChessGame
    ): MutableSet<CheckmateSolution> {
        val solution = mutableSetOf<CheckmateSolution>()
        for (command in availableMoves) {
            game.executeCommand(command)
            if (game.isCheckMate()) {
                solution.add(CheckmateSolution(command))
            }
            game.undoCommand()
        }
        return solution
    }

}