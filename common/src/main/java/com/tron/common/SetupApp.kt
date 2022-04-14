package com.tron.common

import android.app.Activity
import kotlin.reflect.KClass

/**
 * Created by Jumadi Janjaya date on 17/02/2022.
 * Jakarta, Indonesia.
 * Copyright (c) First Payments Indonesia. All rights reserved.
 **/
object SetupApp {

    lateinit var SplashActivity: KClass<out Activity>
    lateinit var CheckBalanceActivity: KClass<out Activity>
    var VERSION_CODE = 0

}