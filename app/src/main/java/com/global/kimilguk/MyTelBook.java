package com.global.kimilguk;

public class MyTelBook {
    //멤버 변수 선언
    String telName;
    String telDetail;
    //생성자 메서드 추가
    public MyTelBook(String name, String detail) {
        this.telName = name;
        this.telDetail = detail;
    }

    public String getTelName() {
        return telName;
    }

    public void setTelName(String telName) {
        this.telName = telName;
    }

    public String getTelDetail() {
        return telDetail;
    }

    public void setTelDetail(String telDetail) {
        this.telDetail = telDetail;
    }
}
