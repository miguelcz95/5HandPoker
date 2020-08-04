package co.com.cardgame.core;

import co.com.cardgame.model.Player;

public class Game {

    public void runGame() {
        SetUp setUp = new SetUp();

        Player player = new Player();
        player.setHand(setUp.getHand());
        System.out.println("Player Hand: " + player.getHand().toString());

        Player robot = new Player();
        robot.setHand(setUp.getHand());
        System.out.println("Robot Hand: " + robot.getHand().toString());

    }

}
