package com.tangula.android.commons.testapp.service

import android.content.Intent
import android.util.Log
import com.tangula.android.base.TglLocalServiceBinder
import com.tangula.android.base.TglService



class TestServiceBinder:TglLocalServiceBinder(){

    init {
        Log.v("console", "create a TestServiceBinder")
    }

    override fun onStart(): Int {
        Log.v("console", "A test service is starting.")
        return super.onStart()
    }

    fun exeucte(){
        Log.v("console", "invoke execute function.")
        this.close()
        this.serviceInstance?.stopSelf()
    }
}

class TestService:TglService<TestServiceBinder>() {

    override fun onCreateBinder(intent: Intent?, flags: Int, startId: Int): TestServiceBinder {
        return TestServiceBinder()
    }
}