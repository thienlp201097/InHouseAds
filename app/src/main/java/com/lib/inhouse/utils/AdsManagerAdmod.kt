package com.lib.inhouse.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import com.admob.max.inhouse.AdmobUtils
import com.admob.max.inhouse.GoogleENative
import com.admob.max.inhouse.utils.Utils
import com.admob.max.inhouse.utils.admod.InterHolderAdmob
import com.admob.max.inhouse.utils.admod.NativeHolderAdmob
import com.admob.max.inhouse.utils.admod.callback.AdCallBackInterLoad
import com.admob.max.inhouse.utils.admod.callback.AdsInterCallBack
import com.admob.max.inhouse.utils.admod.callback.NativeAdmobCallback
import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.nativead.NativeAd
import com.lib.inhouse.R

object AdsManagerAdmod {
    var nativeHolder = NativeHolderAdmob("ca-app-pub-3940256099942544/2247696110")
    var interholder = InterHolderAdmob("ca-app-pub-3940256099942544/1033173712")

    fun loadInter(context: Context, interHolder: InterHolderAdmob) {
        AdmobUtils.loadAndGetAdInterstitial(context,interHolder,
            object :
                AdCallBackInterLoad {
                override fun onAdClosed() {
                    Utils.getInstance().showMessenger(context, "onAdClosed")
                }

                override fun onEventClickAdClosed() {
                    Utils.getInstance().showMessenger(context, "onEventClickAdClosed")
                }

                override fun onAdShowed() {
                    Utils.getInstance().showMessenger(context, "onAdShowed")
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd, isLoad: Boolean) {
                    interholder.inter = interstitialAd
                    interHolder.check = isLoad
                    Utils.getInstance().showMessenger(context, "onAdLoaded")
                }

                override fun onAdFail(message: String?) {
                    Utils.getInstance().showMessenger(context, "onAdFail")
                }

                override fun onPaid(adValue: AdValue?, adUnitAds: String?) {

                }
            }
        )
    }


    fun showInter(
        context: Context,
        interHolder: InterHolderAdmob,
        adListener: AdListener,
        enableLoadingDialog: Boolean
    ) {
        AdmobUtils.showAdInterstitialWithCallbackNotLoadNew(
            context as Activity,interHolder,10000, object :
                AdsInterCallBack {
                override fun onAdLoaded() {
                    Utils.getInstance().showMessenger(context, "onAdLoaded")
                }

                override fun onStartAction() {
                    adListener.onAdClosed()
                }

                override fun onAdFail(error: String?) {
                    interHolder.inter = null
                    loadInter(context,interHolder)
                    adListener.onFailed()
                    Utils.getInstance().showMessenger(context, "onAdFail")
                }

                override fun onPaid(adValue: AdValue?, adUnitAds: String?) {

                }

                override fun onEventClickAdClosed() {
                    interHolder.inter = null
                    loadInter(context,interHolder)
//                    adListener.onAdClosed()
                    Utils.getInstance().showMessenger(context, "onEventClickAdClosed")
                }

                override fun onAdShowed() {
                    Utils.getInstance().showMessenger(context, "onAdShowed")
                }
            }, enableLoadingDialog)
    }

    fun loadAdsNativeNew(context: Context, holder: NativeHolderAdmob) {
        AdmobUtils.loadAndGetNativeAds(
            context,
            holder,
            object : NativeAdmobCallback {
                override fun onLoadedAndGetNativeAd(ad: NativeAd?) {
                }

                override fun onNativeAdLoaded() {
                }

                override fun onAdFail(error: String?) {
                }

                override fun onPaid(adValue: AdValue?, adUnitAds: String?) {

                }
            })
    }

    fun showNative(activity: Activity, viewGroup: ViewGroup, holder: NativeHolderAdmob) {
        if (!AdmobUtils.isNetworkConnected(activity)) {
            viewGroup.visibility = View.GONE
            return
        }
        AdmobUtils.showNativeAdsWithLayout(activity, holder, viewGroup, R.layout.ad_unified_medium, GoogleENative.UNIFIED_MEDIUM, object : AdmobUtils.AdsNativeCallBackAdmod {
            override fun NativeLoaded() {
                Utils.getInstance().showMessenger(activity, "onNativeShow")
            }

            override fun NativeFailed(massage: String) {
                Utils.getInstance().showMessenger(activity, "onAdsFailed")
            }

            override fun onPaid(adValue: AdValue?, adUnitAds: String?) {

            }
        })
    }



    interface AdListener {
        fun onAdClosed()
        fun onFailed()
    }
}
