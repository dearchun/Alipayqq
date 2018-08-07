package cn.sanlicun.pay.ui.activity;

import android.content.Intent;
import android.os.Bundle;

import cn.sanlicun.pay.MainActivity;
import cn.sanlicun.pay.R;
import cn.sanlicun.pay.util.AppUtils;

/**
 * @author s
 *
 * 进行数据跳转 无缓存则登录
 *
 * 有缓存
 */
public class LaunchActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        if (AppUtils.IS_LOGIN(this)) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }


    }
}
