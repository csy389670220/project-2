package com.example.push.model.view;

import com.example.push.model.CmdbMountInfo;
import com.example.push.model.CmdbServerInfo;

import java.util.List;

public class CmdbServerInfoVo  extends CmdbServerInfo {
    private List<CmdbMountInfo> CmdbMountInfos;

    public List<CmdbMountInfo> getCmdbMountInfos() {
        return CmdbMountInfos;
    }

    public void setCmdbMountInfos(List<CmdbMountInfo> cmdbMountInfos) {
        CmdbMountInfos = cmdbMountInfos;
    }
}