package com.project.menuflash.dto.response;

import lombok.Data;

import java.util.List;
@Data
public class FindAllCategoryMenuResponse {
    private Long id;
    private String name;
    private Boolean active;
    private List<FindAllMenuItemResponse> menuItems;
}
