package com.donald.game;

public class Card {

	private String cardFace;
	private String cardSuit;
	private int cardValue;

	private boolean inPlay = false;


	// get card values later
	public Card() {

	}

	public Card(String cardSuit, String cardFace, int cardValue) {
		this.cardSuit = cardSuit;
		this.cardFace = cardFace;
		this.cardValue = cardValue;

	}

	// getters and settors
	public String getCardFace() {
		return cardFace;
	}

	public void setCardFace(String cardFace) {
		this.cardFace = cardFace;
	}

	public String getCardSuit() {
		return cardSuit;
	}

	public void setCardSuit(String cardSuit) {
		this.cardSuit = cardSuit;
	}

	public int getCardValue() {
		return this.cardValue;
	}

	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}

	public boolean getInPlay() {
		return this.inPlay;
	}

	public void setInPlay(boolean inPlay) {
		this.inPlay = inPlay;
	}

	// toString
	@Override
	public String toString() {
		return "Card [cardFace=" + cardFace + ", cardSuit=" + cardSuit + ", cardValue=" + cardValue + ", inPlay="
				+ inPlay + "]";
	}

}
