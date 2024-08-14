package com.admob.max.inhouse.utils.admod.remote

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.doOnLayout
import androidx.core.view.updateLayoutParams
import com.google.ads.mediation.admob.AdMobAdapter
import com.google.android.gms.ads.*
import com.admob.max.inhouse.utils.admod.remote.BannerPlugin.Companion.log
import com.vapp.admoblibrary.ads.remote.BannerRemoteConfig

@SuppressLint("ViewConstructor")
internal class BannerAdView(
    private val activity: Activity,
    adUnitId: String,
    private val bannerType: BannerPlugin.BannerType,
    refreshRateSec: Int?,
    private val cbFetchIntervalSec: Int, val bannerRemoteConfig: BannerRemoteConfig
) : BaseAdView(activity, refreshRateSec) {
    companion object{
        var lastCBRequestTime = 0L
    }
    private val adView: AdView = AdView(activity)
    private var hasSetAdSize = false


    init {
        adView.adUnitId = adUnitId
        addView(adView, getCenteredLayoutParams(this))
    }

    private fun getCenteredLayoutParams(container: ViewGroup) = when (container) {
        is FrameLayout -> LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            this.gravity = Gravity.CENTER
        }
        is LinearLayout -> LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
            this.gravity = Gravity.CENTER
        }
        else -> LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    }

    override fun loadAdInternal(onDone: () -> Unit) {
        if (!hasSetAdSize) {
            doOnLayout {
                val adSize = getAdSize(bannerType)
                adView.setAdSize(adSize)
                adView.updateLayoutParams {
                    width = adSize.getWidthInPixels(activity)
                    height = adSize.getHeightInPixels(activity)
                }
                hasSetAdSize = true
                doLoadAd(onDone)
            }
        } else {
            doLoadAd(onDone)
        }
    }

    private fun getAdSize(bannerType: BannerPlugin.BannerType): AdSize {
        return when (bannerType) {
            BannerPlugin.BannerType.Standard -> AdSize.BANNER
            BannerPlugin.BannerType.Adaptive->{
                val display = activity.windowManager.defaultDisplay
                val outMetrics = DisplayMetrics()
                display.getMetrics(outMetrics)
                val widthPixels = outMetrics.widthPixels.toFloat()
                val density = outMetrics.density
                val adWidth = (widthPixels / density).toInt()
                // Step 3 - Get adaptive ad size and return for setting on the ad view.
                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, adWidth)
            }
            BannerPlugin.BannerType.CollapsibleBottom,
            BannerPlugin.BannerType.CollapsibleTop -> {
                val displayMetrics = activity.resources.displayMetrics

                var adWidthPx = width.toFloat()
                if (adWidthPx == 0f) {
                    adWidthPx = displayMetrics.widthPixels.toFloat()
                }

                val density = displayMetrics.density
                val adWidth = (adWidthPx / density).toInt()

                AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth)
            }
        }
    }

    private fun doLoadAd(onDone: () -> Unit) {
        var isCollapsibleBannerRequest = false
        val adRequestBuilder = AdRequest.Builder()
        when (bannerType) {
            BannerPlugin.BannerType.CollapsibleTop,
            BannerPlugin.BannerType.CollapsibleBottom -> {
                log("shouldRequestCollapsible() = ${shouldRequestCollapsible()}")
                if (shouldRequestCollapsible()) {
                    val position =
                        if (bannerType == BannerPlugin.BannerType.CollapsibleTop) "top" else "bottom"
                    adRequestBuilder.addNetworkExtrasBundle(
                        AdMobAdapter::class.java, Bundle().apply {
                            putString("collapsible", position)
                        }
                    )
                    isCollapsibleBannerRequest = true
                }
            }
            else -> {}
        }

        if (isCollapsibleBannerRequest) {
            lastCBRequestTime = System.currentTimeMillis()
        }
        adView.onPaidEventListener = OnPaidEventListener { adValue -> bannerRemoteConfig.onAdPaid(adValue,adView) }
        adView.adListener = object : AdListener() {
            override fun onAdLoaded() {
                adView.adListener = object : AdListener() {}
                onDone()
                BannerPlugin.shimmerFrameLayout?.stopShimmer()
                bannerRemoteConfig.onBannerAdLoaded(getAdSize(bannerType))

            }

            override fun onAdFailedToLoad(p0: LoadAdError) {
                adView.adListener = object : AdListener() {}
                onDone()
                BannerPlugin.shimmerFrameLayout?.stopShimmer()
                bannerRemoteConfig.onAdFail()
            }
        }
        adView.loadAd(adRequestBuilder.build())
    }

    private fun shouldRequestCollapsible(): Boolean {
        return System.currentTimeMillis() - lastCBRequestTime >= cbFetchIntervalSec * 1000L
    }

    override fun onVisibilityAggregated(isVisible: Boolean) {
        super.onVisibilityAggregated(isVisible)
        if (isVisible) {
            adView.resume()
        } else {
            adView.pause()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        adView.adListener = object : AdListener() {}
        adView.destroy()
    }
}