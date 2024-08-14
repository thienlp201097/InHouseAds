package com.lib.inhouse

import com.admob.max.inhouse.AppOpenManager
import com.admob.max.inhouse.adjust.AdjustUtils
import com.admob.max.inhouse.application.AdsApplication

class MyApplication : AdsApplication() {
    override fun onCreateApplication() {
        AdjustUtils.initAdjust(this,"",false)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level == TRIM_MEMORY_UI_HIDDEN){
            AppOpenManager.getInstance().timeToBackground = System.currentTimeMillis()
        }
    }
}