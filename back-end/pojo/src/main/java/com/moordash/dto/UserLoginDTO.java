package com.moordash.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * C-side user login
 */
@Data
public class UserLoginDTO implements Serializable {

    private String code;

}
