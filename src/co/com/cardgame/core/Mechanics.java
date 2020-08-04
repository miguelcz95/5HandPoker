package co.com.cardgame.core;

import co.com.cardgame.model.Hand;

import static co.com.cardgame.resources.Constants.*;

public class Mechanics {

    PokerHands pokerHands = new PokerHands();

    public String whoIsTheWinner(int playerScore, int robotScore, int playerHighCard, int robotHighCard) {
        if (evaluateWinner(playerScore, robotScore))
            return PLAYER_WINS;
        else {
            if (playerScore == robotScore) {
                if (evaluateTie(playerHighCard, robotHighCard)) {
                    return PLAYER_WINS;
                } else {
                    if (playerHighCard == robotHighCard) {
                        return TIE_GAME;
                    } else {
                        return ROBOT_WINS;
                    }
                }
            } else {
                return ROBOT_WINS;
            }
        }
    }

    private boolean evaluateTie(int playerHighCard, int robotHighCard) {
        return playerHighCard > robotHighCard;
    }

    private boolean evaluateWinner(int playerScore, int robotScore) {
        return playerScore < robotScore;
    }

    public int evaluateHands(Hand hand) {
        pokerHands.fillHand(hand);
        if (pokerHands.isRoyalFlush(hand)) {
            System.out.println(ROYAL_FLUSH);
            return 1;
        } else if (pokerHands.isStraightFlush(hand)) {
            System.out.println(STRAIGHT_FLUSH);
            return 2;
        } else if (pokerHands.isPoker(hand)) {
            System.out.println(POKER);
            return 3;
        } else if (pokerHands.isFullHouse(hand)) {
            System.out.println(FULLHOUSE);
            return 4;
        } else if (pokerHands.isFlush(hand)) {
            System.out.println(FLUSH);
            return 5;
        } else if (pokerHands.isStraight(hand)) {
            System.out.println(STRAIGHT);
            return 6;
        } else if (pokerHands.isThreeOfAKind(hand)) {
            System.out.println(THREE_OF_A_KIND);
            return 7;
        } else if (pokerHands.isTwoPair(hand)) {
            System.out.println(TWO_PAIRS);
            return 8;
        } else if (pokerHands.isOnePair(hand)) {
            System.out.println(ONE_PAIR);
            return 9;
        } else {
            System.out.println(CARD_HIGH);
            return 10;
        }
    }
}
