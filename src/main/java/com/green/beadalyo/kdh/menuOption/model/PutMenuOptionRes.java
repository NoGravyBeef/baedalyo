package com.green.beadalyo.kdh.menuOption.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class PutMenuOptionRes {
    private long optionPk;
    private long optionMenuPk;
    private String option1Name;
    private String option2Name;
    private int optionPrice;
    private int optionState;
    private String createdAt;
    private String updatedAt;
}
