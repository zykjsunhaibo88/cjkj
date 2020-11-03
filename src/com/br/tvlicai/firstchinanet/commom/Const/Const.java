package com.br.tvlicai.firstchinanet.commom.Const;

/**
 * 常量
 * Created by huhao on 2016/5/4.
 */
public class Const {
    /**
     * 空字符串
     */
    public static String STR_EMPTY = "";

    /**
     * 获取ID用的ID类型
     * 类型1
     */
    public static char GET_ID_TYPE_1 = 'U';

    public static char NOTICE_TYPE_GOV = 'U';


    /**
     * 处置合约类型
     */
    public static final int CONTRACT_TYPE_DISPOSE = 1;

    /**
     * 清运合约类型
     */
    public static final int CONTRACT_TYPE_CLEAR = 2;

    /**
     * 处置合约类型
     */
    public static final String CONTRACT_TYPE_DISPOSE_STR = "1";

    /**
     * 清运合约类型
     */
    public static final String CONTRACT_QR_CLEAR = "CONT_CLOG";

    /**
     * 处置合约类型
     */
    public static final String CONTRACT_QR_DISPOSE = "CONT_DISP";

    /**
     * 清运合约类型
     */
    public static final String CONTRACT_TYPE_CLEAR_STR = "2";


    /**
     * 合约扩展项目key
     */
    public static final String CONTRACT_EXTEND_PROJECT_KEY = "project_id";

    /**
     * 合约扩展处置场key
     */
    public static final String CONTRACT_EXTEND_DISPOSE_KEY = "dispose_id";

    /**
     * 合约扩展禁止开始时间key
     */
    public static final String CONTRACT_EXTEND_TRANS_TIME_START_KEY = "trans_time_start";


    /**
     * 合约扩展禁止截止时间key
     */
    public static final String CONTRACT_EXTEND_TRANS_TIME_END_KEY = "trans_time_end";

    /**
     * 合约扩展备注key
     */
    public static final String CONTRACT_EXTEND_REMARK_KEY = "remark";

    /**
     * 合约状态--1未提交2已提交3已生效4已作废5已过期6已完成
     */
    public static final int CONTRACT_STATUS_NO_SUBMIT = 1;
    public static final int CONTRACT_STATUS_SUBMIT = 2;
    public static final int CONTRACT_STATUS_VALID = 3;
    public static final int CONTRACT_STATUS_INVALID = 4;
    public static final int CONTRACT_STATUS_OVERDUE = 5;
    public static final int CONTRACT_STATUS_FINISHED = 6;


    /**
     * 用户类型
     */
    public final static String USER_TYPE_PERSONAL = "U01";//个人

    public final static String USER_TYPE_ENTERPRISE_DISPOSE = "E01";//处置单位

    public final static String USER_TYPE_ENTERPRISE_CONSTRUCTION = "E02";//建设/施工单位

    public final static String USER_TYPE_ENTERPRISE_LOGISTICS = "E03";//清运（物流）单位

    public final static String USER_TYPE_GOVERNMENT = "G01";//园区/街道

    public final static String USER_TYPE_STREET = "G02";//政府

    public final static String USER_TYPE_DISPOSE = "D01";//处置场

    public final static String USER_TYPE_PROJECT = "P01";//项目

    public final static String USER_TYPE_DEPOT = "D02";//停车场

    public final static String USER_TYPE_MAINT = "A00";//平台运维
    public final static String USER_TYPE_MAINT_MAILL = "A10";//商城运维
    public final static String USER_TYPE_MAINT_GOV = "A20";//政务运维
    public final static String USER_TYPE_MAINT_CONST = "A30";//建设/施工单位运维
    public final static String USER_TYPE_MAINT_PJ = "A40";//项目运维
    public final static String USER_TYPE_MAINT_LOG = "A50";//运输单位运维
    public final static String USER_TYPE_MAINT_DPE = "A60";//处置单位运维
    public final static String USER_TYPE_MAINT_DPR = "A70";//处置场运维

    /**
     * 资质类型
     */
    public static String QUALIFICATION_TYPE_Q010 = "Q010"; //个人身份证
    public static String QUALIFICATION_TYPE_Q011 = "Q011"; //行政执法证正面
    public static String QUALIFICATION_TYPE_Q012 = "Q012"; //行政执法证反面


    //    public static String QUALIFICATION_TYPE_Q020 = "Q020"; //司机身份证
    public static String QUALIFICATION_TYPE_Q021 = "Q021"; //司机驾驶证
    public static String QUALIFICATION_TYPE_Q022 = "Q022"; //司机从业资格证

    public static String QUALIFICATION_TYPE_Q030 = "Q030"; //建设/施工单位营业执照
    public static String QUALIFICATION_TYPE_Q032 = "Q032"; //建设/施工单位社会信用代码
    public static String QUALIFICATION_TYPE_Q033 = "Q033"; //建设/施工单位税务登记号
    public static String QUALIFICATION_TYPE_Q034 = "Q034"; //建设/施工单位组织结构代码
    public static String QUALIFICATION_TYPE_Q036 = "Q036"; //建设/施工单位法人正面照
    public static String QUALIFICATION_TYPE_Q037 = "Q037"; //建设/施工单位法人反面照
    public static String QUALIFICATION_TYPE_Q038 = "Q038"; //建设/施工单位开户许可证件照

    public static String QUALIFICATION_TYPE_Q040 = "Q040"; //施工项目许可证

    public static String QUALIFICATION_TYPE_Q050 = "Q050"; //运输单位营业执照
    public static String QUALIFICATION_TYPE_Q051 = "Q051"; //运输单位运输许可证
    public static String QUALIFICATION_TYPE_Q052 = "Q052"; //运输单位社会信用代码
    public static String QUALIFICATION_TYPE_Q053 = "Q053"; //运输单位税务登记号
    public static String QUALIFICATION_TYPE_Q054 = "Q054"; //运输单位组织结构代码
    public static String QUALIFICATION_TYPE_Q056 = "Q056"; //运输单位法人正面照
    public static String QUALIFICATION_TYPE_Q057 = "Q057"; //运输单位法人反面照
    public static String QUALIFICATION_TYPE_Q058 = "Q058"; //运输单位开户许可证件照

    public static String QUALIFICATION_TYPE_Q060 = "Q060"; //处置单位营业执照
    public static String QUALIFICATION_TYPE_Q062 = "Q062"; //处置单位社会信用代码
    public static String QUALIFICATION_TYPE_Q063 = "Q063"; //处置单位税务登记号
    public static String QUALIFICATION_TYPE_Q064 = "Q064"; //处置单位组织结构代码
    public static String QUALIFICATION_TYPE_Q066 = "Q066"; //处置单位法人正面照
    public static String QUALIFICATION_TYPE_Q067 = "Q067"; //处置单位法人反面照
    public static String QUALIFICATION_TYPE_Q068 = "Q068"; //处置单位开户许可证件照

    public static String QUALIFICATION_TYPE_Q070 = "Q070"; //处置场建筑垃圾处置服务许可证
    public static String QUALIFICATION_TYPE_Q080 = "Q080"; //车辆行驶证
    public static String QUALIFICATION_TYPE_Q081 = "Q081"; //车辆登记证
    public static String QUALIFICATION_TYPE_Q082 = "Q082"; //车辆运营许可证
    public static String QUALIFICATION_TYPE_Q083 = "Q083"; //车辆年检证
    public static String QUALIFICATION_TYPE_Q084 = "Q084"; //车辆交强险证

    // 类型工具表类型区分 公告类型
    public static char STR_TYPE_IDENTIFYING_1 = 'N';

    // 删除区分 未删除
    public static char STR_DELETE_TYPE_1 = '1';
    // 删除区分 删除
    public static char STR_DELETE_TYPE_2 = '0';

    // 使用区分 使用
    public static String STR_USE_FLAG_1 = "1";
    // 使用区分 未使用
    public static String STR_USE_FLAG_2 = "0";

    // 撤销区分 未撤销
    public static char STR_REVOKE_TYPE_1 = '1';
    // 撤销区分 撤销
    public static char STR_REVOKE_TYPE_2 = '0';

    /* START 公告模块使用常量 */

    /**
     * 公告类型：政务介绍
     */
    public static char CHAR_NOTICE_TYPE_1 = 'G';
    /**
     * 公告类型：禁行公告
     */
    public static char CHAR_NOTICE_TYPE_2 = 'F';
    /**
     * 公告类型：项目公告
     */
    public static char CHAR_NOTICE_TYPE_3 = 'P';
    /**
     * 公告类型：单位评级
     */
    public static char CHAR_NOTICE_TYPE_4 = 'E';
    /**
     * 处置业务介绍维护
     */
    public static char CHAR_NOTICE_TYPE_5 = 'D';
    /**
     * 清运业务介绍维护
     */
    public static char CHAR_NOTICE_TYPE_6 = 'C';

    /* END 公告模块使用常量 */

    /* START 文件管理工具使用常量 */
    public static char FILE_TOOL_TYPE_FLAG_1 = 'F';
    public static char FILE_TOOL_TYPE_FLAG_2 = 'I';
    public static String FILE_TOOL_URL = "url";
    public static String FILE_TOOL_FILE_NAME = "fileName";
    public static String FILE_TOOL_FILE_TYPE = "fileType";
    public static String FILE_TOOL_FILE_ID = "fileId";

     /* END 文件管理工具使用常量 */

    /* START 消息用常量 */
    //消息类型
    public static final String MSG_TYPE_SYSTEM = "M01";//系统消息
    public static final String MSG_TYPE_CONFIRM = "M02";//确认消息

    public static final String MSG_OPT_DATA_ID = "optDataId";//操作数据ID

    public static final String MSG_AGREE_0 = "0";//0未操作
    public static final String MSG_AGREE_1 = "1";//已同意
    public static final String MSG_AGREE_2 = "2";//未同意

    //模板类型
    //eq: 发送方|接收方|消息类别|链接|按钮
    public static final String MSG_TEMPLAET_P1U101 = "P1U101";  //平台|个人|系统消息|无|无
    public static final String MSG_TEMPLAET_G1U101 = "G1U101";  //群体|个人|确认消息|无|同意/不同意
    public static final String MSG_TEMPLAET_U1G101 = "U1G101";  //个人|群体|系统消息|进入成员信息页|无
    public static final String MSG_TEMPLAET_U1G102 = "U1G102";  //
    public static final String MSG_TEMPLAET_U1G103 = "U1G103";  //
    public static final String MSG_TEMPLAET_P1E201 = "P1E201";  //
    public static final String MSG_TEMPLAET_P1E202 = "P1E202";  //
    public static final String MSG_TEMPLAET_P1E301 = "P1E301";  //
    public static final String MSG_TEMPLAET_G1E302 = "G1E302";  //
    public static final String MSG_TEMPLAET_G1E303 = "G1E303";  //
    public static final String MSG_TEMPLAET_D1E101 = "D1E101";  //
    public static final String MSG_TEMPLAET_D1E102 = "D1E102";  //
    public static final String MSG_TEMPLAET_E3P101 = "E3P101";  //
    public static final String MSG_TEMPLAET_E3P102 = "E3P102";  //
    public static final String MSG_TEMPLAET_D1P103 = "D1P103";  //
    public static final String MSG_TEMPLAET_D1P104 = "D1P104";  //
    public static final String MSG_TEMPLAET_G1P105 = "G1P105";  //
    public static final String MSG_TEMPLAET_G1P106 = "G1P106";  //
    public static final String MSG_TEMPLAET_E2P107 = "E2P107";  //
    public static final String MSG_TEMPLAET_E2P108 = "E2P108";  //
    public static final String MSG_TEMPLAET_E1D101 = "E1D101";  //
    public static final String MSG_TEMPLAET_E1D102 = "E1D102";  //
    public static final String MSG_TEMPLAET_P1D103 = "P1D103";  //
    public static final String MSG_TEMPLAET_P1G101 = "P1G101";  //
    public static final String MSG_TEMPLAET_E3G102 = "E3G102";  //


    public static final String MSG_TEMPLAET_P1E203 = "P1E203";  //
    public static final String MSG_TEMPLAET_D1E103 = "D1E103";  //


    public static final String MSG_PROJECT_SENDTO_CONSTRUCTION_URL = "/construction/queryProjectConfirmList";  //项目发申请加入建设/施工单位请求消息url
    public static final String MSG_DISPOSE_SENDTO_DISPOSEENTERPRISE_URL = "/disposeEnterprise/queryDisposeConfirmList";  //处置场发申请加入处置单位消息url
    public static final String MSG_CONTRACT_PROJECT_SENDTO_CLEAR_URL = "/contract/logitics/contract_detail";//项目申请提交淸运合约
    public static final String MSG_CONTRACT_PROJECT_SENDTO_DISPOSE_URL = "/contract/disposer/contract_confirm_detail";//项目申请提交处置合约
    public static final String MSG_CONTRACT_DISPOSE_SENDTO_PROJECT_URL = "/contractVm/contract_detail_query";//项目申请提交处置合约
    public static final String MSG_CONTRACT_LOGTICS_SENDTO_PROJECT_URL = "/contractVm/contract_detail_query";//项目申请提交处置合约

    /* END 消息用常量 */

    //群组用户关系
    public static String MSG_TABLENAME_GROUP_USER = "bru_group_user_relation";
    //用户成员关系
    public static String MSG_TABLENAME_USER_MEMBER = "bru_user_member_info";

    public static final String SUCCESS = "success";//
    public static final String SUCCESS_TRUE = "true";//成功
    public static final String SUCCESS_FALSE = "false";//失败

    /* START 地址工具使用常量 */
    public static char ADR_TOOL_LEVEL_1 = '1';
    public static char ADR_TOOL_LEVEL_2 = '2';
    public static char ADR_TOOL_LEVEL_3 = '3';
     /* END 地址工具使用常量 */

    /* START  用户缓存信息常量 */
    public static final String CACHE_USERINFO = "cacheUserInfo"; //用户信息缓存
    public static final String CACHE_SELECT_PROJECTINFO = "";  //选择项目信息缓存
    public static final String CACHE_ALL_USER_LIST = "allUserList";//所有用户信息
    public static final String CACHE_ALL_ADDRESS = "allAddress";//所有地址信息

    /*用户信息相关*/

    public static final String USER_CHECK_TYPE_USERID = "userId";
    public static final String USER_CHECK_TYPE_EMAIL = "email";
    public static final String USER_CHECK_TYPE_PHONE = "phone";

    public static final String USERINFO_PLATFORM_ID = "platformId";   //平台ID
    public static final String USERINFO_MALL_ID = "mallId";           //商城ID
    public static final String USERINFO_SYSTEM_ID = "systemId";       //系统ID
    public static final String MALL_DISP_NAME = "mallDispName";       //商城地址缩略


    public static final String LOGIN_TOKEN = "loginToken";            //登录时生成的token
    public static final String USERINFO_USER_AID = "userAid";         //用户认证ID
    public static final String USERINFO_USER_AIDOLD = "userAidOld";         //旧用户认证ID
    public static final String ServerIP= "serverIp";
    public static final String USERINFO_USER_ID = "userId";           //用户登录id
    public static final String USERINFO_USER_ORGID = "userSsJg";           //用户机构
    public static final String USERINFO_USER_TYPE = "userType";       //用户类型
    public static final String USERINFO_USER_TYPE_NAME = "userTypeName";//用户类型名称
    public static final String USERINFO_USER_NAME = "userName";       //用户名称
    public static final String USERINFO_USER_GOV_STS_CD = "userStsCd";       //用户状态
    public static final String USERINFO_USER_MALL_STS_CD = "userMallStsCd";       //用户状态
    public static final String USERINFO_USER_OBJ = "userObj";         //用户对象实体

    public static final String USERINFO_USER_PHONE = "phone";         //手机号
    public static final String USERINFO_USER_EMAIL = "email";         //邮箱
    public static final String USERINFO_USER_MSG_NUM = "msgNum";         //消息数量

    /*选择项目信息相关*/
    public static final String SEL_GROUP_AID = "sel_GroupAid";                     //选择项目/单位aid
    public static final String SEL_GROUP_TYPE = "sel_GroupType";                //选择项目/单位类别
    public static final String SEL_PROJECT_BEAN = "sel_projectBean";          //项目实体
    public static final String SEL_ENTERPRISE_BEAN = "sel_enterpriseBean";    //单位实体

    /* END  用户缓存信息常量 */

    /*Start  用户审核用*/
    public static final String USER_APPROVE_TYPE_PLATFORM = "0";    //审核类型 平台审核
    public static final String USER_APPROVE_TYPE_GOV = "1";    //审核类型 政府审核


    public static final String USER_APPROVE_STS_0 = "0";            //审核状态 未审
    public static final String USER_APPROVE_STS_1 = "1";            //审核状态 审核通过
    public static final String USER_APPROVE_STS_2 = "2";            //审核状态 审核未通过
    public static final String USER_APPROVE_STS_9 = "9";            //审核状态 禁用
    /*End  用户审核用*/

    /*Start  菜单使用*/public static final String CACHE_MENU_INFO = "cacheMenuInfo";                //共通菜单缓存
    public static final String MENU_INFO_PLATFORM_ID = "PID";                   //平台ID
    public static final String MENU_INFO_MALL_ID = "MID";                       //商城ID
    public static final String SESSION_BUSINESS_MENU_INFO = "businessMenuInfo";  //业务菜单
    public static final String MENU_INFO_SYSTEM_ID = "SID";                     //商城ID
    public static final String SESSION_BUSINESS_MENU_TOP_INFO = "businessMenuTopInfo";  //业务菜单
    public static final String SESSION_BUSINESS_MENU_LEFT_INFO = "businessMenuLeftInfo";  //业务菜单

    public static final String MENU_BELONG_FUN_TYPE_1 = "T1";                    //菜单所属功能类型 主页导航菜单
    public static final String MENU_BELONG_FUN_TYPE_2 = "T2";                    //菜单所属功能类型 主页管理菜单
    public static final String MENU_BELONG_FUN_TYPE_3 = "T3";                    //菜单所属功能类型 主页展示菜单
    public static final String MENU_BELONG_FUN_TYPE_4 = "T4";                    //菜单所属功能类型 展示详细菜单
    public static final String MENU_BELONG_FUN_TYPE_5 = "T5";                    //菜单所属功能类型 用户导航菜单
    public static final String MENU_BELONG_FUN_TYPE_6 = "T6";                    //菜单所属功能类型 个人中心菜单
    public static final String MENU_BELONG_FUN_TYPE_7 = "T7";                    //菜单所属功能类型 用户业务菜单
    public static final String MENU_LEVEL_1 = "1";                               //菜单级别1
    public static final String MENU_LEVEL_2 = "2";                               //菜单级别2

    public static final String MENU_BELONG_USER_TYPE_1 = "000";                  // 菜单类型 共通使用
    public static final String MENU_BELONG_USER_TYPE_1_1 = "00A";                  // 菜单类型 共通使用
    public static final String MENU_BELONG_USER_TYPE_1_2 = "00B";                  // 菜单类型 共通使用
    public static final String MENU_BELONG_USER_TYPE_1_3 = "00C";                  // 菜单类型 共通使用
    public static final String MENU_BELONG_USER_TYPE_1_4 = "00D";                  // 菜单类型 共通使用

    public static final String MENU_BELONG_USER_TYPE_2 = "U01";                  // 菜单类型 个人
    public static final String MENU_BELONG_USER_TYPE_3 = "E01";                  // 菜单类型 处置单位
    public static final String MENU_BELONG_USER_TYPE_4 = "E02";                  // 菜单类型 建设/施工单位
    public static final String MENU_BELONG_USER_TYPE_5 = "E03";                  // 菜单类型 物流单位
    public static final String MENU_BELONG_USER_TYPE_6 = "G01";                  // 菜单类型 政府
    public static final String MENU_BELONG_USER_TYPE_7 = "D01";                  // 菜单类型 处置场
    public static final String MENU_BELONG_USER_TYPE_8 = "P01";                  // 菜单类型 项目

    public static final String MENU_MOD_ID_HH_1 = "topMenu";                     //菜单模块ID 首页 顶部 菜单栏
    public static final String MENU_MOD_ID_HH_2 = "business";                    //菜单模块ID 首页 按钮 业务
    public static final String MENU_MOD_ID_HH_3 = "show";                        //菜单模块ID 首页 按钮 展示
    public static final String MENU_MOD_ID_HM_1 = "notice";                      //菜单模块ID 首页 菜单 公告菜单栏
    public static final String MENU_MOD_ID_HM_2 = "approval";                    //菜单模块ID 首页 菜单 核准菜单栏
    public static final String MENU_MOD_ID_HM_3 = "illegal";                     //菜单模块ID 首页 菜单 违章管理

    public static final String MENU_MOD_ID_BM_1 = "personalCenter";              //菜单模块ID 共通 顶部 个人中心

    public static final String MENU_MOD_ID_BH_G01_0 = "homePage";                //菜单模块ID 政府 顶部 首页
    public static final String MENU_MOD_ID_BH_G01_1 = "approval";                //菜单模块ID 政府 顶部 审批管理
    public static final String MENU_MOD_ID_BH_G01_2 = "monitor";                 //菜单模块ID 政府 顶部 监控管理
    public static final String MENU_MOD_ID_BH_G01_3 = "rating";                  //菜单模块ID 政府 顶部 评级管理
    public static final String MENU_MOD_ID_BH_G01_4 = "government";              //菜单模块ID 政府 顶部 政务公告
    public static final String MENU_MOD_ID_BH_G01_5 = "capital";                 //菜单模块ID 政府 顶部 资金监管
    public static final String MENU_MOD_ID_BH_G01_6 = "message";                 //菜单模块ID 政府 顶部 消息中心

    public static final String MENU_MOD_ID_BH_P01_0 = "homePage";                //菜单模块ID 项目 顶部 首页
    public static final String MENU_MOD_ID_BH_P01_1 = "project";                 //菜单模块ID 项目 顶部 项目管理
    public static final String MENU_MOD_ID_BH_P01_2 = "contract";                //菜单模块ID 项目 顶部 合约管理
    public static final String MENU_MOD_ID_BH_P01_3 = "approval";                //菜单模块ID 项目 顶部 核准管理
    public static final String MENU_MOD_ID_BH_P01_4 = "reserve";                 //菜单模块ID 项目 顶部 约车管理
    public static final String MENU_MOD_ID_BH_P01_5 = "distribution";            //菜单模块ID 项目 顶部 配送管理
    public static final String MENU_MOD_ID_BH_P01_6 = "finance";                 //菜单模块ID 项目 顶部 财务管理

    public static final String MENU_MOD_ID_BH_E02_0 = "homePage";                //菜单模块ID 建设/施工单位 顶部 首页
    public static final String MENU_MOD_ID_BH_E02_1 = "project";                 //菜单模块ID 建设/施工单位 顶部 项目管理
    public static final String MENU_MOD_ID_BH_E02_2 = "contract";                //菜单模块ID 建设/施工单位 顶部 合约管理
    public static final String MENU_MOD_ID_BH_E02_3 = "approval";                //菜单模块ID 建设/施工单位 顶部 核准管理
    public static final String MENU_MOD_ID_BH_E02_4 = "finance";                 //菜单模块ID 建设/施工单位 顶部 约车管理
    public static final String MENU_MOD_ID_BH_E02_5 = "distribution";            //菜单模块ID 建设/施工单位 顶部 配送管理
    public static final String MENU_MOD_ID_BH_E02_6 = "finance";                 //菜单模块ID 建设/施工单位 顶部 财务管理

    public static final String MENU_MOD_ID_BH_E03_0 = "homePage";                //菜单模块ID 运输单位 顶部 首页
    public static final String MENU_MOD_ID_BH_E03_1 = "vehicle";                 //菜单模块ID 运输单位 顶部 车辆管理
    public static final String MENU_MOD_ID_BH_E03_2 = "driver";                  //菜单模块ID 运输单位 顶部 司机管理
    public static final String MENU_MOD_ID_BH_E03_3 = "contract";                //菜单模块ID 运输单位 顶部 合约管理
    public static final String MENU_MOD_ID_BH_E03_4 = "reserve";                 //菜单模块ID 运输单位 顶部 预约管理
    public static final String MENU_MOD_ID_BH_E03_5 = "dispatch";                //菜单模块ID 运输单位 顶部 调度管理
    public static final String MENU_MOD_ID_BH_E03_6 = "distribution";            //菜单模块ID 运输单位 顶部 配送管理
    public static final String MENU_MOD_ID_BH_E03_7 = "finance";                 //菜单模块ID 运输单位 顶部 财务管理

    public static final String MENU_MOD_ID_BH_D01_0 = "homePage";                //菜单模块ID 处置场 顶部 首页
    public static final String MENU_MOD_ID_BH_D01_1 = "absorptive";              //菜单模块ID 处置场 顶部 处置场管理
    public static final String MENU_MOD_ID_BH_D01_2 = "contract";                //菜单模块ID 处置场 顶部 合约管理
    public static final String MENU_MOD_ID_BH_D01_3 = "sign";                    //菜单模块ID 处置场 顶部 签收管理
    public static final String MENU_MOD_ID_BH_D01_4 = "recycled";                //菜单模块ID 处置场 顶部 再生产品
    public static final String MENU_MOD_ID_BH_D01_5 = "finance";                 //菜单模块ID 处置场 顶部 财务管理

    public static final String MENU_MOD_ID_BH_E01_0 = "homePage";                //菜单模块ID 处置单位 顶部 首页
    public static final String MENU_MOD_ID_BH_E01_1 = "absorptive";              //菜单模块ID 处置单位 顶部 处置场管理
    public static final String MENU_MOD_ID_BH_E01_2 = "contract";                //菜单模块ID 处置单位 顶部 合约管理
    public static final String MENU_MOD_ID_BH_E01_3 = "sign";                    //菜单模块ID 处置单位 顶部 签收管理
    public static final String MENU_MOD_ID_BH_E01_4 = "recycled";                //菜单模块ID 处置单位 顶部 再生产品管理
    public static final String MENU_MOD_ID_BH_E01_5 = "finance";                 //菜单模块ID 处置单位 顶部 财务管理

    public static final String MENU_TYPE_1 = "M";                                //菜单类型 M:只显示型菜单 F：功能型菜单
    public static final String MENU_TYPE_2 = "F";                                //菜单类型 M:只显示型菜单 F：功能型菜单

    public static final String MENU_PARENT_MENU_ID_1 = null;                      //父菜单ID 没有父菜单

    public static final String MENU_TARGET_TYPE_1 = "self";                      //页面打开方式 self:画面迁移 blank：新窗口打开
    public static final String MENU_TARGET_TYPE_2 = "blank";                     //页面打开方式 self:画面迁移 blank：新窗口打开

    public static final String MENU_ROLE_TYPE_NO_ROLE = "ROLE_PERS";             //菜单角色分类 无角色
    /*End  菜单使用*/

    /**
     * START SESSION用常量
     **/
    public static final String SESSION_SEL_GROUP_AID = "sessionSelGroupAid";//选择群组AID      党群
    public static final String SESSION_SEL_GROUP_TYPE = "sessionSelGroupType";//选择群组类别              活动
    public static final String SESSION_SEL_GROUP_NAME = "sessionSelGroupName";//选择群组名
    public static final String SESSION_SEL_GROUP_STS = "sessionSelGroupSts";//选择群组名
    public static final String SESSION_SEL_GROUP_LIST = "sessionSelGroupList";//下拉选择list
    /**
     * END SESSION用常量
     **/

    public static final int APPLY_ORDER = 1; // 申请顺序
    public static final int APPROVE_STS_PENDING = 0; // 待审批
    public static final int APPROVE_STS_PROCESSING = 1; // 审批中
    public static final int APPROVE_STS_OK = 2; // 审批通过
    public static final int APPROVE_STS_REJECT = 3; // 审批未通过
    public static final int APPROVE_STS_COMPLETE = 4; // 审批完成
    public static final int APPROVE_STS_OTHER = 5; // 其他人审批
    public static final int AMOUNT_NOT_APPLY = 0; // 垃圾类型数量未申请
    public static final int AMOUNT_APPLY = 1; // 垃圾类型数量已申请
    public static final String APPROVE_AMOUNT_LOG_ID = "approve_amount_log_id"; // 核量logId
    public static final int ITEM_HIDE = 0; // 不显示项目
    public static final int INSPECT_YES = 1; // 已勘察
    public static final int INSPECT_NO = 0; // 未勘察
    //核准二维码常量
    public static final String APPROVE_QR = "APPR_DISP";

    /*Start  角色用信息*/
    public static final String ROLE_GOV = "ROLE_GOV";//政府超级管理员
    public static final String ROLE_CONST = "ROLE_CONST";//建设/施工单位超级管理员
    public static final String ROLE_DPE = "ROLE_DPE";//处置单位超级管理员
    public static final String ROLE_LOG = "ROLE_LOG";//运输单位超级管理员
    public static final String ROLE_DPR = "ROLE_DPR";//处置场超级管理员
    public static final String ROLE_PJ = "ROLE_PJ";//政府超级管理员
    public static final String ROLE_PERS = "ROLE_PERS";//个人
    /*End  角色用信息*/

    /*Start 处置场  处置类型*/
    public static final String DISPOSE_TYPE_1 = "1";//临时堆放
    public static final String DISPOSE_TYPE_2 = "2";//规范化填埋
    public static final String DISPOSE_TYPE_3 = "3";//现场式资源化处置
    public static final String DISPOSE_TYPE_4 = "4";//工厂式资源化处置
    /*End 处置场  处置类型*/

    public static final String SYSTEM_ID = "cws_all_system";

    /*Start 车辆相关 */

    //车辆审核状态
    public static final String VEHICLE_APPROVE_STS_0 = "0"; //已通过
    public static final String VEHICLE_APPROVE_STS_1 = "1"; //未通过
    public static final String VEHICLE_APPROVE_STS_2 = "2"; //未提交
    public static final String VEHICLE_APPROVE_STS_3 = "3"; //待审核
    public static final String VEHICLE_APPROVE_STS_4 = "4"; //已过期
    public static final String VEHICLE_APPROVE_STS_5 = "5"; //已通过，不能运输

    public static final String VEHICLE_SELECTED_STS_0 = "0"; //未选择该车辆
    public static final String VEHICLE_SELECTED_STS_1 = "1"; //已选择该车辆
    //车辆运输状态 0:空载、1：重载、2：返程中
    public static final String VEHICLE_TRANSPORT_STS_1 = "0"; //空载
    public static final String VEHICLE_TRANSPORT_STS_2 = "1"; //重载
    public static final String VEHICLE_TRANSPORT_STS_3 = "2"; //返程中
    // 车辆在线状态 1：在线 0：离线
    public static final String VEHICLE_ONLINE_STS_0 = "0"; //离线
    public static final String VEHICLE_ONLINE_STS_1 = "1"; //在线


    public static final String MQ_MSG_KEY_VEHICLE = "MonStaCenForVeh"; //统计中心发送车辆消息KEY
    public static final String MQ_MSG_KEY_SOLIDWASTE = "MonSolidwasteSta"; //统计中心发送固废统计消息KEY
//    public static final String MQ_MSG_KEY_VIOLATIONRESULT = "ViolationToEvaluateDefaultForVehResult"; //发送消息(单位评级出计划)KEY

    //判断值类型
    public static final String RULE_TYPE_0 = "0"; //数字
    public static final String RULE_TYPE_1 = "1"; //日期

    //判断条件 0大于，1小于，2大于等于，3小于等于，4等于，5不等于
    public static final String RULE_CONDITION_0 = "0"; // >
    public static final String RULE_CONDITION_1 = "1"; // <
    public static final String RULE_CONDITION_2 = "2"; // >=
    public static final String RULE_CONDITION_3 = "3"; // <=
    public static final String RULE_CONDITION_4 = "4"; // ==
    public static final String RULE_CONDITION_5 = "5"; // !=

    /*End 车辆相关 */

    /*******各种单据状态**********/
    //预约单状态
    public static final String PRECONTRACT_ORDER_STS_0 = "0"; //待接受
    public static final String PRECONTRACT_ORDER_STS_1 = "1"; //已拒绝
    public static final String PRECONTRACT_ORDER_STS_2 = "2"; //执行中
    public static final String PRECONTRACT_ORDER_STS_3 = "3"; //已到达
    public static final String PRECONTRACT_ORDER_STS_4 = "4"; //已完成
    public static final String PRECONTRACT_ORDER_STS_5 = "5"; //已取消
    public static final String PRECONTRACT_ORDER_STS_6 = "6"; //已撤回

    //承运商应约单状态
    public static final String RESPONSION_ORDER_STS_0 = "0"; //待应答
    public static final String RESPONSION_ORDER_STS_1 = "1"; //已撤回
    public static final String RESPONSION_ORDER_STS_2 = "2"; //执行中
    public static final String RESPONSION_ORDER_STS_3 = "3"; //已取消
    public static final String RESPONSION_ORDER_STS_4 = "4"; //已完成

    //派车单状态
    public static final String ALLOT_VEHICLE_ORDER_STS_0 = "0"; //待应答
    public static final String ALLOT_VEHICLE_ORDER_STS_1 = "1"; //未接受
    public static final String ALLOT_VEHICLE_ORDER_STS_2 = "2"; //已接受
    public static final String ALLOT_VEHICLE_ORDER_STS_3 = "3"; //已取消

    //司机应约单状态
    public static final String DRIVER_RESPONSION_ORDER_STS_0 = "0"; //待应答
    public static final String DRIVER_RESPONSION_ORDER_STS_1 = "1"; //未接受
    public static final String DRIVER_RESPONSION_ORDER_STS_2 = "2"; //已接受
    public static final String DRIVER_RESPONSION_ORDER_STS_3 = "3"; //已达到
    public static final String DRIVER_RESPONSION_ORDER_STS_4 = "4"; //已取消
    public static final String DRIVER_RESPONSION_ORDER_STS_5 = "5"; //已完成
    public static final String DRIVER_RESPONSION_ORDER_STS_6 = "6"; //返程中
    public static final String DRIVER_RESPONSION_ORDER_STS_7 = "7"; //配送中

    //发货单状态
    public static final String DELIVER_ORDER_STS_0 = "0"; //待确认
    public static final String DELIVER_ORDER_STS_1 = "1"; //未发货
    public static final String DELIVER_ORDER_STS_2 = "2"; //已发货
    public static final String DELIVER_ORDER_STS_3 = "3"; //已拒绝

    //配送单状态
    public static final String DISPATCH_ORDER_STS_0 = "0"; //配送中
    public static final String DISPATCH_ORDER_STS_1 = "1"; //已到达
    public static final String DISPATCH_ORDER_STS_2 = "2"; //待签收
    public static final String DISPATCH_ORDER_STS_3 = "3"; //已签收
    public static final String DISPATCH_ORDER_STS_4 = "4"; //已拒绝
    public static final String DISPATCH_ORDER_STS_5 = "5"; //待接受

    //配送单补签状态（配送单 新加 字段）
    public static final String AFTER_RECEIVE_STS_0 = "0";//正常签
    public static final String AFTER_RECEIVE_STS_1 = "1";//待补签
    public static final String AFTER_RECEIVE_STS_2 = "2";//已补签

    //发货单补发状态（发货单 新加 字段）
    public static final String AFTER_DELIVER_STS_0 = "0";//正常发
    public static final String AFTER_DELIVER_STS_1 = "1";//待补发
    public static final String AFTER_DELIVER_STS_2 = "2";//已补发

    //车辆审核单据状态
    public static final String APPROVE_STS_0 = "0"; //待审批
    public static final String APPROVE_STS_1 = "1"; //已审批
    public static final String APPROVE_STS_3 = "3"; //已过期

    //单号前缀
    public static final String ORDER_RES = "RES"; //预约单
    public static final String ORDER_REO = "REO"; //应约单
    public static final String ORDER_SEN = "SEN"; //派车单
    public static final String ORDER_REN = "REN"; //司机应约单
    public static final String ORDER_DEN = "DEN"; //发货单
    public static final String ORDER_DIN = "DIN"; //配送单
    public static final String ORDER_VAA = "VAA"; //车辆核准单

    public static final String ORDER_XC = "XC"; //巡查编号


    //APP 三端类别
    public static final String APP_TYPE_DRIVR = "0"; //司机端
    public static final String APP_TYPE_DISPR = "1"; //发货端（调度端）
    public static final String APP_TYPE_RECPT = "2"; //签收端


    public static final String APP_DEVICE_DRIVER = "CWSA01"; //司机端
    public static final String APP_DEVICE_DISPR = "CWSA02"; //发货端（调度端）
    public static final String APP_DEVICE_RECPT = "CWSA03"; //签收端

    //APP登录存储token常量
    public static final String APP_LOGIN_TOKEN = "appLoginToken";

    //map调用汽车服务接口存redisKey
    public static final String MAP_VEHICLE_REDIS_KEY = "mapVehicleRedisKey";



    /* Start 资质相关 */
    public static char IMG_TYPE_HEADER = 'H';//头像
    public static char IMG_TYPE_QUA = 'Q';//资质图片
    public static char IMG_TYPE_INTRODUCE = 'T';//简介图片
    /* END 资质相关 */


    //APP核量部分，token未登陆状态固定值
    public static String APPROVE_AMOUT_NO_LOGIN="approve_no_login";

    // MQ使用KEY名 start
    // 发车消息 KEY
    public static String MQ_MSG_KEY_1 = "senderVehicleMsg";
    // 签收消息 KEY
    public static String MQ_MSG_KEY_2 = "signVehicleMsg";
    // MQ使用KEY名 end

    // 在线状态更新时间间隔类型 start
    // 年：year
    public static String STR_TIME_INTERVALS_UNIT_1 = "year";
    // 月：month
    public static String STR_TIME_INTERVALS_UNIT_2 = "month";
    // 日：day
    public static String STR_TIME_INTERVALS_UNIT_3 = "day";
    // 时：hour
    public static String STR_TIME_INTERVALS_UNIT_4 = "hour";
    // 分：minute
    public static String STR_TIME_INTERVALS_UNIT_5 = "minute";
    // 秒：second
    public static String STR_TIME_INTERVALS_UNIT_6 = "second";

    // 处置场在线状态时间间隔
    public static String STR_TIME_INTERVALS_TYPE_1 = "DIS_ONLINE_STS_TIME_INTERVALS";
    // 项目在线状态时间间隔
    public static String STR_TIME_INTERVALS_TYPE_2 = "PJ_ONLINE_STS_TIME_INTERVALS";
    // 在线状态更新时间间隔类型 end


    //---------------------对账结算-------------------------------
    //对账开始日期
    public static String PAYMENT_START_DATE = "2016-08-16";

    //系统对账时间跨度(单位：天)
    public static int PAYMENT_ALONG = 30;
    //对账状态-  1：待处理   2：延期处理 3：作废  4：已统计   5:已生成(正常)   6：已提交   7:已确认
    public static String PAYMENT_STATUS_1 = "1";
    public static String PAYMENT_STATUS_2 = "2";
    public static String PAYMENT_STATUS_3 = "3";
    public static String PAYMENT_STATUS_4 = "4";
    public static String PAYMENT_STATUS_5 = "5";
    public static String PAYMENT_STATUS_6 = "6";
    public static String PAYMENT_STATUS_7 = "7";
    //付款状态状态-  1：已付款   0：未付款
    public static int PAY_STATUS_0 = 0;
    public static int PAY_STATUS_1 = 1;

    //结算价格标识-   0:待计算   1：合约价格   2：非合约价格
    public static String PAYMENT_PRICE_0 ="0" ;
    public static String PAYMENT_PRICE_1 = "1";
    public static String PAYMENT_PRICE_2 = "2";

    // 账单账目状态 0：未提交(已保存)   1：已提交  2：已生效（已同意）   3：已失效  4：已完成 5：未生效（未同意）
    public static int ACCOUNT_STATUS_0 = 0;
    public static int ACCOUNT_STATUS_1 = 1;
    public static int ACCOUNT_STATUS_2 = 2;
    public static int ACCOUNT_STATUS_3 = 3;
    public static int ACCOUNT_STATUS_4 = 4;
    public static int ACCOUNT_STATUS_5 = 5;

    // 对账单flag标示
    public static String FLAG_1 = "1";
    public static String FLAG_2 = "2";
    public static String FLAG_3 = "3";
    public static String FLAG_4 = "4";
    public static String FLAG_5 = "5";
    public static String FLAG_6 = "6";
    public static String FLAG_7 = "7";
    /**
     * 二维码对象类型定义列表
     */
    // 个人	USER_U01	用户类型
    public static String QR_TYPE_USER_U01 = "USER_U01";
    // 政府	USER_G01	用户类型
    public static String QR_TYPE_USER_G01 = "USER_G01";
    // 项目	USER_P01	用户类型
    public static String QR_TYPE_USER_P01 = "USER_P01";
    // 处置场	USER_D01	用户类型
    public static String QR_TYPE_USER_D01 = "USER_D01";
    // 处置单位	USER_E01	用户类型
    public static String QR_TYPE_USER_E01 = "USER_E01";
    // 建设/施工单位	USER_E02	用户类型
    public static String QR_TYPE_USER_E02 = "USER_E02";
    // 运输单位	USER_E03	用户类型
    public static String QR_TYPE_USER_E03 = "USER_E03";
    // 车辆	EQUIP_VEC	设备类型
    public static String QR_TYPE_EQUIP_VEC = "EQUIP_VEC";
    // 处置合约单	CONT_DISP	合约类型
    public static String QR_TYPE_CONT_DISP = "CONT_DISP";
    // 清运合约单	CONT_CLOG	合约类型
    public static String QR_TYPE_CONT_CLOG = "CONT_CLOG";
    // 处置核准单	APPR_DISP	核准类型
    public static String QR_TYPE_APPR_DISP = "APPR_DISP";
    // 车辆核准单	APPR_CLOG	核准类型
    public static String QR_TYPE_APPR_CLOG = "APPR_CLOG";
    /**
     *二维码时效性校验方式
     */
    // 时间：T
    public static char QR_V_TYPE_1 = 'T';
    // 次数：C
    public static char QR_V_TYPE_2 = 'C';
    // 永久
    public static char QR_V_TYPE_3 = 'F';


    public static final String PROJECT_MQSENDER_MSG_KEY = "MonStaCenForPj"; //项目MQSenderKey
    public static final String DISPOSE_MQSENDER_MSG_KEY = "MonStaCenForDisp"; //处置场MQSenderKey

    public static final String PROJECT_VIOLATION_MQSENDER_MSG_KEY = "VioToEvaDefForPJRes"; //项目评分MQSenderKey
    public static final String DISPOSE_VIOLATION_MQSENDER_MSG_KEY = "VioToEvaDefForDisRes"; //处置场评分MQSenderKey
    public static final String LOGISTICS_VIOLATION_MQSENDER_MSG_KEY = "VioToEvaDefForVehRes"; //运输单位评分MQSenderKey
    public static final String VIOLATION_EVA_DIS_RES_MSG_KEY = "VioToEvaForDisResult";
    public static final String VIOLATION_EVA_PJ_RES_MSG_KEY = "VioToEvaForPJResult";
    public static final String VIOLATION_EVA_VEH_RES_MSG_KEY = "VioToEvaForVehResult";
    public static final String VIOLATION_EVA_DIS_DEL_MSG_KEY = "VioToEvaForDisDel";
    public static final String VIOLATION_EVA_PJ_DEL_MSG_KEY = "VioToEvaForPJDel";
    public static final String VIOLATION_EVA_VEH_DEL_MSG_KEY = "VioToEvaForVehDel";






    /**
     * 认证相关start
     */
    public static final String ACCREDITATION_CERTIFICATE_URL = "http://i.yjapi.com/IDCard/Verify"; //身份证实名验证url
    public static final String ACCREDITATION_ENTERPRISE_URL = "http://i.yjapi.com/ECIMatch/CompanyVerify"; //单位认证url
    public static final String ACCREDITATION_KEY = "e086f8a26d6d4f67a39f5bc4ac92100a"; //认证key
    public static final String ACCREDITATION_NAME_QUERY = "http://i.yjapi.com/ECISimple/GetDetailsByName";

    /**
     * 处置场实体Bean状态
     */
    // 0	停止营业
    public static final String DIS_BEAN_STS_1 = "0";
    // 1	营业中
    public static final String DIS_BEAN_STS_2 = "1";
    // 2	正在签收
    public static final String DIS_BEAN_STS_3 = "2";
    /**
     * 项目实体Bean状态
     */
    // 0	停工
    public static final String PJ_BEAN_STS_1 = "0";
    // 1	执行中
    public static final String PJ_BEAN_STS_2 = "1";
    // 2	已完成
    public static final String PJ_BEAN_STS_3 = "2";
    // 3	正在作业
    public static final String PJ_BEAN_STS_4 = "3";
    // 4	暂停
    public static final String PJ_BEAN_STS_5 = "4";

    /**
     * 定时器使用的常量
     */
    //定时器中的用户信息
    public static final String STR_USER_INFO = "ALLUSERINFO";
    //定时器中的垃圾类型信息
    public static final String STR_WASTE_INFO = "ALLWASTEINFO";
    //定时器中的角色信息
    public static final String STR_ROLE_INFO = "ALLROLEINFO";
    //定时器中的所有地址信息
    public static final String STR_ADR_INFO_ALL_JSON = "ADRINFOALLJSON";

    /**
     * 关系用户状态
     * 0:申请/确认中，1：确认完成，9：解除
     */
    public static final String RELATION_MEMBER_STS_0 = "0";
    public static final String RELATION_MEMBER_STS_1 = "1";
    public static final String RELATION_MEMBER_STS_9 = "9";

//    /**
//     * 洛阳商城临时应对（摄屏卡操作）
//     */
    public static final String CWS_MID= "CWS-MID-Guangdong-Dongguan";
    public static final String PlatformId= "CWS-PID-Beta";
    public static final String DataSourceName="UNIEAP";
    
    public static final String IMPORTCRM="IMPORTCRM";
    
    public static final String systemId="CWS-SID-00";
    public static final String tenantId="441900";//东莞
    
    public static final String USER_INFO = "userInfo";
    /**
     * 核准类型（1：排放许可；2：处置备案）
     */
    public static final int APPLY_TYPE_1 = 1;
    public static final int APPLY_TYPE_2 = 2;

    //地图报警缓存用相关常量定义
    //获取缓存清运单（配送单）
    public static String ALARM_DISPATCHORDER="alarm_dispatchorder";


    /**
     * 调用乐橙api需要的常量
     */
    public static String PORT = "443";
    public static String HOST = "https://openapi.lechange.cn";
    // 如果不知道appid，请登录open.lechange.com，开发者服务模块中创建应用
    public static String APPID = "lc86650f4805584776";
    // 如果不知道appsecret，请登录open.lechange.com，开发者服务模块中创建应用
    public static String SECRET = "89ed0b54b9384f5382ae9e2eec7916";
    // 管理员账号
    public static final String PHONE = "f3c11c8c98ff417f";

    //大华视频json
    public static final String DHVideoJson = "{\n" +
            "    \"sim\": null,\n" +
            "    \"plateCode\": \"15700600545\",\n" +
            "    \"deviceId\": \"15700600545\",\n" +
            "    \"deviceType\": \"\",\n" +
            "    \"deviceName\": \"\",\n" +
            "    \"channelNum\": \"2\",\n" +
            "    \"channelName\": \"CH1,CH2,CH3,CH4\",\n" +
            "    \"registrationServicePort\": \"\",\n" +
            "    \"deviceIp\": \"119.23.225.10\",\n" +
            "    \"devicePort\": \"6601\",\n" +
            "    \"provinceId\": \"\",\n" +
            "    \"cityId\": \"\",\n" +
            "    \"manufacturer\": \"\",\n" +
            "    \"model\": \"\",\n" +
            "    \"streamingMediaServerIP\": \"192.168.1.108\",\n" +
            "    \"streamingMediaServerPort\": \"37777\",\n" +
            "    \"streamingMediaServerUsername\": \"admin\",\n" +
            "    \"streamingMediaServerPassword\": \"123456\",\n" +
            "    \"signalingServerIP\": \"\",\n" +
            "    \"signalingServerPort\": \"\",\n" +
            "    \"signalingServerUsername\": \"\",\n" +
            "    \"signalingServerPassword\": \"\",\n" +
            "    \"balancedServerIP\": \"\",\n" +
            "    \"balancedServerPort\": \"\",\n" +
            "    \"onlineStatus\": null\n" +
            "}";

    //乐橙视频json
    public static final String LeChangeVideoJson ="{\"sim\":null,\"plateCode\":\"15700600545\",\"deviceId\":\"2M04881PBQ2TU8J\",\"deviceType\":\"\",\"deviceName\":\"\",\"channelNum\":\"4\",\"channelName\":\"CH1,CH2,CH3,CH4\",\"registrationServicePort\":\"\",\"deviceIp\":\"119.23.225.10\",\"devicePort\":\"6601\",\"provinceId\":\"\",\"cityId\":\"\",\"manufacturer\":\"dhlc\",\"model\":\"\",\"streamingMediaServerIP\":\"192.168.1.108\",\"streamingMediaServerPort\":\"37777\",\"streamingMediaServerUsername\":\"admin\",\"streamingMediaServerPassword\":\"123456\",\"signalingServerIP\":\"\",\"signalingServerPort\":\"\",\"signalingServerUsername\":\"\",\"signalingServerPassword\":\"\",\"balancedServerIP\":\"\",\"balancedServerPort\":\"\",\"onlineStatus\":null,\"runModel\":\"realplay\",\"channel\":\"1\",\"startTime\":\"2017-07-12\",\"token\":\"At_8aaf0962f99a4454a7c2f759381d5fe5\"}";

    //1:大华直连，2：大华主动注册，3：乐橙P2P
    public static final String NvrType_1 = "1";
    public static final String NvrType_2 = "2";
    public static final String NvrType_3 = "3";

    //运单状态 1、已运，0、未运
    public final static String orderStatus_1 = "1";
    public final static String orderStatus_2 = "0";

    //作废状态1、作废，0、未作废
    public final static int obsoleteStatus_1 = 1;
    public final static int obsoleteStatus_2 = 0;

    //发布常量
    public final static String releaseStatus_0 = "0";//待审核
    public final static String releaseStatus_1 = "1";//审核通过
    public final static String releaseStatus_2 = "2";//审核不通过
    //上下架常量
    public final static String upperLowerSts_0 = "0";//下架
    public final static String upperLowerSts_1 = "1";//上架

}
