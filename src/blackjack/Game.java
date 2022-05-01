package blackjack;

import java.util.Random;
import java.util.Scanner;

public class Game {
    Scanner take = new Scanner(System.in);

    private Player[] arrOfPlayers = new Player[4]; // a
    private Card[] arrOfCards = new Card[52]; // b
    private int maxScore; // c

    // d
    private void createCardDeck(Card[] cards) {
        int j = 0;
        for (int i = 0; i < 13; i++) {
            if (j < 10)
                j++;
            cards[i] = new Card(0, i, j);
        }
        j = 0;
        for (int i = 13; i < 26; i++) {
            if (j < 10)
                j++;
            cards[i] = new Card(1, i-13, j);
        }
        j = 0;
        for (int i = 26; i < 39; i++) {
            if (j < 10)
                j++;
            cards[i] = new Card(2, i-26, j);
        }
        j = 0;
        for (int i = 39; i < 52; i++) {
            if (j < 10)
                j++;
            cards[i] = new Card(3, i-39, j);
        }
    }

    // d-1
    public void setArrOfCards() {
        createCardDeck(arrOfCards);
    }

    // e
    Card drawRandomCard(Card cards[]) {
        Card c;
        Random rand = new Random();
        while (true) {
            int randomChoice = rand.nextInt(52);
            if (cards[randomChoice] == null) {
                continue;
            } else {
                c = cards[randomChoice];
                cards[randomChoice] = null;
                break;
            }
        }
        return c;
    }

    // f
    private void intializPlayers(Player players[], Card cards[]) {// hena hadelo arrOfPlayers
        for (int i = 0; i < 4; i++) {
            System.out.println("Enter player " + (i + 1) + " name: ");
            String playerName = take.nextLine();
            Card c0 = drawRandomCard(cards);// bma any h3ml intialize le arrOfCards 2abl ma ast5dem el fun de so,
                                            // iam kinda safe
            Card c1 = drawRandomCard(cards);
            players[i] = new Player(playerName, c0, c1);
        }
    }
    // markup

    // f-1
    public void setArrOfPlayers() {
        intializPlayers(arrOfPlayers, arrOfCards);
    }

    // g
    private void updateMaxScore(Player p[]) {// hena hadelo el escore bta3 kol player get.maxscore
        int max = 0;
        for (int i = 0; i < 3; i++) {
            if (p[i].getScore() > max && p[i].getScore() <= 21) {
                max = p[i].getScore();
            }
        }

        this.maxScore = max;
    }

    // m1
    private void playingMechanism(Player players[], Card cards[], GUI gui) {
        for (int i = 0; i < 3; i++) {
            players[i].numOfDraws = 0;
            int counter = 1;
            while (true) {
                System.out.println("Hit or Stand?");
                String choice = take.nextLine();
                if (choice.equals("Hit")) {
                    Card c = drawRandomCard(cards);
                    players[i].numOfDraws++;
                    counter++;
                    players[i].addCard(c, counter);

                    gui.updatePlayerHand(c, i);
                    // lil test
                    // System.out.println(players[i].getScore());

                    if (players[i].getScore() == 21) {
                        players[i].setGotPlackJack(true);
                        break;
                    } else if (players[i].getScore() > 21) {
                        players[i].setiLost(true);// busted
                         players[i].setGotPlackJack(false);
                        break;
                    } else
                        continue;
                } else if (choice.equals("Stand")) {

                    // lil test
                    // System.out.println(players[i].getScore());

                    break;
                }
                // if (players[i].isGotPlackJack() == true || players[i].isiLost())
                // break;
            }
        }

        updateMaxScore(players);

        // lil test
        // System.out.println("max score = " + maxScore);

        players[3].numOfDraws = 0;
        int counter = 1;
        // dealer turn
        while (true) {
            if (players[3].getScore() > maxScore) {

                // lil test
                // System.out.println("ALREDY BIGGER THAN MAX SCORE " + players[3].getScore());

                break;
            } else {

                Card c = drawRandomCard(cards);
                players[3].numOfDraws++;
                counter++;
                players[3].addCard(c, counter);
                gui.updateDealerHand(c, arrOfCards);
                // lil test
                // System.out.println("value of the dealer hand " + players[3].getScore());

                if (players[3].getScore() == 21) {
                    players[3].setGotPlackJack(true);

                    // lil test
                    // System.out.println("equals 21" + players[3].getScore());

                    break;
                }
                if (players[3].getScore() > 21) {
                    players[3].setiLost(true);

                    // lil test
                    // System.out.println("BIGGER THAN 21 " + players[3].getScore());

                    break;
                }
            }
        }

    }

    // m1-1
    public void readyToPlay(GUI gui) {
        playingMechanism(arrOfPlayers, arrOfCards, gui);
    }

    // m2
    private void qualifying(Player p[]) {
        int winnerIndx = 0;
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (p[i].getScore() == maxScore) {// bashof kam wa7ed m3ah max score
                count++;
                winnerIndx = i;
            }
        }
        if (count > 1) {
            if (p[3].isiLost() == true)// aktar mn player m3ah same max score
            {
                System.out.println("PUSH!");
            } else if (p[3].isiLost() == false) {
                if (p[winnerIndx].isGotPlackJack()) {// hmmmmmmmmmmmmmm maybe there is a problem about winnerIndx
                    System.out.println("PUSH!");
                } else if (p[winnerIndx].getScore() < 21) {
                    System.out.println("player " + p[3].getName() + " WON");
                }

            }
        }
        if (count == 1) {
            if (p[3].isiLost() == true)// aktar mn player m3ah same max score
            {
                System.out.println("player " + p[winnerIndx].getName() + " WON");
            } else if (p[3].isiLost() == false) {
                if (p[winnerIndx].isGotPlackJack() && p[3].isGotPlackJack()) {
                    System.out.println("PUSH!");
                } else if (p[winnerIndx].isGotPlackJack() == false) {
                    System.out.println("player " + p[3].getName() + " WON");
                }
            }
        }

    }

    // m2-1
    public void finalResults() {
        qualifying(arrOfPlayers);
    }

    public Player[] getMyPlayers() {
        return arrOfPlayers;
    }

    public Card[] getMyCardsDeck() {
        return arrOfCards;
    }
}
