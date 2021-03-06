package cn.sanlicun.pay.net;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import cn.sanlicun.pay.model.BaseModel;


/**
 * Created by 小饭 on 2018/7/4.
 */

public class BaseApi {


    private static final String TAG = "tag";

    public static void send(final Context context, String url, final Object param, final Class clazz, final int TAG) {


        if (!url.startsWith("http")) {
            url = "http://sanlicun.cn/" + url;
        }
        Log.d("tag", url);


        RequestQueue requestQueue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        BaseModel model = JSON.parseObject(response, BaseModel.class);


                        Log.d("tag", response);


                        if (context != null&&context instanceof  INet) {
                            if (model.getStatus() == 1) {

                                ((INet) context).response(TAG, model.getData(clazz));
                            } else {
                                ((INet) context).response(TAG, "");
                            }

                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                //在这里设置需要post的参数
                Map<String, String> map = new HashMap<String, String>();

                try {
                    String p = new String(Base64.encode(JSONObject.toJSONString(param).getBytes(), Base64.DEFAULT), "utf-8");
                    Log.d("tag", p);

                    map.put("param", p);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


                return map;
            }
        };

        requestQueue.add(stringRequest);

    }
}
