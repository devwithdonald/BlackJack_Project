package com.donald.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//all dealer are players but not all players are not dealers
public class Dealer extends Player {


	
	//method
	public Card dealAnotherCard() {

		Card card = new Card();

		// card randomizer
		Random cardRandomizer = new Random();

		// do this 1 time -> only need 1 card
		for (int i = 0; i < 1; i++) {

			// add cards that arent in play to a new list --> ensure cards in play are not
			// used THEN randomize from that NEW list
			List<Card> tempCardHandList = new ArrayList<>();

			// changed CardDeck
			for (int j = 0; j < CardDeck.deck.size(); j++) {

				if (CardDeck.deck.get(j).getInPlay() == false) {

					tempCardHandList.add(CardDeck.deck.get(j));

				}
			}

			// new randomizer ->
			Card cardPicker = tempCardHandList.get(cardRandomizer.nextInt(tempCardHandList.size()));

			cardPicker.setInPlay(true);
			card = cardPicker;
		}

		return card;
	}

	
	//passing handView
	@Override
	public void handViewer() {
		// if turnNumber is 0 then show one card
		// if turnNumber is 1 then show two
		if (GameManager.turnNumber == 0) {
			// only show one card
			System.out.println("Face: " + getCardHand().get(0).getCardFace() + " Suit: "
					+ getCardHand().get(0).getCardSuit() + " Value: " + getCardHand().get(0).getCardValue());
			System.out.println("1 Card Hidden");
		} else {
			super.handViewer();
		}
	}

}
