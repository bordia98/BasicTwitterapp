package com.example.bordia98.twitter;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class TwitterPrivateMessageApiClient extends TwitterApiClient {
    public TwitterPrivateMessageApiClient (TwitterSession session){
        super(session);
    }

    /** This class provides the customized PrivateMessageService */
    public PrivateMessageService getPrivateMessageService(){
        return getService(PrivateMessageService.class);
    }
}

interface PrivateMessageService {
    @FormUrlEncoded
    @POST("/1.1/direct_messages/new.json?" +
            "tweet_mode=extended&include_cards=true&cards_platform=TwitterKit-13")
    Call<Tweet> sendPrivateMessage(@Field("user_id") Long userId,
                                   @Field("screen_name") String screenName,
                                   @Field("text") String text);

}

