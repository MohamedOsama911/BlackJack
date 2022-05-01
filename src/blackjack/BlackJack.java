package blackjack;

import java.util.Scanner;
import java.util.Random;

public class BlackJack {
    static Game g;

    public static void main(String args[]) {
        GUI gui = new GUI();
        g = new Game();
        g.setArrOfCards();
        g.setArrOfPlayers();
        gui.runGUI(g.getMyCardsDeck(), g.getMyPlayers()[0].myPlayerCards(),
                g.getMyPlayers()[1].myPlayerCards(), g.getMyPlayers()[2].myPlayerCards(),
                g.getMyPlayers()[3].myPlayerCards());
        g.readyToPlay(gui);
        g.finalResults();
    }

}
