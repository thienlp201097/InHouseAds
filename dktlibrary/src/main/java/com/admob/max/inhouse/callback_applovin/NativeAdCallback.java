package com.admob.max.inhouse.callback_applovin;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.nativeAds.MaxNativeAdView;

public interface NativeAdCallback {
    void onNativeAdLoaded();
    void onLoadedAndGetNativeAd(MaxAd ad, MaxNativeAdView adView);
    void onAdFail();
}
