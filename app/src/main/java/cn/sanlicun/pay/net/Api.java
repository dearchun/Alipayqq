package cn.sanlicun.pay.net;

import android.content.Context;

/**
 * Created by 小饭 on 2018/7/4.
 */

public class Api extends BaseApi { 

    /**
     * 上传url
     * @param context
     * @param param
     * @param clazz
     * @param TAG
     */
    public static void PUSH_URL(Context context, Object param, final Class clazz, final int TAG) {
        send(context, "/pay/api/5b29d566d2414", param, clazz, TAG);
    }

    /**
     * 绑定设备
     * @param context
     * @param param
     * @param clazz
     * @param TAG
     */
    public static void BIND_DEVICE(Context context, Object param, final Class clazz, final int TAG) {
        send(context, "/pay/api/5b4441682af1a", param, clazz, TAG);


    }

    /**
     * 解除绑定
     * @param context
     * @param param
     * @param clazz
     * @param TAG
     */
    public static void UNBIND_DEVICE(Context context, Object param, final Class clazz, final int TAG) {
        send(context, "/pay/api/5b4441a279629", param, clazz, TAG);


    }

    /**
     * 上传支付结果
     * @param context
     * @param param
     * @param clazz
     * @param TAG
     */
    public static void PUSH_ORDER_RESULT(Context context, Object param, final Class clazz, final int TAG) {
        send(context, "/pay/notify/Index/notify", param, clazz, TAG);


    }
}
