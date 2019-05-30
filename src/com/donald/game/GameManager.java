package com.donald.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameManager {

	// TODO NEED TO ENCAPSULATE
	public static boolean gameEnd = false;

	// keep track of dealer index
	public static int dealerTrackerIndex = 0;
	public static boolean allPlayersBusted = false;

	// keeping track of turns
	public static int turnNumber = 0;
	// player numbers
	public static int numberOfPlayers = 0;
	public List<Player> listOfPlayers = new ArrayList<>();

	// Methods
	public void playerCreator() {
		// create player objects based on how many players the user says 1-4
		// always create one dealer object

		// scanner class to read input
		Scanner scanner = new Scanner(System.in);

		// store int variable here
		int playerNumber = 0;

		boolean isValidNumber = false;

		do {
			// ask user
			System.out.println("Please enter the number of players in the game.. ");

			// while its not an int
			while (!scanner.hasNextInt()) {
				System.out.println("please enter a NUMBER!!!!!!!!!!!");
				scanner.next();
			}

			playerNumber = scanner.nextInt();
			scanner.nextLine();

			if (playerNumber > 0 && playerNumber <= 4) {
				numberOfPlayers = playerNumber;
				isValidNumber = true;
			} else {
				System.out.println("please enter a valid number (1-4)");
				// isNotValidNumber = true;
			}

		} while (!isValidNumber);

		// thank you message
		System.out.println("You entered: " + numberOfPlayers + " Thank you.");

		// CREATE PLAYER OBJECTS
		for (int i = 0; i < numberOfPlayers; i++) {
			listOfPlayers.add(new Player());
		}

		// CREATE DEALER OBJECT & add to list of players
		Dealer dealer = new Dealer();
		dealer.isDealer = true;
		listOfPlayers.add(dealer);

		// keeping track of the dealer index as int to use in future
		dealerTrackerIndex = listOfPlayers.size() - 1;

	}

	// add card hands depending on size and make sure dealer gets a hand
	public void playerSetCardHand() {

		// card deck
		CardDeck cardDeck = new CardDeck();

		// need to do this until every player has two cards run through this size * 2
		// times
		int cardDealCounter = 0;
		while (cardDealCounter < listOfPlayers.size() * 2) {
			for (int i = 0; i < listOfPlayers.size(); i++) {
				// if object is dealer then give hand
				// dealer is getting cards

				// getting this player
				listOfPlayers.get(i).addCardToHand(Dealer.dealAnotherCard());

				cardDealCounter++;

			}
		}

	}

	// viewGameState (so the players can view the state of the game)
	public void viewGameState() {
		// print out all
		// player 1 (i) has cards..
		// player 2 (i) has cards...
		// dealer (last i) has this card (can only view one card)

		// iterate through listofPlayers size
		for (int i = 0; i < listOfPlayers.size(); i++) {
			// iterate through the players card list and print them

			// IF LAST PLAYER THEN IT IS DEALER
			if (listOfPlayers.get(i).isDealer) {
				System.out.println("\nDealer has the hand of: ");
				listOfPlayers.get(i).handViewer();
			} else {
				System.out.println("\nPlayer " + (i + 1) + " has the hand of: ");
				listOfPlayers.get(i).handViewer();
			}

			System.out.println(" ");

		}

	}

	public void playerDecision() {

		// the dealer needs to check if he has an ACE then flip it and game over
		// else continue on with the game
		if (listOfPlayers.get(dealerTrackerIndex).getCardHand().get(0).getCardFace().equals("Ace")) {
			System.out.println("--dealer has ace. players have decision to get insurance--");

			//  INSURANCE HERE
			setInsurance();
		}

		Scanner scan = new Scanner(System.in);

		// iterate through the players
		// last player is always dealer
		for (int i = 0; i < listOfPlayers.size(); i++) {

			if (!listOfPlayers.get(i).isDealer) {

				// need this in here
				boolean stand = false;
				// calculate what they got
				listOfPlayers.get(i).addCardTotal();
				// show what they got
				// showing cards
				// listOfPlayers.get(i).handViewer();
				System.out.println(" ");

				// ask them what they would like to do
				// TODO TODO TODO NEED TO GIVE THEM DIFFERENT FUNCTIONALITY BASED ON THE CARDS

				// System.out.println("Would you like to hit?");

				// TODO switch case state for each option,
				// has to be h for hit or s for stand

				String decision = "";

				// TODO TODO TODO TODO copied this from above
				do {
					// ask user if hit or stand
					// scan.nextLine();

					// wont need this
					// if dealer
					if (listOfPlayers.get(i).isDealer) {
						System.out.println("Dealer has the hand total of: " + listOfPlayers.get(i).getCardHandTotal());
					} else {
						System.out.println("Player " + (i + 1) + " has the hand total of: "
								+ listOfPlayers.get(i).getCardHandTotal());
					}

					System.out.println("Please choose if you would like to hit or stand");

					// while its not an int
					// while (!scanner.hasNext()) {
					// System.out.println("please enter a NUMBER!!!!!!!!!!!");
					// scanner.next();
					// }

					// decision = scan.nextLine();

					// TODO MAY NEED THIS??
					while (!scan.hasNextLine()) {
						System.out.println("scan the right thing..");
						scan.next();
					}

					// reading input
					decision = scan.nextLine();

					// switch(decision)

					if (decision.equals("h") || decision.equals("H")) {
						// do hit method
						listOfPlayers.get(i).playerHit(Dealer.dealAnotherCard());
						// after hit method ask if they wanna hit again?

						// repeat^
						// TODO TODO check value if busted method should run
						// TODO TODO NEED TO KEEP TRACK OF WINNERS AND LOSERS AND AT END OF GAME NEED TO
						// TODO TODO FIGURE OUT WHO WON OR LOSE
						// TODO TODO WHEN GAME IS OVER (GAMEOVER = TRUE) ADD POINTS TO PLAYERS WHO WIN
						// STATE IS TRUE
						// TODO TODO IF DEALER WINS NO ONE ONES
						// TODO TODO TIE WITH DEALER
						if (listOfPlayers.get(i).getCardHandTotal() > 21) {
							// PLAYER BUST
							// CALL THE BUST METHOD
							System.out.println("Player " + (i + 1) + " has the hand total of: "
									+ listOfPlayers.get(i).getCardHandTotal());
							System.out.println("Busted. Sorry :-(");
							listOfPlayers.get(i).setBust(true);
							stand = true;

						}

					} else if (decision.equals("s") || decision.equals("S")) {
						// break out of loop
						stand = true;
					} else {
						System.out.println("please enter a valid 's' or 'h'");
						// isNotValidNumber = true;
					}
					// scanner.close();
				} while (stand == false);

			} else if (listOfPlayers.get(i).isDealer) {
				// dealer should do other stuff
				turnNumber = 1;
				System.out.println("-----dealer-----");

				// show dealer cards
				System.out.println("dealer hand view");
				listOfPlayers.get(i).handViewer();

				// dealer hit decision
				// if all players have busted
				int bustCounter = 0;

				for (int j = 0; j < listOfPlayers.size(); j++) {
					if (listOfPlayers.get(j).isBust()) {
						bustCounter++;
					}
				}

				// TODO TODO TODO ??
				if (bustCounter == listOfPlayers.size() - 1) {
					// SHOULD B END OF GAME!
					allPlayersBusted = true;
					System.out.println(
							"breaking because everyone busted -> should get out of method and calculate winners");
					System.out.println("dont call calulate game here");
					break;
				}

				// listOfPlayers.get(i).playerHit();
				// TODO FUNCTIONALITY NEEDED

				// adding dealer cards
				listOfPlayers.get(i).addCardTotal();
				// System.out.println("Dealer has the hand total of: " +
				// listOfPlayers.get(i).getCardHandTotal());

				while (listOfPlayers.get(i).getCardHandTotal() < 16) {
					System.out.println("Dealer has the hand total of: " + listOfPlayers.get(i).getCardHandTotal());
					listOfPlayers.get(i).playerHit(Dealer.dealAnotherCard());

				}

				// letting user know what dealer has after 16
				System.out.println("Dealer has the hand total of: " + listOfPlayers.get(i).getCardHandTotal());

				// if dealer is < less than 21

				// if
				if (listOfPlayers.get(i).getCardHandTotal() > 21) {
					listOfPlayers.get(i).setBust(true);
					// calculateGame();
				} else {
					// calculateGame();
				}

				// System.out.println("need to do comparison here");
				// calculateGame();
				// gameOver should accept a win state, String winner
				// players or dealers

				// else stand and gameover sequence
				// need to check if all players have busted
				// game should end after dealer makes all his decisions
				// call game over
				// gameOver();

			}

			// when stand is true break out
			// System.out.println("here");
			// ask for decision
		}

		System.out.println("end of player decicison loop - should call calculateGame here");
		calculateGame();

	}

	// & players who are not busted are greater
	// than dealer than game over players that are not busted win
	// go to game over and define players that are not busted get points
	// players that are busted get no points
	// check the winner state another method?
	// TODO GAME OVER METHOD

	// TODO SHOULD CALL POINTS ADDER ON THE PLAYER!!!!!
	public void calculateGame() {
		System.out.println("--calculateGame--");

		// if dealer has 21 game is over automatically
		// need hit functionality for dealer

		// big loop for loop?

		// if all players busted!

		for (int i = 0; i < listOfPlayers.size() - 1; i++) {
			if (listOfPlayers.get(i).isBust()) {
				System.out.println("all players busted");
				System.out.println("--calling points adder--");
				pointsAdder(listOfPlayers.get(i));
			} else if (listOfPlayers.get(dealerTrackerIndex).getCardHandTotal() == 21) {
				// if dealer gets 21 everyone loses except for people who tied the dealer
				System.out.println("Dealer has 21!!!!!!");
				if (listOfPlayers.get(i).getCardHandTotal() == listOfPlayers.get(dealerTrackerIndex)
						.getCardHandTotal()) {
					listOfPlayers.get(i).tie = true;
					System.out.println("tie - if dealer gets 21 everyone loses except for people who tied the dealer");
					System.out.println("Player " + (i + 1) + " tied!");
					// GAME IS OVER
					System.out.println("--calling points adder--");
					pointsAdder(listOfPlayers.get(i));
				} else {
					System.out.println("--calling points adder--");
					pointsAdder(listOfPlayers.get(i));
				}
			} else if (listOfPlayers.get(dealerTrackerIndex).isBust()) {
				// if dealer busted
				// playes who didnt bust win!
				System.out.println("dealer busted!!!");
				if (listOfPlayers.get(i).isBust() == false) {
					listOfPlayers.get(i).win = true;
					System.out.println("Players who didnt bust win!!!!!");
					System.out.println("Player " + (i + 1) + " won!");
					// dont need to add this all at once!!!!!!!!!!!!!
					// called for every player
					System.out.println("--calling points adder--");
					// game over!
					pointsAdder(listOfPlayers.get(i));
				}
			} else if (!listOfPlayers.get(dealerTrackerIndex).isBust()) {
				// if dealer didnt bust see if the players win or not
				System.out.println("dealer DID NOT bust!!!");
				if (listOfPlayers.get(i).isBust() == false) {
					System.out.println("player didnt bust!");
					if (listOfPlayers.get(i).getCardHandTotal() > listOfPlayers.get(dealerTrackerIndex)
							.getCardHandTotal()) {
						// then player wins! woo!
						listOfPlayers.get(i).win = true;
						System.out.println("Player " + (i + 1) + " won!");
						System.out.println("--calling points adder--");
						pointsAdder(listOfPlayers.get(i));
					} else if (listOfPlayers.get(i).getCardHandTotal() == listOfPlayers.get(dealerTrackerIndex)
							.getCardHandTotal()) {
						listOfPlayers.get(i).tie = true;
						System.out.println("Player " + (i + 1) + " tied!");
						System.out.println("--calling points adder--");
						pointsAdder(listOfPlayers.get(i));
					} else {
						System.out.println("Player " + (i + 1) + " lost!");
						System.out.println("--calling points adder--");
						pointsAdder(listOfPlayers.get(i));
					}
				}
			}

		}

		// game over
		// TODO CALL GAME END METHOD HERE!!! DISPLAY POINTS AND ASK IF WANT TO PLAY
		// AGAIN

		System.out.println("--need to call game end method here---");
		endGameSequence();

	}

	// TODO points adder
	// different
	// CAN JUST CHECK WIN or TIE AND OTHER STUFF
	public void pointsAdder(Player player) {
		System.out.println("--inside points adder method--");

		// TODO TODO TODO
		// add points to the player
		if (player.win == true) {
			player.setPoints(player.getPoints() + 10);
			// shows point accumulated
			System.out.println("10 Points Added! ->" + player.getPoints());
			// if player doubled
			if (player.doubleDown == true) {
				player.setPoints(player.getPoints() + 10);
				// shows point accumulated
				System.out.println("Doubled Down! Another 10 Points added! ->" + player.getPoints());
			}
			
			//insurance
			if(player.insurance == true) {
				player.setPoints(player.getPoints() - 5);
				System.out.println("5 Points Deducted - Insurance!" + player.getPoints());
			}
			

		} else if (player.tie) {
			player.setPoints(player.getPoints());
			System.out.println("Tie! No points taken or deducted! ->" + player.getPoints());
			
			//insurance
			if(player.insurance == true) {
				player.setPoints(player.getPoints() - 5);
				System.out.println("5 Points Deducted - Insurance!" + player.getPoints());
			}
			
		} else if (player.win == false) {
			// LOSER
			player.setPoints(player.getPoints() - 10);
			System.out.println("10 Points Deducted! ->" + player.getPoints());

			// INSURANCE
			if (player.insurance == true && listOfPlayers.get(dealerTrackerIndex).getCardHandTotal() == 21) {
				// player only gets 5 points
				player.setPoints(player.getPoints() + 5);
				System.out.println("5 Points Added (insurance)! ->" + player.getPoints());
			}

			// DOUBLE DOWN
			if (player.doubleDown == true) {
				player.setPoints(player.getPoints() - 10);
				System.out.println("Doubled Down! 10 Points Deducted ->" + player.getPoints());
			}
		}

	}

	// TODO END GAME
	// return boolean
	public void endGameSequence() {
		System.out.println("--in endGameSequence method --");
		// display everyones points
		// if they play wants to play again then return true
		// the they dont want to play again

		// display points

		// restart
		gameEnd = false;

		pointsDisplayer();
		// return boolean;

		Scanner scanner = new Scanner(System.in);

		boolean validInput = true;
		String input = "";

		// play again??????????????
		do {
			System.out.println("Would you like to play again? (y/n)");

			input = scanner.nextLine();

			if (input.equals("y") || input.equals("Y")) {
				validInput = false;
				gameEnd = false;
				System.out.println("--calling game reset method--");
				gameReset();
			} else if (input.equals("n") || input.equals("N")) {
				validInput = false;
				gameEnd = true;
			}
		} while (validInput);

	}

	// TODO
	public void gameReset() {
		System.out.println("--in gameReset method--");
		// reset game but dont reset player score!
		// loop through the players and remove all cards and put them back into the
		// deck?
		//

		// get their card hand and remove everything from it

		// EMPTY THE HANDS
		// RESET GAME STATE
		for (int i = 0; i < listOfPlayers.size(); i++) {
			// for (int j = 0; j < listOfPlayers.get(i).getCardHand().size(); j++) {
			for (int j = 0; j < 1; j++) {
				System.out.println("Empty Cards");
				listOfPlayers.get(i).getCardHand().removeAll(listOfPlayers.get(i).getCardHand());
				listOfPlayers.get(i).doubleDown = false;
				listOfPlayers.get(i).insurance = false;
				listOfPlayers.get(i).tie = false;
				listOfPlayers.get(i).win = false;
				listOfPlayers.get(i).setBust(false);
				turnNumber = 0;
			}

		}

	}

	// displays all points
	public void pointsDisplayer() {
		System.out.println("--in pointsDisplayer Method--");
		// point displayer
		for (int i = 0; i < listOfPlayers.size() - 1; i++) {
			System.out.println("Player " + (i + 1) + " has" + listOfPlayers.get(i).getPoints() + " points.");
		}

	}

	// insurance for players
	public void setInsurance() {
		System.out.println("--in insurance method--");
		// loop through the players
		// ask if they want insurance
		// scanner y or no
		// if they do want insurance then set insurance to true
		// -- else
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < listOfPlayers.size() - 1; i++) {

			boolean validResponse = false;
			String response = "";

			do {
				// ask player if they want insurance
				System.out.println("Does Player " + (i + 1) + " want insurance? (y/n)");
				response = scanner.nextLine();

				if (response.equals("y") || response.equals("Y")) {
					listOfPlayers.get(i).insurance = true;
					validResponse = true;
				} else if (response.equals("n") || response.equals("N")) {
					listOfPlayers.get(i).insurance = false;
					validResponse = true;
				}

			} while (!validResponse);

		}

	}

}
