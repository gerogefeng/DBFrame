package cn.devifish.dbframe.view.main;

import cn.devifish.dbframe.base.HoldView;
import cn.devifish.dbframe.entity.DBUser;
import cn.devifish.dbframe.entity.DataBase;
import com.jfoenix.controls.*;
import io.datafx.controller.ViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

@ViewController(value = "/layout/add_db_dialog.fxml")
public class AddDBDialog extends HoldView<DBUser> {

    @FXML private Label title;
    @FXML private JFXTextField name;
    @FXML private JFXTextField user;
    @FXML private JFXPasswordField password;
    @FXML private JFXComboBox<Label> database;
    @FXML private Label protocol;
    @FXML private JFXTextField host;
    @FXML private JFXTextField port;
    @FXML private JFXButton test;
    @FXML private JFXButton add;
    @FXML private JFXButton cancel;

    @Override
    protected void initView() throws Exception {
        List<Label> labels = new ArrayList<>();
        DataBase[] dataBases = DataBase.values();
        for (DataBase db : dataBases) {
            labels.add(new Label(db.toString()));
        }

        database.setItems(FXCollections.observableList(labels));
    }

    @Override
    protected void initEvent() throws Exception {
        //选择数据库类型后操作
        database.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            DataBase dataBase = DataBase.valueOf(newValue.getText());
            protocol.setText(dataBase.url);
            port.setText(String.valueOf(dataBase.port));
        });

        //端口设置输入限制
        port.setOnKeyReleased(event -> {
            String temp = event.getText();
            if (temp.length() > 0) {
                JFXTextField source = (JFXTextField) event.getSource();
                String text = source.getText();
                char c = temp.charAt(0);
                if (!(c >= '0' && c <= '9')) {
                    source.setText(text.substring(0, text.length() - 1));
                }
            }
        });

        //测试配置连接性
        test.setOnAction(event -> {

        });

        //保存设置
        add.setOnAction(event -> {
            DBUser dbUser = new DBUser();
            dbUser.setName(name.getText());
            dbUser.setUser(user.getText());
            dbUser.setPassword(password.getText());
            dbUser.setDataBase(DataBase.valueOf(database.getSelectionModel().getSelectedItem().getText()));
            dbUser.setHost(host.getText());
            dbUser.setPort(Integer.valueOf(port.getText()));
        });
    }

    @Override
    protected void initData(DBUser dbUser) {

    }

    /**
     * 初始化取消按钮事件
     * @param parent 父级容器
     */
    public void initCancelEvent(JFXDialog parent) {
        cancel.setOnAction(event -> parent.close());
    }

}
