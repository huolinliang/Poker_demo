package leung.poker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "leungadd";
    private String[] color = {"♦", "♥", "♠", "♣"};
    private String[] cardValue = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
    private String[] card;
    private TextView player1;
    private TextView player2;
    private TextView player3;
    private TextView player4;
    private Button btn1;
    private Player AA;
    private Player BB;
    private Player CC;
    private Player DD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = (TextView) findViewById(R.id.player1);
        player2 = (TextView) findViewById(R.id.player2);
        player3 = (TextView) findViewById(R.id.player3);
        player4 = (TextView) findViewById(R.id.player4);
        btn1 =(Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Roll();
            }
        });
        AA = new Player("AA");
        BB = new Player("BB");
        CC = new Player("CC");
        DD = new Player("DD");
        card = new String[52];
        //生成所有牌
        for(int i = 0; i<color.length; i++) {
            for(int j = 0; j<cardValue.length; j++) {
                card[i*13 +j] = color[i]+cardValue[j];
            }
        }
        //发牌
        Roll();
    }

    public void Roll() {
        int[] randomarray_first = getRandomArray();
        int[] randomarray_second = getRandomArray();
        for(int i = 0; i < 13; i++) {
            //使用两重随机，尽量打乱每个人获得的牌
            AA.setCard(i, card[randomarray_first[randomarray_second[i]]]);
            BB.setCard(i, card[randomarray_first[randomarray_second[13+i]]]);
            CC.setCard(i, card[randomarray_first[randomarray_second[13*2+i]]]);
            DD.setCard(i, card[randomarray_first[randomarray_second[13*3+i]]]);
        }
        String player1_card = Arrays.toString(AA.getCard());
        String player2_card = Arrays.toString(BB.getCard());
        String player3_card = Arrays.toString(CC.getCard());
        String player4_card = Arrays.toString(DD.getCard());
        player1.setText(player1_card.substring(1,player1_card.length()-1));
        player2.setText(player2_card.substring(1,player2_card.length()-1));
        player3.setText(player3_card.substring(1,player3_card.length()-1));
        player4.setText(player4_card.substring(1,player4_card.length()-1));
    }

    public int[] getRandomArray() {
        int[] intRet = new int[52];
        int intRd = 0; //存放随机数
        int count = 0; //记录生成的随机数个数
        int flag = 0; //是否已经生成过标志
        while(count < intRet.length) {
            Random rdm = new Random(System.currentTimeMillis());
            intRd = Math.abs(rdm.nextInt()) % 52;
            for (int i = 0; i < count; i++) {
                if (intRet[i] == intRd) {
                    flag = 1;
                    break;
                } else {
                    flag = 0;
                }
            }
            if (flag == 0) {
                intRet[count] = intRd;
                count++;
            }
        }
        return intRet;
    }
}
