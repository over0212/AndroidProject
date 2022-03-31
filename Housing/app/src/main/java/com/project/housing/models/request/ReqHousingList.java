package com.project.housing.models.request;

import java.io.Serializable;

public class ReqHousingList implements Serializable {

    // 공통으로 들어가는 요청(List 로 가져오는 간략한 데이터)
    private String startMonth;
    private String endMonth;
    
    // APT
    private String sidoName;
    // 오피스텔
    private int searchHouseSecd;
    // APT 무순위
    private String searchName;

    public ReqHousingList(String startMonth, String endMonth, String sidoName) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.sidoName = sidoName;
    }

    public ReqHousingList(String startMonth, String endMonth){
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public String getSidoName() {
        return sidoName;
    }
}
