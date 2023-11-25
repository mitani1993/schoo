package com.example.demo2.service;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo2.entity.UserEntity;
import com.example.demo2.form.UserForm;

@SpringBootTest
public class UserServiceImpleTest {
    @Autowired
    UserService userService;

    @Test
    public void test001() {
        // テスト用ユーザの作成
        UserForm userForm = new UserForm();
        userForm.setUserName("スクー太郎");
        userForm.setAge(20);
        // FormからEntityへの変換
        UserEntity userEntity = userService.createUser(userForm);
        // 変換結果の検証
        assertThat(userEntity.getUserId(), is(1));
        assertThat(userEntity.getUserName(), is("スクー太郎"));
        assertThat(userEntity.getAge(), is(20));
    }

    @Test
    public void test002() {
        // テスト用ユーザの作成
        UserForm userForm = new UserForm();
        userForm.setUserName("スクー次郎");
        userForm.setAge(19);
        // FormからEntityへの変換
        UserEntity userEntity = userService.createUser(userForm);
        // 変換結果の検証
        assertThat(userEntity, is(nullValue()));
    }
}
