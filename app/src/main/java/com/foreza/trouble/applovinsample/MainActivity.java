package com.foreza.trouble.applovinsample;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

import static android.view.Gravity.BOTTOM;


public class MainActivity extends AppCompatActivity {

    AppLovinAdView adView;
    String LOG_TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeAdSDKs();
        initializeAndLoadBanner();

    }


    private void initializeAdSDKs() {

        // Initialize Application Context
        AppLovinSdk.initializeSdk(this);

        // TODO: Any other Ad SDKs that require init might go here
        // ....
    }


    // This method will initialize the adView and attach listeners.
    private void initializeAndLoadBanner(){

        adView = new AppLovinAdView( AppLovinAdSize.BANNER, "7d19bb9d8bcac94d",this );
        adView.setId(ViewCompat.generateViewId());

        final ViewGroup rootView = (ViewGroup) findViewById( android.R.id.content );
        rootView.addView( adView );

        adView.setLayoutParams( new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                com.applovin.sdk.AppLovinSdkUtils.dpToPx( this, AppLovinAdSize.BANNER.getHeight()), BOTTOM));



        adView.setAdLoadListener(new AppLovinAdLoadListener() {
            @Override
            public void adReceived(AppLovinAd ad) {
                String msg = "Banner adReceived for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void failedToReceiveAd(int errorCode) {
                String msg = "Banner failedToReceiveAd with error: " + errorCode;
                Log.d(LOG_TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });


        adView.setAdDisplayListener(new AppLovinAdDisplayListener() {
            @Override
            public void adDisplayed(AppLovinAd ad) {
                String msg = "Banner adDisplayed for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void adHidden(AppLovinAd ad) {
                String msg = "Banner adHidden for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        adView.setAdClickListener(new AppLovinAdClickListener() {
            @Override
            public void adClicked(AppLovinAd ad) {
                String msg = "Banner adClicked for: " + ad.getZoneId();
                Log.d(LOG_TAG, msg);
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });

        adView.loadNextAd();

    }

}
