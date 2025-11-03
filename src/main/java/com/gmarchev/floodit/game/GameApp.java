package com.gmarchev.floodit.game;

import java.io.PrintStream;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.board.RandomColorBoardCreator;
import com.gmarchev.floodit.core.engine.GameEngine;
import com.gmarchev.floodit.core.engine.GameEngineImpl;
import com.gmarchev.floodit.core.strategy.FloodStrategy;
import com.gmarchev.floodit.core.strategy.FloodStrategyFactory;

/**
 * This is a demo implementation of the game core, using the system console as UI.
 */
public class GameApp {

	public static void main(String[] args) {

		int rows = 5, columns = 5;

		int[] colors = {1, 2, 3, 4, 5};

		PrintStream outputPrinter = System.out;

		Board board = RandomColorBoardCreator.create(rows, columns, colors);

		FloodStrategy strategy = FloodStrategyFactory.create(rows, columns);

		GameEngine engine = new GameEngineImpl(board, strategy);

		engine.addObserver(new GameRenderer(outputPrinter));

		try (GameLoop game = new GameLoop(engine, System.in, outputPrinter)) {

			game.start();
		}
	}
}