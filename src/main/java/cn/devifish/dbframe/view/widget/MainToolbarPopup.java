package cn.devifish.dbframe.view.widget;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.util.StringUtil;
import com.jfoenix.controls.JFXListView;
import io.datafx.controller.ViewController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;

@ViewController(value = "/layout/pop_menu_main.fxml")
public class MainToolbarPopup extends BaseView {

    @FXML private JFXListView toolbarPopup;

    @Override
    protected void initView() throws Exception {

    }

    @Override
    protected void initEvent() throws Exception {
        toolbarPopup.setOnMouseClicked(event -> {
            Node node = (Node) toolbarPopup.getSelectionModel().getSelectedItem();
            String id = node.getId();
            if (StringUtil.isNotEmpty(id)) {
                switch (node.getId()) {
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
