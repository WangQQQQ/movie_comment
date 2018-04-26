package com.wq.mapper;

import com.wq.model.MovieDesc;
import com.wq.model.MovieDescKey;

/**
 * @author kyrieqing[wangq_0228@163.com]
 */
public interface MovieDescMapper {
    int deleteByPrimaryKey(MovieDescKey key);

    int insert(MovieDesc record);

    int insertSelective(MovieDesc record);

    MovieDesc selectByPrimaryKey(MovieDescKey key);

    int updateByPrimaryKeySelective(MovieDesc record);

    int updateByPrimaryKey(MovieDesc record);
}