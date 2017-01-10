package com.saksham.yuminfo.domain;

/**
 * Created by Belal on 10/24/2015.
 */
public class Config {

    //Address of our scripts of the CRUD
    public static final String URL_ADD="http://skmceicher.16mb.com/skmcapp/addUser.php";
    public static final String URL_CV="http://skmceicher.16mb.com/skmcapp/addCvisit.php";
    public static final String URL_CE="http://skmceicher.16mb.com/skmcapp/addCenquiry.php";
    //public static final String URL_GET_ALL = "http://192.168.1.20/Android/CRUD/getAllEmp.php";
    //public static final String URL_GET_EMP = "http://192.168.1.20/Android/CRUD/getEmp.php?id=";
    //public static final String URL_UPDATE_EMP = "http://192.168.1.20/Android/CRUD/updateEmp.php";
    //public static final String URL_DELETE_EMP = "http://192.168.1.20/Android/CRUD/deleteEmp.php?id=";

    //URL to our login.php file
    public static final String LOGIN_URL = "http://192.168.1.9/skmcapp/login.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_TYPE = "type";
    public static final String KEY_PAYLOAD = "payload";
    public static final String KEY_VEHICLE = "vehicle";
    public static final String KEY_DT = "dt";


    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "skmcapp";

    //This would be used to store the email of current logged in user
    public static final String PHONE_SHARED_PREF = "phone";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    //Keys that will be used to send the request to php scripts
    public static final String KEY_EMP_ID = "id";
    public static final String KEY_EMP_NAME = "name";
    public static final String KEY_EMP_PHONE = "phone";
    //public static final String KEY_EMP_SAL = "salary";

    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_PHONE = "phone";
    //public static final String TAG_SAL = "salary";

    //employee id to pass with intent
    public static final String EMP_ID = "emp_id";
}
