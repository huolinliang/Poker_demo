package leung.poker;


/**
 * Created by Leung on 2016/5/11.
 */
public class Player {
    String name;
    String[] card;

    public Player(String name) {
        this.name = name;
        card = new String[13];
    }

    public String[] getCard() {
        return card;
    }

    public void setCard(int index, String cardname) {
        card[index] = cardname;
    }

}
