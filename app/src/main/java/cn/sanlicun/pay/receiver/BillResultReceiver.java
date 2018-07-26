package cn.sanlicun.pay.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.sanlicun.pay.Constans;
import cn.sanlicun.pay.net.Api;
import cn.sanlicun.pay.param.PushParam;

public class BillResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String money = intent.getStringExtra(Constans.BILL_MONEY);
        String mark = intent.getStringExtra(Constans.BILL_MARK);
        String type = intent.getStringExtra(Constans.BILL_TYPE);
        String billNo = intent.getStringExtra(Constans.BILL_NO);

        PushParam pushParam = new PushParam();
        pushParam.setMark(mark);
        pushParam.setMoney(money);
        pushParam.setBillNo(billNo);
        pushParam.setType(type);

        Api.PUSH_ORDER_RESULT(context, pushParam, String.class, 1);
    }
}
