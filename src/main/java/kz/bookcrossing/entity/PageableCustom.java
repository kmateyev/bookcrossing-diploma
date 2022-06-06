package kz.bookcrossing.entity;

import lombok.Data;

@Data
public class PageableCustom {
    private Integer page;
    private Integer size;
    private Long total;
    private Object content;
}
