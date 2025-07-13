package tests;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import deckOfCards.Card;
import deckOfCards.Rank;
import deckOfCards.Suit;
import blackjack.BlackjackModel;
import blackjack.GameResult;
import blackjack.HandAssessment;

public class StudentTest {

	@Test
	public void possHands() {
		ArrayList<Card> hand = new ArrayList<>();
		Card c1 = new Card(Rank.TWO, Suit.HEARTS);
		Card c2 = new Card(Rank.THREE, Suit.DIAMONDS);
		Card c3 = new Card(Rank.FOUR, Suit.DIAMONDS);
		hand.add(c1);
		hand.add(c2);
		hand.add(c3);

		ArrayList<Integer> nums = new ArrayList<>();
		nums.add(1);
		nums.add(2);
		nums.add(3);

		assertTrue(BlackjackModel.possibleHandValues(hand).get(0) == 9);
		// System.out.println(BlackjackModel.possibleHandValues(hand));
		// System.out.println(nums);
		// System.out.print(c1.getRank().getValue());

		ArrayList<Card> hand1 = new ArrayList<>();
		Card c4 = new Card(Rank.ACE, Suit.HEARTS);
		Card c5 = new Card(Rank.TWO, Suit.DIAMONDS);
		Card c6 = new Card(Rank.THREE, Suit.DIAMONDS);
		hand1.add(c4);
		hand1.add(c5);
		hand1.add(c6);
		// System.out.println(BlackjackModel.possibleHandValues(hand1));
		assertTrue(BlackjackModel.possibleHandValues(hand1).get(0) == 6
				&& BlackjackModel.possibleHandValues(hand1).get(1) == 16);

		ArrayList<Card> hand2 = new ArrayList<>();
		Card c7 = new Card(Rank.ACE, Suit.HEARTS);
		Card c8 = new Card(Rank.SIX, Suit.DIAMONDS);
		Card c9 = new Card(Rank.FIVE, Suit.DIAMONDS);
		hand2.add(c7);
		hand2.add(c8);
		hand2.add(c9);
		assertTrue(BlackjackModel.possibleHandValues(hand2).get(0) == 12);
		// System.out.println(BlackjackModel.possibleHandValues(hand2));
		ArrayList<Card> hand3 = new ArrayList<>();
		Card c10 = new Card(Rank.ACE, Suit.HEARTS);
		Card c11 = new Card(Rank.EIGHT, Suit.DIAMONDS);
		Card c12 = new Card(Rank.FOUR, Suit.DIAMONDS);
		Card c13 = new Card(Rank.QUEEN, Suit.DIAMONDS);
		hand3.add(c10);
		hand3.add(c11);
		hand3.add(c12);
		hand3.add(c13);
		// System.out.println(BlackjackModel.possibleHandValues(hand3));
		assertTrue(BlackjackModel.possibleHandValues(hand3).get(0) == 23);

	}

	@Test
	public void asessHands() {
		ArrayList<Card> playerHand = new ArrayList<>();
		Card c1 = new Card(Rank.ACE, Suit.HEARTS);
		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		playerHand.add(c1);
		playerHand.add(c2);

		// System.out.println(BlackjackModel.assessHand(playerHand));
		assertTrue(BlackjackModel.assessHand(playerHand) == HandAssessment.NATURAL_BLACKJACK);

		ArrayList<Card> hand = new ArrayList<>();
		Card c3 = new Card(Rank.SEVEN, Suit.HEARTS);
		Card c4 = new Card(Rank.SEVEN, Suit.DIAMONDS);
		Card c5 = new Card(Rank.SEVEN, Suit.DIAMONDS);
		hand.add(c3);
		hand.add(c4);
		hand.add(c5);
		assertTrue(BlackjackModel.assessHand(hand) == HandAssessment.NORMAL);

		ArrayList<Card> hand1 = new ArrayList<>();
		Card c6 = new Card(Rank.JACK, Suit.HEARTS);
		Card c7 = new Card(Rank.KING, Suit.DIAMONDS);
		Card c8 = new Card(Rank.QUEEN, Suit.DIAMONDS);
		hand1.add(c6);
		hand1.add(c7);
		hand1.add(c8);
		//System.out.println(BlackjackModel.possibleHandValues(hand1));
		assertTrue(BlackjackModel.assessHand(hand1) == HandAssessment.BUST);

		
		ArrayList<Card> hand2 = new ArrayList<>();
		Card c9 = new Card(Rank.ACE, Suit.HEARTS);
		Card c10 = new Card(Rank.ACE, Suit.DIAMONDS);
		hand2.add(c9);
		hand2.add(c10);
		assertTrue(BlackjackModel.assessHand(hand2) == HandAssessment.NORMAL);
		
		
		ArrayList<Card> hand3 = new ArrayList<>();
		Card c11 = new Card(Rank.TWO, Suit.HEARTS);
		Card c12 = new Card(Rank.FIVE, Suit.DIAMONDS);
		Card c13 = new Card(Rank.ACE, Suit.DIAMONDS);
		hand3.add(c11);
		hand3.add(c12);
		hand3.add(c13);
		
		//System.out.println(BlackjackModel.possibleHandValues(hand3));
		assertTrue(BlackjackModel.assessHand(hand3) == HandAssessment.NORMAL);
		
	}

	@Test
	public void gameResult1() { //both dealer and player have natural blackjack

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.ACE, Suit.HEARTS);
		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		playerCards.add(c1);
		playerCards.add(c2);

		dealerCards.add(c1);
		dealerCards.add(c2);

		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
			
		
	}
	
	@Test
	public void gameResult() { // player has natural blackjack 

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.ACE, Suit.HEARTS);
		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		playerCards.add(c1);
		playerCards.add(c2);

		dealerCards.add(c1);
		dealerCards.add(c1);

		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.NATURAL_BLACKJACK);
			
		
	}

	@Test
	public void gameResult3() { //player has a bust

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.QUEEN, Suit.HEARTS);
		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		Card c3 = new Card(Rank.SEVEN, Suit.HEARTS);
	
		playerCards.add(c1);
		playerCards.add(c2);
		playerCards.add(c3);

		dealerCards.add(c1);
		dealerCards.add(c2);

		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_LOST);
			
		
	}

	
	@Test
	public void gameResult4() { //player doesn't have a bust, but dealer does

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.QUEEN, Suit.HEARTS);
		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		Card c3 = new Card(Rank.SEVEN, Suit.HEARTS);
	
		playerCards.add(c1);
		playerCards.add(c2);

		dealerCards.add(c1);
		dealerCards.add(c2);
		dealerCards.add(c3);
		
		BlackjackModel game = new BlackjackModel();
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
			
		
	}
	
	
	@Test
	public void gameResult5() { //comparing value of cards to determine winner

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.ACE, Suit.HEARTS);
		Card c2 = new Card(Rank.TWO, Suit.DIAMONDS);
		Card c3 = new Card(Rank.SEVEN, Suit.HEARTS);
	
		playerCards.add(c1);
		playerCards.add(c2);
		playerCards.add(c3);

		dealerCards.add(c1);
		dealerCards.add(c3);
		
		
	//	System.out.println(BlackjackModel.possibleHandValues(playerCards));
	//	System.out.println(BlackjackModel.possibleHandValues(dealerCards));
		BlackjackModel game = new BlackjackModel();
		
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
			
		
	}

	
	
	
	@Test
	public void gameResult6() { 

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.ACE, Suit.HEARTS);
		Card c2 = new Card(Rank.TWO, Suit.DIAMONDS);
		Card c3 = new Card(Rank.SEVEN, Suit.HEARTS);
	
		playerCards.add(c1);
		playerCards.add(c2);
		playerCards.add(c3);

		dealerCards.add(c1);
		dealerCards.add(c2);
		dealerCards.add(c3);
		
		
		//System.out.println(BlackjackModel.possibleHandValues(playerCards));
		//System.out.println(BlackjackModel.possibleHandValues(dealerCards));
		BlackjackModel game = new BlackjackModel();
		
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
		
	}
	
	
	@Test
	public void gameResult7() { // dealer has natural black jack, player has 21 but not black jack

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.ACE, Suit.HEARTS);
		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		Card c3 = new Card(Rank.SEVEN, Suit.HEARTS);
		Card c4 = new Card(Rank.THREE, Suit.HEARTS);
	
		playerCards.add(c1);
		playerCards.add(c3);
		playerCards.add(c4);

		dealerCards.add(c1);
		dealerCards.add(c2);
		
		
		//System.out.println(BlackjackModel.possibleHandValues(playerCards));
		//System.out.println(BlackjackModel.possibleHandValues(dealerCards));
		BlackjackModel game = new BlackjackModel();
		
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.PUSH);
		
	}
	
	@Test
	public void gameResult8() { 

		ArrayList<Card> playerCards = new ArrayList<>();
		ArrayList<Card> dealerCards = new ArrayList<>();

		Card c1 = new Card(Rank.ACE, Suit.HEARTS);
		Card c2 = new Card(Rank.KING, Suit.DIAMONDS);
		Card c3 = new Card(Rank.SEVEN, Suit.HEARTS);
		Card c4 = new Card(Rank.THREE, Suit.HEARTS);
	
		playerCards.add(c1);
		playerCards.add(c3);
		playerCards.add(c4);

		dealerCards.add(c2);
		dealerCards.add(c2);
		
		
		//System.out.println(BlackjackModel.possibleHandValues(playerCards));
		//System.out.println(BlackjackModel.possibleHandValues(dealerCards));
		BlackjackModel game = new BlackjackModel();
		
		game.setDealerCards(dealerCards);
		game.setPlayerCards(playerCards);
		assertTrue(game.gameAssessment() == GameResult.PLAYER_WON);
		
		
	}

}
