package com.moordash.controller.user;

import com.moordash.constant.JwtClaimsConstant;
import com.moordash.dto.UserLoginDTO;
import com.moordash.entity.User;
import com.moordash.properties.JwtProperties;
import com.moordash.result.Result;
import com.moordash.service.UserService;
import com.moordash.utils.JwtUtil;
import com.moordash.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

/**
 * WeChat login
 */
@RestController
@RequestMapping("/user/user")
@Api(tags = "Customer-side: user-related API")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtProperties jwtProperties;

    /**
     * WeChat login
     *
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("WeChat login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO) {
        log.info("WeChat login: {}",userLoginDTO.getCode());

        // WeChat login
        User user = userService.wxLogin(userLoginDTO);

        // Generate JWT token for WeChat login users
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, user.getId());
        String token = JwtUtil.createJWT(jwtProperties.getUserSecretKey(), jwtProperties.getUserTtl(), claims);

        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openid(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }

}
