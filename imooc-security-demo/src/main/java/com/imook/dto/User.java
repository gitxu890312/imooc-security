package com.imook.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class User {
    
    public interface UserSimpleView{};
    public interface UserDetailView extends UserSimpleView{ }
    
    @JsonView(UserSimpleView.class)
    private String username;
    
    @JsonView(UserDetailView.class)
    @NotBlank(message="密码不能为空")
    private String password;

    @JsonView(UserSimpleView.class)
    private String id;
    
    @JsonView(UserSimpleView.class)
    private Date birthday;
}
