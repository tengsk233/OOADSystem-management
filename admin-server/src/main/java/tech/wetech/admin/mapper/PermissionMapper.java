package tech.wetech.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.wetech.admin.model.entity.Permission;
import tech.wetech.mybatis.mapper.BaseMapper;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}
