package cn.sanlicun.pay.hook;

import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

import cn.sanlicun.pay.Constans;
import dalvik.system.BaseDexClassLoader;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static cn.sanlicun.pay.Constans.ACTION_LAUNCH_ALIPAY_WALLET;

/**
 * Created by Mr Ding on 2018/7/25.
 */

public class QQPlugin implements IPlugin {
    private int isAlipay = 0;

    @Override
    public void load(final XC_LoadPackage.LoadPackageParam loadPackageParam) {

        try {

            ClassLoader clazzLoader = loadPackageParam.classLoader;

            if (loadPackageParam.packageName.contains(Constans.QQ)) {

                XposedHelpers.findAndHookMethod(Application.class, "attach", new Object[]{Context.class, new XC_MethodHook() {
                    protected void afterHookedMethod(XC_MethodHook.MethodHookParam param) {
                        try {
                            super.afterHookedMethod(param);
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                        Context ctx = (Context) param.args[0];

                        if ((Constans.QQ.equals(getProcessName((Context) param.thisObject))) && (isAlipay < 1)) {
                            registerLaunchReceiver((Context) param.thisObject, loadPackageParam.classLoader);


                        }
                    }
                }});
                XposedHelpers.findAndHookConstructor("dalvik.system.BaseDexClassLoader", clazzLoader, new Object[]{String.class, File.class, String.class, ClassLoader.class, new XC_MethodHook() {
                    protected void afterHookedMethod(XC_MethodHook.MethodHookParam paramAnonymousMethodHookParam) {
                        Log.e("插件版本", paramAnonymousMethodHookParam.args[0] + "");
                        if (paramAnonymousMethodHookParam.args[0].toString().contains("qwallet_plugin.apk")) {
                            BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) paramAnonymousMethodHookParam.thisObject;
                            new QQPayeePlugin().load(baseDexClassLoader);
                        }
                    }
                }});

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerLaunchReceiver(Context context, final ClassLoader classLoader) {

        IntentFilter intentFilter = new IntentFilter(ACTION_LAUNCH_ALIPAY_WALLET);


        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String str1 = intent.getStringExtra(Constans.MONEY);
                String str2 = intent.getStringExtra(Constans.MARK);
                if ((!TextUtils.isEmpty(str1)) && (!TextUtils.isEmpty(str2)))
                {
                    new m(paramContext).a(paramIntent.getStringExtra("money"), paramIntent.getStringExtra("mark"));
                    long l = System.currentTimeMillis();
                    Intent qqIntent= new Intent("android.intent.action.VIEW", Uri.parse("mqqapi://wallet/open?src_type=web&viewtype=0&version=1&view=7&entry=1&seq=" + l));
                    qqIntent.addFlags(268435456);
                    context.startActivity(qqIntent);
                }
            }
        };

        context.registerReceiver(receiver, intentFilter);


    }

    private void launchWallet(Context context, Class clazz, String money, String mark) {

        Intent intent = new Intent(context, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(Constans.MARK, mark);
        intent.putExtra(Constans.MONEY, money);
        context.startActivity(intent);
    }

    public String getProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if (processInfo.pid == pid) {
                return processInfo.processName;
            }
        }
        return null;
    }

}
