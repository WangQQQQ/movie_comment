package com.wq.mapper;

import com.wq.model.UserComments;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public interface UserCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserComments record);

    int insertSelective(UserComments record);

    UserComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserComments record);

    int updateByPrimaryKey(UserComments record);
}