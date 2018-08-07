package cn.sanlicun.pay.param;

/**
 * Created by 小饭 on 2018/7/10.
 */

public class PushBindParam    {
    private  String uId;
    private  String channelId;
    private  String requestId;

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
