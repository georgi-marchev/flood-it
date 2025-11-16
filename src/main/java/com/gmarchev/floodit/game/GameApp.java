package com.gmarchev.floodit.game;

import java.io.InputStream;
import java.io.PrintStream;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.board.RandomColorBoardCreator;
import com.gmarchev.floodit.core.engine.GameEngine;
import com.gmarchev.floodit.core.engine.GameEngineImpl;
import com.gmarchev.floodit.core.strategy.FloodStrategy;
import com.gmarchev.floodit.core.strategy.OptimizedFloodStrategyFactory;

/**
 * This is a demo implementation of the game core, using the system console as UI.
 * <p>
 * This class is also the Composition Root of the application, which determines all concrete implementations that will
 * be used.
 */
public class GameApp {

	public static void main(String[] args) {

		int rows = 5, columns = 5;

		int[] colors = {1, 2, 3, 4, 5};

		InputStream inputReader = System.in;
		PrintStream outputPrinter = System.out;

		Board board = RandomColorBoardCreator.create(rows, columns, colors);

		FloodStrategy strategy = new TimeTrackingFloodStrategyDecorator(
				OptimizedFloodStrategyFactory.create(rows), outputPrinter);

		GameEngine engine = new GameEngineImpl(board, strategy);

		engine.addObserver(new GameRenderer(outputPrinter));

		try (GameLoop game = new GameLoop(engine, inputReader, outputPrinter)) {

			game.start();
		}
	}
}