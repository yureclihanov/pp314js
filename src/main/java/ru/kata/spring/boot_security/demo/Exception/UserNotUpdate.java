package ru.kata.spring.boot_security.demo.Exception;

public class UserNotUpdate extends RuntimeException{
    private String msg;

    public UserNotUpdate(String msg){
        this.msg = msg;
    }
}
