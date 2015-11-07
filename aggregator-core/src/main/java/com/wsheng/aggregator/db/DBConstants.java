/**
 *
 */
package com.wsheng.aggregator.db;

/**
 * @author Josh Wang(Sheng)
 * @email josh_wang23@hotmail.com
 */
public class DBConstants {

    // ================================================
    // Query related statements
    // ================================================
//    public static final String QUERY_LOAD_USER_BY_ID = "load_user_by_id";
//
//    public static final String QUERY_LIST_USERS = "list_users";
//    public static final String QUERY_LIST_USERS_BY_PARAMS = "list_users_by_params";
//    public static final String QUERY_LIST_USERS_BY_BEAN = "list_users_by_bean";
//
//    public static final String QUERY_LIST_ACTIVITIES = "list_activies";
//
//    // ================================================
//    // Save related statements
//    // ================================================
//    public static final String INSERT_USER = "insert_user";
//
//    // ================================================
//    // Update related statements
//    // ================================================
//    public static final String UPDATE_USER_BY_ID = "update_user_by_id";
//
//    // ================================================
//    // Delete related statements
//    // ================================================
//    public static final String DELETE_USER_BY_ID = "delete_user_by_id";

    // ================================================
    // query message by cid
    // ================================================
    public static final String QUERY_MESSAGE_BY_CID = "QUERY_MESSAGE_BY_CID";
    public static final String QUERY_SPACE_MESSAGE_BY_CID = "QUERY_SPACE_MESSAGE_BY_CID";
    public static final String QUERY_SPACE_MESSAGE_BY_CID_WITHOUT_TARGET_CIDS_FILTER = "QUERY_SPACE_MESSAGE_BY_CID_WITHOUT_TARGET_CIDS_FILTER";

    // ================================================
    // query message by mid
    // ================================================
    public static final String QUERY_SPACE_MESSAGE_BY_MID = "QUERY_SPACE_MESSAGE_BY_MID";
    public static final String QUERY_SPACE_MESSAGE_ONLY_BY_MID = "QUERY_SPACE_MESSAGE_ONLY_BY_MID";
    public static final String DELETE_SPACE_MESSAGE_BY_MID_CID = "DELETE_SPACE_MESSAGE_BY_MID_CID";
    public static final String DELETE_SPACE_MESSAGE_COMMENT_BY_MID_CID = "DELETE_SPACE_MESSAGE_COMMENT_BY_MID_CID";
    public static final String DELETE_SPACE_MESSAGE_THUMB_BY_MID_CID = "DELETE_SPACE_MESSAGE_THUMB_BY_MID_CID";

    // ================================================
    // query message by id
    // ================================================
    public static final String QUERY_SPACE_MESSAGE_BY_ID = "QUERY_SPACE_MESSAGE_BY_ID";


    // ================================================
    // query message by cid
    // ================================================
    public static final String QUERY_UNREAD_MESSAGE_BY_UID = "QUERY_UNREAD_MESSAGE_BY_UID";

    // ================================================
    // query recent cids by uid
    // ================================================
    public static final String QUERY_RECENT_CONTACTS_BY_UID = "QUERY_RECENT_CONTACTS_BY_UID";

    // ================================================
    // query topics by cid
    // ================================================
    public static final String QUERY_TOPIC = "query_topic";

    // ================================================
    // create new topic
    // ================================================
    public static final String INSERT_TOPIC = "insert_topic";

    // ================================================
    // load topic by provide cid and tid
    // ================================================
    public static final String LOAD_TOPIC = "load_topic";

    // ================================================
    // update topic with cid and tid
    // ================================================
    public static final String UPDATE_TOPIC = "update_topic";


    public static final String QUERY_CIRCLE = "QUERY_CIRCLE";
    public static final String QUERY_CIRCLE_WITH_CIDS = "QUERY_CIRCLE_WITH_CIDS";
    public static final String QUERY_SUMMARY_CIRCLES_OF_USER = "QUERY_SUMMARY_CIRCLES_OF_USER";
    public static final String QUERY_SIMPLE_CIRCLES_OF_USER = "QUERY_SIMPLE_CIRCLES_OF_USER";

    public static final String QUERY_SIMPLE_USERS_OF_CIRCLE = "QUERY_SIMPLE_USERS_OF_CIRCLE";
    public static final String QUERY_SUMMARY_USER_OF_CIRCLE = "QUERY_SUMMARY_USER_OF_CIRCLE";
    public static final String QUERY_SIMPLE_USER_OF_CIRCLE = "QUERY_SIMPLE_USER_OF_CIRCLE";
    
    // ================================================
    // query constants for index nets
    // ================================================
    public static final String INDEX_QUERY_SPACE_MESSAGE_BY_TIMERANGE = "INDEX_QUERY_SPACE_MESSAGE_BY_TIMERANGE";
    public static final String INDEX_QUERY_CIRCLE_BY_TIMERANGE = "INDEX_QUERY_CIRCLE_BY_TIMERANGE";
    public static final String INDEX_QUERY_CIRCLE = "INDEX_QUERY_CIRCLE";
    public static final String INDEX_QUERY_CIRCLE_BY_STATUS = "INDEX_QUERY_CIRCLE_BY_STATUS";
    
    // ================================================
    // query constants for weight management
    // ================================================
    public static final String WEIGHT_QUERY_CIRCLE_MESSAGE_NUMBER = "WEIGHT_QUERY_CIRCLE_MESSAGE_NUMBER";
    public static final String WEIGHT_QUERY_CIRCLE_SPACE_MESSAGE_NUMBER = "WEIGHT_QUERY_CIRCLE_SPACE_MESSAGE_NUMBER";
    public static final String WEIGHT_QUERY_CIRCLE_MEMBER_NUMBER = "WEIGHT_QUERY_CIRCLE_MEMBER_NUMBER";
    public static final String WEIGHT_QUERY_CIRCLE_MEMBER_NUMBER_BEFORE_TODAY = "WEIGHT_QUERY_CIRCLE_MEMBER_NUMBER_BEFORE_TODAY";
    public static final String WEIGHT_QUERY_CIRCLE_MEMBER_NUMBER_BEFORE_YESTERDAY = "WEIGHT_QUERY_CIRCLE_MEMBER_NUMBER_BEFORE_YESTERDAY";
    public static final String WEIGHT_QUERY_CIRCLE_LIKE_NUMBER = "WEIGHT_QUERY_CIRCLE_LIKE_NUMBER";
    public static final String WEIGHT_QUERY_CIRCLE_COMMENT_NUMBER = "WEIGHT_QUERY_CIRCLE_COMMENT_NUMBER";
    public static final String UPDATE_CIRCLE_WEIGHT = "UPDATE_CIRCLE_WEIGHT";

    public static final String UPDATE_CIRCLE = "UPDATE_CIRCLE";
    public static final String UPDATE_CIRCLE_USER = "UPDATE_CIRCLE_USER";

    public static final String LIST_USER_CONTACTS_BY_UID = "LIST_USER_CONTACTS_BY_UID";
    public static final String SELECT_USER_CONTACT_OF_USER = "SELECT_USER_CONTACT_OF_USER";
    public static final String UPDATE_USER_CONTACT = "UPDATE_USER_CONTACT";

    public static final String LIST_SENT_REQUESTS = "LIST_SENT_REQUESTS";
    public static final String LIST_RECEIVED_REQUESTS = "LIST_RECEIVED_REQUESTS";
    public static final String SELECT_RECEIVED_REQUEST_BY_REQUEST_ID = "SELECT_RECEIVED_REQUEST_BY_REQUEST_ID";

    public static final String LIST_USER_CONTACTS_UID_BY_UID = "LIST_USER_CONTACTS_UID_BY_UID";
    public static final String QUERY_SIMPLE_USERS_UID_OF_CIRCLE = "QUERY_SIMPLE_USERS_UID_OF_CIRCLE";
    public static final String QUERY_SIMPLE_USERS_UID_OF_CIRCLE_OF_SPACE_MESSAGE = "QUERY_SIMPLE_USERS_UID_OF_CIRCLE_OF_SPACE_MESSAGE";

    public static final String INSERT_NOTIFY = "INSERT_NOTIFY";
    public static final String INSERT_OPERATION = "INSERT_OPERATION";
    public static final String LIST_NOTIFY_BY_SUBSCRIBER = "LIST_NOTIFY_BY_SUBSCRIBER";
    public static final String GET_LAST_SYNC_ID = "GET_LAST_SYNC_ID";
    public static final String GET_TAIL_SYNC_ID = "GET_TAIL_SYNC_ID";
    public static final String UPDATE_NOTIFY = "UPDATE_NOTIFY";


    public static final String QUERY_USER = "QUERY_USER";
    public static final String UPDATE_USER = "UPDATE_USER";
    public static final String UPDATE_USER_WITH_CU_C = "UPDATE_USER_WITH_CU_C";
    public static final String UPDATE_USER_WITH_CU_UC = "UPDATE_USER_WITH_CU_UC";
    public static final String SEARCH_USER = "SEARCH_USER";
    public static final String INSERT_USER_SETTING = "INSERT_USER_SETTING";
    public static final String QUERY_USER_SETTING = "QUERY_USER_SETTING";
    public static final String UPDATE_USER_SETTING = "UPDATE_USER_SETTING";


    public static final String INSERT_MESSAGE = "INSERT_MESSAGE";
    public static final String UPDATE_CIRCLE_LAST_MESSAGE = "UPDATE_CIRCLE_LAST_MESSAGE";
    public static final String GET_NEXT_MESSAGE_ID = "GET_NEXT_MESSAGE_ID";

    public static final String CHECK_SPACE_MESSAGE_COMMENT_PERMISSION = "CHECK_SPACE_MESSAGE_COMMENT_PERMISSION";
    public static final String INSERT_COMMENT = "INSERT_COMMENT";
    public static final String QUERY_COMMENT = "QUERY_COMMENT";
    public static final String DELETE_COMMENT = "DELETE_COMMENT";

    public static final String INSERT_LIKE = "INSERT_LIKE";
    public static final String INSERT_LIKE_ON_ALL_CIRCLE = "INSERT_LIKE_ON_ALL_CIRCLE";
    public static final String GET_LIKE = "GET_LIKE";
    public static final String QUERY_LIKE = "QUERY_LIKE";
    public static final String QUERY_LIKE_BY_UID = "QUERY_LIKE_BY_UID";
    public static final String UPDATE_LIKE = "UPDATE_LIKE";

    public static final String SELECT_SPIDER_INVITE = "SELECT_SPIDER_INVITE";
    public static final String INSERT_SPIDER_INVITE = "INSERT_SPIDER_INVITE";
    public static final String UPDATE_SPIDER_INVITE = "UPDATE_SPIDER_INVITE";

    public static final String FILTER_CIDS_WITH_UID = "FILTER_CIDS_WITH_UID";
    public static final String FILTER_CIDS_WITH_UID_ALL_TYPE = "FILTER_CIDS_WITH_UID_ALL_TYPE";
    public static final String QUERY_ACL_CID_LIST_WITH_UID = "QUERY_ACL_CID_LIST_WITH_UID";
    public static final String ADD_NOTIFY_SEQUENCE = "ADD_NOTIFY_SEQUENCE";

    public static final String INSERT_APNS_FEEDBACK = "INSERT_APNS_FEEDBACK";
    public static final String QUERY_APNS_FEEDBACK = "QUERY_APNS_FEEDBACK";
    public static final String QUERY_APNS_FEEDBACKS = "QUERY_APNS_FEEDBACKS";
    public static final String UPDATE_APNS_FEEDBACK_OLD = "UPDATE_APNS_FEEDBACK_OLD";
    public static final String DELETE_APNS_FEEDBACK = "DELETE_APNS_FEEDBACK";

    public static final String INSERT_DIANDI_FEEDBACK = "INSERT_DIANDI_FEEDBACK";
    public static final String QUERY_DIANDI_FEEDBACK = "QUERY_DIANDI_FEEDBACK";
    public static final String UPDATE_DIANDI_FEEDBACK = "UPDATE_DIANDI_FEEDBACK";

    public static final String QUERY_VERSION = "QUERY_VERSION";

    public static final String INSERT_SHARE_ACTION = "INSERT_SHARE_ACTION";
    public static final String QUERY_SHARE_ACTION_BY_SHARE_ID = "QUERY_SHARE_ACTION_BY_SHARE_ID";

    public static final String INSERT_SPAM_RECORD = "INSERT_SPAM_RECORD";
    public static final String UPDATE_SPAM_RECORD = "UPDATE_SPAM_RECORD";
    public static final String QUERY_SPAM_RECORD = "QUERY_SPAM_RECORD";

    public static final String INSERT_RECOMMEND = "INSERT_RECOMMEND";
    public static final String DELETE_RECOMMEND = "DELETE_RECOMMEND";
    public static final String QUERY_RECOMMEND = "QUERY_RECOMMEND";

    public static final String SELECT_RELATIVE_UIDS = "SELECT_RELATIVE_UIDS";
    public static final String QUERY_CIDS_OF_USER = "QUERY_CIDS_OF_USER";
    public static final String QUERY_USER_CONTACT_CIRCLE_INACTIVE = "QUERY_USER_CONTACT_CIRCLE_INACTIVE";

    // ================================================
    // Pagination SQL
    // ================================================

    // ================================================
    // Package Related SQL
    // ================================================

    public static final String PARAM_ID = "id";
    public static final String PARAM_USER_ID = "uid";
    public static final String PARAM_USER_CONTACT_ID = "contact_uid";
    public static final String PARAM_CUSTOM_USER_CONTACT_NICK = "custom_contact_nick";
    public static final String PARAM_COMMENT_ID = "commentId";

    public static final String PARAM_USER_NICK = "nick";
    public static final String PARAM_USER_AVATAR = "avatar";
    public static final String PARAM_USER_GENDER = "gender";
    public static final String PARAM_USER_DESC = "description";
    public static final String PARAM_USER_EMAIL = "email";
    public static final String PARAM_USER_CANOPY = "canopy";
    public static final String PARAM_INFO_UPDATE_TIME = "info_update_time";

    public static final String PARAM_IS_NOT_IN = "isNotIn";
    public static final String PARAM_MESSAGE_ID = "mid";
    public static final String PARAM_REFERENCE_MESSAGE_ID = "reference_mid";
    public static final String PARAM_CIRCLE_ID = "cid";
    public static final String PARAM_CIRCLE_LIST_ID = "cids";
    public static final String PARAM_CIRCLE_LIST_STR = "cid_str";
    public static final String PARAM_CREATION_TIME = "creation_time";
    public static final String PARAM_PAGE_ID = "page_id";
    public static final String PARAM_TOPIC_ID = "tid";
    public static final String PARAM_BASE_STATUS = "status";
    public static final String PARAM_CONTENT_TYPES = "content_types";
    public static final String PARAM_MESSAGE_UID = "messageUid";

    public static final String PARAM_PLATFORMS = "platforms";
    public static final String PARAM_LAST_UPDATE_TIME = "last_update_time";
    public static final String PARAM_ALSO_CIRCLE = "also_circle";
    
    public static final String PARAM_START_UPDATE_TIME = "start_update_time";
    public static final String PARAM_END_UPDATE_TIME = "end_update_time";

    // Default start time: this useful in IndexSearch - Diandi released today
    public static final String PARAM_DEFAULT_START_TIME = "2015-01-01 00:00:00"; // 
    

    public static final String PARAM_CUSTOM_CIRCLE_TYPES = "custom_circle_types";
    public static final String PARAM_SPAM_ID = "spam_id";
    public static final String PARAM_TYPE = "type";


    public static final String PARAM_SPAM_STATUS = "status";
    public static final String PARAM_R_ID = "r_id";
    // Days for recent contacts.
    public static final String RECENT_DAYS = "recent_days";
    
    public static final String FEEDBACK_STATUS = "status"; 
    public static final String FEEDBACK_ID = "id"; 

}