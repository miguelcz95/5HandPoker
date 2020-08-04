package co.com.cardgame.core;

import co.com.cardgame.model.Card;
import co.com.cardgame.model.Hand;
import co.com.cardgame.model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static co.com.cardgame.resources.Constants.*;

public class Game {


    private final String[] cardSuits = {HEARTS, DIAMONDS, CLUBS, SPADES};

    List<Card> cardDeck = new ArrayList<>();
    PokerHands pokerHands = new PokerHands();

    final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public void runGame() {
        cardDeck = fillDeck();


        Player player = new Player();
        player.setHand(getHand());
        System.out.println("Player Hand: " + player.getHand().toString());

        Player robot = new Player();
        robot.setHand(getHand());
        System.out.println("Robot Hand: " + robot.getHand().toString());

        int playerScore = evaluateHands(player.getHand());
        int robotScore = evaluateHands(robot.getHand());

        int playerHighCard = pokerHands.isHighCard(player.getHand());
        int robotHighCard = pokerHands.isHighCard(robot.getHand());

        System.out.println("player High Card: " + playerHighCard);
        System.out.println("robot High Card: " + robotHighCard);

        System.out.println(whoIsTheWinner(playerScore, robotScore, playerHighCard, robotHighCard));

    }

    public String whoIsTheWinner(int playerScore, int robotScore, int playerHighCard, int robotHighCard) {
        if (evaluateWinner(playerScore, robotScore))
            return "The player Wins";
        else {
            if (playerScore == robotScore) {
                if (evaluateTie(playerHighCard, robotHighCard)) {
                    return "The player wins";
                } else {
                    if (playerHighCard == robotHighCard) {
                        return "it's a tie game";
                    } else {
                        return "The robot wins";
                    }
                }
            } else {
                return "The robot Wins";
            }
        }
    }

    private boolean evaluateTie(int playerHighCard, int robotHighCard) {
        return playerHighCard > robotHighCard;
    }

    private boolean evaluateWinner(int playerScore, int robotScore) {
        return playerScore < robotScore;
    }

    private int evaluateHands(Hand hand) {
        pokerHands.fillHand(hand);
        if (pokerHands.isRoyalFlush(hand)) {
            System.out.println("Royal Flush");
            return 1;
        } else if (pokerHands.isStraightFlush(hand)) {
            System.out.println("Straight Flush");
            return 2;
        } else if (pokerHands.isPoker(hand)) {
            System.out.println("Poker");
            return 3;
        } else if (pokerHands.isFullHouse(hand)) {
            System.out.println("Fullhouse");
            return 4;
        } else if (pokerHands.isFlush(hand)) {
            System.out.println("Flush");
            return 5;
        } else if (pokerHands.isStraight(hand)) {
            System.out.println("Straight");
            return 6;
        } else if (pokerHands.isThreeOfAKind(hand)) {
            System.out.println("Three of a kind");
            return 7;
        } else if (pokerHands.isTwoPair(hand)) {
            System.out.println("Two Pairs");
            return 8;
        } else if (pokerHands.isOnePair(hand)) {
            System.out.println("One Pair");
            return 9;
        } else {
            System.out.println("Card High");
        }
        return 0;
    }

    public List<Card> fillDeck() {
        List<Card> deck = new ArrayList<>();
        int cardNumber = 1;
        int cardSuit = 0;
        for (int i = 0; i < 52; i++) {
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
        deck.sort(Comparator.comparing(card -> card.getNumber()));
        return deck;
    }

    public Hand getHand() {
        Hand hand = new Hand();
        int cardCount = 0;
        for (int i = 0; i < 5; i++) {
            while (cardCount < 5) {
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
        List<Card> sortedList = cardDeck.stream().filter(c -> c.getSuit().contains(card.getSuit())).collect(Collectors.toList());
        return sortedList.contains(card);
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
