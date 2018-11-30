package com.tangula.android.base;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tangula.android.utils.ActivityResultUtils;
import com.tangula.android.utils.ApplicationUtils;
import com.tangula.android.utils.PermissionUtils;

public abstract class TglBasicActivity extends AppCompatActivity {

    @Override
    public void onCreate( @Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        if(ApplicationUtils.APP==null){
            ApplicationUtils.APP = getApplication();
        }
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermisionsFinish(requestCode, permissions ,grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        ActivityResultUtils.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
