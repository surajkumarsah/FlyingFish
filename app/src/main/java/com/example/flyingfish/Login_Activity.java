package com.example.flyingfish;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Login_Activity extends AppCompatActivity {

    private EditText Name,Mobile;
    private Button Submit,Home;
    ProgressDialog loading;
    private DatabaseReference userref;

    String score;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);

        MobileAds.initialize(this,
                "ca-app-pub-9028512770259391~3327327144");//9028512770259391~3327327144

        mAdView = findViewById(R.id.adview);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Name = (EditText) findViewById(R.id.name);
        Mobile = (EditText) findViewById(R.id.mob_no);
        Submit = (Button) findViewById(R.id.submit);
        Home = (Button) findViewById(R.id.home);

        loading = new ProgressDialog(this);
        score = getIntent().getExtras().get("score").toString();



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                RegisterAcc();
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(Login_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void RegisterAcc()
    {
        final String Nametxt = Name.getText().toString();
        final String Mobiletxt = Mobile.getText().toString();

        if (TextUtils.isEmpty(Nametxt))
        {
            Toast.makeText(Login_Activity.this,"Please, Enter your Name.",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Mobiletxt))
        {
            Toast.makeText(Login_Activity.this,"Please, Enter Your Mobile No.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            loading.setTitle("submit");

            userref = FirebaseDatabase.getInstance().getReference();
            userref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {
                    HashMap<String,Object> userDetails = new HashMap<>();
                    userDetails.put("name",Nametxt);
                    userDetails.put("mobile",Mobiletxt);
                    userDetails.put("score",score);

                    userref.child(score).updateChildren(userDetails)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful())
                                    {
                                        loading.dismiss();
                                        Toast.makeText(Login_Activity.this,"Data Submitted Successfully ThankYou.",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        loading.dismiss();
                                        Toast.makeText(Login_Activity.this,"Network Error,Please try after sometime ThankYou.",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}
