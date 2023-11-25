package com.example.demo2.form;

import jakarta.validation.constraints.NotNull;

import lombok.Data;

/**
 * ユーザ用Form
 */
@Data
public class UserForm {
    @NotNull
    private String userName;
    @NotNull
    private Integer age;
}
