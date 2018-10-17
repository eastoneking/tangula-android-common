package com.tangula.android.commons.testapp

import android.os.Bundle
import android.util.Log
import android.view.View
import com.tangula.android.base.TglBasicActivity
import com.tangula.android.base.TglService
import com.tangula.android.commons.testapp.service.TestService
import com.tangula.android.commons.testapp.service.TestServiceBinder
import kotlinx.android.synthetic.main.activity_test_main.*

class TestMainActivity : TglBasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_main)
        vw_btn_start_test_service.setOnClickListener(this::onClickStartTestService)
        vw_btn_bind_test_service.setOnClickListener(this::onClickBindTestService)
    }

    fun onClickStartTestService(vw: View){
        TglService.startService(this, TestService::class.java)
    }

    fun onClickBindTestService(vw: View){
        val conn = TglService.bind2NormalService(this, TestService::class.java, {binder:TestServiceBinder->

            binder.exeucte()

        }, {_->}, {})

    }

}
