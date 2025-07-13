package blackjack;

import java.util.ArrayList;
import java.util.Random;

import deckOfCards.*;

public class BlackjackModel {

	/* Remove this comment and implement this class */

	private ArrayList<Card> dealerCards;
	private ArrayList<Card> playerCards;
	private Deck deck;

	public ArrayList<Card> getDealerCards() {
		ArrayList<Card> dealerCards2 = new ArrayList<>();
		for (Card card1 : dealerCards) {
			dealerCards2.add(card1);
		}

		return dealerCards2;

	}

	public ArrayList<Card> getPlayerCards() {
		ArrayList<Card> playerCards2 = new ArrayList<>();
		for (Card card1 : playerCards) {
			playerCards2.add(card1);
		}

		return playerCards2;
	}

	public void setDealerCards(ArrayList<Card> cards) {
		if (cards != null) {
			this.dealerCards = new ArrayList<Card>(cards);
		}

	}

	public void setPlayerCards(ArrayList<Card> cards) {
		if (cards != null) {
			this.playerCards = new ArrayList<Card>(cards);
		}
	}

	public void createAndShuffleDeck(Random random) {
		deck = new Deck();
		deck.shuffle(random);
	}

	public void initialDealerCards() {
		dealerCards = new ArrayList<>();
		for (int index = 0; index < 2; index++) {
			dealerCards.add(deck.dealOneCard()); // adding 2 cards from the deck to dealer cards
		}
	}

	public void initialPlayerCards() {
		playerCards = new ArrayList<>();
		for (int index = 0; index < 2; index++) {
			playerCards.add(deck.dealOneCard()); // adding 2 cards from the deck to player cards
		}

	}

	public void playerTakeCard() {
		playerCards.add(deck.dealOneCard());
	}

	public void dealerTakeCard() {
		dealerCards.add(deck.dealOneCard());
	}

	public static ArrayList<Integer> possibleHandValues(ArrayList<Card> hand) {
		int sum = 0;// representing value of hand
		int sum2 = 0; // representing second possible value if ace is present
		boolean ace = false;
		ArrayList<Integer> possibleValues = new ArrayList<>();

		for (Card card : hand) {// loop thru hand
			if (card.getRank() == Rank.ACE) { // if there is an ace in hand
				ace = true;
				sum += 1; // handling case where ace is 1
			} else {
				sum += card.getRank().getValue(); // get value of rank from each card in hand and adding them all up
			}
		}

		possibleValues.add(sum);

		if (ace == true) {
			sum2 = sum;
			sum2 += 10; // handling case where ace is 11
			if (sum2 <= 21) {
				possibleValues.add(sum2); // only adds this sum as a possible value if the total sum is <= 21 (valid)
			}
		}

		return possibleValues;

	}

	public static HandAssessment assessHand(ArrayList<Card> hand) {

		if (hand == null || hand.size() < 2) {
			return HandAssessment.INSUFFICIENT_CARDS;
		}

		ArrayList<Integer> values = possibleHandValues(hand);

		if (hand.size() == 2 && values.get(0) == 11 && values.get(1) == 21) { // if there are only 2 cards in hand and
																				// value equals 21
			return HandAssessment.NATURAL_BLACKJACK;
		}

		else if (values.get(0) > 21) {
			return HandAssessment.BUST;
		}

		return HandAssessment.NORMAL;
	}

	public GameResult gameAssessment() {
		if (assessHand(playerCards) == HandAssessment.NATURAL_BLACKJACK) {
			if (assessHand(dealerCards) == HandAssessment.NATURAL_BLACKJACK) {
				return GameResult.PUSH;
			} else {
				return GameResult.NATURAL_BLACKJACK; // dealer does not have a Natural black jack
			}
		}

		else if (assessHand(playerCards) == HandAssessment.BUST) {
			return GameResult.PLAYER_LOST;
		}

		else if (assessHand(playerCards) != HandAssessment.BUST && assessHand(dealerCards) == HandAssessment.BUST) {
			return GameResult.PLAYER_WON;
		} else if (assessHand(dealerCards) == HandAssessment.NATURAL_BLACKJACK
				&& possibleHandValues(playerCards).contains(21)) {
			return GameResult.PUSH;
		}

		else {
			ArrayList<Integer> player = possibleHandValues(playerCards);
			ArrayList<Integer> dealer = possibleHandValues(dealerCards);

			if (handValue(player) < handValue(dealer)) { // comparing highest value hands
				return GameResult.PLAYER_LOST;
			} else if (handValue(player) > handValue(dealer)) {
				return GameResult.PLAYER_WON;
			} else {
				return GameResult.PUSH;
			}

		}

	}

	private int handValue(ArrayList<Integer> hand) {
		int max = 0;
		for (int value : hand) {
			if (value > max) {
				max = value;
			}
		}

		return max;
	}

	public boolean dealerShouldTakeCard() {
		ArrayList<Integer> dealerValue = possibleHandValues(dealerCards);
		int highestDealer = 0; // keep track of highest value of dealer's cards
		for (int value : dealerValue) {
			if (value > highestDealer) {
				highestDealer = value;
			}
		}

		if (dealerValue.contains(7) && dealerValue.contains(17)) { // ace in hand
			return true;
		} else if (!dealerValue.contains(7) && dealerValue.contains(17)) {
			return false;
		} else if (highestDealer <= 16) {
			return true;
		} else {// greater than 16 & hasn't met other conditions, it must be 18 or greater
			return false;
		}

	}

}
