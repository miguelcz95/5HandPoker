package co.com.cardgame.core;

import co.com.cardgame.model.Card;
import co.com.cardgame.model.Hand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.cardgame.resources.Constants.*;

public class SetUp {

    private final String[] cardSuits = {HEARTS, DIAMONDS, CLUBS, SPADES};
    List<Card> cardDeck;

    public SetUp() {
        this.cardDeck = fillDeck();
    }

    public List<Card> fillDeck() {
        List<Card> deck = new ArrayList<>();
        int cardNumber = 1;
        int cardSuit = 0;
        for (int i = 0; i < CARD_DECK_SIZE; i++) {
            deck.add(new Card(cardNumber, cardSuits[cardSuit]));
            cardNumber++;
            cardSuit++;
            if (cardNumber >= 14) {
                cardNumber = 1;
            }
            if (cardSuit >= 4) {
                cardSuit = 0;
            }
        }
        deck.sort(Comparator.comparing(Card::getNumber));
        return deck;
    }

    public Hand getHand() {
        Hand hand = new Hand();
        int cardCount = 0;
        for (int i = 0; i < CARD_HAND_SIZE; i++) {
            while (cardCount < CARD_HAND_SIZE) {
                Card card = getCard();
                if (validateIfCardExist(card)) {
                    if ((hand.getCard1() != card) && hand.getCard1() == null) {
                        hand.setCard1(card);
                        cardCount++;
                        cardDeck.remove(card);
                    } else if ((hand.getCard2() != card) && hand.getCard2() == null) {
                        hand.setCard2(card);
                        cardCount++;
                        cardDeck.remove(card);
                    } else if ((hand.getCard3() != card) && hand.getCard3() == null) {
                        hand.setCard3(card);
                        cardCount++;
                        cardDeck.remove(card);
                    } else if ((hand.getCard4() != card) && hand.getCard4() == null) {
                        hand.setCard4(card);
                        cardCount++;
                        cardDeck.remove(card);
                    } else if ((hand.getCard5() != card) && hand.getCard5() == null) {
                        hand.setCard5(card);
                        cardCount++;
                        cardDeck.remove(card);
                    }
                }
            }
        }
        return hand;
    }

    private boolean validateIfCardExist(Card card) {
        List<Card> filteredList = cardDeck.stream().filter(c -> c.getSuit().contains(card.getSuit())).collect(Collectors.toList());
        return filteredList.contains(card);
    }

    public Card getCard() {
        return new Card(getNumber(), getSuit());
    }

    public String getSuit() {
        int suitRandom = (int) (Math.random() * 3 + 1);
        return cardSuits[suitRandom];
    }

    public int getNumber() {
        return (int) (Math.random() * 13 + 1);
    }
}
