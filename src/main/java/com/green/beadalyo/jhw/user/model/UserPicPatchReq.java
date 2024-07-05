package com.green.beadalyo.jhw.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPicPatchReq {
    @JsonIgnore
    private long signedUserPk;
    @JsonIgnore
    private String picName;
}