package com.lhk.mapper;

import com.lhk.pojo.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author TheMutents
 * @creat on 2021-12-27-23:20
 */
public interface RoleMapper {
    @Select("SELECT * FROM sys_role r,sys_user_role ur WHERE r.id=ur.roleid AND userid=#{userid}")
    List<Role> queryRoleByUserId(int userid);
}
