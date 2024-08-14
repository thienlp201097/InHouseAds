package com.admob.max.inhouse.utils.admod

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.ads.nativead.NativeAd

class NativeHolderAdmob(var ads: String){
    var nativeAd : NativeAd?= null
    var isLoad = false
    var native_mutable: MutableLiveData<NativeAd> = MutableLiveData()
}