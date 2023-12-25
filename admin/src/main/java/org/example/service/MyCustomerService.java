package org.example.service;

import org.example.domain.entity.User;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyCustomerService {
    @PreAuthorize("hasAuthority('ACCOUNT')")
    public String getAccount() {
        return "account No: 123, you must have ACCOUNT authority";
    }

    // returnObject 代表要返回的 User 对象。否则，Spring Security 将抛出 AccessDeniedException 并返回 403 状态码。
    @PostAuthorize("returnObject.owner == authentication.name")
    public User getUserById(long id) {
        return new User();
    }

    // filterObject 代表入参 users 中每一项
    @PreFilter("filterObject.owner == authentication.name")
    public List<User> getUserList(User... users) {
        return new ArrayList<>();
    }

    // filterObject 代表返回集合中的每一项。
    @PostFilter("filterObject.owner == authentication.name")
    public List<User> getUserList() {
        return new ArrayList<>();
    }

}
