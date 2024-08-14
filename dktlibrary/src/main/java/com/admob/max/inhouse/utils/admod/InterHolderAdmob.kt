package com.admob.max.inhouse.utils.admod

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.interstitial.InterstitialAd

class InterHolderAdmob(var ads: String) {
    var inter: InterstitialAd? = null
    val mutable: MutableLiveData<InterstitialAd> = MutableLiveData()
    var check = false
}