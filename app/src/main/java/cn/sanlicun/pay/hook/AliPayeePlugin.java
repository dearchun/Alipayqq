package cn.sanlicun.pay.hook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import cn.sanlicun.pay.Constans;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by 小饭 on 2018/7/11.
 */

public class AliPayeePlugin implements ISubPlugin {
    @Override
    public void load(XC_LoadPackage.LoadPackageParam loadPackageParam, Context context) {
        ClassLoader clazzLoader = loadPackageParam.classLoader;

        Object[] payeeArr = new Object[2];
        payeeArr[0] = Bundle.class;
        payeeArr[1] = new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam hookParam) {

                Activity ctx = (Activity) hookParam.thisObject;

                try {

                    Object tvMoney = XposedHelpers.findField(ctx.getClass(), "b").get(ctx);
                    Object tvMark = XposedHelpers.findField(ctx.getClass(), "c").get(ctx);
                    Intent intent = ctx.getIntent();
                    XposedHelpers.callMethod(tvMoney, "setText", intent.getStringExtra(Constans.MONEY));
                    XposedHelpers.callMethod(tvMark, "setText", intent.getStringExtra(Constans.MARK));
                    XposedHelpers.callMethod(tvMark, "setVisibility", View.VISIBLE);
                    Button btn = ((Button) XposedHelpers.findField(ctx.getClass(), "e").get(ctx));
                    btn.performClick();

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        };
        XposedHelpers.findAndHookMethod(Constans.SETMONEYACTIVITY, clazzLoader, "onCreate", payeeArr);


        Object[] payResArr = new Object[2];
        payResArr[0] = XposedHelpers.findClass(Constans.SETAMOUNTRES, clazzLoader);
        payResArr[1] = new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam hookParam) {

                Activity ctx = (Activity) hookParam.thisObject;
                try {
                    String money = (String) XposedHelpers.findField(ctx.getClass(), "g").get(ctx);
                    String mark = (String) XposedHelpers.callMethod(XposedHelpers.findField(ctx.getClass(), "c").get(ctx), "getUbbStr");
                    Object localObject = hookParam.args[0];
                    String qrCodeUrl = (String) XposedHelpers.findField(localObject.getClass(), "qrCodeUrl").get(localObject);


                    if (money != null) {

                        Intent intent = new Intent();
                        intent.putExtra(Constans.MONEY, money);
                        intent.putExtra(Constans.MARK, mark);
                        intent.putExtra(Constans.TYPE, Constans.ALIPAY);
                        intent.putExtra(Constans.PAY_URL, qrCodeUrl);
                        intent.setAction(Constans.QRCODE_RESULT);
                        ctx.sendBroadcast(intent);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        XposedHelpers.findAndHookMethod(Constans.SETMONEYACTIVITY, clazzLoader, "a", payResArr);

    }
}
