package co.com.cardgame.core;

import co.com.cardgame.model.Card;
import co.com.cardgame.model.Hand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static co.com.cardgame.resources.Constants.CARD_HAND_SIZE;

public class PokerHands {


    public List<Card> fillHand(Hand hand) {
        List<Card> cards = new ArrayList<>();
        cards.add(hand.getCard1());
        cards.add(hand.getCard2());
        cards.add(hand.getCard3());
        cards.add(hand.getCard4());
        cards.add(hand.getCard5());
        cards.sort(Comparator.comparing(Card::getNumber));
        return cards;
    }

    public boolean isRoyalFlush(Hand hand) {
        List<Card> listCard = fillHand(hand);
        List<Card> listColor = listCard.stream().filter(card -> card.getSuit().contains(listCard.get(0).getSuit())).collect(Collectors.toList());
        if (listColor.get(0).getNumber() == 1) {
            listColor.get(0).setNumber(14);
        }
        listColor.sort(Comparator.comparing(Card::getNumber));
        if (listColor.get(0).getNumber() == 10) {
            for (int i = 1; i < listColor.size(); i++) {
                if (listColor.get(i).getNumber() - 1 != listColor.get(i - 1).getNumber()) {
                    return false;
                }
            }
            return true;
        }
        if (listColor.get(0).getNumber() == 14) {
            listColor.get(0).setNumber(1);
        }
        return false;
    }

    public boolean isStraightFlush(Hand hand) {
        List<Card> listCard = fillHand(hand);
        List<Card> listColor = listCard.stream().filter(card -> card.getSuit().contains(listCard.get(0).getSuit())).collect(Collectors.toList());
        if (listColor.size() == CARD_HAND_SIZE) {
            for (int i = 1; i < listColor.size(); i++) {
                if (listColor.get(i).getNumber() - 1 != listColor.get(i - 1).getNumber()) {
                    return false;
                }
            }
            return true;
        }
        return false;

    }

    public boolean isPoker(Hand hand) {
        List<Card> listCard = fillHand(hand);
        List<Integer> listNum = listCard.stream().map(Card::getNumber).collect(Collectors.toList());
        int numAux2 = 0;
        for (int num : listNum) {
            int numAux = Collections.frequency(listNum, num);
            if (numAux >= numAux2) {
                numAux2 = numAux;
            }
        }
        return numAux2 == 4;
    }

    public boolean isFullHouse(Hand hand) {
        return (isOnePair(hand) && isThreeOfAKind(hand));
    }

    public boolean isFlush(Hand hand) {
        List<Card> listCard = fillHand(hand);
        List<Card> listColor = listCard.stream().filter(card -> card.getSuit().contains(listCard.get(0).getSuit())).collect(Collectors.toList());
        return listColor.size() == 5;
    }

    public boolean isStraight(Hand hand) {
        List<Card> listCard = fillHand(hand);
        List<Integer> listNum = listCard.stream().map(Card::getNumber).collect(Collectors.toList());
        if (listNum.size() == CARD_HAND_SIZE) {
            for (int i = 1; i < listNum.size(); i++) {
                if (listNum.get(i) - 1 != listNum.get(i - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isThreeOfAKind(Hand hand) {
        List<Card> listCard = fillHand(hand);
        List<Integer> listNum = listCard.stream().map(Card::getNumber).collect(Collectors.toList());
        int numAux2 = 0;
        for (int num : listNum) {
            int numAux = Collections.frequency(listNum, num);
            if (numAux >= numAux2) {
                numAux2 = numAux;
            }
        }
        return numAux2 == 3;
    }

    public boolean isTwoPair(Hand hand) {
        List<Card> listCard = fillHand(hand);
        int numAux = 0;
        int cont = 0;
        for (Card card : listCard) {
            if (card.getNumber() == numAux) {
                cont++;
            } else {
                numAux = card.getNumber();
            }
        }
        return cont == 2;
    }

    public boolean isOnePair(Hand hand) {
        List<Card> listCard = fillHand(hand);
        List<Integer> listNum = listCard.stream().map(Card::getNumber).collect(Collectors.toList());
        int numAux = 0;
        int numAux2 = 0;
        for (int num : listNum) {
            numAux = Collections.frequency(listNum, num);
            if (numAux == 2) {
                numAux2 = numAux;
            }
        }
        return numAux2 == 2;
    }

    public int isHighCard(Hand hand) {
        List<Card> cards = new ArrayList<>();
        cards.add(hand.getCard1());
        cards.add(hand.getCard2());
        cards.add(hand.getCard3());
        cards.add(hand.getCard4());
        cards.add(hand.getCard5());
        return Collections.max(cards.stream().map(Card::getNumber).collect(Collectors.toList()));
    }
}
