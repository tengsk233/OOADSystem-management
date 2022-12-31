package tech.wetech.admin.model.dto;

import lombok.Data;
import org.springframework.util.ObjectUtils;
import tech.wetech.admin.model.entity.Academy;
import tech.wetech.admin.model.query.AcademyQuery;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Data
public class AcademyDTO {
    /**
     * 编号
     */
    private Long id;
    /**
     * 学院名
     */
    private String name;
    /**
     * 拥有的班级列表
     */
    private List<Long> classIds;
    /**
     * 拥有的学生人数
     */
    private Long NumOfStu;
    /**
     * 拥有的教师人数
     */
    private Long NumOfTec;

    private Integer locked;

    public AcademyDTO(Academy academy){
        this.id=academy.getId();
        this.name=academy.getName();
        this.NumOfStu=academy.getNumOfStu();
        this.NumOfTec=academy.getNumOfTea();
        this.locked=academy.getLocked();
        this.classIds=getClassIds(academy.getClassIds());
    }
    private List<Long> getClassIds(String classIdStr) {
        if (ObjectUtils.isEmpty(classIdStr)) {
            return Collections.emptyList();
        }
        return Stream.of(classIdStr.split(","))
            .map(Long::valueOf)
            .collect(Collectors.toList());
    }
}
