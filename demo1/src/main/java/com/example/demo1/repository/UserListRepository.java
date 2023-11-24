package com.example.demo1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo1.entity.UserList;

/**
 * user_listテーブルを操作するためのRepositoryクラスです
 */
public interface UserListRepository extends JpaRepository<UserList, Integer> {
    /**
     * IDの降順で全検索した結果を返却します
     * 
     * @return 全検索結果（降順）
     */
    public List<UserList> findAllByOrderByIdDesc();

    /**
     * idが1の条件で検索した結果を返却します
     * 
     * @return 検索結果（ID1）
     */
    @Query("select u from UserList u where u.id = 1")
    public UserList findByUserOne();
}
