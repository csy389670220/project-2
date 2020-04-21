package com.example.push.model.view;

import com.example.push.model.PushGroup;

public class PushGroupVo  extends PushGroup {

    private String departName;

    private String createName;

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}