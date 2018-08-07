package cn.sanlicun.pay.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import cn.sanlicun.pay.Constans;
import cn.sanlicun.pay.MainActivity;
import cn.sanlicun.pay.R;
import cn.sanlicun.pay.net.Api;
import cn.sanlicun.pay.net.INet;
import cn.sanlicun.pay.param.LoginParam;
import cn.sanlicun.pay.util.SPUtils;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity  implements INet {



    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    private  String username;
    private  String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    private void attemptLogin() {
          username=mEmailView.getText().toString();
          pwd=mPasswordView.getText().toString();


        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(pwd)){}else {
            fetch(1);

        }




    }






    @Override
    public void fetch(int TAG) {

        LoginParam p=new LoginParam();
        p.setPwd(pwd);
        p.setUsername(username);

        Api.LOGIN(this,p,String.class,TAG);


    }

    @Override
    public void response(int TAG, Object o) {


        if (TAG==1){
            Log.i("tag",o.toString());

            SPUtils.setParam(this, Constans.LOGIN, username);
            startActivity(new Intent(this, MainActivity.class));



        }

    }


}

