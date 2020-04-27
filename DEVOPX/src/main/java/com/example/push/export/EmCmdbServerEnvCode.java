package com.example.push.export;

/**
 * @author: Farben
 * @description: EmCmdbServerEnvCode CMDB系统环境枚举类
 * @create: 2020/4/27-9:10
 **/
public enum EmCmdbServerEnvCode {

    //生产环境
    ENV_PRDO("prod","生产"),
    //会员环境
    ENV_MEM("mem","会员"),
    //新会员
    ENV_NEWSIMYL("newsimyl","新会员"),
    //模拟
    ENV_SIM("sim","模拟"),
    //模拟数据
    ENV_SIMDATA("simdata","模拟数据"),
    //模拟演练
    ENV_SIMYL("simyl","模拟演练"),
    ENV_UAT1("uat1","UAT1"),
    ENV_UAT2("uat2","UAT2"),
    ENV_UAT3("uat3","UAT3"),
    ENV_UAT4("uat4","UAT4"),
    ENV_UAT5("uat5","UAT5"),
    ENV_UAT6("uat6","UAT6"),
    //培训
    ENV_TRAIN("train","培训"),
    ENV_ST1("st1","ST1"),
    ENV_ST2("st2","ST2"),
    ENV_ST3("st3","ST3"),
    ENV_ST4("st4","ST4"),
    ;

    private String code;
    private String  msg;

    private EmCmdbServerEnvCode(String errCode, String errMsg) {
        this.code = errCode;
        this.msg = errMsg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }


    /**
     * 根据code获取去value
     * @param code
     * @return
     */
    public static String getValueByCode(String code){
        for(EmCmdbServerEnvCode platformFree:EmCmdbServerEnvCode.values()){
            if(code.equals(platformFree.getCode())){
                return platformFree.getMsg();
            }
        }
        return  null;
    }

}
