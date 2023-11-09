package com.fish.myspringboot.entity.mapstruct;

import com.fish.myspringboot.entity.dto.UserDTO;
import com.fish.myspringboot.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapping {
    UserMapping INSTANCE = Mappers.getMapper(UserMapping.class);

    UserDTO toDTO(User source);

    List<UserDTO> toDTO(List<User> source);

    @InheritInverseConfiguration(name = "toDTO")
    User toEntity(UserDTO source);

    @InheritInverseConfiguration(name = "toDTO")
    List<User> toEntity(List<UserDTO> source);

}