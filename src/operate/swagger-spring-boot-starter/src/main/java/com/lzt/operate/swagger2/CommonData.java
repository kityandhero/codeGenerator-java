package com.lzt.operate.swagger2;

/**
 * Created by yueh on 2018/9/18.
 */
public class CommonData {

    public static final String RESULT_TYPE_NORMAL_FINAL = "normal";
    private static String RESULT_TYPE_NORMAL = "normal";
    private static String RESULT_TYPE_PAGE = "page";
    private static String RESULT_TYPE_LIST = "list";
    private static String RESULT_TYPE_OTHER = "other";
    private static String JSON_ERROR_CODE = "code";
    private static String JSON_ERROR_MSG = "message";
    private static String JSON_START_PAGE_NUM = "startPageNum";
    private static String JSON_PAGE_SIZE = "pageSize";
    private static String JSON_PAGE_COUNT = "pageCount";
    private static String JSON_TOTAL_COUNT = "totalCount";

    static String getResultTypeNormal() {
        return RESULT_TYPE_NORMAL;
    }

    public static void setResultTypeNormal(String resultTypeNormal) {
        RESULT_TYPE_NORMAL = resultTypeNormal;
    }

    static String getResultTypePage() {
        return RESULT_TYPE_PAGE;
    }

    public static void setResultTypePage(String resultTypePage) {
        RESULT_TYPE_PAGE = resultTypePage;
    }

    public static String getResultTypeList() {
        return RESULT_TYPE_LIST;
    }

    public static void setResultTypeList(String resultTypeList) {
        RESULT_TYPE_LIST = resultTypeList;
    }

    static String getResultTypeOther() {
        return RESULT_TYPE_OTHER;
    }

    public static void setResultTypeOther(String resultTypeOther) {
        RESULT_TYPE_OTHER = resultTypeOther;
    }

    static String getJsonErrorCode() {
        return JSON_ERROR_CODE;
    }

    public static void setJsonErrorCode(String jsonErrorCode) {
        JSON_ERROR_CODE = jsonErrorCode;
    }

    static String getJsonErrorMsg() {
        return JSON_ERROR_MSG;
    }

    public static void setJsonErrorMsg(String jsonErrorMsg) {
        JSON_ERROR_MSG = jsonErrorMsg;
    }

    static String getJsonStartPageNum() {
        return JSON_START_PAGE_NUM;
    }

    public static void setJsonStartPageNum(String jsonStartPageNum) {
        JSON_START_PAGE_NUM = jsonStartPageNum;
    }

    static String getJsonPageSize() {
        return JSON_PAGE_SIZE;
    }

    public static void setJsonPageSize(String jsonPageSize) {
        JSON_PAGE_SIZE = jsonPageSize;
    }

    public static String getJsonPageCount() {
        return JSON_PAGE_COUNT;
    }

    public static void setJsonPageCount(String jsonPageCount) {
        JSON_PAGE_COUNT = jsonPageCount;
    }

    static String getJsonTotalCount() {
        return JSON_TOTAL_COUNT;
    }

    public static void setJsonTotalCount(String jsonTotalCount) {
        JSON_TOTAL_COUNT = jsonTotalCount;
    }
}