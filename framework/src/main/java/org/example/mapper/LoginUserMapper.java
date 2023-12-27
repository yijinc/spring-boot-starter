package org.example.mapper;

import org.example.domain.entity.User;

public interface LoginUserMapper {
    User selectUserByName(String name);

    User selectUserByPhone(String phone);
}
