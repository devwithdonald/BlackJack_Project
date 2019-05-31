package com.donald.game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	// player has cardHard list (2 -> can be more)

	private List<Card> cardHand;
	
	private boolean dealer = false;
	private boolean doubleDown = false;
	private boolean insurance = false;
	private boolean win = false;
	private boolean tie = false;

	// player has a card SUM
	private int cardHandTotal;
	// PLAYER LOSS WIN TO KEEP TRACK
	private boolean bust = false;
	private int points;
	
	// constructor - needs to initialize the cardHand
	public Player() {
		cardHand = new ArrayList<>();
	}

	

	// getter and setters

	public boolean isTie() {
		return tie;
	}

	public void setTie(boolean tie) {
		this.tie = tie;
	}
	
	public boolean isWin() {
		return win;
	}

	public void setWin(boolean win) {
		this.win = win;
	}

	public boolean isInsurance() {
		return insurance;
	}

	public void setInsurance(boolean insurance) {
		this.insurance = insurance;
	}

	public boolean isDoubleDown() {
		return doubleDown;
	}

	public void setDoubleDown(boolean doubleDown) {
		this.doubleDown = doubleDown;
	}

	public boolean isDealer() {
		return dealer;
	}

	public void setDealer(boolean dealer) {
		this.dealer = dealer;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public int getCardHandTotal() {
		return this.cardHandTotal;
	}

	
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

		System.out.println("\nDealer Dealt -> " + anotherCard.getCardFace() + " " + anotherCard.getCardSuit() + " "
				+ anotherCard.getCardValue());

	}

	public void handViewer() {

		// for every card in card hand say this
		for (int i = 0; i < cardHand.size(); i++) {
			System.out.println("Face: " + cardHand.get(i).getCardFace() + " Suit: " + cardHand.get(i).getCardSuit()
					+ " Value: " + cardHand.get(i).getCardValue());
		}
		// "you have an *ace* of *spades*"

	}

	// TODO MAKE CARD INSURANCE METHOD

	// TODO MAKE CARD SPLIT METHOD

	// TODO MAKE CARD DOUBLE METHOD

	
	// add card to hand
	public void addCardToHand(Card card) {
		this.cardHand.add(card);
	}

	@Override
	public String toString() {
		return "Player [cardHand=" + cardHand + ", cardHandTotal=" + cardHandTotal + "]";
	}

}
