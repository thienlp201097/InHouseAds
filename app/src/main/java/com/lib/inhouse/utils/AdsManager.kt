package com.lib.inhouse.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.admob.max.inhouse.AppOpenManager
import com.admob.max.inhouse.ApplovinUtil
import com.admob.max.inhouse.GoogleENative
import com.admob.max.inhouse.callback_applovin.InterstititialCallback
import com.admob.max.inhouse.callback_applovin.InterstititialCallbackNew
import com.admob.max.inhouse.callback_applovin.NativeCallBackNew
import com.admob.max.inhouse.utils.InterHolder
import com.admob.max.inhouse.utils.NativeHolder
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView


object AdsManager {
    var interHolder = InterHolder("134656413e36e374")
    var nativeHolder = NativeHolder("0f688c4e22b9688b")
    var banner = "f443c90308f39f17"

    fun showAdsNative(activity: Activity, nativeHolder: NativeHolder,viewGroup: ViewGroup){
        ApplovinUtil.loadAndShowNativeAds(activity,nativeHolder,viewGroup,
            GoogleENative.UNIFIED_MEDIUM,object :
                NativeCallBackNew {
            override fun onNativeAdLoaded(nativeAd: MaxAd?, nativeAdView: MaxNativeAdView?) {
                Toast.makeText(activity,"Loaded",Toast.LENGTH_SHORT).show()
            }

            override fun onAdFail(error: String) {
                Toast.makeText(activity,"LoadFailed",Toast.LENGTH_SHORT).show()
            }

                override fun onAdRevenuePaid(ad: MaxAd) {

                }
            })
    }
    fun loadInter(context: Context){
        ApplovinUtil.loadAnGetInterstitials(context, interHolder,object :
            InterstititialCallbackNew {
            override fun onInterstitialReady(interstitialAd: MaxInterstitialAd) {
//                Toast.makeText(context,"Loaded",Toast.LENGTH_SHORT).show()
            }

            override fun onInterstitialClosed() {

            }

            override fun onInterstitialLoadFail(error: String) {
//                Toast.makeText(context,"LoadFailed",Toast.LENGTH_SHORT).show()
            }

            override fun onInterstitialShowSucceed() {

            }

            override fun onAdRevenuePaid(ad: MaxAd?) {

            }
        })
    }

    fun showInter(context: AppCompatActivity,interHolder: InterHolder,adsOnClick: AdsOnClick){
        ApplovinUtil.showInterstitialsWithDialogCheckTimeNew(context, 800,interHolder ,object :
            InterstititialCallbackNew {
            override fun onInterstitialReady(interstitialAd : MaxInterstitialAd) {
                Toast.makeText(context,"Ready",Toast.LENGTH_SHORT).show()
            }

            override fun onInterstitialClosed() {
                loadInter(context)
                Toast.makeText(context,"Closed",Toast.LENGTH_SHORT).show()
                adsOnClick.onAdsCloseOrFailed()
            }


            override fun onInterstitialLoadFail(error: String) {
                loadInter(context)
                adsOnClick.onAdsCloseOrFailed()
                Toast.makeText(context, "Failed: $error",Toast.LENGTH_SHORT).show()
            }

            override fun onInterstitialShowSucceed() {
                Toast.makeText(context,"Show",Toast.LENGTH_SHORT).show()
            }

            override fun onAdRevenuePaid(ad: MaxAd?) {

            }
        })
    }

    interface AdsOnClick{
        fun onAdsCloseOrFailed()
    }

    var nativeAdLoader : MaxNativeAdLoader?=null
    var native: MaxAd? = null
    var isLoad = false
    var native_mutable: MutableLiveData<MaxAd> = MutableLiveData()

    fun loadAndShowIntersial(activity: Activity, idAd: InterHolder,adsOnClick: AdsOnClick){
        ApplovinUtil.loadAndShowInterstitialsWithDialogCheckTime(activity as AppCompatActivity,idAd, object :
            InterstititialCallback {
            override fun onInterstitialReady() {
                AppOpenManager.getInstance().isAppResumeEnabled = false
            }

            override fun onInterstitialClosed() {
                adsOnClick.onAdsCloseOrFailed()
            }

            override fun onInterstitialLoadFail(error: String) {
                Log.d("===Ads",error)
                adsOnClick.onAdsCloseOrFailed()
            }

            override fun onInterstitialShowSucceed() {
                AppOpenManager.getInstance().isAppResumeEnabled = false
            }

            override fun onAdRevenuePaid(ad: MaxAd) {

            }

        })

    }
}
