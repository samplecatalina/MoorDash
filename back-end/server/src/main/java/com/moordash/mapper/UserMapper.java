package com.moordash.mapper;

import com.moordash.annotation.AutoFill;
import com.moordash.entity.User;
import com.moordash.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface UserMapper {
    /**
     * Get user by id
     *
     * @param id
     * @return
     */
    User getById(String id);

    /**
     * Get current user by openid
     *
     * @param openid
     * @return
     */
    User getByOpenId(@Param("openid") String openid);

    /**
     * Create new user
     *
     * @param user
     */
    @AutoFill(OperationType.INSERT)
    void insert(User user);

    /**
     * Count amount of users by dynamic conditions
     *
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}
