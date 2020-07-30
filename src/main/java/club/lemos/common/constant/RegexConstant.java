package club.lemos.common.constant;

public interface RegexConstant {

    /**
     * 手机号
     */
    String PHONE = "1[3-9][0-9]{9}";

    /**
     * 用户名
     */
    String USERNAME = "[a-zA-Z][a-zA-Z0-9_]{1,11}";

    /**
     * 用户名或手机号
     */
    String USERNAME_OR_PHONE = String.format("(%s|%s)", USERNAME, PHONE);

    /**
     * 密码
     */
    String PASSWORD = ".{6,32}";

    /**
     * 邮箱
     */
    String EMAIL = "\\w+([-+.]*\\w+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+";

    /**
     * 网址
     */
    String URL = "^https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
}
