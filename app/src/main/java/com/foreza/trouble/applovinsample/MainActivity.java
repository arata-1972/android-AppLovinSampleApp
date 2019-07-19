package com.foreza.trouble.applovinsample;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdk;

import static android.view.Gravity.BOTTOM;


public class MainActivity extends AppCompatActivity {

    AppLovinAdView adView;


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

    private void initializeAndLoadBanner(){

        adView = new AppLovinAdView( AppLovinAdSize.BANNER, "7d19bb9d8bcac94d",this );
        adView.setId(ViewCompat.generateViewId());

        final ViewGroup rootView = (ViewGroup) findViewById( android.R.id.content );
        rootView.addView( adView );

        adView.setLayoutParams( new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                com.applovin.sdk.AppLovinSdkUtils.dpToPx( this, AppLovinAdSize.BANNER.getHeight()), BOTTOM));

        adView.loadNextAd();

    }

}
