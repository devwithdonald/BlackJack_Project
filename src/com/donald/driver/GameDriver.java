package com.donald.driver;

import java.util.Arrays;

import com.donald.game.CardDeck;
import com.donald.game.Dealer;
import com.donald.game.GameManager;
import com.donald.game.Player;

public class GameDriver {

	public static void main(String[] args) {
		// in main we...
		// 1. create a dealer object
		// 2. create a player object
		// 3. the dealer need to deal a player a hand (cardDeal Method)
		// 4. the
		
		//test

		// TODO need to ask the user how many players there are
		// for loop to go through and do this -->

		// create player
		// player does not initial start with a hand
		//Player player = new Player();

		// create this once need it


		// print out the card deck
		//System.out.println("Card Deck List->" + Arrays.toString(CardDeck.deck.toArray()));
//		System.out.println("Card Deck Size->" + CardDeck.deck.size());

		// create dealer
	//	Dealer dealer = new Dealer();

		// dealer the player

		// TODO CHANGING THIS
		//player.setCardHand(dealer.dealPlayer());

		
		
		
		
		// player should now have two RANDOM cards
		//System.out.println("Card Hand FOR PLAYER-->" + player.getCardHand());
		//System.out.println("Card Deck Size For player-->" + player.getCardHand().size());

		//player.addCardTotal();
		//player.handViewer();
		// print card total
		//System.out.println("card value for player-->" + player.getCardHandTotal());

		// hiting, adding one more card
//		player.playerHit(dealer.dealAnotherCard());

	//	player.addCardTotal();

		//player.handViewer();

		//System.out.println("Card Hand FOR PLAYER-->" + player.getCardHand());
		//System.out.println("Card Deck Size For player-->" + player.getCardHand().size());
		//System.out.println("card value for player AFTER HIT -->" + player.getCardHandTotal());


		
		
		
		// TODO DEALER NEEDS CARDS TOO!!!!
		//dealer.setCardHand(dealer.dealPlayer());

		// dealer should now have two RANDOM cards
		//System.out.println("Card Hand for DEALER-->" + dealer.getCardHand());
		//System.out.println("Card Deck Size For DEALER-->" + dealer.getCardHand().size());

		
		//dealer.addCardTotal();
		//dealer.handViewer();
		// print card total
		//System.out.println("card value for dealer-->" + dealer.getCardHandTotal());
		
		
		
		//while (true) play {gamemanger needs static method and needs to check at end// return?}
		
		
		//TODO cardDeck -> called inside set card hand (need to reset)
		//CardDeck cardDeck = new CardDeck();
		//TODO GAME MANAGER
		GameManager gameManager = new GameManager();
		
		//creates number of players
		gameManager.playerCreator();
		
		//TODO while goes here 
		//boolean gameEnd;
		
		//if "Y" then game reset then start again
		do {
			//deals cards to players
			gameManager.playerSetCardHand();
			
			//need to view game state
			gameManager.viewGameState();
			
			//need to go through each player and check their decision 
			gameManager.playerDecision();
			System.out.println("here1");
			//gameEnd = gameManager.endGameSequence();
			//System.out.println("here2");
		} while (!GameManager.gameEnd);

		System.out.println("Game End!");
		System.exit(0);
		
		
		//need to call calculate game
		
		
		
		
		//game is over
		//gameManager.gameOver();
		
		
		//print list
		System.out.println("END-> " + gameManager.listOfPlayers);
		
		
		
		
		// MULTIPLE PLAYER TEST
//		Player player2 = new Player();
//		player2.setCardHand(dealer.dealPlayer());
//		player2.addCardTotal();
//		System.out.println("Card Hand-->" + player2.getCardHand());	
//		System.out.println("Card Deck Size For player-->" + player2.getCardHand().size());
//		System.out.println("card value for player-->" + player2.getCardHandTotal());
//		Player player3 = new Player();
//		player3.setCardHand(dealer.dealPlayer());
//		player3.addCardTotal();
//		System.out.println("Card Hand-->" + player3.getCardHand());	
//		System.out.println("Card Deck Size For player-->" + player3.getCardHand().size());
//		System.out.println("card value for player-->" + player3.getCardHandTotal());

	}

}
