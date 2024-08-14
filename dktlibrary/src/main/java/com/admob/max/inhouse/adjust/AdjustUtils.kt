package com.admob.max.inhouse.adjust

import android.content.Context
import android.util.Log
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustAdRevenue
import com.adjust.sdk.AdjustConfig
import com.adjust.sdk.LogLevel
import com.applovin.mediation.MaxAd
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.AdapterResponseInfo
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.rewarded.RewardedAd


object AdjustUtils {
    fun initAdjust(context: Context, key : String, debug: Boolean) {
        val config = if (debug){
            AdjustConfig.ENVIRONMENT_SANDBOX
        }else{
            AdjustConfig.ENVIRONMENT_PRODUCTION
        }
        val adjustConfig = AdjustConfig(
            context,
            key,
            config
        )
        adjustConfig.setLogLevel(LogLevel.WARN)
        Adjust.initSdk(adjustConfig)
    }

    fun postRevenueAdjustMax(ad : MaxAd){
        Log.d("==postRevenueAdjustMax==", "postRevenueAdjustMax: ${ad.revenue}")

        val adjustAdRevenue = AdjustAdRevenue("applovin_max_sdk")
        adjustAdRevenue.setRevenue(ad.revenue, "USD")
        adjustAdRevenue.adRevenueNetwork = ad.networkName
        adjustAdRevenue.adRevenueUnit = ad.adUnitId
        adjustAdRevenue.adRevenuePlacement = ad.placement
        Adjust.trackAdRevenue(adjustAdRevenue)
    }
    fun postRevenueAdjust(ad: AdValue, adUnit: String?) {
        Log.d("==postRevenueAdjustBannerReward==", "postRevenueAdjustBannerReward: ${ad.valueMicros / 1000000.0}")
        val adjustAdRevenue = AdjustAdRevenue("admob_sdk")
        adjustAdRevenue.setRevenue(ad.valueMicros / 1000000.0, ad.currencyCode)
        adjustAdRevenue.adRevenueUnit = adUnit
        Adjust.trackAdRevenue(adjustAdRevenue)
    }
    fun postRevenueAdjustInter(interAd : InterstitialAd,ad: AdValue, adUnit: String?) {
        Log.d("==postRevenueAdjustInter==", "postRevenueAdjustInter: ${ad.valueMicros / 1000000.0}")
        val loadedAdapterResponseInfo: AdapterResponseInfo? = interAd.responseInfo.loadedAdapterResponseInfo
        val adjustAdRevenue = AdjustAdRevenue("admob_sdk")
        adjustAdRevenue.setRevenue(ad.valueMicros / 1000000.0, ad.currencyCode)
        adjustAdRevenue.adRevenueUnit = adUnit
        adjustAdRevenue.adRevenueNetwork = loadedAdapterResponseInfo?.adSourceName
        Adjust.trackAdRevenue(adjustAdRevenue)
    }

    fun postRevenueAdjustNative(nativeAd : NativeAd,ad: AdValue, adUnit: String?) {
        Log.d("==postRevenueAdjustNative==", "postRevenueAdjustNative: ${ad.valueMicros / 1000000.0}")
        val loadedAdapterResponseInfo: AdapterResponseInfo? = nativeAd.responseInfo?.loadedAdapterResponseInfo
        val adjustAdRevenue = AdjustAdRevenue("admob_sdk")
        adjustAdRevenue.setRevenue(ad.valueMicros / 1000000.0, ad.currencyCode)
        adjustAdRevenue.adRevenueUnit = adUnit
        adjustAdRevenue.adRevenueNetwork = loadedAdapterResponseInfo?.adSourceName
        Adjust.trackAdRevenue(adjustAdRevenue)
    }

    fun postRevenueAdjustRewarded(interAd : RewardedAd,ad: AdValue, adUnit: String?) {
        val loadedAdapterResponseInfo: AdapterResponseInfo? = interAd.responseInfo.loadedAdapterResponseInfo
        val adjustAdRevenue = AdjustAdRevenue("admob_sdk")
        adjustAdRevenue.setRevenue(ad.valueMicros / 1000000.0, ad.currencyCode)
        adjustAdRevenue.adRevenueUnit = adUnit
        adjustAdRevenue.adRevenueNetwork = loadedAdapterResponseInfo?.adSourceName
        Adjust.trackAdRevenue(adjustAdRevenue)
    }
}