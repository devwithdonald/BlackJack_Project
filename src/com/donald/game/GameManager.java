package com.donald.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GameManager {

	// keeping track of turns
	public static int turnNumber = 0;

	private boolean gameEnd = false;
	// keep track of dealer index
	private int dealerTrackerIndex;
	private boolean allPlayersBusted = false;
	// player numbers
	private int numberOfPlayers = 0;
	// list of players
	private List<Player> listOfPlayers = new ArrayList<>();

	// constructors
	public boolean isGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}

	public int getDealerTrackerIndex() {
		return dealerTrackerIndex;
	}

	public void setDealerTrackerIndex(int dealerTrackerIndex) {
		this.dealerTrackerIndex = dealerTrackerIndex;
	}

	public boolean isAllPlayersBusted() {
		return allPlayersBusted;
	}

	public void setAllPlayersBusted(boolean allPlayersBusted) {
		this.allPlayersBusted = allPlayersBusted;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public List<Player> getListOfPlayers() {
		return listOfPlayers;
	}

	// METHODS
	// take in turnNumber & players
	public void playerCreator() {
		// ask how many players?
		// create player objects based on how many players the user says 1-4
		// store those player objects in a list
		// always create one dealer object

		// scanner class to read input
		Scanner scanner = new Scanner(System.in);

		// store int variable here
		int playerNumber = 0;

		boolean isValidNumber = false;

		do {
			// ask user
			System.out.println("Please Enter The Number Of Players In The Game (1-4)");

			// while its not an int
			while (!scanner.hasNextInt()) {
				System.out.println("Please Enter A Valid Number (1-4)");
				scanner.next();
			}

			playerNumber = scanner.nextInt();
			// need this after nextInt();
			scanner.nextLine();

			if (playerNumber > 0 && playerNumber <= 4) {
				setNumberOfPlayers(playerNumber);
				isValidNumber = true;
			} else {
				System.out.println("Please Enter A Valid Number (1-4)");

			}

		} while (!isValidNumber);

		// thank you message
		System.out.println("You Entered: " + getNumberOfPlayers() + ". Thank You. Good Luck!!!");
		System.out.println("\nDealer Stands On 16! Don't Forget!");

		// CREATE PLAYER OBJECTS
		for (int i = 0; i < getNumberOfPlayers(); i++) {
			getListOfPlayers().add(new Player());
		}

		// create dealer object & add to list of players
		Dealer dealer = new Dealer();
		dealer.setDealer(true);
		getListOfPlayers().add(dealer);

		setDealerTrackerIndex(getListOfPlayers().size() - 1);

	}

	// add card hands depending on size and make sure dealer gets a hand
	public void playerSetCardHand() {

		// need to create to create the main deck
		CardDeck cardDeck = new CardDeck();

		// need to do this until every player has two cards run through this size * 2
		// times
		int cardDealCounter = 0;
		while (cardDealCounter < getListOfPlayers().size() * 2) {
			for (int i = 0; i < getListOfPlayers().size(); i++) {
				// if object is dealer then give hand
				// dealer is getting cards
				if (getListOfPlayers().get(getDealerTrackerIndex()) instanceof Dealer) {
					//
					Dealer dealer = (Dealer) getListOfPlayers().get(getDealerTrackerIndex());
					getListOfPlayers().get(i).addCardToHand(dealer.dealAnotherCard());
				}

				cardDealCounter++;

			}
		}

	}

	// viewGameStateMethod (so the players can view the state of the game)
	public void viewGameState() {
		// print out all
		// player 1 (i) has cards..
		// player 2 (i) has cards...
		// dealer (last i) has this card (can only view one card)

		System.out.println("\n--------------- Dealing The Cards ---------------");

		for (int i = 0; i < getListOfPlayers().size(); i++) {

			if (getListOfPlayers().get(i).isDealer()) {
				System.out.println("\n---- Dealer's Hand ----");
				getListOfPlayers().get(i).handViewer();
			} else {
				System.out.println("\n---- Player #" + (i + 1) + "'s Hand ----");
				getListOfPlayers().get(i).handViewer();
			}

			System.out.println(" ");

		}

	}

	public void playerDecision() {

		// TODO fix insurance functionality
		if (getListOfPlayers().get(getDealerTrackerIndex()).getCardHand().get(0).getCardFace().equals("Ace")) {


			setInsurance();
		}

		Scanner scan = new Scanner(System.in);

		// iterate through the players
		// last player is always dealer
		for (int i = 0; i < getListOfPlayers().size(); i++) {

			if (!getListOfPlayers().get(i).isDealer()) {

				// Title
				System.out.println("--------------- Player #" + (i + 1) + "'s Turn! ---------------");

				boolean stand = false;
				// calculate what they have
				getListOfPlayers().get(i).addCardTotal();

				System.out.println(" ");

				String decision = "";

				// ace logic
				for (int j = 0; j < getListOfPlayers().get(i).getCardHand().size(); j++) {
					if (getListOfPlayers().get(i).getCardHand().get(j).getCardFace().equals("Ace")) {
						// ask user if they want to use ace as 1 or 11

						String input = "";
						boolean validInput = false;
						// scanner
						do {
							Scanner scanner = new Scanner(System.in);

							System.out.println(
									"Player " + (i + 1) + " Has An Ace!! Would You Like To Play This As A 1 Or 11?\n");
							// call card total
							getListOfPlayers().get(i).addCardTotal();
							System.out.println("If You Play This As A '11' You Will Have "
									+ getListOfPlayers().get(i).getCardHandTotal());
							System.out.println("If You Play This As A '1' You Will Have A "
									+ (getListOfPlayers().get(i).getCardHandTotal() - 10));

							System.out.println("\nDealer Is Showing Card Total ->" + getListOfPlayers()
									.get(getDealerTrackerIndex()).getCardHand().get(0).getCardValue());

							System.out.println("\nPlease Input '1' Or '11' To Move On!");
							input = scanner.nextLine();

							if (input.equals("1")) {
								validInput = true;
							} else if (input.equals("11")) {
								validInput = true;
							}

						} while (!validInput);

						// logic to make a 1 or an 11
						int newValue = Integer.parseInt(input);

						getListOfPlayers().get(i).getCardHand().get(j).setCardValue(newValue);

						System.out.println("You Have Successfully Turned Your "
								+ getListOfPlayers().get(i).getCardHand().get(j).getCardFace() + " To The Value Of "
								+ getListOfPlayers().get(i).getCardHand().get(j).getCardValue());
					}
				}

				// add card total for double
				getListOfPlayers().get(i).addCardTotal();

				boolean doubleDecision = false;

				if (getListOfPlayers().get(i).getCardHandTotal() == 9
						|| getListOfPlayers().get(i).getCardHandTotal() == 10
						|| getListOfPlayers().get(i).getCardHandTotal() == 11) {

					boolean validInput = false;
					String input = "";

					do {

						Scanner scanner = new Scanner(System.in);
						System.out.println("Player " + (i + 1) + " Has The Hand Total Of -> "
								+ getListOfPlayers().get(i).getCardHandTotal());
						System.out.println("\nDealer Is Showing Card Total ->"
								+ getListOfPlayers().get(getDealerTrackerIndex()).getCardHand().get(0).getCardValue());
						System.out.println("\nWould You Like To Double? (Y)/(N)");

						input = scanner.nextLine();

						if (input.equals("y") || input.equals("Y")) {
							validInput = true;
							doubleDecision = true;

							// double functionality
							// calls hit method only once!
							if (getListOfPlayers().get(getDealerTrackerIndex()) instanceof Dealer) {
								Dealer dealer = (Dealer) getListOfPlayers().get(getDealerTrackerIndex());
								getListOfPlayers().get(i).playerHit(dealer.dealAnotherCard());
							}

							// call add card total
							getListOfPlayers().get(i).addCardTotal();

							// displayer
							System.out.println("You Were Dealt 1 Card & Now Have The Hand Total Of -> "
									+ getListOfPlayers().get(i).getCardHandTotal());

							// set double to true
							getListOfPlayers().get(i).setDoubleDown(true);

						} else if (input.equals("n") || input.equals("N")) {
							validInput = true;
							doubleDecision = false;
						}

					} while (!validInput);

				}

				if (doubleDecision == false) {
					do {

						// if dealer
						if (getListOfPlayers().get(i).isDealer()) {
							System.out.println("\nDealer Has The Hand Total Of -> "
									+ getListOfPlayers().get(i).getCardHandTotal());
						} else {
							getListOfPlayers().get(i).addCardTotal();
							System.out.println("Player " + (i + 1) + " Has The Hand Total Of -> "
									+ getListOfPlayers().get(i).getCardHandTotal());

							System.out.println("\nDealer Is Showing Card Total ->" + getListOfPlayers()
									.get(getDealerTrackerIndex()).getCardHand().get(0).getCardValue());
						}

						System.out.println("\nPlease Choose If You Would Like To Hit (H) Or Stand (S).");

						while (!scan.hasNextLine()) {
							System.out.println("scan the right thing..");
							scan.next();
						}

						// reading input
						decision = scan.nextLine();

						if (decision.equals("h") || decision.equals("H")) {

							if (getListOfPlayers().get(getDealerTrackerIndex()) instanceof Dealer) {
								//
								Dealer dealer = (Dealer) getListOfPlayers().get(getDealerTrackerIndex());
								getListOfPlayers().get(i).playerHit(dealer.dealAnotherCard());
							}

							if (getListOfPlayers().get(i).getCardHandTotal() > 21) {
								// PLAYER BUST
								// CALL THE BUST METHOD
								System.out.println("\nPlayer " + (i + 1) + " Has The Hand Total Of -> "
										+ getListOfPlayers().get(i).getCardHandTotal());
								System.out.println("Busted. Sorry :-(");
								getListOfPlayers().get(i).setBust(true);
								stand = true;

							}

						} else if (decision.equals("s") || decision.equals("S")) {
							// break out of loop
							stand = true;
						} else {
							System.out.println("\nPlease Enter A Valid 'S' Or 'H'\n");

						}

					} while (stand == false);

				}
			} else if (getListOfPlayers().get(i).isDealer()) {
				// title
				System.out.println("\n--------------- Dealer's Turn! ---------------\n");

				// dealer should do other stuff
				turnNumber = 1;

				// show dealer cards
				getListOfPlayers().get(i).handViewer();

				// dealer hit decision
				// to help if all players have busted
				int bustCounter = 0;

				for (int j = 0; j < getListOfPlayers().size(); j++) {
					if (getListOfPlayers().get(j).isBust()) {
						bustCounter++;
					}
				}

				if (bustCounter == getListOfPlayers().size() - 1) {
					// SHOULD B END OF GAME!
					setAllPlayersBusted(true);
					// show dealer result
					// add card total
					getListOfPlayers().get(dealerTrackerIndex).addCardTotal();
					System.out.println(
							"\nDealer Had The Hand Total Of -> " + getListOfPlayers().get(i).getCardHandTotal());

					break;
				}

				// adding dealer cards
				getListOfPlayers().get(i).addCardTotal();

				while (getListOfPlayers().get(i).getCardHandTotal() < 16) {
					System.out.println(
							"\nDealer Has The Hand Total Of -> " + getListOfPlayers().get(i).getCardHandTotal());

					if (getListOfPlayers().get(getDealerTrackerIndex()) instanceof Dealer) {

						Dealer dealer = (Dealer) getListOfPlayers().get(getDealerTrackerIndex());
						getListOfPlayers().get(i).playerHit(dealer.dealAnotherCard());
					}

				}

				// letting user know what dealer has after 16
				System.out.println("\nDealer Has The Hand Total Of -> " + getListOfPlayers().get(i).getCardHandTotal());

				// if dealer is < less than 21
				if (getListOfPlayers().get(i).getCardHandTotal() > 21) {
					getListOfPlayers().get(i).setBust(true);

				} else {

				}

			}

		}

		calculateGame();

	}

	public void calculateGame() {

		for (int i = 0; i < getListOfPlayers().size() - 1; i++) {

			System.out.println(
					"\nPlayer #" + (i + 1) + " Has Hand Total -> " + getListOfPlayers().get(i).getCardHandTotal());

			if (getListOfPlayers().get(i).isBust()) {
				System.out.println("Oh No! Player #" + (i + 1) + " Busted");

				pointsAdder(getListOfPlayers().get(i));
			} else if (getListOfPlayers().get(getDealerTrackerIndex()).getCardHandTotal() == 21) {
				// if dealer gets 21 everyone loses except for people who tied the dealer
				System.out.println("Dealer Has 21!!!!!!");

				if (getListOfPlayers().get(i).getCardHandTotal() == getListOfPlayers().get(getDealerTrackerIndex())
						.getCardHandTotal()) {
					getListOfPlayers().get(i).setTie(true);

					System.out.println("Player #" + (i + 1) + " Tied The Dealer!");

					pointsAdder(getListOfPlayers().get(i));
				} else {

					System.out.println("Player #" + (i + 1) + " Lost!");

					pointsAdder(getListOfPlayers().get(i));
				}
			} else if (getListOfPlayers().get(getDealerTrackerIndex()).isBust()) {
				// if dealer busted
				// playes who didnt bust win!
				System.out.println("Dealer Busted!!!");

				if (getListOfPlayers().get(i).isBust() == false) {
					getListOfPlayers().get(i).setWin(true);

					System.out.println("Players Who Didnt Bust Win!");
					System.out.println("Player #" + (i + 1) + " Won!");

					// called for every player
					pointsAdder(getListOfPlayers().get(i));
				}
			} else if (!getListOfPlayers().get(getDealerTrackerIndex()).isBust()) {
				// if dealer didnt bust see if the players win or not

				if (getListOfPlayers().get(i).isBust() == false) {

					if (getListOfPlayers().get(i).getCardHandTotal() > getListOfPlayers().get(getDealerTrackerIndex())
							.getCardHandTotal()) {
						// then player wins! woo!
						getListOfPlayers().get(i).setWin(true);
						System.out.println("Player #" + (i + 1) + " won!");

						pointsAdder(getListOfPlayers().get(i));
					} else if (getListOfPlayers().get(i).getCardHandTotal() == getListOfPlayers()
							.get(getDealerTrackerIndex()).getCardHandTotal()) {
						getListOfPlayers().get(i).setTie(true);
						System.out.println("Player #" + (i + 1) + " tied!");

						pointsAdder(getListOfPlayers().get(i));
					} else {
						System.out.println("Player #" + (i + 1) + " lost!");
						pointsAdder(getListOfPlayers().get(i));
					}
				}
			}

		}

		// game over
		// calls game end method here, display points & ask if they want to play again
		endGameSequence();

	}

	public void pointsAdder(Player player) {
		// format
		System.out.println("\n");

		// add points to the player
		if (player.isWin() == true) {
			player.setPoints(player.getPoints() + 10);
			// shows point accumulated
			System.out.println("10 Points added! New Total ----> " + player.getPoints());
			// if player doubled
			if (player.isDoubleDown() == true) {
				player.setPoints(player.getPoints() + 10);
				// shows point accumulated
				System.out.println("Doubled Down! Another 10 Points added! New Total ----> " + player.getPoints());
			}

			if (player.isInsurance() == true) {
				player.setPoints(player.getPoints() - 5);
				// shows point accumulated
				System.out.println("Dealer Didn't Have BlackJack.");
				System.out.println("5 Points Deducted (insurance)! New Total ----> " + player.getPoints());
			}

		} else if (player.isTie()) {
			player.setPoints(player.getPoints());
			System.out.println("Tie! No Points Taken or Deducted! Total ----> " + player.getPoints());

			// INSURANCE
			if (player.isInsurance() == true
					&& getListOfPlayers().get(getDealerTrackerIndex()).getCardHandTotal() == 21) {
				// player only gets 5 points
				player.setPoints(player.getPoints() + 5);

				System.out.println("5 Points Added (insurance)! New Total ----> " + player.getPoints());
			} else if (player.isInsurance() == true) {
				// insurance loser
				player.setPoints(player.getPoints() - 5);
				System.out.println("Dealer Didn't Have BlackJack.");
				System.out.println("5 Points Deducted (insurance)! New Total ----> " + player.getPoints());
			}

			
			

		} else if (player.isWin() == false) {

			// LOSER
			player.setPoints(player.getPoints() - 10);
			System.out.println("10 Points Deducted! New Total ----> " + player.getPoints());

			// INSURANCE
			if (player.isInsurance() == true
					&& getListOfPlayers().get(getDealerTrackerIndex()).getCardHandTotal() == 21) {
				// player only gets 5 points
				player.setPoints(player.getPoints() + 5);

				System.out.println("5 Points Added (insurance)! New Total ----> " + player.getPoints());
			}

			// insurance loser
			if (player.isInsurance() == true) {
				player.setPoints(player.getPoints() - 5);
				System.out.println("Dealer Didn't Have BlackJack.");
				System.out.println("5 Points Deducted (insurance)! New Total ----> " + player.getPoints());
			}
			// DOUBLE DOWN
			if (player.isDoubleDown() == true) {
				player.setPoints(player.getPoints() - 10);
				System.out.println("Doubled Down! 10 Points Deducted! New Total ----> " + player.getPoints());
			}
		}

	}

	// end game method
	public void endGameSequence() {
		// restart
		setGameEnd(false);

		pointsDisplayer();

		Scanner scanner = new Scanner(System.in);

		boolean validInput = true;
		String input = "";

		// if user wants to play again
		do {
			System.out.println("\nWould You Like To Play Again? (Y)/(N)");

			input = scanner.nextLine();

			if (input.equals("y") || input.equals("Y")) {
				validInput = false;
				setGameEnd(false);
				gameReset();
			} else if (input.equals("n") || input.equals("N")) {
				validInput = false;
				setGameEnd(true);
			}
		} while (validInput);

	}

	public void gameReset() {
		// EMPTY THE HANDS
		// RESET GAME STATE
		for (int i = 0; i < getListOfPlayers().size(); i++) {

			for (int j = 0; j < 1; j++) {
				getListOfPlayers().get(i).getCardHand().removeAll(getListOfPlayers().get(i).getCardHand());
				getListOfPlayers().get(i).setDoubleDown(false);
				getListOfPlayers().get(i).setInsurance(false);
				getListOfPlayers().get(i).setTie(false);
				getListOfPlayers().get(i).setWin(false);
				getListOfPlayers().get(i).setBust(false);
				turnNumber = 0;
			}

		}
		

	}

	// displays all points
	public void pointsDisplayer() {
		// point displayer
		// title
		System.out.println("--------------- Points Displayer! ---------------");

		for (int i = 0; i < getListOfPlayers().size() - 1; i++) {
			System.out.println("\nPlayer " + (i + 1) + " Has " + getListOfPlayers().get(i).getPoints() + " Points.");
		}

	}

	// TODO insurance for players
	public void setInsurance() {

		// loop through the players
		// ask if they want insurance
		// scanner y or no
		// if they do want insurance then set insurance to true
		// -- else
		Scanner scanner = new Scanner(System.in);

		for (int i = 0; i < getListOfPlayers().size() - 1; i++) {

			boolean validResponse = false;
			String response = "";

			do {
				// ask player if they want insurance
				System.out.println("Dealer Is Showing Ace!");
				System.out.println("Does Player " + (i + 1) + " want insurance? (Y)/(N)");
				response = scanner.nextLine();

				if (response.equals("y") || response.equals("Y")) {
					getListOfPlayers().get(i).setInsurance(true);
					validResponse = true;
				} else if (response.equals("n") || response.equals("N")) {
					getListOfPlayers().get(i).setInsurance(false);
					validResponse = true;
				}

			} while (!validResponse);

		}

	}

}