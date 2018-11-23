package com.qg.anywork.enums;

/**
 * Created by FunriLy on 2017/7/10.
 * From small beginnings comes great things.
 */
public enum StatEnum {

    /**
     * 通用板块
     */
    VALCODE_WRONG(0, "验证码错误"),
    DEFAULT_WRONG(0, "其他错误"),
    CAUSE_TROUBLE(0, "你不要搞事"),
    ERROR_PARAM(0, "输入参数有误"),
    REQUEST_ERROR(0, "请求信息异常"),
    PARAM_IS_EMPTY(0, "参数为空"),
    PARAM_IS_NOT_EXIST(0, "参数不存在"),

    /**
     * 注册板块
     */
    REGISTER_SUCESS(1, "用户注册成功"),
    REGISTER_EMPTY_USER(0, "空用户对象"),
    REGISTER_FORMAT_FAULT(0, "注册信息格式错误"),
    REGISTER_ALREADY_EXIST(0, "已存在的用户"),
    STUDENT_ID_NOT_FOUND(0, "没有找到该学号对应的记录"),
    STUDENT_ID_HAS_BEEN_REGISTERED(0, "该学号已经被注册"),
    MAIL_HAS_BEEN_REGISTERED(0, "邮箱已经被注册"),

    /**
     * 登录板块
     */
    LOGIN_SUCCESS(1, "用户登录成功"),
    LOGIN_NOT_EXIT_USER(0, "不存在的用户"),
    LOGIN_USER_MISMATCH(0, "用户名或密码错误"),
    USER_NOT_LOGIN(0, "用户还未登录"),
    USER_LOGIN_OUT(1, "用戶退出登录"),
    LOGIN_HAVE_ERROR(0, "用户登录发生错误"),

    /**
     * 更新用户信息板块
     */
    INFORMATION_CHANGE_SUCCESS(1, "用户更改信息成功"),
    OLD_PASSWORD_ERROR(0, "原密码输入错误"),
    NEW_PASSWORD_FORMAT_ERROR(0, "新密码格式错误"),
    EMAIL_FORMAT_ERROR(0, "邮箱格式错误"),
    PASSWORD_FORMAT_ERROR(0, "密码格式错误"),
    USERNAME_FORMAT_ERROR(0, "用户名格式不对"),
    PHONE_FORMAT_ERROR(0, "手机格式错误"),
    FROMATTER_WARNING(0, "格式有误"),
    INFORMATION_GET_MYSELF(1, "获取个人信息"),
    NOT_FOUND_USER_USE_THIS_EMAIL(0, "没有找到该邮箱对应的用户"),
    INCONSISTENT_PASSWORD(0, "密码不一致"),
    PASSWORD_RESET_SUCCESS(1, "密码重置成功"),
    PASSWORD_RESET_FAIL(0, "密码重置失败"),


    /**
     * 文件上传类型板块
     */
    FILE_UPLOAD_SUCCESS(1, "文件上传成功"),
    FILE_UPLOAD_FAIL(0, "文件上传失败"),
    FILE_FORMAT_ERROR(0, "文件格式错误"),
    PICTURE_UPLOAD_SUCCESS(1, "头像上传成功"),
    FILE_READ_SUCCESS(1, "文件读取成功"),
    FILE_READ_FAIL(0, "文件读取失败"),
    FILE_NOT_EXIT(0, "文件不存在"),
    FILE_EXPORT_SUCCESS(1, "Excel文件导出成功"),
    FILE_EXPORT_FAIL(0, "Excel文件导出失败"),
    FILE_PARSE_FAIL(0, "Excel文件解析失败"),


    /**
     * 试卷、练习板块
     */
    TEST_RELEASE_SUCESS(1, "发布成功"),
    TEST_RELEASE_FAIL(0, "发布试卷/练习失败"),
    NOT_HAVE_POWER(0, "没有相应的权限"),
    DELETE_TEST_SUCCESS(1, "删除试卷/练习成功"),
    DELETE_TEST_FAIL(0, "删除试卷/练习失败"),
    TEST_IS_NOT_EXIT(0, "试卷/练习不存在"),
    EXAM_DID_NOT_START_YET(0, "考试还未开始"),
    THE_EXAM_IS_OVER(0, "考试已经结束"),
    TEST_TIME_IS_OVER(0, "该试卷的最晚提交时间已过"),
    EXAM_CANNOT_BE_SUBMITTED_REPEATEDLY(0, "试卷无法重复提交"),
    TEST_LIST_IS_NULL(0, "没有对应的试卷"),
    GET_SUCCESS(1, "获取成功"),
    WRONG_QUESTION_LIST_IS_NULL(0, "错题列表为空"),
    TEST_PAPER_TIME_ERROR(0, "试卷时间有错误"),
    NOT_HAVE_LEADER_BOARD(0, "暂时没有排行榜"),

    /**
     * 组织板块
     */
    ORGAN_SEARCH_SUCCESS(1, "搜索组织成功"),
    ORGAN_SEARCH_FAIL(0, "搜索组织失败"),
    ORGAN_JOIN_SUCCESS(1, "加入组织成功"),
    ORGAN_JOIN_FAIL(0, "加入组织失败"),
    ORGANIZATION_NOT_EXIST(0, "组织不存在"),
    CHAPTER_TOO_LONG(0, "章节名过长"),
    THE_NAME_IS_NULL(0, "搜索的组织名为空"),
    USER_HAS_JOINED_THE_ORGANIZATION(0, "用户已加入该组织"),
    CAN_ONLY_JOIN_ONE_ORGANIZATION(0, "只能加入一个组织"),
    USER_HAS_NOT_JOINED_THE_ORGANIZATION(0, "用户未加入该组织"),
    THE_TOKEN_IS_ERROR(0, "口令错误"),
    TOKEN_IS_NULL(0, "口令为空"),
    INVALID_ORGANIZATION(0, "无效的组织"),
    MY_ORGANIZATION_IS_NULL(0, "您还未创建组织"),

    /***
     * 做题模块
     */
    GET_TEST_SUCCESS(1, "获取成功"),
    GET_TEST_FAIL(0, "获取失败"),
    SUBMIT_TEST_SUCCESS(1, "提交成功"),
    SUBMIT_TEST_FAIL(0, "提交失败"),
    SAVE_TEST_SUCCESS(1, "保存进度成功"),
    COLLECT_SUCCESS(1, "收藏成功"),
    DO_NOT_COLLECT_AGIAN(0, "不用重复收藏"),
    DELETE_COLLECTION_SUCCESS(1, "删除收藏成功"),
    COLLECTION_LIST_IS_NULL(0, "收藏列表为空"),

    /**
     * 邮件模块
     */
    MAIL_SEND_SUCCESS(1, "邮件发送成功"),
    MAIL_SEND_FAIL(0, "邮件发送失败"),
    PASSWORD_RESET(1, "重置密码成功"),
    MAIL_VERIFICATION_SUCCESS(1, "邮箱验证成功"),

    /**
     * 消息模块
     */
    MESSAGE_LIST(1, "获取用户消息列表"),
    PAGE_IS_ERROR(0, "页面值非法"),


    /**
     * 缓存模块
     */
    REDIS_CACHE_NOT_FOUND(0, "未找到相应的缓存文件"),

    DATA_LIST_IS_NULL(0, "数据列表为空"),


    /**
     * 公告模块
     */
    MESSAGE_PUBLISH_SUCCESS(1, "公告发布成功"),

    LIST_MESSAGE_SUCCESS(1, "获取公告列表成功"),

    NOT_SUCH_MESSAGE(0, "没有该公告"),

    MESSAGE_LIST_IS_NULL(0, "公告列表为空");


    private int state;
    private String stateInfo;

    StatEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static StatEnum statOf(int index) {
        for (StatEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }
}
