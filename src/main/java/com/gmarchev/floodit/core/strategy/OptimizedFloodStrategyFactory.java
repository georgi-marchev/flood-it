package com.gmarchev.floodit.core.strategy;

public class OptimizedFloodStrategyFactory {

	public static final int DFS_THRESHOLD = 20;

	public static FloodStrategy create(int rowCount) {

		if (rowCount < DFS_THRESHOLD) {

			return new DfsFloodStrategy();

		} else {

			return new BfsFloodStrategy();
		}
	}
}
