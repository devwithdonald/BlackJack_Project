package com.donald.driver;

import com.donald.game.GameManager;


public class GameDriver {

	public static void main(String[] args) {

		// GAME MANAGER
		GameManager gameManager = new GameManager();

		// creates number of players
		gameManager.playerCreator();

		do {

			gameManager.playerSetCardHand();

			gameManager.viewGameState();

			gameManager.playerDecision();

		} while (!gameManager.isGameEnd());

		System.out.println("Game End!");
		System.exit(0);

	}

}
