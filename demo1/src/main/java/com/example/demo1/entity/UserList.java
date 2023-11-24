package com.example.demo1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * user_listテーブルのEntityクラスです
 */
@Entity
@Table(name = "user_list")
public class UserList {
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private Integer age;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id セットする id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name セットする name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age セットする age
     */
    public void setAge(Integer age) {
        this.age = age;
    }
}
