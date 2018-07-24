package com.imook.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class UserQueryCondition {

    private String username;
    
    private int age;
    
    private int toAge;
    
    private String xxx;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getToAge() {
        return toAge;
    }

    public void setToAge(int toAge) {
        this.toAge = toAge;
    }

    public String getXxx() {
        return xxx;
    }

    public void setXxx(String xxx) {
        this.xxx = xxx;
    }
    
    
}
