package cn.devifish.dbframe.util;

public interface Config {

    public static final String DATA_PATH = "data/";
    public static final String SETTING_NAME = "setting.json";
    public static final String USER_NAME = "user.json";
    public static final String DB_LIST_NAME = "db_list.json";

    public static String getProjectPath() {
        return ClassLoader.getSystemResource("").getPath();
    }

}
