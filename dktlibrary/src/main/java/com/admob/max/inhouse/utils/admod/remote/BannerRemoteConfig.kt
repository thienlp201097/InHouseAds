package com.vapp.admoblibrary.ads.remote

import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.AdView

interface BannerRemoteConfig {
    fun onBannerAdLoaded(adSize: AdSize?)
    fun onAdFail()
    fun onAdPaid(adValue: AdValue, mAdView: AdView)
}