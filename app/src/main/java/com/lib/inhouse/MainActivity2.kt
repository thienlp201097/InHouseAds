package com.lib.inhouse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.admob.max.inhouse.ApplovinUtil
import com.admob.max.inhouse.callback_applovin.BannerCallback
import com.admob.max.inhouse.callback_applovin.InterstititialCallback
import com.applovin.mediation.MaxAd
import com.lib.inhouse.utils.AdsManager

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val btn = findViewById<Button>(R.id.btn_2)
        btn.setOnClickListener {
                ApplovinUtil.loadAndShowInterstitialsWithDialogCheckTime(this,AdsManager.interHolder,object :
                    InterstititialCallback {
                    override fun onInterstitialReady() {

                    }

                    override fun onInterstitialClosed() {


                    }

                    override fun onInterstitialLoadFail(error: String) {


                    }

                    override fun onInterstitialShowSucceed() {

                    }

                    override fun onAdRevenuePaid(ad: MaxAd) {

                    }

                })
        }
    }
    override fun onResume() {
        val bannerContainer = findViewById<FrameLayout>(R.id.banner_container)
        ApplovinUtil.showBanner(this,bannerContainer,"banner_main", object : BannerCallback {
            override fun onBannerLoadFail(error: String) {
            }

            override fun onBannerShowSucceed() {
            }

            override fun onAdRevenuePaid(ad: MaxAd) {

            }
        })
        super.onResume()
    }
}