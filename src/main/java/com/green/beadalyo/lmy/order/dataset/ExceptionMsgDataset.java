package com.green.beadalyo.lmy.order.dataset;

public interface ExceptionMsgDataset {
    String PAYMENT_METHOD_ERROR = "결제가 완료되지 않았습니다";
    String STRING_LENGTH_ERROR = "글 양식을 맞춰주세요 (글자 수)";
    String MENU_NOT_FOUND_ERROR = "메뉴를 찾을 수 없습니다";
    String DATALIST_FAIL_ERROR = "데이터 리스트 생성 실패";

    String GET_ORDER_LIST_FAIL = "주문 정보 불러오기 실패";
    String GET_ORDER_LIST_NON = "불러올 주문 정보가 없음";

    String ORDER_COMPLETE_FAIL = "주문 완료 실패";

    String ORDER_CONFIRM_FAIL = "주문 접수 실패";
}
