package co.com.cardgame.model;

import java.util.Objects;

public class Player {

    private Hand hand;

    public Player() {
    }

    public Player(Hand hand) {
        this.hand = hand;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(hand, player.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand);
    }
}
