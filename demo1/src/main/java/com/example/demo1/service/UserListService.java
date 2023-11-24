package com.example.demo1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo1.entity.UserList;
import com.example.demo1.repository.UserListRepository;

/**
 * user_list関連のServiceを提供するクラスです
 */
@Service
public class UserListService {

    @Autowired
    UserListRepository userListRepository;

    /**
     * user_listテーブル内のレコードを全件検索して返却します
     *
     * @return user_listテーブル内の全情報
     */
    public List<UserList> findAll() {
        return userListRepository.findAll();
    }

    /**
     * user_listテーブル内のレコードを全件検索（降順）して返却します
     *
     * @return user_listテーブル内の全情報（降順）
     */
    public List<UserList> findAllDesc() {
        return userListRepository.findAllByOrderByIdDesc();
    }

    /**
     * user_listテーブル内のidが1のユーザを検索して返却します
     *
     * @return user_listテーブル内のidが1のユーザ情報
     */
    public UserList findByUserOne() {
        return userListRepository.findByUserOne();
    }
}
