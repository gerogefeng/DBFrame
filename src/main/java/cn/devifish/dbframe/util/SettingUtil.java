package cn.devifish.dbframe.util;

import cn.devifish.dbframe.entity.DBUser;
import cn.devifish.dbframe.entity.Data;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SettingUtil {

    private static final String DB_LIST_FILE;

    private static Data data;

    static {
        DB_LIST_FILE = new File(Config.DATA_PATH + Config.DB_LIST_NAME).getAbsolutePath();
    }

    public static void init() {
        data = initData();
        saveData();
    }

    private static Data initData() {
        Data temp = null;
        Path path = Paths.get(DB_LIST_FILE);
        try {
            if (Files.exists(path)) {
                String text = Files.newBufferedReader(path, UTF_8).lines().collect(Collectors.joining());
                if (StringUtil.isNotEmpty(text)) temp = JSON.parseObject(text, Data.class);
                if (temp == null) temp = new Data();
            }else {
                Files.createFile(path);
                return initData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp;
    }

    private static void saveData() {
        try {
            List<DBUser> users = data.getDbUsers();
            if (users != null) {
                for (int i = 0; i < users.size(); i++) {
                    DBUser dbUser = users.get(i);
                    dbUser.setId(i);
                }
                JSON.writeJSONString(
                        Files.newBufferedWriter(Paths.get(DB_LIST_FILE), UTF_8),
                        data,
                        SerializerFeature.PrettyFormat,
                        SerializerFeature.WriteNullStringAsEmpty
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addDBUser(DBUser dbUser) {
        if (dbUser == null) return;
        Integer id = dbUser.getId();
        if (id != null && id >= 0) {
            data.getDbUsers().set(id, dbUser);
        }else data.getDbUsers().add(dbUser);
        saveData();
    }

    public static void deleteDBUser(DBUser dbUser) {
        if (dbUser == null) return;
        Integer id = dbUser.getId();
        if (id != null && id >= 0) {
            data.getDbUsers().remove(id.intValue());
        }
    }

}
