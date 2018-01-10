package cn.devifish.dbframe.util;

public interface Config {

    public static final String APP_NAME = "DBFrame";
    public static final String APP_TITLE = "DB Frame 数据库工具";

    public static final String DATA_PATH = "data/";
    public static final String SETTING_NAME = "setting.json";
    public static final String DB_LIST_NAME = "db_list.json";

    public static String getProjectPath() {
        return ClassLoader.getSystemResource("").getPath();
    }

}
