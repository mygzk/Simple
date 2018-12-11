package com.example.simple.model.adress;

/**
 * Created by guozhk on 2018/12/9.
 */

public class AdressBean {


    /**
     * areaId : 110100
     * id : 15
     * areaName : 北京市
     * areaCode : 010
     * areaShort : Beijing
     * cityCode : 131
     * center : 116.407394,39.904211
     * areaLevel : 2
     * areaParentId : null
     * areaDictionaryVOList : null
     */

    private String areaId;
    private int id;
    private String areaName;
    private String areaCode;
    private String areaShort;
    private String cityCode;
    private String center;
    private int areaLevel;
    private String areaParentId;
    private String areaDictionaryVOList;


    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaShort() {
        return areaShort;
    }

    public void setAreaShort(String areaShort) {
        this.areaShort = areaShort;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public int getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(int areaLevel) {
        this.areaLevel = areaLevel;
    }

    public String getAreaParentId() {
        return areaParentId;
    }

    public void setAreaParentId(String areaParentId) {
        this.areaParentId = areaParentId;
    }

    public String getAreaDictionaryVOList() {
        return areaDictionaryVOList;
    }

    public void setAreaDictionaryVOList(String areaDictionaryVOList) {
        this.areaDictionaryVOList = areaDictionaryVOList;
    }


    /**
     * 是不是全国
     *
     * @return boolean
     */
    public boolean isQuanguo() {
        if (getAreaId() != null && getAreaId().equals("0")) {
            return true;
        }

        return false;
    }


    public static AdressBean getQuanguoBean() {
        AdressBean bean = new AdressBean();
        bean.setAreaId("0");
        bean.setAreaName("全国");
        bean.setAreaLevel(1);
        return bean;

    }
}
