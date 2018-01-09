package cn.devifish.dbframe.view.main;

import cn.devifish.dbframe.base.HoldView;
import cn.devifish.dbframe.entity.DBUser;
import cn.devifish.dbframe.entity.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import io.datafx.controller.ViewController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

@ViewController(value = "/layout/add_db_dialog.fxml")
public class AddDBDialog extends HoldView<DBUser> {

    @FXML private Label title;
    @FXML private JFXTextField user;
    @FXML private JFXPasswordField password;
    @FXML private JFXComboBox<Label> database;
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
    protected void initData(DBUser dbUser) {

    }

}
