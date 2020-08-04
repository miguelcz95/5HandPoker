package co.com.cardgame.model;

import java.util.Objects;

public class Hand {

    Card card1;
    Card card2;
    Card card3;
    Card card4;
    Card card5;

    public Hand() {
    }

    public Hand(Card card1, Card card2, Card card3, Card card4, Card card5) {
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;
    }

    public Card getCard1() {
        return card1;
    }

    public void setCard1(Card card1) {
        this.card1 = card1;
    }

    public Card getCard2() {
        return card2;
    }

    public void setCard2(Card card2) {
        this.card2 = card2;
    }

    public Card getCard3() {
        return card3;
    }

    public void setCard3(Card card3) {
        this.card3 = card3;
    }

    public Card getCard4() {
        return card4;
    }

    public void setCard4(Card card4) {
        this.card4 = card4;
    }

    public Card getCard5() {
        return card5;
    }

    public void setCard5(Card card5) {
        this.card5 = card5;
    }

    @Override
    public String toString() {
        return " | " + card1 +
                " | " + card2 +
                " | " + card3 +
                " | " + card4 +
                " | " + card5 + " |";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand = (Hand) o;
        return Objects.equals(card1, hand.card1) &&
                Objects.equals(card2, hand.card2) &&
                Objects.equals(card3, hand.card3) &&
                Objects.equals(card4, hand.card4) &&
                Objects.equals(card5, hand.card5);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card1, card2, card3, card4, card5);
    }
}
