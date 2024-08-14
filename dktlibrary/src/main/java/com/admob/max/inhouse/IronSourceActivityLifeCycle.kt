package com.admob.max.inhouse

import android.app.Dialog
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.admob.max.inhouse.utils.SweetAlert.SweetAlertDialog

class DialogHelperActivityLifeCycle(val dialog: Dialog) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onDestroy(){
        if(dialog.isShowing){
            dialog.dismiss()
            if (AppOpenManager.getInstance().isInitialized) {
                AppOpenManager.getInstance().isAppResumeEnabled = true
            }
        }
    }
}