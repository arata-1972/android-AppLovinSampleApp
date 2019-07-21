package com.foreza.trouble.applovinsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class AndIntoTheFireRewardedActivity extends AppCompatActivity {

    AppLovinIncentivizedInterstitial myIncent;
    String LOG_TAG = "AndIntoTheFireRewardedActivity";

    Boolean adIsReady = false;

    AppLovinAdRewardListener adRewardListener;
    AppLovinAdVideoPlaybackListener adVideoPlaybackListener;
    AppLovinAdDisplayListener adDisplayListener;
    AppLovinAdClickListener adClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_and_into_the_fire_rewarded);

        updateDisplayForAdNotLoaded();
        setupListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myIncent.dismiss(); // TODO: Is this called automatically by the SDK?
    }

    public void clickPreloadRewarded(View view){
        preloadRewarded();
    }

    public void showRewarded(View view) {
        if (myIncent.isAdReadyToDisplay()) {
            myIncent.show(AndIntoTheFireRewardedActivity.this, adRewardListener, adVideoPlaybackListener, adDisplayListener, adClickListener);
        }
    }

    public void updateDisplayForAdPreloaded() {

        if (myIncent != null && adIsReady) {
            Button a = findViewById(R.id.buttonShowRewarded);
            a.setClickable(true);
            a.setVisibility(View.VISIBLE);
            Button b = findViewById(R.id.buttonPreloadRewarded);
            b.setClickable(false);
            b.setVisibility(View.INVISIBLE);
        }

    }

    public void updateDisplayForAdNotLoaded() {

        Button a = findViewById(R.id.buttonShowRewarded);
        a.setClickable(false);
        a.setVisibility(View.INVISIBLE);
        Button b = findViewById(R.id.buttonPreloadRewarded);
        b.setClickable(true);
        b.setVisibility(View.VISIBLE);

    }

    private void preloadRewarded(){
        myIncent = AppLovinIncentivizedInterstitial.create("27207f2172b864c1", AppLovinSdk.getInstance( this ));
        adIsReady = false;

        myIncent.preload(new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd appLovinAd) {

                // A rewarded video was successfully received.
                adIsReady = true;
                updateDisplayForAdPreloaded();
                String msg = "Rewarded adReceived for: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();


            }
            @Override
            public void failedToReceiveAd(int errorCode) {

                // A rewarded video failed to load.
                adIsReady = false;
                String msg = "Rewarded failedToReceiveAd error code : " + errorCode;
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();

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
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void userOverQuota(AppLovinAd appLovinAd, Map map) {
                String msg = "Rewarded userOverQuota for:  " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
                String msg = "Rewarded userRewardRejected for: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void validationRequestFailed(AppLovinAd appLovinAd, int responseCode) {

                if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO ) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_USER_CLOSED_VIDEO";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_SERVER_TIMEOUT";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_UNKNOWN_SERVER_ERROR";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                } else if ( responseCode == AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED ) {
                    String msg = "Rewarded validationRequestFailed for: " + appLovinAd.getZoneId()
                            + ", reason: INCENTIVIZED_NO_AD_PRELOADED";
                    Log.d(LOG_TAG, msg);
                    Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
                String msg = "Rewarded userDeclinedToViewAd for: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };

        adVideoPlaybackListener = new AppLovinAdVideoPlaybackListener() {
            @Override
            public void videoPlaybackBegan(AppLovinAd appLovinAd) {
                String msg = "Rewarded videoPlaybackBegan: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd appLovinAd, double percentViewed, boolean fullyWatched) {
                String msg = "Rewarded videoPlaybackEnded: " + appLovinAd.getZoneId()
                        + " at: " + Double.toString(percentViewed) + " and was video complete: " + fullyWatched ;
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();            }
        };


        adDisplayListener = new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd appLovinAd) {
                adIsReady = false;
                String msg = "Rewarded adDisplayed: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
                updateDisplayForAdNotLoaded();
            }

            @Override
            public void adHidden(AppLovinAd appLovinAd) {
                String msg = "Rewarded adHidden: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();                }
        };


        adClickListener = new AppLovinAdClickListener() {
            @Override
            public void adClicked(AppLovinAd appLovinAd) {
                String msg = "Rewarded adClicked: " + appLovinAd.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(AndIntoTheFireRewardedActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        };

    }


}
