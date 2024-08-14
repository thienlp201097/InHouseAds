package com.lib.inhouse

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.admob.max.inhouse.AdmobUtils
import com.admob.max.inhouse.AppOpenManager
import com.admob.max.inhouse.ApplovinUtil
import com.admob.max.inhouse.R
import com.admob.max.inhouse.callback_applovin.NativeCallBackNew
import com.admob.max.inhouse.utils.Utils
import com.applovin.mediation.MaxAd
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.lib.inhouse.databinding.ActivitySplashBinding
import com.lib.inhouse.utils.AdsManager


@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AdmobUtils.initAdmob(this, 10000, isDebug = true, isEnableAds = true)
        AppOpenManager.getInstance().init(application, getString(R.string.test_ads_admob_app_open_new))
        AppOpenManager.getInstance().disableAppResumeWithActivity(SplashActivity::class.java)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (ApplovinUtil.isNetworkConnected(this)){
            ApplovinUtil.initApplovin(application, "Hd8NW44NTx4ndvT7Pw2PIQR_omwB0DB00BKnHGXorX1hCETptrgiRyRCtDcZqbhU9Wi_l4R0Icd5N5SkKJFGIy",
                testAds = true,
                enableAds = true,
                initialization = object : ApplovinUtil.Initialization{
                    override fun onInitSuccessful() {
                        ApplovinUtil.loadNativeAds(this@SplashActivity,
                            AdsManager.nativeHolder,object :
                            NativeCallBackNew {
                            override fun onNativeAdLoaded(nativeAd: MaxAd?, nativeAdView: MaxNativeAdView?) {
                                Toast.makeText(this@SplashActivity,"Loaded", Toast.LENGTH_SHORT).show()
                            }

                            override fun onAdFail(error: String) {
                                Toast.makeText(this@SplashActivity,"Failed", Toast.LENGTH_SHORT).show()
                            }

                                override fun onAdRevenuePaid(ad: MaxAd) {

                                }
                            })
                        Utils.getInstance().replaceActivity(this@SplashActivity, MainActivity::class.java)
                    }
                })
        }else{
            Utils.getInstance().replaceActivity(this@SplashActivity, MainActivity::class.java)

        }
    }
}