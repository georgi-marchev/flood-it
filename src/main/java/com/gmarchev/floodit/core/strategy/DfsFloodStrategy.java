package com.gmarchev.floodit.core.strategy;

import com.gmarchev.floodit.core.board.Board;
import com.gmarchev.floodit.core.utils.BoardMovementUtil;

/**
 * Uses a simple recursive DFS algorithm for flooding the board.
 */
public class DfsFloodStrategy implements FloodStrategy {

	@Override
	public void flood(Board board, int color) {

		int rowCount = board.getGrid().length;

		if (rowCount < 1) {

			throw new IllegalArgumentException("Board must have at least one row");
		}

		int colCount = board.getGrid()[0].length;

		dfs(board, 0, 0, color, new boolean[rowCount][colCount]);

		board.setFloodColor(color);
	}

	private void dfs(Board board, int row, int col, int color, boolean[][] visited) {

		if (visited[row][col]) {

			return;
		}

		visited[row][col] = true;

		if (board.isCellFlooded(row, col)) {

			board.setCellColor(row, col, color);

		} else if (board.getCellColor(row, col) == color) {

			board.markCellFlooded(row, col);

		} else {

			return;
		}

		for (int[] newMove : BoardMovementUtil.getPossibleMoves(board, row, col)) {

			dfs(board, newMove[0], newMove[1], color, visited);
		}
	}
}
