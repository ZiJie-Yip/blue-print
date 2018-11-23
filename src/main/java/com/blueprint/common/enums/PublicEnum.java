package com.blueprint.common.enums;

/**
 * @Author: ZiJie.Yip
 * @Description:公共枚举. 针对只有“是”，“否”两种状态
 * @date: 2018/11/23 19:12
 */
public enum PublicEnum {

    NO(0, "否", "N", "no"),
    YES(1, "是", "Y", "yes");


    /**
     * 状态
     */
    private int state;

    /**
     * 描述
     */
    private String desc;

    /**
     * 缩写
     */
    private String ab;

    /**
     * 英文
     */
    private String en;

    PublicEnum(int state, String desc, String ab, String en){
        this.state = state;
        this.desc = desc;
        this.ab = ab;
        this.en = en;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAb() {
        return ab;
    }

    public void setAb(String ab) {
        this.ab = ab;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }
}
