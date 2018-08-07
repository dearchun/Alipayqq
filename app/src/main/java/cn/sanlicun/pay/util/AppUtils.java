package cn.sanlicun.pay.util;

import android.content.Context;
import android.text.TextUtils;

import cn.sanlicun.pay.Constans;

/**
 * Created by Mr Ding on 2018/8/7.
 */

public class AppUtils {

    /**
     * 是否登录
     * @param ctx
     * @return
     */
    public static boolean IS_LOGIN(Context ctx) {

        return SPUtils.getParam(ctx, Constans.LOGIN, "") != null && !TextUtils.isEmpty(String.valueOf(SPUtils.getParam(ctx, Constans.LOGIN, "")));


    }

    public static String  GET_USERNAME(Context ctx) {
        return SPUtils.getParam(ctx, Constans.LOGIN, "")==null?"" :String.valueOf(SPUtils.getParam(ctx, Constans.LOGIN, "")) ;


    }
}
