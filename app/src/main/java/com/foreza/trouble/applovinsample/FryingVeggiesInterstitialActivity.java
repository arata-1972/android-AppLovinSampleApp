package com.foreza.trouble.applovinsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;

public class FryingVeggiesInterstitialActivity extends AppCompatActivity {

    private AppLovinAd loadedAd;
    private AppLovinInterstitialAdDialog interstitialAd;
    private Boolean adIsReady;
    String LOG_TAG = "FryingVeggiesInterstitialActivity";



    // This activity will preload + show the Interstitial Ad right away

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frying_veggies_interstitial);

        updateDisplayForAdNotLoaded();
        configureInterstitialAdDialog();
    }

    // View method to show another interstitial

    public void clickPreloadInterstitial(View view) {
        preloadInterstital(); // TODO: Show a loading modal until adReceived or failedToReceiveAd
    }

    public void clickShowPreloadedInterstitial(View view) {
        if (interstitialAd != null && adIsReady){
            interstitialAd.showAndRender(loadedAd);
        }
    }

    public void updateDisplayForAdPreloaded() {

        if (loadedAd != null && adIsReady) {
            Button a = findViewById(R.id.buttonShowInterstitial);
            a.setClickable(true);
            a.setVisibility(View.VISIBLE);
            Button b = findViewById(R.id.buttonPreloadInterstitial);
            b.setClickable(false);
            b.setVisibility(View.INVISIBLE);
        }

    }

    public void updateDisplayForAdNotLoaded() {

        Button a = findViewById(R.id.buttonShowInterstitial);
        a.setClickable(false);
        a.setVisibility(View.INVISIBLE);
        Button b = findViewById(R.id.buttonPreloadInterstitial);
        b.setClickable(true);
        b.setVisibility(View.VISIBLE);

    }




    private void preloadInterstital() {

        AppLovinSdk.getInstance( this ).getAdService().loadNextAdForZoneId("0d4010dea819d0d3",
                new AppLovinAdLoadListener()
                {
                    @Override
                    public void adReceived(AppLovinAd ad)
                    {
                        loadedAd = ad;
                        adIsReady = true;
                        String msg = "Interstitial adReceived for: " + ad.getZoneId();
                        Log.d(LOG_TAG, msg);
                        Toast.makeText(FryingVeggiesInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
                        updateDisplayForAdPreloaded();
                    }

                    @Override
                    public void failedToReceiveAd(int errorCode)
                    {
                        // Look at AppLovinErrorCodes.java for list of error codes.
                        adIsReady = false;
                        String msg = "Interstitial failedToReceiveAd with error code : " + errorCode;
                        Log.d(LOG_TAG, msg);
                        Toast.makeText(FryingVeggiesInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                } );
    }

    private void configureInterstitialAdDialog() {

        interstitialAd = AppLovinInterstitialAd.create(
                AppLovinSdk.getInstance( this ), this );



        interstitialAd.setAdDisplayListener(new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd ad) {
                adIsReady = false;
                String msg = "Interstitial adDisplayed for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingVeggiesInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
                updateDisplayForAdNotLoaded();
            }

            @Override
            public void adHidden(AppLovinAd ad) {
                String msg = "Interstitial adHidden for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingVeggiesInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


        interstitialAd.setAdClickListener(new AppLovinAdClickListener() {
            @Override
            public void adClicked(AppLovinAd ad) {
                String msg = "Interstitial adClicked for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingVeggiesInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();

            }
        });


        interstitialAd.setAdVideoPlaybackListener(new AppLovinAdVideoPlaybackListener() {
            @Override
            public void videoPlaybackBegan(AppLovinAd ad) {
                adIsReady = false;
                String msg = "Interstitial videoPlaybackBegan for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingVeggiesInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
                String msg = "Interstitial videoPlaybackEnded for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(FryingVeggiesInterstitialActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

    }



}
