package com.wsheng.aggregator.bean;

/**
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
public class DynamicQueryParam {

    private String startTime;

    private String endTime;

    private String orderName;

    private String groupName;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }


}
