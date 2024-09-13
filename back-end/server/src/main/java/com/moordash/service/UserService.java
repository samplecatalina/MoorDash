package com.moordash.service;

import com.moordash.dto.UserLoginDTO;
import com.moordash.entity.User;

public interface UserService {
    /**
     * 微信登录
     * @param userLoginDTO
     * @return
     */
    User wxLogin(UserLoginDTO userLoginDTO);
}
