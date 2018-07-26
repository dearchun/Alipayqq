package cn.sanlicun.pay.hook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.Iterator;
import java.util.Map;

import cn.sanlicun.pay.Constans;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Created by Mr Ding on 2018/7/25.
 */

public class QQPayeePlugin {


    public void load(ClassLoader paramClassLoader) {
        XposedHelpers.findAndHookMethod("com.tenpay.sdk.activity.QrcodePayActivity", paramClassLoader, "onCreate", new Object[]{Bundle.class, new XC_MethodHook() {
            @SuppressWarnings("ResourceType")
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam) {
                Activity ctx = (Activity) paramAnonymousMethodHookParam.thisObject;

                XposedBridge.log("QrcodePayActivity---->>onCreate");

                XposedHelpers.setIntField(ctx, "n", 1);
                XposedHelpers.setBooleanField(ctx, "o", true);
                XposedHelpers.callMethod(ctx, "d", new Object[0]);
                View view = new View(ctx);
                view.setId(2131363428);
                XposedHelpers.callMethod(ctx, "onClick", new Object[]{view});

                XposedBridge.log("QrcodePayActivity---->>end");
            }
        }});
        XposedHelpers.findAndHookMethod("com.tenpay.sdk.activity.QrcodeSettingActivity", paramClassLoader, "onCreate", new Object[]{Bundle.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam) {
                Activity ctx = (Activity) paramAnonymousMethodHookParam.thisObject;
                XposedBridge.log("QrcodeSettingActivity---->>onCreate");

             /*   localObject1 = new m((Context)localObject1);
                Object localObject2 = ((m)localObject1).a();
                String str = ((n)localObject2).getMoney();
                localObject2 = ((n)localObject2).getMark();*/
                Intent intent = ctx.getIntent();
                String money = intent.getStringExtra(Constans.MONEY);
                String mark = intent.getStringExtra(Constans.MARK);
                XposedBridge.log("QrcodeSettingActivity---->>onCreate"+money);



                if ((!TextUtils.isEmpty(money)) && (!TextUtils.isEmpty(mark))) {
                    //((m) localObject1).b(str, (String) localObject2);
                    XposedHelpers.callMethod(XposedHelpers.getObjectField(paramAnonymousMethodHookParam.thisObject, "d"), "setText", new Object[]{money});
                    XposedHelpers.callMethod(XposedHelpers.getObjectField(paramAnonymousMethodHookParam.thisObject, "e"), "setText", new Object[]{mark});
                    ((Button) XposedHelpers.getObjectField(paramAnonymousMethodHookParam.thisObject, "c")).performClick();
                }
            }
        }});
        XposedHelpers.findAndHookMethod("com.tenpay.sdk.activity.NetBaseActivity", paramClassLoader, "a", new Object[]{String.class, Map.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam) {
                Activity localActivity = (Activity) paramAnonymousMethodHookParam.thisObject;
                Object localObject = (Map) paramAnonymousMethodHookParam.args[1];
                String a = (String) paramAnonymousMethodHookParam.args[0];
                StringBuffer localStringBuffer = new StringBuffer();


                Toast.makeText(localActivity, localObject + a, Toast.LENGTH_LONG).show();
                localObject = ((Map) localObject).entrySet().iterator();
                while (((Iterator) localObject).hasNext()) {
                    Map.Entry localEntry = (Map.Entry) ((Iterator) localObject).next();
                    localStringBuffer.append((String) localEntry.getKey());
                    localStringBuffer.append("=");
                    localStringBuffer.append((String) localEntry.getValue());
                    localStringBuffer.append("&");
                }

                XposedBridge.log("QrcodeSettingActivity---->>onCreate" + localStringBuffer);
              /*  k.b(localActivity, "NetBaseActivity---->>a");
                k.b(localActivity, paramAnonymousMethodHookParam + localStringBuffer.substring(0, localStringBuffer.toString().length() - 1));*/
            }
        }});
        XposedHelpers.findAndHookMethod("com.tenpay.sdk.h.y", paramClassLoader, "c", new Object[]{String.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam) {
                XposedBridge.log((String) paramAnonymousMethodHookParam.args[0]);
            }
        }});
        XposedHelpers.findAndHookMethod("com.tenpay.sdk.activity.QrcodePayActivity", paramClassLoader, "a", new Object[]{String.class, JSONObject.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam) {
                //  k.b((Activity)paramAnonymousMethodHookParam.thisObject, ((JSONObject)paramAnonymousMethodHookParam.args[1]).toString());
            }
        }});
        XposedHelpers.findAndHookMethod("com.tenpay.sdk.activity.QrcodePayActivity", paramClassLoader, "c", new Object[]{String.class, new XC_MethodHook() {
            protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam) {
                Activity localActivity = (Activity) paramAnonymousMethodHookParam.thisObject;
                String str1 = (String) XposedHelpers.getObjectField(paramAnonymousMethodHookParam.thisObject, "eb");
                String str2 = (String) XposedHelpers.getObjectField(paramAnonymousMethodHookParam.thisObject, "ec");
                Object localObject = (String) paramAnonymousMethodHookParam.args[0];
                str1 = "https://i.qianbao.qq.com/wallet/sqrcode.htm?m=tenpay&f=wallet&" + "u=" + str1 + "&a=1&n=" + str2 + "&ac=" + (String) localObject;
                str2 = (String) XposedHelpers.getObjectField(paramAnonymousMethodHookParam.thisObject, "aa");
                String ab = (String) XposedHelpers.getObjectField(paramAnonymousMethodHookParam.thisObject, "ab");
                if ((!TextUtils.isEmpty(str2)) && (!TextUtils.isEmpty(ab)) && (((String) localObject).length() > 64)) {
                    ///   g.a("调用增加数据方法==>QQ");
                    localObject = new Intent();
                    ((Intent) localObject).putExtra("money", str2 + "");
                    ((Intent) localObject).putExtra("mark", ab);
                    ((Intent) localObject).putExtra("type", "qq");
                    ((Intent) localObject).putExtra("payurl", str1);





                    /*((Intent)localObject).setAction(c.a);
                    localActivity.sendBroadcast((Intent)localObject);
                    if (k.c(localActivity) > 2) {
                        localActivity.finish();
                    }*/
                }
            }
        }});
    }
}
