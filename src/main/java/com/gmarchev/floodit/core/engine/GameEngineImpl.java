package com.gmarchev.floodit.core.engine;

import java.util.ArrayList;
import java.util.List;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.strategy.FloodStrategy;

public class GameEngineImpl implements GameEngine {

	private final Board board;

	private final FloodStrategy floodStrategy;

	private int moveCount;

	private final List<GameObserver> observers;

	boolean isStarted;

	public GameEngineImpl(Board board, FloodStrategy floodStrategy) {

		this.board = board;
		this.floodStrategy = floodStrategy;
		this.observers =  new ArrayList<>();
	}

	@Override
	public void addObserver(GameObserver observer) {

		observers.add(observer);
	}

	@Override
	public void removeObserver(GameObserver observer) {

		observers.remove(observer);
	}

	@Override
	public void start() {

		floodStrategy.flood(board, board.getFloodColor());

		isStarted = true;

		notifyObservers();
	}

	private void notifyObservers() {

		GameState gameState = new GameState(moveCount, board.isCompleted(), board.getGrid());

		for (GameObserver observer : observers) {

			observer.onGameStateUpdate(gameState);
		}
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

		notifyObservers();

		return true;
	}

	@Override
	public boolean isCompleted() {

		return board.isCompleted();
	}
}
