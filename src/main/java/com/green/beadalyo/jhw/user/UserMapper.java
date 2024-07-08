package com.green.beadalyo.jhw.user;

import com.green.beadalyo.jhw.user.model.*;
import com.green.beadalyo.jhw.useraddr.model.UserAddrGetRes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int signUpUser(UserSignUpPostReq p);
    User getUserById(String userId);
    User getUserByPk(long userPk);
    UserInfoGetRes selProfileUserInfo(long signedUserPk);
    int updProfilePic(UserPicPatchReq p);
    int updUserInfo(UserInfoPatchReq p);
    int updUserPassword(UserPasswordPatchReq p);
    int deleteUser(long userPk);
    UserAddrGetRes getMainAddr(long signedUserPk);
}
