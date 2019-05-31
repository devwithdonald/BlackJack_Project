package com.donald.game;

import java.util.ArrayList;
import java.util.List;

public class CardDeck {

	private final String[] cardSuitList = {"Clubs", "Diamonds", "Hearts", "Spades"};
	private final String[] cardFaceList = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};

	
	//needs to be static
	public static List<Card> deck = new ArrayList<>();
	
	//constructor
	//when make 1 card deck we need to populate the deck and make 52 card objects, with suit,face,value
	public CardDeck(){
		for(int i = 0; i < cardSuitList.length; i++) {
			for (int j = 0; j < cardFaceList.length; j++) {
				//calculate card value for each card
				if(j == 0) {
					deck.add(new Card(cardSuitList[i], cardFaceList[j],11));
				} else if (j < 9) {
					deck.add(new Card(cardSuitList[i], cardFaceList[j],j + 1));
				} else {
					deck.add(new Card(cardSuitList[i], cardFaceList[j],10));
				}
			}
		}
	}
	
	
}
