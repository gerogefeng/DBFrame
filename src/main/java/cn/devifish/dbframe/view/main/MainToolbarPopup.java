package cn.devifish.dbframe.view.main;

import cn.devifish.dbframe.base.ContextView;
import cn.devifish.dbframe.util.StringUtil;
import cn.devifish.dbframe.widget.MDDialog;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

@ViewController(value = "/layout/pop_menu_main.fxml")
public class MainToolbarPopup extends ContextView {

    @FXML private JFXListView<Label> toolbarPopup;

    private StackPane contentPane;
    private JFXPopup popup;
    private JFXDialog exitDialog;

    @Override
    protected void initVar(ViewFlowContext context) {
        contentPane = (StackPane) context.getRegisteredObject("contentPane");
        popup = (JFXPopup) context.getRegisteredObject("toolbarPopup");
    }

    @Override
    protected void initView() throws Exception {
        exitDialog = MDDialog.build()
                .setTitle("退出程序")
                .setContent("你确定要退出程序吗？")
                .setAcceptActionEvent(e -> Platform.exit())
                .getDialog();
    }

    @Override
    protected void initEvent() throws Exception {
        toolbarPopup.setOnMouseClicked(event -> {
            Node node = toolbarPopup.getSelectionModel().getSelectedItem();
            String id = node.getId();
            if (StringUtil.isNotEmpty(id)) {
                switch (id) {
                    case "add":
                        break;
                    case "about":
                        break;
                    case "exit":
                        exitDialog.show(contentPane);
                        break;
                    default: break;
                }
                popup.hide();
            }
        });
    }

}
