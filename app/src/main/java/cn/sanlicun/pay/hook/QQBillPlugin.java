//package cn.sanlicun.pay.hook;
//
//import android.app.Activity;
//import android.content.Intent;
//
//import de.robv.android.xposed.XC_MethodHook;
//import de.robv.android.xposed.XposedBridge;
//import de.robv.android.xposed.XposedHelpers;
//
///**
// * Created by Mr Ding on 2018/7/26.
// */
//
//public class QQBillPlugin {
//
//      XposedHelpers.findAndHookMethod("com.tencent.mobileqq.app.MessageHandlerUtils", paramClassLoader, "a", new Object[] { "com.tencent.mobileqq.app.QQAppInterface", "com.tencent.mobileqq.data.MessageRecord", Boolean.TYPE, new XC_MethodHook()
//    {
//        protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
//        {
//            paramAnonymousMethodHookParam = (byte[])XposedHelpers.getObjectField(paramAnonymousMethodHookParam.args[1], "msgData");
//            paramAnonymousMethodHookParam = XposedHelpers.callStaticMethod(XposedHelpers.findClass("com.tencent.mobileqq.structmsg.StructMsgFactory", paramClassLoader), "a", new Object[] { paramAnonymousMethodHookParam });
//            if (paramAnonymousMethodHookParam != nu
// ll)
//            {
//                String str2 = (String)XposedHelpers.callMethod(paramAnonymousMethodHookParam, "getXml", new Object[0]);
//                if (str2.contains("转账金额"))
//                {
//                    paramAnonymousMethodHookParam = p.a(str2, "transId=", "\"");
//                    String str1 = p.a(str2, "转账金额：", "</summary>");
//                    str2 = p.a(str2, "转账留言：", "</summary>");
//                    XposedBridge.log("收到qq支付订单：" + paramAnonymousMethodHookParam + "==" + str1 + "==" + str2);
//                    Intent localIntent = new Intent();
//                    localIntent.putExtra("bill_no", paramAnonymousMethodHookParam);
//                    localIntent.putExtra("bill_money", str1);
//                    localIntent.putExtra("bill_mark", str2);
//                    localIntent.putExtra("bill_type", "qq");
//                    localIntent.setAction(b.a);
//                    paramContext.sendBroadcast(localIntent);
//                }
//            }
//        }
//    } });
//      XposedHelpers.findAndHookMethod("com.tencent.mobileqq.activity.SplashActivity", paramClassLoader, "doOnResume", new Object[] { new XC_MethodHook()
//    {
//        protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam)
//        {
//            paramAnonymousMethodHookParam = (Activity)paramAnonymousMethodHookParam.thisObject;
//            k.a(k.e(paramAnonymousMethodHookParam), "qq", paramAnonymousMethodHookParam);
//        }
//    } });
//}
