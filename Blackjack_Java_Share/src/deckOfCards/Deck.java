package deckOfCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

	private ArrayList<Card> deck;
	/* Remove this comment and implement this class */

	public Deck() { // default constructor
		deck = new ArrayList<>();

		Suit[] suits = Suit.values(); // creating an array with all suits
		Rank[] ranks = Rank.values(); // creating an array with all ranks

		for (int i = 0; i < suits.length; i++) {// looping thru each suit
			for (int k = 0; k < ranks.length; k++) { // looping thru each rank
				deck.add(new Card(ranks[k], suits[i])); // adding a new card with each possible case to deck
			}
		}
	}

	public void shuffle(Random randomNumberGenerator) {
		Collections.shuffle(deck, randomNumberGenerator);
	}

	public Card dealOneCard() {
		if (deck == null) {
			System.out.print("Deck is Null!");
		}
		
		Card c1 = deck.get(0);
		deck.remove(0);
		return c1;
	}

}
