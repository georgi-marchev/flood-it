package com.gmarchev.floodit.core.strategy;

import com.gmarchev.floodit.core.board.Board;

public interface FloodStrategy {

	void flood(Board board, int color);
}
