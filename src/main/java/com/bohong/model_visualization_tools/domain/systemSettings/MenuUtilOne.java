package com.bohong.model_visualization_tools.domain.systemSettings;

import lombok.Data;

import java.util.List;

@Data
public class MenuUtilOne {
    private String id;
    private List<MenuUtilTwo> menu;
}
