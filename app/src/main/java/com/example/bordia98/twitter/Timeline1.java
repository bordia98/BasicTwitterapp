package com.example.bordia98.twitter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineRecyclerViewAdapter;

import java.util.Locale;

public class Timeline1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchlayout);

        String y=getIntent().getStringExtra("search").toString();
        Log.i("key",y);
        System.out.print(y);
        TextView text = (TextView)findViewById(R.id.searchresult);
        text.setText("");
        text.setText("Search Result");
        if(y.length()==0){
         text.setText("");
         text.setText("No Match Found");
        }
       else{
            RecyclerView searchview = (RecyclerView)findViewById(R.id.recyclesearch);
            searchview.setLayoutManager(new LinearLayoutManager(this));

            final SearchTimeline searchTimeline = new SearchTimeline.Builder()
                    .query(y)
                    .languageCode(Locale.ENGLISH.getLanguage())
                    .build();
            final TweetTimelineRecyclerViewAdapter adapter = new TweetTimelineRecyclerViewAdapter.Builder(this)
                    .setTimeline(searchTimeline)
                    .setViewStyle(R.style.tw__TweetLightWithActionsStyle)
                    .build();

            searchview.setAdapter(adapter);
        }

    }
}
