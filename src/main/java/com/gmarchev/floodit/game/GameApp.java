package com.gmarchev.floodit.game;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.board.RandomBoardCreator;
import com.gmarchev.floodit.core.engine.GameEngine;
import com.gmarchev.floodit.core.engine.GameEngineImpl;
import com.gmarchev.floodit.core.strategy.FloodStrategy;
import com.gmarchev.floodit.core.strategy.FloodStrategyFactory;

public class Main {

	public static void main(String[] args) {

		int rows = 5, columns = 5;

		int[] colors = {1, 2, 3, 4, 5};

		Board board = RandomBoardCreator.create(rows, columns, colors);

		FloodStrategy strategy = new TimeTrackingFloodStrategyDecorator(
				FloodStrategyFactory.create(rows, columns), System.out);

		GameEngine engine = new GameEngineImpl(board, strategy);

		engine.addObserver(new ConsoleRenderer(System.out));

		try (ConsoleGame game = new ConsoleGame(engine, System.in, System.out)) {

			game.start()
		}
	}
}