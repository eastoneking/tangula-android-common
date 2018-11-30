package com.tangula.android.base;

/**
 * 前台服务元数据.
 */
public class ForegroundServiceMetaInfo {
    private int serviceId;
    private int iconRes;
    private String channelId;
    private String channelName;

    @SuppressWarnings("unused")
    public ForegroundServiceMetaInfo(){
        this(0,0,"","");
    }

    @SuppressWarnings("WeakerAccess")
    public ForegroundServiceMetaInfo(int serviceId, int iconRes, String channelId, String channelName) {
        this.serviceId = serviceId;
        this.iconRes = iconRes;
        this.channelId = channelId;
        this.channelName = channelName;
    }
    @SuppressWarnings("WeakerAccess")
    public int getServiceId() {
        return serviceId;
    }
    @SuppressWarnings("unused")
    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }
    @SuppressWarnings("WeakerAccess")
    public int getIconRes() {
        return iconRes;
    }
    @SuppressWarnings("unused")
    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
    @SuppressWarnings("WeakerAccess")
    public String getChannelId() {
        return channelId;
    }
    @SuppressWarnings("unused")
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    @SuppressWarnings("WeakerAccess")
    public String getChannelName() {
        return channelName;
    }
    @SuppressWarnings("unused")
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
