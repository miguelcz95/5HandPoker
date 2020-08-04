package co.com.cardgame.core;

import co.com.cardgame.model.Player;

import java.util.logging.Logger;

public class Game {


    final Logger LOGGER = Logger.getLogger(this.getClass().getName());

    public void runGame() {
        PokerHands pokerHands = new PokerHands();
        SetUp setUp = new SetUp();
        Mechanics mechanics = new Mechanics();

        Player player = new Player();
        player.setHand(setUp.getHand());
        System.out.println("Player Hand: " + player.getHand().toString());

        Player robot = new Player();
        robot.setHand(setUp.getHand());
        System.out.println("Robot Hand: " + robot.getHand().toString());

        int playerScore = mechanics.evaluateHands(player.getHand());
        int robotScore = mechanics.evaluateHands(robot.getHand());

        int playerHighCard = pokerHands.isHighCard(player.getHand());
        int robotHighCard = pokerHands.isHighCard(robot.getHand());

        System.out.println("player High Card: " + playerHighCard);
        System.out.println("robot High Card: " + robotHighCard);

        System.out.println(mechanics.whoIsTheWinner(playerScore, robotScore, playerHighCard, robotHighCard));

    }

}
