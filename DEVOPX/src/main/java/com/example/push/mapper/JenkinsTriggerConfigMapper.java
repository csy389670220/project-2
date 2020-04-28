package com.example.push.mapper;

import com.example.push.model.JenkinsTriggerConfig;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JenkinsTriggerConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JenkinsTriggerConfig record);

    int insertSelective(JenkinsTriggerConfig record);

    JenkinsTriggerConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JenkinsTriggerConfig record);

    int updateByPrimaryKey(JenkinsTriggerConfig record);

    /**
     * 删除jenkins信息 根据ID跟所属部门
     * @param record
     * @return
     */
    int deleteJenkinsTrigger(JenkinsTriggerConfig record);

    /**
     * 查询所属部门的Source
     * @return
     */
    List<JenkinsTriggerConfig> querySource(JenkinsTriggerConfig record);

    /**
     * 查询所属部门的JenkinsFlavor
     * @return
     */
    List<JenkinsTriggerConfig> queryJenkinsFlavor(JenkinsTriggerConfig record);

    /**
     * 查询所属部门的JenkinsVersion
     * @return
     */
    List<JenkinsTriggerConfig> queryJenkinsVersion(JenkinsTriggerConfig record);
}