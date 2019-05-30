package com.donald.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	// player has cardHard list (2 -> can be more)
	public boolean isDealer = false;
	public boolean doubleDown = false;
	public boolean insurance = false;
	
	public boolean win = false;
	public boolean tie = false;
	
	private List<Card> cardHand;
	// private CardHand cardHand;
	// player has a card SUM
	private int cardHandTotal;
	
	//PLAYER LOSS WIN TO KEEP TRACK
	private boolean bust = false;
	
	private int points;
	
	



	// constructor - IT NEEDS TO INITIALIZE THE CARDHAND	
	public Player() {
		cardHand = new ArrayList<>();
	}

	// getter and setter
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	
	public int getCardHandTotal() {
		return this.cardHandTotal;
	}

	// add logic here
	public void setCardHandTotal(int carHandTotal) {
		this.cardHandTotal = carHandTotal;
	}

	public List<Card> getCardHand() {
		return this.cardHand;
	}

	public void setCardHand(List<Card> cardHand) {
		this.cardHand = cardHand;
	}
	
	public boolean isBust() {
		return bust;
	}

	public void setBust(boolean bust) {
		this.bust = bust;
	}

	// methods
	// method to add cardTotal
	public void addCardTotal() {
		int total = 0;

		for (int i = 0; i < cardHand.size(); i++) {
			total += cardHand.get(i).getCardValue();
		}

		this.cardHandTotal = total;
	}

	// method to add hit functionality to player (should be a decision)
	public void playerHit(Card anotherCard) {

		// call dealAnotherCard method from Dealer class to get another card
		// ITS GOING TO RETURN A SINGLE CARD ->>
		// WE NEED TO ADD THIS CARD TO OUR LIST
		cardHand.add(anotherCard);

		// go through cardHand list and add get card total
		addCardTotal();
		
		System.out.println("Dealer dealt -> " + anotherCard.getCardFace() + " " + anotherCard.getCardSuit() + " " + anotherCard.getCardValue());

	}

	// TODO view cards
	// DEALER will probably need to override this
	// will need to add this to method functions after the functionalty methods
	// very end right before the return!
	public void handViewer() {

		// for every card in card hand say this
		for (int i = 0; i < cardHand.size(); i++) {
			System.out.println("Face: " + cardHand.get(i).getCardFace() + " Suit: " + cardHand.get(i).getCardSuit()
					+ " Value: " + cardHand.get(i).getCardValue());
		}
		// "you have an *ace* of *spades*"

	}
	
	//TODO MAKE CARD INSURANCE METHOD
	
	
	//TODO MAKE CARD SPLIT METHOD
	
	//TODO  MAKE CAR DOUBLE METHOD
	



	// add card to hand
	public void addCardToHand(Card card) {
		this.cardHand.add(card);
	}
	
//	public void removeCardFromHand(Card card) {
//		
//	}

	@Override
	public String toString() {
		return "Player [cardHand=" + cardHand + ", cardHandTotal=" + cardHandTotal + "]";
	}

}
