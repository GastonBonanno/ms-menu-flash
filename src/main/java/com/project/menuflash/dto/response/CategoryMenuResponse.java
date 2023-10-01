package com.project.menuflash.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class CategoryMenuResponse {
    private Long id;
    private String name;
    private Long position;
    private Boolean active;
    private Long companyMenuId;
    private List<ItemMenuResponse> menuItems;
}
