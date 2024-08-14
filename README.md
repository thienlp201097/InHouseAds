<!-- GETTING STARTED -->

//Add it in your root build.gradle at the end of repositories:
  maven { url = uri("https://jitpack.io") }
  
  implementation("com.github.thienlp201097:DktechLib:1.0.0")
// Init Admob, Applovin:

  Init Admob:
  AdmobUtils.initAdmob(this, 10000, isDebug = true, isEnableAds = true)

  ApplovinUtil.initApplovin(application, "your-key",testAds = true,enableAds = true, initialization = object : ApplovinUtil.Initialization{
                    override fun onInitSuccessful() {
                       //Start load Ads
                    }
                })
//Init AppOnResume:

 AppOpenManager.getInstance().init(application, "your-id")
 AppOpenManager.getInstance().disableAppResumeWithActivity(SplashActivity::class.java)



  
