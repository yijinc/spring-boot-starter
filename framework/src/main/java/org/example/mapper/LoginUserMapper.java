package org.example.mapper;

import org.example.domain.entity.User;

public interface LoginUserMapper {
    User selectUserByPhone(String phone);
}
