package com.admob.max.inhouse.utils.admod

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd

open class RewardHolderAdmob(var ads: String) {
    var inter: RewardedAd? = null
    val mutable: MutableLiveData<RewardedAd> = MutableLiveData(null)
    var isLoading = false
}