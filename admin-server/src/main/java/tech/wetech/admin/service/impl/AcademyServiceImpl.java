package tech.wetech.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.wetech.admin.exception.BusinessException;
import tech.wetech.admin.mapper.AcademyMapper;
import tech.wetech.admin.model.PageWrapper;
import tech.wetech.admin.model.dto.AcademyDTO;
import tech.wetech.admin.model.entity.Academy;
import tech.wetech.admin.model.enumeration.CommonResultStatus;
import tech.wetech.admin.model.query.AcademyQuery;
import tech.wetech.admin.model.query.PageQuery;
import tech.wetech.admin.service.AcademyService;
import tech.wetech.mybatis.domain.Page;
import tech.wetech.mybatis.domain.Sort;
import tech.wetech.mybatis.example.Criteria;
import tech.wetech.mybatis.example.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcademyServiceImpl implements AcademyService {
    @Autowired
    private AcademyMapper academyMapper;


    @Override
    public void createAcademy(Academy academy) throws BusinessException {
        Academy newAcademy=academyMapper.selectByUsername(academy.getName());
        if (newAcademy != null) {
            throw new BusinessException(CommonResultStatus.FAILED_USER_ALREADY_EXIST);
        }
        academyMapper.insertSelective(academy);
    }

    @Override
    public PageWrapper<AcademyDTO> queryAcademyPage(AcademyQuery academyQuery) {
        Example<Academy> example = buildUserExample(academyQuery);

        Page<Academy> academies = Page.of(academyQuery.getPageNo(), academyQuery.getPageSize(), true).list(() -> academyMapper.selectByExample(example));
        List<AcademyDTO> list = new ArrayList<>();
        for (Academy academy : academies) {
            AcademyDTO academyDTO =new AcademyDTO(academy);
            list.add(academyDTO);
        }
        return new PageWrapper<AcademyDTO>(list, academies.getTotal(), academies.getPageNumber(), academies.getPageSize());
    }


    @Override
    public void updateNotNull(Academy academy) {
        academyMapper.updateByPrimaryKeySelective(academy);
    }


    @Override
    public void deleteById(Long id) {
        academyMapper.deleteByPrimaryKey(id);
    }

    private Example<Academy> buildUserExample(AcademyQuery academyQuery) {
        Example<Academy> example = Example.of(Academy.class);
        if (academyQuery.getSortField() != null && academyQuery.getSortOrder() != null) {
            if (academyQuery.getSortOrder() == PageQuery.SortOrder.ascend) {
                example.setSort(Sort.by(academyQuery.getSortField()).asc());
            }
            if (academyQuery.getSortOrder() == PageQuery.SortOrder.descend) {
                example.setSort(Sort.by(academyQuery.getSortField()).desc());
            }
        } else {
            example.setSort(Sort.by("id").desc());
        }
        Criteria<Academy> criteria = example.createCriteria();
        if (academyQuery.getId() != null) {
            criteria.andEqualTo(Academy::getId, academyQuery.getId());
        }
        if (academyQuery.getAcademyname() != null) {
            criteria.andEqualTo(Academy::getName, academyQuery.getAcademyname());
        }
        if (academyQuery.getLocked() != null) {
            criteria.andEqualTo(Academy::getLocked, academyQuery.getLocked());
        }
        return example;
    }
}
