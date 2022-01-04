package com.global.kimilguk;

public class MovieVO {
    String rnum;//순번을 출력
    String rank;//해당일자의 박스오피스 순위를 출력
    String rankInten;//전일대비 순위의 증감분을 출력 intensity
    String rankOldAndNew;//랭킹에 신규진입여부를 출력
    String movieCd;//영화의 대표코드를 출력
    String movieNm;//영화명(국문)을 출력
    String openDt;//영화의 개봉일을 출력
    String salesAmt;//해당일의 매출액을 출력
    String salesShare;//해당일자 상영작의 매출총액 대비 해당 영화의 매출비율을 출력
    String salesInten;//전일 대비 매출액 증감분을 출력
    String salesChange;//전일 대비 매출액 증감 비율을 출력
    String salesAcc;//누적매출액을 출력
    String audiCnt;//해당일의 관객수를 출력 audience
    String audiInten;//전일 대비 관객수 증감분을 출력
    String audiChange;//전일 대비 관객수 증감 비율을 출력
    String audiAcc;//누적관객수를 출력
    String scrnCnt;//해당일자에 상영한 스크린수를 출력
    String showCnt;//해당일자에 상영된 횟수를 출력
}
