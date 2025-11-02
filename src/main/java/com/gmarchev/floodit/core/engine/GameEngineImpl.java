package com.gmarchev.floodit.core.engine;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.strategy.FloodStrategy;

public class GameEngineImpl implements GameEngine {

	private final Board board;

	private final FloodStrategy floodStrategy;

	private int moveCount;

	boolean isStarted;

	public GameEngineImpl(Board board, FloodStrategy floodStrategy) {

		this.board = board;
		this.floodStrategy = floodStrategy;
	}

	@Override
	public void start() {

		floodStrategy.flood(board, board.getFloodColor());

		isStarted = true;
	}

	@Override
	public int[][] getGrid() {

		return board.getGrid();
	}

	@Override
	public boolean flood(int color) {

		if (!isStarted) {

			throw new IllegalStateException("Game has not been started!");
		}

		if (board.getFloodColor() == color) {

			return false;
		}

		floodStrategy.flood(board, color);

		moveCount++;

		return true;
	}

	@Override
	public boolean isCompleted() {

		return board.isCompleted();
	}
}
