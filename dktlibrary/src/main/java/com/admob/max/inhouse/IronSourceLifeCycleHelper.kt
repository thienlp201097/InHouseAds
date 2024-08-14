//package com.admob.max.inhouse
//
//import android.app.Activity
//import android.app.Application
//import android.os.Bundle
//import android.util.Log
//
//object IronSourceLifeCycleHelper: Application.ActivityLifecycleCallbacks {
//    private const val TAG = "IrSrcLifecycleCallbacks"
//    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
//        Log.d(TAG,"onActivityCreated at ${p0.localClassName}")
//
//    }
//
//    override fun onActivityStarted(p0: Activity) {
//        Log.d(TAG,"onActivityStarted at ${p0.localClassName}")
//    }
//
//    override fun onActivityResumed(p0: Activity) {
////        IronSource.onResume(p0);
//        if(p0.javaClass.simpleName.equals("ControllerActivity")){
//            return
//        }
////        if(ApplovinUtil.isLoadInterstitialFailed){
////            ApplovinUtil.loadInterstitials()
////        }
//    }
//
//    override fun onActivityPaused(p0: Activity) {
////        IronSource.onPause(p0);
//    }
//
//    override fun onActivityStopped(p0: Activity) {
//        Log.d(TAG,"onActivityStopped at ${p0.localClassName}")
//    }
//
//    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
//        Log.d(TAG,"onActivitySaveInstanceState at ${p0.localClassName}")
//    }
//
//    override fun onActivityDestroyed(p0: Activity) {
//        Log.d(TAG,"onActivityDestroyed at ${p0.localClassName}")
//    }
//}