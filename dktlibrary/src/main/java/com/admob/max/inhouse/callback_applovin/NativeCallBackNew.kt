package com.admob.max.inhouse.callback_applovin

import com.applovin.mediation.MaxAd
import com.applovin.mediation.nativeAds.MaxNativeAdView

interface NativeCallBackNew {
    fun onNativeAdLoaded(nativeAd: MaxAd?, nativeAdView: MaxNativeAdView?)
    fun onAdFail(error : String)
    fun onAdRevenuePaid(ad: MaxAd)
}