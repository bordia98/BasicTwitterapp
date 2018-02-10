package com.example.bordia98.twitter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.tweetcomposer.ComposerActivity;

public class Status_updatae extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_updatae);
        Uri uri;
        final String usersecret=getIntent().getStringExtra("usertoken").toString();
        final String username=getIntent().getStringExtra("username");
        final String usertoken=getIntent().getStringExtra("usertoken").toString();
        Button twitting = (Button)findViewById(R.id.tweeting);
        final EditText ta = (EditText)findViewById(R.id.ta);
        twitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tweet = ta.getText().toString().trim();
                ta.setText("");

                final TwitterSession session = TwitterCore.getInstance().getSessionManager()
                        .getActiveSession();
                final Intent intent = new ComposerActivity.Builder(getApplicationContext())
                        .session(session)
                        .text(tweet)
                        .createIntent();
                startActivity(intent);

            }
        });

        Button back = (Button)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Timeline.class);
                i.putExtra("username",username);
                i.putExtra("token",usertoken);
                i.putExtra("secret",usersecret);
                startActivity(i);
            }
        });

    }
}
