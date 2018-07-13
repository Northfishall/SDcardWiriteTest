package com.example.a37013.sdcardstore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import LoginService.LoginService;

public class MainActivity extends AppCompatActivity {
    private EditText et_username = null;
    private EditText et_password = null;
    private CheckBox cb_remeber_password = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.et_username = this.findViewById(R.id.et_username);
        this.et_password = this.findViewById(R.id.et_password);
        this.cb_remeber_password = this.findViewById(R.id.cb_remember_psw);
        HashMap<String, String> info = LoginService.getInfo(this);
        if (info != null) {
            this.et_username.setText(info.get("username"));
            this.et_password.setText(info.get("password"));
        }
    }
    public void login(View view) {
        String username = this.et_username.getText().toString().trim();
        String password = this.et_password.getText().toString().trim();
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            if (this.cb_remeber_password.isChecked()) {
                boolean result = LoginService.saveInfo(this, username, password);
                if (result) {
                    Toast.makeText(this, "保存密码成功", Toast.LENGTH_SHORT).show();
                }
            }
            if ("aaa".equals(username) && "123".equals(password)) {
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
