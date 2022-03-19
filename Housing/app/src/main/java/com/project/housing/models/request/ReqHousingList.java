package com.project.housing.models.request;

import java.io.Serializable;

public class ReqHousingList implements Serializable {

    private String startMonth;
    private String endMonth;
    private String sidoName;

    public ReqHousingList(String startMonth, String endMonth, String sidoName) {
        this.startMonth = startMonth;
        this.endMonth = endMonth;
        this.sidoName = sidoName;
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
