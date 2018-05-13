package com.wq.mapper;

import java.sql.SQLException;

import com.wq.model.UserInfo;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record) throws SQLException;

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
}