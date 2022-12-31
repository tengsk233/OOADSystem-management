package tech.wetech.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import tech.wetech.admin.model.entity.Academy;
import tech.wetech.mybatis.mapper.BaseMapper;

@Mapper
public interface AcademyMapper extends BaseMapper<Academy> {
    default Academy selectByUsername(String academyname) {
        return this.createCriteria().andEqualTo(Academy::getName, academyname).selectOne();
    }
}
