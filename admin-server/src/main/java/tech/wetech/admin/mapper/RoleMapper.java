package tech.wetech.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.wetech.admin.model.entity.Role;
import tech.wetech.mybatis.mapper.BaseMapper;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}
