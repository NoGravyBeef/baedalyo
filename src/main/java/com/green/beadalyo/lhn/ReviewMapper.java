package com.green.beadalyo.lhn;

import com.green.beadalyo.lhn.model.*;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper

public interface ReviewMapper {
    boolean ReviewExistForOrder(long orderPk);
    void insertReview(ReviewPostReq reviewPostReq);
    void postReviewReply(ReviewReplyReq replyReq);
    List<ReviewGetRes> getReviewList(ReviewGetReq req);
    void putReview(ReviewPutReq p);
    long getReviewUserPk(long reviewPk);
    ReviewReplyRes getReviewComment(long reviewPk);
    int deleteReview(long reviewPk, int reviewState);
    ReviewGetRes getReview(long reviewPk);

}