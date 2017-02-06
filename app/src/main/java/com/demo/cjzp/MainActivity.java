package com.demo.cjzp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Administrator on 2017/2/6.
 */

public class MainActivity extends Activity {

    private LuckyPan mLuckyPan;
    private ImageView mStartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLuckyPan = (LuckyPan) findViewById(R.id.id_luckypan);
        mStartBtn = (ImageView) findViewById(R.id.id_start_btn);

        mStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mLuckyPan.isStart()) {
                    mStartBtn.setImageResource(R.drawable.stop);
//					mLuckyPanView.luckyStart(3);
                    mLuckyPan.luckyStart(getRate());
                } else {
                    if (!mLuckyPan.isShouldEnd()) {
                        mStartBtn.setImageResource(R.drawable.start);
                        mLuckyPan.luckyEnd();
                    }
                }
            }
        });
    }

    // { "单反相机", "IPAD", "恭喜发财", "IPHONE","妹子一只", "恭喜发财" };
    private int[] rates = new int[]{1, 1, 10, 1, 10, 10};

    private int getRate() {
        int sum = 0;
        for (int i : rates) {
            sum += i;
        }
        int a = new Random().nextInt(sum);
        for (int i = 0; i < rates.length; i++) {
            int b = rates[i];
            if (a <= b) {
                return i;
            }
            a -= b;
        }
        return 0;
    }
}
