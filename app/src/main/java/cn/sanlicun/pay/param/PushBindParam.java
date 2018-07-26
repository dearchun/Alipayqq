package cn.sanlicun.pay.param;

/**
 * Created by 小饭 on 2018/7/10.
 */

public class PushBindParam    {
    private  String userId;
    private  String channelId;
    private  String requestId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
