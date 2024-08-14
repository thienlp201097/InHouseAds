package com.admob.max.inhouse.utils

import androidx.lifecycle.MutableLiveData
import com.applovin.mediation.MaxAd
import com.applovin.mediation.nativeAds.MaxNativeAdLoader

class NativeHolder(var adsId: String) {
    var nativeAdLoader: MaxNativeAdLoader? = null
    var native: MaxAd? = null
    var isLoad = false
    var native_mutable: MutableLiveData<MaxAd> = MutableLiveData()
}