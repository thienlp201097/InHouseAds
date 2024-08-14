package com.admob.max.inhouse.callback_applovin

interface RewardVideoCallback {
    fun onRewardClosed()
    fun onRewardEarned()
    fun onRewardFailed()
    fun onRewardNotAvailable()
}