package com.project.menuflash.dto.request;

import com.project.menuflash.dto.response.FindAllMenuItemResponse;
import lombok.Data;

import java.util.List;

@Data
public class CreateCategoryMenuDto {
    private String name;
    private Boolean active;
    private List<CreateMenuItemDto> menuItems;
}
