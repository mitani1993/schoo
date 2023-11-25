package com.example.demo2.service;

import org.springframework.stereotype.Service;

import com.example.demo2.entity.UserEntity;
import com.example.demo2.form.UserForm;

/**
 * ユーザ用処理Serviceクラス
 */
@Service
public interface UserService {
    public UserEntity createUser(UserForm userForm);
}
