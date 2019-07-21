package com.foreza.trouble.applovinsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public class FryingMeatInterstitialActivity extends AppCompatActivity {

    private AppLovinAd loadedAd;
    private AppLovinInterstitialAdDialog interstitialAd;
    String LOG_TAG = "FryingMeatInterstitialActivity";



    // This activity will preload + show the Interstitial Ad right away

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frying_meat_interstitial);

        configureInterstitialAdDialog();
        loadAndShowInterstital();
    }

    // View method to show another interstitial

    public void clickLoadInterstitial(View view) {
        loadAndShowInterstital(); // TODO: Show a loading modal until adReceived or failedToReceiveAd
    }


    private void loadAndShowInterstital() {

        AppLovinSdk.getInstance( this ).getAdService().loadNextAdForZoneId("81f14095db0fe841",
                new AppLovinAdLoadListener()
        {
            @Override
            public void adReceived(AppLovinAd ad)
            {
                loadedAd = ad;
                interstitialAd.showAndRender(loadedAd);
                String msg = "Interstitial adReceived for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingMeatInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failedToReceiveAd(int errorCode)
            {
                // Look at AppLovinErrorCodes.java for list of error codes.
                String msg = "Interstitial failedToReceiveAd with error code : " + errorCode;
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingMeatInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        } );
    }

    private void configureInterstitialAdDialog() {

        interstitialAd = AppLovinInterstitialAd.create(
                AppLovinSdk.getInstance( this ), this );



        interstitialAd.setAdDisplayListener(new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd ad) {
                String msg = "Interstitial adDisplayed for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingMeatInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void adHidden(AppLovinAd ad) {
                String msg = "Interstitial adHidden for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingMeatInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


        interstitialAd.setAdClickListener(new AppLovinAdClickListener() {
            @Override
            public void adClicked(AppLovinAd ad) {
                String msg = "Interstitial adClicked for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingMeatInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });


        interstitialAd.setAdVideoPlaybackListener(new AppLovinAdVideoPlaybackListener() {
            @Override
            public void videoPlaybackBegan(AppLovinAd ad) {
                String msg = "Interstitial videoPlaybackBegan for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingMeatInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
                String msg = "Interstitial videoPlaybackEnded for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingMeatInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }



}
