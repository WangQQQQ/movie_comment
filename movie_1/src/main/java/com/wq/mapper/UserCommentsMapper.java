package com.wq.mapper;

import com.wq.model.UserComments;
import com.wq.model.UserCommentsKey;

public interface UserCommentsMapper {
    int deleteByPrimaryKey(UserCommentsKey key);

    int insert(UserComments record);

    int insertSelective(UserComments record);

    UserComments selectByPrimaryKey(UserCommentsKey key);

    int updateByPrimaryKeySelective(UserComments record);

    int updateByPrimaryKey(UserComments record);
}