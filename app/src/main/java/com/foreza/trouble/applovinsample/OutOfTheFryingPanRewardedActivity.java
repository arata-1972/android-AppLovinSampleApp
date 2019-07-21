package com.foreza.trouble.applovinsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;

import java.util.Map;

public class OutOfTheFryingPanRewardedActivity extends AppCompatActivity {

    AppLovinIncentivizedInterstitial myIncent;
    String LOG_TAG = "OutOfTheFryingPanRewardedActivity";

    AppLovinAdRewardListener adRewardListener;
    AppLovinAdVideoPlaybackListener adVideoPlaybackListener;
    AppLovinAdDisplayListener adDisplayListener;
    AppLovinAdClickListener adClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_out_of_the_frying_pan_rewarded);

        setupListeners();
        initializeAndLoadRewarded();
    }

    public void clickLoadRewarded(View view){
        initializeAndLoadRewarded();
    }

    private void initializeAndLoadRewarded(){
        myIncent = AppLovinIncentivizedInterstitial.create("49557b5ae8beb006", AppLovinSdk.getInstance( this ));

        myIncent.preload(new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd appLovinAd) {

                // A rewarded video was successfully received.
                String msg = "Rewarded adReceived for: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();

                myIncent.show( OutOfTheFryingPanRewardedActivity.this, adRewardListener, adVideoPlaybackListener, adDisplayListener, adClickListener );

            }
            @Override
            public void failedToReceiveAd(int errorCode) {

                // A rewarded video failed to load.
                String msg = "Rewarded failedToReceiveAd error code : " + errorCode;
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void setupListeners(){

        adRewardListener = new AppLovinAdRewardListener() {
            @Override
            public void userRewardVerified(AppLovinAd appLovinAd, Map map) {

                String currencyName = (String) map.get( "currency" );
                String amountGivenString = (String) map.get( "amount" );

                String msg = "Rewarded userRewardVerified: " + amountGivenString + currencyName + " for: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void userOverQuota(AppLovinAd appLovinAd, Map map) {
                String msg = "Rewarded userOverQuota for:  " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
                String msg = "Rewarded userRewardRejected for: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void validationRequestFailed(AppLovinAd appLovinAd, int responseCode) {

                if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO ) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_USER_CLOSED_VIDEO";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_SERVER_TIMEOUT";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_UNKNOWN_SERVER_ERROR";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED ) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_NO_AD_PRELOADED";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                String msg = "Rewarded userDeclinedToViewAd for: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };

        adVideoPlaybackListener = new AppLovinAdVideoPlaybackListener() {
            @Override
            public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                String msg = "Rewarded videoPlaybackBegan: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean fullyWatched) {
                String msg = "Rewarded videoPlaybackEnded: " + appLovinAd.getZoneId()
                        + " at: " + Double.toString(percentViewed) + " and was video complete: " + fullyWatched ;
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();            }
        };


        adDisplayListener = new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd) {
                String msg = "Rewarded adDisplayed: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd) {
                String msg = "Rewarded adHidden: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();                }
        };


        adClickListener = new AppLovinAdClickListener() {
            @Override
            public void adClicked(AppLovinAd appLovinAd) {
                String msg = "Rewarded adClicked: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(OutOfTheFryingPanRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };

    }

}
