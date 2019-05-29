package com.donald.game;

import java.util.ArrayList;
import java.util.List;

public abstract class Participant {

	//for the initial deal - will need to reset this every game
	private int cardCount;
	//to keep track of the value of each card given - will need to reset this every game 
	private int cardSum;
	//list of cards to pass to gamemanager to get back card value
	List<Integer> cards = new ArrayList();
	
	
	//if cardCount == 0 fill card1 
	//else fill card == 1
	
}
