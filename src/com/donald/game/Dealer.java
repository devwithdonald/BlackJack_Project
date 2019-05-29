package com.donald.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

//all dealer are players but not all players are not dealers
public class Dealer extends Player {

	//public boolean isDealer = true;
	// has cardHand

	// has cardDeck

	// TODO have to create a dealSelf method - to ensure one card is hidden

	// maybe interface? // DEALING PLAY INITIALLY STARTING WITH NO HAND BUT CREATING
	// A HAND
	// CONSTRUCTURE

	// might need a CardDeck deck parameter

	// changed return type from List<Card>
	public List<Card> dealPlayer() {

		// cardHand we are returning
		// TODO CHANGED THIS FROM List<Card>
		// List<Card> cardHandList = new ArrayList<>();
		List<Card> cardHandList = new ArrayList<>();

		// card randomizer
		Random cardRandomizer = new Random();

		// temp list
		// List<Card> tempCardHandList = new ArrayList<>();

		// set
		// Set<Card> tempCardHandList1 = new HashSet<>();

		
		//INITIALLY DEAL PLAYER??
		//ONLY CALL THIS IF CARD HAND LIST IS LESS THAN 2
		//THIS SHOULD ONLY RUN ONCE AND RETURN ONE SINGLE CARD
		
		for (int i = 0; i < 2; i++) {
			// add random

			// add cards that arent in play to a new list --> ensure cards in play are not
			// used THEN randomize from that NEW list

			List<Card> tempCardHandList = new ArrayList<>();

			// changed CardDeck
			for (int j = 0; j < CardDeck.deck.size(); j++) {
				// only add if card hand is not in play && if the card object is not already in
				// there
				if (CardDeck.deck.get(j).getInPlay() == false){ //&& !tempCardHandList.contains(CardDeck.deck.get(j))) {
					tempCardHandList.add(CardDeck.deck.get(j));
				}
			}

			System.out.println("tempCardHandList:" + tempCardHandList.size());
			// THEN GET RANDOMIZER

			// old randomizer ->
			// Card cardPicker =
			// CardDeck.deck.get(cardRandomizer.nextInt(CardDeck.deck.size()));

			// new randomizer ->
			Card cardPicker = tempCardHandList.get(cardRandomizer.nextInt(tempCardHandList.size()));

			// need to check in play

			// DONT NEED IF STATEMENT ----------------------------------
			// if (!cardPicker.getInPlay()) {
			cardHandList.add(cardPicker);
			//
			//
			//
			cardPicker.setInPlay(true);
			// }
		}

		
		return cardHandList;
	}

	// TODO ---> NEED TO UNCOMMENT
	// give card & THEN CALL ADD VALUE METHOD? OR MAKE ANOTHER ONE IN GAME MANAGER,
	// OR IN PLAYER CLASS
	
	//STATIC SINCE ONLY 1 DEALER?
	public static Card dealAnotherCard() {

		// TODO NEED TO RETURN JUST 1 CARD
		// TODO THIS MAY B WRONG
		Card card = new Card();

		// card randomizer
		Random cardRandomizer = new Random();

		
		
		// do this 1 time -> only need 1 card
		for (int i = 0; i < 1; i++) {

			
			
			// add cards that arent in play to a new list --> ensure cards in play are not
			// used THEN randomize from that NEW list
			List<Card> tempCardHandList = new ArrayList<>();

			// changed CardDeck
			//running twice?
			for (int j = 0; j < CardDeck.deck.size(); j++) {
				// only add if card hand is not in play && if the card object is not already in
				// there
				// TODO MIGHT NOT NEED SECOND IF CHECK SINCE ONLY GOING THROUGH IT ONCE?
				if (CardDeck.deck.get(j).getInPlay() == false) { //&& !tempCardHandList.contains(CardDeck.deck.get(j))) {
					tempCardHandList.add(CardDeck.deck.get(j));
				}
			}

			// THEN GET RANDOMIZER

			// old randomizer ->
			// Card cardPicker =
			// CardDeck.deck.get(cardRandomizer.nextInt(CardDeck.deck.size()));

			// new randomizer ->
			Card cardPicker = tempCardHandList.get(cardRandomizer.nextInt(tempCardHandList.size()));

			// need to check in play

			// DONT NEED IF STATEMENT ----------------------------------
			// if (!cardPicker.getInPlay()) {

			// TODO? ISTHIS NEEDEDTO CHANGE THE STATE
			// cardHandList.add(cardPicker);
			//
			//
			//
			// cardPicker.setInPlay(true);

//			}
			cardPicker.setInPlay(true);
			card = cardPicker;
		}
		
		return card;
	}
	
	
	//TODO OVERRIDE THE VIEW -> BECAUSE DEALER CAN ONLY VIEW IF TURN IS 0
	@Override
	public void handViewer() {
		//if turnNumber is 0 then show one card 
		//if turnNumber is 1 then show two 
		if(GameManager.turnNumber == 0) {
			//only show one card
			System.out.println("Face: " + getCardHand().get(0).getCardFace() + " Suit: " + getCardHand().get(0).getCardSuit() + " Value: " + getCardHand().get(0).getCardValue());
			System.out.println("1 Card Hidden");
		} else {
			super.handViewer();
		}
	}
	
	//dealer has different functionality of hitting 
	//@Override
	//public void playerHit(Card card) {
		
	//}
	

	
	

}
