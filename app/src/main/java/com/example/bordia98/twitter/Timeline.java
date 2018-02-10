package com.example.bordia98.twitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class Timeline extends AppCompatActivity {
     String username;
     String token;
     String secret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        Toolbar toolbar =(Toolbar)findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        username=getIntent().getStringExtra("username").toString();
        token=getIntent().getStringExtra("token").toString();
        secret=getIntent().getStringExtra("secret").toString();

        final Button search = (Button) findViewById(R.id.searchbutton);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText toseacrch = (EditText)findViewById(R.id.search);
                final String y=toseacrch.getText().toString().trim();
                Intent i = new Intent(getApplicationContext(),Timeline1.class);
                Log.i("key",y);
                i.putExtra("search",y);
                startActivity(i);
            }
        });
        RecyclerView ls = (RecyclerView)findViewById(R.id.usertimeline);
        Long id = TwitterCore.getInstance().getSessionManager().getActiveSession().getUserId();
        ls.setLayoutManager(new LinearLayoutManager(this));

        final UserTimeline usertimeline = new UserTimeline.Builder()
                .screenName(username)
                .build();

        final TweetTimelineRecyclerViewAdapter adapter=new TweetTimelineRecyclerViewAdapter.Builder(this)
                .setTimeline(usertimeline)
                .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                .build();

        ls.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timeline,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id=item.getItemId();

        switch (id){
            case R.id.mes:{

                message();
                return true;
            }
            case R.id.tweetbutton:{
                tweet();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void tweet() {
        Intent i = new Intent(getApplicationContext(),Status_updatae.class);
        i.putExtra("usertoken",token);
        i.putExtra("usersecret",secret);
        i.putExtra("username",username);
        startActivity(i);
    }

    private void message() {
        Intent i = new Intent(getApplicationContext(),messageactivity.class);
        i.putExtra("username",username);
        i.putExtra("token",token);
        i.putExtra("secret",secret);
        startActivity(i);
    }
}
