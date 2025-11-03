package com.gmarchev.floodit.game;

import java.io.PrintStream;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.strategy.FloodStrategy;

/**
 * A Decorator pattern implementation used for tracking how long the flood move takes.
 */
public class TimeTrackingFloodStrategyDecorator implements FloodStrategy {

	private final FloodStrategy wrapped;

	private final PrintStream printer;

	public TimeTrackingFloodStrategyDecorator(FloodStrategy wrapped, PrintStream printer) {

		this.wrapped = wrapped;
		this.printer = printer;
	}

	@Override
	public void flood(Board board, int color) {

		long start = System.currentTimeMillis();

		wrapped.flood(board, color);

		printer.println("Flood took " + (System.currentTimeMillis() - start) + " ms");
	}
}
