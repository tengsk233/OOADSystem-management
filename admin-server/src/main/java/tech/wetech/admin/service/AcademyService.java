package tech.wetech.admin.service;

import tech.wetech.admin.exception.BusinessException;
import tech.wetech.admin.model.PageWrapper;
import tech.wetech.admin.model.dto.AcademyDTO;
import tech.wetech.admin.model.dto.UserPageDTO;
import tech.wetech.admin.model.entity.Academy;
import tech.wetech.admin.model.query.AcademyQuery;

public interface AcademyService {

    /**
     * 创建用户
     *
     * @param academy
     */
    void createAcademy(Academy academy) throws BusinessException;


    PageWrapper<AcademyDTO> queryAcademyPage(AcademyQuery academyQuery);

    void updateNotNull(Academy academy);

    void deleteById(Long id);
}
