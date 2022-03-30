package com.project.housing.models.response;

import com.tickaroo.tikxml.annotation.PropertyElement;
import com.tickaroo.tikxml.annotation.Xml;

import java.io.Serializable;

@Xml(name = "item")
public class Item implements Serializable {
    @PropertyElement(name = "bsnsMbyNm") // 건설업체
    private String buildCompanyName;
    @PropertyElement(name = "houseDtlSecdNm") // 주택구분
    private String houseDetailSectionedName;
    @PropertyElement(name = "houseManageNo") // 주택관리번호
    private String houseManageNumber;
    @PropertyElement(name = "houseNm") // 주택명
    private String houseName;
    @PropertyElement(name = "pblancNo") // 공고번호
    private String noticeNumber;
    @PropertyElement(name = "przwnerPresnatnDe") // 당첨자 발표
    private String winnerPresentDate;
    @PropertyElement(name = "rceptBgnde") // 청약접수 시작일
    private String receiptStartDate;
    @PropertyElement(name = "rceptEndde") // 청약접수 종료일
    private String receiptEndDate;
    @PropertyElement(name = "rcritPblancDe") // 모집공고일
    private String recruitmentNoticeDate;
    @PropertyElement(name = "rentSecdNm") // 분양/임대
    private String rentOrSaleName;
    @PropertyElement(name = "sido") // 공급지역
    private String sido;
    @PropertyElement(name = "cntrctcnclsbgnde") // 계약 시작일
    private String startContract;
    @PropertyElement(name = "cntrctcnclsendde") // 계약 종료일
    private String endContract;
    @PropertyElement(name = "gnrlrnk1crsparearceptpd") // 1순위 접수일 해당지역
    private String rnk1CrspArea;
    @PropertyElement(name = "gnrlrnk1etcggrcptdepd") // 1순위 접수일 경기지역
    private String rnk1EtcGg;
    @PropertyElement(name = "gnrlrnk1etcarearcptdepd") // 1순위 접수일 기타지역
    private String rnk1EtcArea;
    @PropertyElement(name = "gnrlrnk2crsparearceptpd") // 2순위 접수일 해당지역
    private String rnk2CrspArea;
    @PropertyElement(name = "gnrlrnk2etcggrcptdepd") // 2순위 접수일 경기지역
    private String rnk2EtcGg;
    @PropertyElement(name = "gnrlrnk2etcarearcptdepd") // 2순위 접수일 기타지역
    private String rnk2EtcArea;
    @PropertyElement(name = "hmpgadres") // 홈페이지
    private String homepageAddress;
    @PropertyElement(name = "hssplyadres") // 공급위치(주소)
    private String houseAddress;
    @PropertyElement(name = "spsplyrceptbgnde") // 특별 공급 접수 시작일
    private String specialReceiptStartDate;
    @PropertyElement(name = "spsplyrceptendde") // 특별 공급 접수 종료일
    private String specialReceiptEndDate;
    @PropertyElement(name = "totsuplyhshldco") // 공급 규모
    private String totalSupply;

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

    public String getStartContract() {
        return startContract;
    }

    public void setStartContract(String startContract) {
        this.startContract = startContract;
    }

    public String getEndContract() {
        return endContract;
    }

    public void setEndContract(String endContract) {
        this.endContract = endContract;
    }

    public String getRnk1CrspArea() {
        return rnk1CrspArea;
    }

    public void setRnk1CrspArea(String rnk1CrspArea) {
        this.rnk1CrspArea = rnk1CrspArea;
    }

    public String getRnk1EtcGg() {
        return rnk1EtcGg;
    }

    public void setRnk1EtcGg(String rnk1EtcGg) {
        this.rnk1EtcGg = rnk1EtcGg;
    }

    public String getRnk1EtcArea() {
        return rnk1EtcArea;
    }

    public void setRnk1EtcArea(String rnk1EtcArea) {
        this.rnk1EtcArea = rnk1EtcArea;
    }

    public String getRnk2CrspArea() {
        return rnk2CrspArea;
    }

    public void setRnk2CrspArea(String rnk2CrspArea) {
        this.rnk2CrspArea = rnk2CrspArea;
    }

    public String getRnk2EtcGg() {
        return rnk2EtcGg;
    }

    public void setRnk2EtcGg(String rnk2EtcGg) {
        this.rnk2EtcGg = rnk2EtcGg;
    }

    public String getRnk2EtcArea() {
        return rnk2EtcArea;
    }

    public void setRnk2EtcArea(String rnk2EtcArea) {
        this.rnk2EtcArea = rnk2EtcArea;
    }

    public String getHomepageAddress() {
        return homepageAddress;
    }

    public void setHomepageAddress(String homepageAddress) {
        this.homepageAddress = homepageAddress;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public String getSpecialReceiptStartDate() {
        return specialReceiptStartDate;
    }

    public void setSpecialReceiptStartDate(String specialReceiptStartDate) {
        this.specialReceiptStartDate = specialReceiptStartDate;
    }

    public String getSpecialReceiptEndDate() {
        return specialReceiptEndDate;
    }

    public void setSpecialReceiptEndDate(String specialReceiptEndDate) {
        this.specialReceiptEndDate = specialReceiptEndDate;
    }

    public String getTotalSupply() {
        return totalSupply;
    }

    public void setTotalSupply(String totalSupply) {
        this.totalSupply = totalSupply;
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
                ", startContract='" + startContract + '\'' +
                ", endContract='" + endContract + '\'' +
                ", rnk1CrspArea='" + rnk1CrspArea + '\'' +
                ", rnk1EtcGg='" + rnk1EtcGg + '\'' +
                ", rnk1EtcArea='" + rnk1EtcArea + '\'' +
                ", rnk2CrspArea='" + rnk2CrspArea + '\'' +
                ", rnk2EtcGg='" + rnk2EtcGg + '\'' +
                ", rnk2EtcArea='" + rnk2EtcArea + '\'' +
                ", homepageAddress='" + homepageAddress + '\'' +
                ", houseAddress='" + houseAddress + '\'' +
                ", specialReceiptStartDate='" + specialReceiptStartDate + '\'' +
                ", specialReceiptEndDate='" + specialReceiptEndDate + '\'' +
                ", totalSupply='" + totalSupply + '\'' +
                '}';
    }
}


