package com.saksham.yuminfo.login;

/**
 * Created by IT001 on 23-Jun-16.
 */
public class User {
    // Labels table name
    public static final String TABLE = "User";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_phone = "phone";
    public static final String KEY_status = "status";

    // property help us to keep data
    public int user_ID;
    public String name;
    public String phone;
    public int status;
}