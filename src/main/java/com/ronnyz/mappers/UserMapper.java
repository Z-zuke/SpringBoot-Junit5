package com.ronnyz.mappers;


import com.ronnyz.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectByUsername(@Param("username") String username);

    //@Select("select id, username, age from `user`")
    List<User> selectAll();
}
