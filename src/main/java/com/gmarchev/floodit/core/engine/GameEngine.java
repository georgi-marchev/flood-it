package com.gmarchev.floodit.core.engine;

public interface GameEngine {

	int[][] getGrid();

	void start();

	boolean flood(int color);

	boolean isCompleted();
}
