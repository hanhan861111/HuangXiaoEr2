package com.zz.huangxiaoer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.zz.huangxiaoer.activity.HomepageActivity;
import com.zz.huangxiaoer.utils.CommonUtils;

public class MainActivity extends AppCompatActivity {

    private EditText etnum_login;
    private EditText etpass_login;
    private Button code_login;
    private Button entry_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initView();
        initLogin();
    }

    private void initLogin() {
        entry_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtils.startActivity(MainActivity.this, HomepageActivity.class);
            }
        });
    }

    private void initView() {
        etnum_login = (EditText) findViewById(R.id.etnum_login);
        etpass_login = (EditText) findViewById(R.id.etpass_login);
        code_login = (Button) findViewById(R.id.code_login);
        entry_login = (Button) findViewById(R.id.entry_login);
    }
}
