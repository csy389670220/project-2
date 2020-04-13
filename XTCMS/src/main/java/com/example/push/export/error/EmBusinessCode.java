package com.example.push.export.error;
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
    SYSTEM_ADD_ERROR(10002,"新增失败"),
    SYSTEM_DEL_ERROR(10003,"删除失败"),
    SYSTEM_UPDATE_ERROR(10004,"更新失败"),
    PUSHSUB_DEL_ERROR(10005,"订阅人删除失败"),
    PUSHGROUP_DEL_ERROR(10006,"群组信息删除失败"),
    PUSHGROUP_ADD_ERROR(10007,"群组信息新增失败"),
    PUSHGROUP_UPDATE_ERROR(10008,"群组信息更新失败"),
    GAT_QR_CODE_ERROR(10009,"获取二维码接口失败"),
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
