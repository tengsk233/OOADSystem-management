package tech.wetech.admin.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.wetech.admin.model.PageWrapper;
import tech.wetech.admin.model.Result;
import tech.wetech.admin.model.dto.AcademyDTO;
import tech.wetech.admin.model.entity.Academy;
import tech.wetech.admin.model.entity.User;
import tech.wetech.admin.model.enumeration.CommonResultStatus;
import tech.wetech.admin.model.query.AcademyQuery;
import tech.wetech.admin.model.query.UserQuery;
import tech.wetech.admin.model.vo.UserBatchDeleteVO;
import tech.wetech.admin.service.AcademyService;

import java.util.Arrays;

@RequestMapping("academy")
@RestController
public class AcademyController {

    @Autowired
    private AcademyService academyService;

    @GetMapping
    public Result<PageWrapper<AcademyDTO>> queryUserList(AcademyQuery academyQuery) {
        return Result.success(academyService.queryAcademyPage(academyQuery));
    }

    @PostMapping
    public Result create(@RequestBody Academy academy) {
        academyService.createAcademy(academy);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Academy academy) {
        academyService.updateNotNull(academy);
        return Result.success();
    }

    @DeleteMapping
    public Result deleteBatchByIds(@RequestBody @Validated UserBatchDeleteVO userDeleteVO) {
        Long[] ids = userDeleteVO.getIds();
        Arrays.stream(ids).forEach(academyService::deleteById);
        return Result.success();
    }
}
