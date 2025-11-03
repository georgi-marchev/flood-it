package com.gmarchev.floodit.game;

import java.io.Closeable;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.gmarchev.floodit.core.engine.GameEngine;

public class GameLoop implements Closeable {

	private final GameEngine engine;

	private final PrintStream printer;

	private final Scanner scanner;

	public GameLoop(GameEngine engine, InputStream inputStream, PrintStream printer) {

		this.engine = engine;
		this.printer = printer;
		this.scanner = new Scanner(inputStream);
	}

	public void start() {

		printer.println("Welcome to Flood-It (Console Edition)!");

		engine.start();

		while (!engine.isCompleted()) {

			printer.print("Enter color number (e.g. 1–5) or 'u' to undo: ");

			String input = scanner.next().trim().toLowerCase();

			if (input.equals("u")) {

				engine.undo();

				continue;
			}

			try {

				int color = Integer.parseInt(input);
				engine.flood(color);

			} catch (NumberFormatException e) {

				printer.println("Invalid input. Please enter a number.");
			}
		}

		printer.println("Congratulations, you flooded the board!");
	}

	@Override
	public void close() {

		this.scanner.close();
	}
}
