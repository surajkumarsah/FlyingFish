package com.example.flyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class GameOverActivity extends AppCompatActivity {

    private Button TryAgain,LeaderBoardBtn;
    private TextView displayScore;
    String score;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        MobileAds.initialize(this,
                "ca-app-pub-9028512770259391~3327327144");//9028512770259391~3327327144

        mAdView = findViewById(R.id.adview);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        TryAgain = (Button) findViewById(R.id.try_again);
        LeaderBoardBtn = (Button) findViewById(R.id.click_here);
        displayScore = (TextView) findViewById(R.id.display_score);

        score = getIntent().getExtras().get("score").toString();


        TryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        LeaderBoardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(GameOverActivity.this,Login_Activity.class);
                intent.putExtra("score",score);
                startActivity(intent);
            }
        });

        displayScore.setText(score);

    }
}
