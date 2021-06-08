package com.bohong.model_visualization_tools.domain.systemSettings;

import lombok.Data;

import java.util.List;

@Data
public class MenuUtilTwo {
    private String text;
    private List<SubMenu> items;
}
