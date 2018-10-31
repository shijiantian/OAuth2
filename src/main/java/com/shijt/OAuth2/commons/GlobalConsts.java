package com.shijt.OAuth2.commons;

public class GlobalConsts {

    public static final String login_need="/api/";
    public static final String login_no_need="/openapi/";

    public static final String db_schema="test";
    public static final String tb_user="user";
    public static final String tb_role="role";
    public static final String tb_user_role="user_role";
    public static final String tb_resource="resource";
    public static final String tb_role_resource="role_resource";

    public static final int    access_token_valid_sec=120;
    public static final int    refresh_token_valid_sec=600;
}
