package cn.devifish.dbframe.widget;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import io.datafx.controller.FxmlLoadException;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewFactory;
import io.datafx.controller.context.ViewContext;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

@ViewController(value = "/layout/default_dialog.fxml")
public class MDDialog {

    @FXML private Label title;
    @FXML private Label content;
    @FXML private JFXButton accept;
    @FXML private JFXButton cancel;

    private static JFXDialog dialog;

    public static MDDialog build() {
        MDDialog mdDialog = null;
        try {
            ViewContext<MDDialog> controller = ViewFactory.getInstance().createByController(MDDialog.class);
            dialog = new JFXDialog();
            dialog.setContent((JFXDialogLayout) controller.getRootNode());
            mdDialog = controller.getController();
            mdDialog.setCancelActionEvent(event -> dialog.close());
        } catch (FxmlLoadException e) {
            e.printStackTrace();
        }
        return mdDialog;
    }

    public MDDialog setTitle(String title) {
        this.title.setText(title);
        return this;
    }

    public MDDialog setContent(String content) {
        this.content.setText(content);
        return this;
    }

    public MDDialog setAccept(String text) {
        this.accept.setText(text);
        return this;
    }

    public MDDialog setAcceptActionEvent(EventHandler<ActionEvent> event) {
        this.accept.setOnAction(event);
        return this;
    }

    public MDDialog setCancel(String text) {
        this.cancel.setText(text);
        return this;
    }

    public MDDialog setCancelActionEvent(EventHandler<ActionEvent> event) {
        this.cancel.setOnAction(event);
        return this;
    }

    public JFXDialog getDialog() {
        return dialog;
    }

}
