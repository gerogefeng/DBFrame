package cn.devifish.dbframe.entity;

public enum DataBase {

    MySQL("jdbc:mysql://", 3306),
    MongoDB("jdbc:mysql://", 3306),
    SQLServer("jdbc:sqlserver://", 1433),
    Oracle("jdbc:oracle:thin:@//", 1521);

    public String url;
    public int port;

    DataBase(String url, int port) {
        this.url = url;
        this.port = port;
    }

}
