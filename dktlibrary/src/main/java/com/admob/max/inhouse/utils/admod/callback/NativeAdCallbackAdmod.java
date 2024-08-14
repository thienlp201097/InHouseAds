package com.admob.max.inhouse.utils.admod.callback;

import com.google.android.gms.ads.AdValue;
import com.google.android.gms.ads.nativead.NativeAd;

public interface NativeAdCallbackAdmod {
    void onLoadedAndGetNativeAd(NativeAd ad );
    void onNativeAdLoaded();
    void onAdFail();
    void onAdPaid(AdValue adValue);
}
