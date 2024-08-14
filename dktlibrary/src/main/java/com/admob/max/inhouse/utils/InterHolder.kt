package com.admob.max.inhouse.utils

import androidx.lifecycle.MutableLiveData
import com.applovin.mediation.ads.MaxInterstitialAd

class InterHolder(var adsId: String) {
    var inter: MaxInterstitialAd? = null
    val mutable: MutableLiveData<MaxInterstitialAd> = MutableLiveData()
    var check = false
}