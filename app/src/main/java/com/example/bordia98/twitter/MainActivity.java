package com.example.bordia98.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {

    TwitterLoginButton login ;
    public TwitterSession session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Twitter.initialize(this);
        setContentView(R.layout.activity_main);

        typewriter writer = (typewriter)findViewById(R.id.typewriter);



        RotateAnimation anim = new RotateAnimation(0f, 360f, 25f, 25f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(1);
        anim.setDuration(500);


        final ImageView splash = (ImageView) findViewById(R.id.imageView);
        splash.startAnimation(anim);


        Thread th = new Thread();
        try{
            th.sleep(500);
            writer.setCharacterDelay(250);
            writer.animateText("An app where you can have the functionalities of Twitter");
        }catch (Exception e){

        }
        th.start();

        login=(TwitterLoginButton)findViewById(R.id.login_button);
        login.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                String token = authToken.token;
                String secret = authToken.secret;
                loggedin(session,token,secret);
                //Toast.makeText(getApplicationContext(),"You are logged in",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(TwitterException exception) {

                Toast.makeText(getApplicationContext(),"Unable to login please try later",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loggedin(TwitterSession session,String token,String secret) {

        Intent i = new Intent(getApplicationContext(),Timeline.class);
        String username=session.getUserName();
        i.putExtra("username",username);
        i.putExtra("token",token);
        i.putExtra("secret",secret);
        startActivity(i);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        login.onActivityResult(requestCode, resultCode, data);
    }
}
