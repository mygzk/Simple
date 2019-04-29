package com.example.lib.file;

import java.io.File;

/**
 * create by guozhk on 2019/4/13 11:16
 **/
public class MyFile {

    private final static String context_dangdang = "dangdang";
    private final static String context_iTableMos = "iTableMOS";
    private final static String context_pcs = "pcs";

    /**
     * 服务器主机地址
     */
    private final static String BASE_SERVER_MAIN = "http://10.230.163.235";
    private final static String BASE_SERVER_PORT = "8008";
    private final static String BASE_SERVER_MAIN_URL = BASE_SERVER_MAIN + ":" + BASE_SERVER_PORT;


    //wps预览功能下载附件接口的服务器ip
    public static final String server_wpspreview_download = "http://10.229.145.42";

    /**
     * 天气地址
     */
    public static final String WEATHER_SERVICE = "https://restapi.amap.com/v3/weather/weatherInfo";

    //投票H5界面链接地址
    public static final String VOTE_CREATE_URL = BASE_SERVER_MAIN_URL + "/voteCreate.html";
    public static final String VOTE_LIST_URL = BASE_SERVER_MAIN_URL + "/voteCreate.html";
    public static final String VOTE_MYVOTE_URL = BASE_SERVER_MAIN_URL + "/voteMyself.html";


    //代办中心
    public static final String TODO_CENTER_MAIN = BASE_SERVER_MAIN_URL + File.separator + context_dangdang + "/todocenter/index.html";
    public static final String TODO_CENTER_INDEX_URL = TODO_CENTER_MAIN + "#/todolistIndex?userId=%s";

    //登录
    public static final String LOGIN_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_dangdang + "/login";
    public static final String LOGOUT_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_dangdang + "/LogOut";
    public static final String FACELOGIN_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_iTableMos + "/faceLogin";
    public static final String FACESIGN_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_iTableMos + "/faceSign";
    public static final String FACECHECK_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_iTableMos + "/faceCheck";

    //考勤接口地址
    public static final String KAOQIN_QIANDAO_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_dangdang + "/attendance/signIn";
    public static final String KAOQIN_QIANTUI_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_dangdang + "/attendance/signOut";

    //人员搜索
    public static final String SEARCH_PEOPLE_SERVICE = BASE_SERVER_MAIN_URL + File.separator + context_dangdang + "/search/searchContactList";


    /**
     * 会议服务器主机地址
     */
    private final static String BASE_SERVER_MEETING = "http://10.233.93.110";
    private final static String BASE_SERVER__MEETING_PORT = "344";
    private final static String BASE_SERVER_MEETING_URL = BASE_SERVER_MEETING + ":" + BASE_SERVER__MEETING_PORT + File.separator + context_pcs;


    public static final String MEETING_N001 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n001";
    public static final String MEETING_N002 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n002";
    public static final String MEETING_N003 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n003";
    public static final String MEETING_N004 = server_wpspreview_download + File.separator + context_dangdang + "/api/rest/meeting/n004";
    public static final String MEETING_N005 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n005";
    public static final String MEETING_N006 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n006";
    public static final String MEETING_N013 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n013";
    public static final String MEETING_N014 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n014";
    public static final String MEETING_N015 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n015";
    public static final String MEETING_N016 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n016";
    public static final String MEETING_N017 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n017";
    public static final String MEETING_N018 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n018";
    public static final String MEETING_N019 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n019";
    public static final String MEETING_N035 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n035";
    public static final String MEETING_N036 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n036";
    public static final String MEETING_N037 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n037";
    public static final String MEETING_N038 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n038";
    public static final String MEETING_N039 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n039";
    public static final String MEETING_N040 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n040";
    public static final String MEETING_N041 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n041";
    public static final String MEETING_N047 = BASE_SERVER_MEETING_URL + "/api/rest/meeting/n047";


    //wps预览
    public static final String WPS_PREVIEW = BASE_SERVER_MAIN_URL + "/xview/v1/view/preview";

    // public static final String WPS_REPLACE_INTERCEPT_URL = BASE_SERVER_MAIN_URL + "wps_replace_intercept_url";
    // public static final String WPS_REPLACE_TARGET_1 = BASE_SERVER_MAIN_URL + "wps_replace_target_1";
    //public static final String WPS_REPLACE_TARGET_2 = BASE_SERVER_MAIN_URL + "wps_replace_target_2";
    //public static final String WPS_REPLACE_TARGET_3 = BASE_SERVER_MAIN_URL + "wps_replace_target_3";
    // public static final String WPS_REPLACE_REPLACEMENT_1 = BASE_SERVER_MAIN_URL + "wps_replace_replacement_1";
    // public static final String WPS_REPLACE_REPLACEMENT_2 = BASE_SERVER_MAIN_URL + "wps_replace_replacement_2";


    public static final String UPDATE_CHECK = "https://www.pgyer.com/apiv2/app/check";
    public static final String UPDATE_APP_INSTALL = "http://www.pgyer.com/apiv2/app/install";
    /// public static final String UPDATE_SHORTURL = BASE_SERVER_MAIN_URL + "update_shorturl";
    //public static final String UPDATE_API_KEY = BASE_SERVER_MAIN_URL + "update_api_key";
    //public static final String UPDATE_APPKEY = BASE_SERVER_MAIN_URL + "update_appKey";


    private static final String EMAIL_URL = BASE_SERVER_MAIN_URL + "/MailAppService";

    public static final String EMAIL_RECEIVE_INBOX_SERVICE = EMAIL_URL + "/oamail/receiveInboxSummaryByAgent/";
    public static final String EMAIL_RECEIVE_SENT_SERVICE = EMAIL_URL + "/oamail/receiveSentSummary/";
    public static final String EMAIL_RECEIVE_DRAFTS_SERVICE = EMAIL_URL + "/oamail/receiveViewSummary/";
    public static final String EMAIL_RECEIVE_TRASH_SERVICE = EMAIL_URL + "/oamail/receiveViewSummary/";
    public static final String EMAIL_RECEIVE_FLAGGED_SERVICE = EMAIL_URL + "/oamail/receiveViewSummary/";
    public static final String EMAIL_RECEIVE_DETAILS_SERVICE = EMAIL_URL + "/oamail/getOAMail/";
    public static final String EMAIL_RECEIVE_DETAILS_FILE_SERVICE = EMAIL_URL + "/oamail/getOAMailWithAttachment/";
    public static final String EMAIL_RECEIVE_NOTESID_SERVICE = EMAIL_URL + "/oamail/getNotesId/";
    public static final String EMAIL_SEND_EMAIL_SERVICE = EMAIL_URL + "/oamail/send/";
    public static final String EMAIL_SEND_FILE_SERVICE = EMAIL_URL + "/oamail/sendWithAttachment/";
    public static final String EMAIL_SAVE_EMAIL_SERVICE = EMAIL_URL + "/oamail/save/";
    public static final String EMAIL_UNREAD_NUM_SERVICE = EMAIL_URL + "/oamail/getUnreadMailCount/";
    public static final String EMAIL_MAILID_DELETE_SERVICE = EMAIL_URL + "/oamail/deleteAll/";
    public static final String EMAIL_MAILID_SEARCH_SERVICE = EMAIL_URL + "/oamail/searchMail/";

    //wps 文件预览地址
    public static final String EMAIL_FILE_VIEW_SERVICE = BASE_SERVER_MAIN_URL + "/xview/v1/view/preview";
    //wps压缩文件内容
    public static final String EMAIL_ZIP_VIEW_SERVICE = BASE_SERVER_MAIN_URL + "/Converter/convertfile";

    //#获取压缩文件内容拼接的压缩包的识别地址
    public static final String EMAIL_ZIP_URL_SERVICE = BASE_SERVER_MAIN_URL + "/MailAppService/oamail";

    //#头像文件下载
    public static final String USER_PHOTO_DOWNLOAD_URL = BASE_SERVER_MAIN_URL + "/iTableAttachment/fileupdown/file.action?id=%s";
    //#监控首页url
    public static final String MONITOR_INDEX_URL = BASE_SERVER_MAIN_URL + "/d/a6e7d1362e1ddbb79db21d5bb40d7137/k8s-use-method-cluster?refresh=10s&orgId=1";
    //#了解铛铛
    public static final String ABOUT_DANGDANG_URL = BASE_SERVER_MAIN_URL + File.separator + context_dangdang + "/main/aboutdang.html";

}
