package tech.wetech.admin.model.query;


import lombok.Data;

@Data
public class AcademyQuery extends PageQuery{
    private Long id;
    private String academyname;
    private Integer locked;
}
