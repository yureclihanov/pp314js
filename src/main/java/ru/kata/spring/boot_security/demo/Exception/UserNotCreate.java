package ru.kata.spring.boot_security.demo.Exception;

public class UserNotCreate extends RuntimeException{
     private String msg;
    public UserNotCreate(String msg){
        this.msg=msg;
    }

}
