package com.example.bordia98.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import retrofit2.Call;

public class messageactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messageactivity);
        final String replyname = getIntent().getStringExtra("username").toString();
        final String token=getIntent().getStringExtra("token").toString();
        final String secreat = getIntent().getStringExtra("secret").toString();

        final EditText userid = (EditText)findViewById(R.id.receiver);
        final EditText actualmsg=(EditText)findViewById(R.id.messagefield);
        Button send = (Button) findViewById(R.id.send) ;
        Button back = (Button) findViewById(R.id.back) ;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),Timeline.class);
                i.putExtra("username",replyname);
                i.putExtra("token",token);
                i.putExtra("secret",secreat);
                startActivity(i);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String receiver = userid.getText().toString().trim();
                String status=actualmsg.getText().toString().trim();
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                Long username = session.getUserId();
                TwitterPrivateMessageApiClient clienty = new TwitterPrivateMessageApiClient(session);
                Call<Tweet> call = clienty.getPrivateMessageService().sendPrivateMessage(username,receiver,status);
                userid.setText("");
                actualmsg.setText(" ");
                call.enqueue(new Callback<Tweet>() {
                    @Override
                    public void success(Result<Tweet> result) {
                        Toast.makeText(getApplicationContext(), "Message send", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void failure(TwitterException exception) {
                        Toast.makeText(getApplicationContext(), exception.toString(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}
