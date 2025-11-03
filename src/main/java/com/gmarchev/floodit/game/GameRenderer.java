package com.gmarchev.floodit.game;

import java.io.PrintStream;

import com.gmarchev.floodit.core.engine.GameState;
import com.gmarchev.floodit.core.engine.GameObserver;

/**
 * An Observer pattern implementation that draws the board whenever there is a game state change.
 */
public class GameRenderer implements GameObserver {

	private final PrintStream printer;

	public GameRenderer(PrintStream printer) {

		this.printer = printer;
	}

	@Override
	public void onGameStateUpdate(GameState event) {

		printBoard(event.board());

		printer.println("Moves: " + event.moveCount() + (event.isComplete() ? " Completed!" : ""));
	}

	private void printBoard(int[][] board) {

		printer.println("\nCurrent board:");

		for (int[] row : board) {

			for (int color : row) {

				printer.print(color + " ");
			}

			printer.println();
		}

		printer.println();
	}
}
