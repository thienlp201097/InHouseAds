package com.admob.max.inhouse.callback_applovin

import com.applovin.mediation.MaxAd

interface RewardCallback {
    fun onRewardReady()
    fun onRewardClosed()
    fun onRewardLoadFail(error:String)
    fun onRewardShowSucceed()
    fun onUserRewarded()
    fun onRewardedVideoStarted()
    fun onRewardedVideoCompleted()

    fun onAdRevenuePaid(ad: MaxAd?)

}