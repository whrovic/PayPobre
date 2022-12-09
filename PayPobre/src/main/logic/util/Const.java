package util;

public final class Const {
    private Const(){}

    // Account types
    public static final String COMMERCIAL = "Commercial";
    public static final String PERSONAL = "Personal";

    // Window Dimensions
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 700;

    // Error Control
    public static final int e_LOGIN_SUCCESSFUL = 0x0000;
    public static final int e_USER_NOT_FOUND = 0x0001;
    public static final int e_WRONG_CREDENTIALS = 0x0002;
    public static final int e_USER_ALREADY_EXISTS = 0x0003;
    public static final int e_INVALID_EMAIL = 0x0004;
    public static final int e_INVALID_CREDIT_CARD = 0x0005;

    public static final int e_SIGNUP_SUCCESSFUL = 0x0010;
    public static final int e_EMPTY_FIELDS = 0xeeee;
    public static final int e_ERROR = 0xffff;
}
