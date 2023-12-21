package org.example.dao;

import org.example.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    /**
     * 在内存中存储应用程序的用户信息，使用 BCryptPasswordEncoder 加密
     * password: $2a$10$QH8jrf5AXUPjC0P7Y0vBa.lCr8XQelJ2o/Xrv5xiOHCfctpYbc3K6
     * 123456: $2a$10$plu6QWcc075zq2gGiMgvk.jgLkaOualLRIMm7apbj8i5v/zSACdFC
     */
    private final static List<User> APPLICATION_USERS = Arrays.asList(
            new User(1L, "admin", "$2a$10$QH8jrf5AXUPjC0P7Y0vBa.lCr8XQelJ2o/Xrv5xiOHCfctpYbc3K6", "13888888888", false, null, null),
            new User(2L, "zhangsan", "$2a$10$plu6QWcc075zq2gGiMgvk.jgLkaOualLRIMm7apbj8i5v/zSACdFC", "13666666666", false, null, null)
    );
    // 根据用户名查找用户
    public Optional<User> findUserByName(String phone){
        return APPLICATION_USERS
                .stream()
                .filter(u-> u.getPhone().equals(phone))
                .findFirst();
    }
}
