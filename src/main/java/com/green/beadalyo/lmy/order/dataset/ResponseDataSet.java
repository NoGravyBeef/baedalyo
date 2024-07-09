package com.green.beadalyo.lmy.order.dataset;

public interface ResponseDataSet {
    Integer SUCCESS_CODE= 1;

    String POST_ORDER_SUCCESS = "주문하기 성공";
    String CANCEL_ORDER_SUCCESS = "주문 취소 성공";
    String COMPLETE_ORDER_SUCCESS = "주문 완료 성공";
    String USER_ORDER_LIST_SUCCESS = "유저의 진행중인 주문 불러오기 완료";
    String CONFIRM_ORDER_SUCCESS = "주문 접수 완료";
    String RES_ORDER_NO_CONFIRM_LIST_SUCCESS = "상점의 접수 전 주문정보 불러오기 완료";
    String RES_ORDER_CONFIRM_LIST_SUCCESS = "상점의 접수 후 주문정보 불러오기 완료";
    String ORDER_INFO_SUCCESS = "주문정보 상세보기 완료";


}
