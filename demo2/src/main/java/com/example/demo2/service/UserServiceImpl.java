package com.example.demo2.service;

import org.springframework.stereotype.Service;

import com.example.demo2.entity.UserEntity;
import com.example.demo2.form.UserForm;

/**
 * ユーザ用処理Serviceクラス
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * ユーザFormの内容をEntityに変換して返します.
     *
     * @param userForm ユーザForm情報
     * @return ユーザEntityクラス
     */
    public UserEntity createUser(UserForm userForm) {
        // FormクラスからEntityクラスに情報を詰め替え
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userForm.getUserName());
        userEntity.setAge(userForm.getAge());
        return userEntity;
    }
}
