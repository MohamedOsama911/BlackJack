package blackjack;

public class Player {
    private String Name;
    private int Score;
    public int numOfDraws = 0;
    private Card[] arrOfPlayerCards = new Card[11];
    private boolean gotPlackJack;
    private boolean iLost;

    Player(String Name, Card c0, Card c1) {
        this.Name = Name;
        arrOfPlayerCards[0] = c0;
        arrOfPlayerCards[1] = c1;
    }

    public String getName() {
        return Name;
    }

    public boolean isiLost() {
        return iLost;
    }

    public void setiLost(boolean iLost) {
        this.iLost = iLost;
    }

    public boolean isGotPlackJack() {
        return gotPlackJack;
    }

    public void setGotPlackJack(boolean gotPlackJack) {
        this.gotPlackJack = gotPlackJack;
    }

    public int getScore() {
        int sum = 0;
        for (int i = 0; i < numOfDraws + 2; i++) {
            sum = sum + arrOfPlayerCards[i].getValue();
        }
        this.Score = sum;
        return this.Score;
    }

    void addCard(Card c, int i) {
        arrOfPlayerCards[i] = c;
    }

    public Card[] myPlayerCards() {
        return arrOfPlayerCards;
    }
    

}
