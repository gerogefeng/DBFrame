package cn.devifish.dbframe.view.main;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.util.StringUtil;
import com.jfoenix.controls.JFXListView;
import io.datafx.controller.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@ViewController(value = "/layout/pop_menu_main.fxml")
public class MainToolbarPopup extends BaseView {

    @FXML private JFXListView<Label> toolbarPopup;

    @Override
    protected void initView() throws Exception {

    }

    @Override
    protected void initEvent() throws Exception {
        toolbarPopup.propagateMouseEventsToParent();
        toolbarPopup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String id = newValue.getId();
            if (StringUtil.isNotEmpty(id)) {
                switch (id) {
                    case "about":
                        break;
                    case "exit":
                        Platform.exit();
                        break;
                    default: break;
                }
            }
        });
    }

}
