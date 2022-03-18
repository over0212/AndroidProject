package com.project.housing.models.response;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

@Xml(name = "item")
public class Item {
    @PropertyElement(name="bsnsMbyNm") // 건설업체
    private String buildCompanyName;
    @PropertyElement(name="houseDtlSecdNm") // 주택구분
    private String houseDetailSectionedName;
    @PropertyElement(name="houseManageNo") // 주택관리번호
    private String houseManageNumber;
    @PropertyElement(name="houseNm") // 주택명
    private String houseName;
    @PropertyElement(name="pblancNo") // 공고번호
    private String noticeNumber;
    @PropertyElement(name="przwnerPresnatnDe") // 당첨자 발표
    private String winnerPresentDate;
    @PropertyElement(name="rceptBgnde") // 청약접수 시작일
    private String receiptStartDate;
    @PropertyElement(name="rceptEndde") // 청약접수 종료일
    private String receiptEndDate;
    @PropertyElement(name="rcritPblancDe") // 모집공고일
    private String recruitmentNoticeDate;
    @PropertyElement(name="rentSecdNm") // 분양/임대
    private String rentOrSaleName;
    @PropertyElement(name="sido") // 공급지역
    private String sido;

    public String getBuildCompanyName() {
        return buildCompanyName;
    }

    public void setBuildCompanyName(String buildCompanyName) {
        this.buildCompanyName = buildCompanyName;
    }

    public String getHouseDetailSectionedName() {
        return houseDetailSectionedName;
    }

    public void setHouseDetailSectionedName(String houseDetailSectionedName) {
        this.houseDetailSectionedName = houseDetailSectionedName;
    }

    public String getHouseManageNumber() {
        return houseManageNumber;
    }

    public void setHouseManageNumber(String houseManageNumber) {
        this.houseManageNumber = houseManageNumber;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getNoticeNumber() {
        return noticeNumber;
    }

    public void setNoticeNumber(String noticeNumber) {
        this.noticeNumber = noticeNumber;
    }

    public String getWinnerPresentDate() {
        return winnerPresentDate;
    }

    public void setWinnerPresentDate(String winnerPresentDate) {
        this.winnerPresentDate = winnerPresentDate;
    }

    public String getReceiptStartDate() {
        return receiptStartDate;
    }

    public void setReceiptStartDate(String receiptStartDate) {
        this.receiptStartDate = receiptStartDate;
    }

    public String getReceiptEndDate() {
        return receiptEndDate;
    }

    public void setReceiptEndDate(String receiptEndDate) {
        this.receiptEndDate = receiptEndDate;
    }

    public String getRecruitmentNoticeDate() {
        return recruitmentNoticeDate;
    }

    public void setRecruitmentNoticeDate(String recruitmentNoticeDate) {
        this.recruitmentNoticeDate = recruitmentNoticeDate;
    }

    public String getRentOrSaleName() {
        return rentOrSaleName;
    }

    public void setRentOrSaleName(String rentOrSaleName) {
        this.rentOrSaleName = rentOrSaleName;
    }

    public String getSido() {
        return sido;
    }

    public void setSido(String sido) {
        this.sido = sido;
    }

    @Override
    public String toString() {
        return "Item{" +
                "buildCompanyName='" + buildCompanyName + '\'' +
                ", houseDetailSectionedName='" + houseDetailSectionedName + '\'' +
                ", houseManageNumber='" + houseManageNumber + '\'' +
                ", houseName='" + houseName + '\'' +
                ", noticeNumber='" + noticeNumber + '\'' +
                ", winnerPresentDate='" + winnerPresentDate + '\'' +
                ", receiptStartDate='" + receiptStartDate + '\'' +
                ", receiptEndDate='" + receiptEndDate + '\'' +
                ", recruitmentNoticeDate='" + recruitmentNoticeDate + '\'' +
                ", rentOrSaleName='" + rentOrSaleName + '\'' +
                ", sido='" + sido + '\'' +
                '}';
    }
}


