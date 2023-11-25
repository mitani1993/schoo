package com.example.demo2.service;

import org.springframework.stereotype.Service;

import com.example.demo2.entity.UserEntity;
import com.example.demo2.form.UserForm;

/**
 * ユーザ用処理Serviceクラス
 */
@Service
public class UserServiceImpl implements UserService {
    // ユーザID管理用
    private int userId = 0;

    public UserEntity createUser(UserForm userForm) {
        // ユーザ情報
        // Entityクラスは本来DBなどに格納する用途（今回はDB使用無しなのでここまで）
        UserEntity userEntity = new UserEntity();
        // ユーザIDを更新（IDも本来はDBなどで管理する）
        userId = userId + 1;
        userEntity.setUserId(userId);
        userEntity.setUserName(userForm.getUserName());
        userEntity.setAge(userForm.getAge());
        return userEntity;
    }
}
