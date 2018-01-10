package cn.devifish.dbframe.entity;

import java.util.List;

public class Data {

    private List<DBUser> dbUsers;
    private Setting setting;

    public List<DBUser> getDbUsers() {
        return dbUsers;
    }

    public void setDbUsers(List<DBUser> dbUsers) {
        this.dbUsers = dbUsers;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }
}
