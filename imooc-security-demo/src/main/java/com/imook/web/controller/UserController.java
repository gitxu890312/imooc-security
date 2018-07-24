package com.imook.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imook.dto.User;
import com.imook.dto.UserQueryCondition;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Company: 中化能源科技有限公司</p>
 *
 * @author xuming
 * @since：2018年7月22日
 * @version v1.0
 *
 */
@RestController
public class UserController {
    
    @GetMapping("/user")
    @JsonView(User.UserSimpleView.class)
    public List<User> query(UserQueryCondition condition ){
        System.out.println(condition);
//        System.out.println("size:"+pageable.getPageSize());
//        System.out.println("page:"+pageable.getPageNumber());
//        System.out.println("sort:"+pageable.getSort());
        List<User> lists = new ArrayList<User>();
        lists.add(new User());
        lists.add(new User());
        lists.add(new User());
        return lists;
    }
    @GetMapping("/user/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@PathVariable String id) {
//        throw new UserNotExistException(id);
        User user = new User();
        user.setUsername("tom");
        return user;
    }
    @PostMapping("/user")
    public User create(@Valid @RequestBody User user,BindingResult result) {
        if(result.hasErrors()) {
            System.out.println(result.getFieldError().getDefaultMessage());
        }
        System.out.println(user);
        User u = new User();
        u.setId(user.getId());
        u.setBirthday(user.getBirthday());
        u.setPassword(user.getPassword());
        u.setUsername(user.getUsername());
        u.setId("1");
        return u;
    }
}
