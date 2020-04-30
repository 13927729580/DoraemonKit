package com.didichuxing.doraemondemo

import android.app.Activity
import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.didichuxing.doraemondemo.dokit.CustomDokitGroup0
import com.didichuxing.doraemondemo.dokit.CustomDokitGroup1
import com.didichuxing.doraemondemo.dokit.DemoKit
import com.didichuxing.doraemonkit.DoraemonKit
import com.didichuxing.doraemonkit.kit.AbstractKit
import com.didichuxing.doraemonkit.kit.core.group.AbsDokitGroup
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.core.ImagePipelineConfig
import java.util.*
import kotlin.collections.LinkedHashMap

/**
 * Created by zhangweida on 2018/6/22.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val kits: MutableList<AbstractKit> = ArrayList()
        kits.add(DemoKit())
        //测试环境:a49842eeebeb1989b3f9565eb12c276b
//线上环境:749a0600b5e48dd77cf8ee680be7b1b7
        // DoraemonKit.disableUpload()
        //是否显示入口icon
//        DoraemonKit.setAwaysShowMainIcon(false);
        //DoraemonKit.install(this, kits, "749a0600b5e48dd77cf8ee680be7b1b7")
        val selfKits: LinkedHashMap<AbsDokitGroup, MutableList<AbstractKit>> = linkedMapOf()
        selfKits.put(CustomDokitGroup0(), mutableListOf(DemoKit()))
        selfKits.put(CustomDokitGroup1(), mutableListOf(DemoKit()))
        DoraemonKit.init(this, selfKits = selfKits)
//        //DoraemonKit.install(this, kits, "70e78c27f9174d68668d8a66a2b66483")
        val config = ImagePipelineConfig.newBuilder(this)
                .setDiskCacheEnabled(false)
                .build()
        Fresco.initialize(this, config)

        //严格检查模式
        //StrictMode.enableDefaults();
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        private const val TAG = "App"
        var leakActivity: Activity? = null
    }
}