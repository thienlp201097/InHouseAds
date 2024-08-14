package com.admob.max.inhouse.utils.admod.callback;

import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.interstitial.InterstitialAd;

public interface AdCallBackInterLoad {
    void onAdClosed();
    void onEventClickAdClosed();
    void onAdShowed();
    void onAdLoaded(InterstitialAd interstitialAd, boolean isLoading);
    void onAdFail(String message);
    void onPaid(AdValue adValue, String adUnitAds);
}
