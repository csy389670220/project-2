package com.example.ips.export.error;
/**
 * @author: Farben
 *
 * @description: x系统枚举自定义code字典
 *
 * @create: 2019/8/21-10:14
 **/
public enum EmBusinessCode implements CommonError{

    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    //系统通用CURD类型
    SYSTEM_ADD_SUCCESS(10002," 新增成功"),
    SYSTEM_ADD_ERROR(10003,"新增失败"),
    SYSTEM_DEL_SUCCESS(10004,"删除成功"),
    SYSTEM_DEL_ERROR(10005,"删除失败"),
    SYSTEM_UPDATE_SUCCESS(10006,"更新成功"),
    SYSTEM_UPDATE_ERROR(10007,"更新失败"),
    SYSTEM_QUERY_SUCCESS(10008,"查询成功"),
    SYSTEM_QUERY_ERROR(10009,"查询失败"),
    //服务器规划-iDealNew
    SERVERPLAN_IDEALNEW_ADD_ERROR(20001,"新增失败"),
    //服务器规划-iTrader
    SERVERPLAN_ITRADER_ADD_SUCCESS(20002,"新增成功"),
    SERVERPLAN_ITRADER_ADD_ERROR(20003,"新增失败"),
    //服务部署-UAT升级记录
    //1-已提交
    SERVICEDEP_UATUP_SAUTUS_SUBMITTED(30001,"1"),
    SERVICEDEP_UATUP_ADD_SUCCESS(30002,"保存成功"),
    SERVICEDEP_UATUP_ADD_ERROR(30003,"保存失败"),
    SERVICEDEP_UATUP_SAVE_SUCCESS(30004,"提交成功"),
    SERVICEDEP_UATUP_SAVE_ERROR(30005,"提交失败"),
    ;

    private int errCode;
    private String errMsg;

    private EmBusinessCode(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
