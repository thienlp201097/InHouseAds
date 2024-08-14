package com.admob.max.inhouse.utils.admod.callback;

import com.google.android.gms.ads.AdValue;

public interface AdsInterCallBack {
    void onStartAction();
    void onEventClickAdClosed();
    void onAdShowed();
    void onAdLoaded();
    void onAdFail(String error);
    void onPaid(AdValue adValue, String adUnitAds);
}
