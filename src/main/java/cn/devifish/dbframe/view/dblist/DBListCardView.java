package cn.devifish.dbframe.view.dblist;

import cn.devifish.dbframe.base.BaseView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import io.datafx.controller.ViewController;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

@ViewController(value = "/layout/db_list_item.fxml")
public class DBListCardView extends BaseView {

    @FXML private StackPane root;
    @FXML private Label title;
    @FXML private Label subTitle;
    @FXML private JFXButton button;
    @FXML private StackPane header;

    @Override
    protected void initView() throws Exception {
        JFXDepthManager.setDepth(root, 1);

        // 设置按钮图标
        SVGGlyph glyph = new SVGGlyph(-1,
                "test",
                "M1008 6.286q18.857 13.714 15.429 36.571l-146.286 877.714q-2.857 16.571-18.286 25.714-8 4.571-17.714 4.571-6.286 "
                        + "0-13.714-2.857l-258.857-105.714-138.286 168.571q-10.286 13.143-28 13.143-7.429 "
                        + "0-12.571-2.286-10.857-4-17.429-13.429t-6.571-20.857v-199.429l493.714-605.143-610.857 "
                        + "528.571-225.714-92.571q-21.143-8-22.857-31.429-1.143-22.857 18.286-33.714l950.857-548.571q8.571-5.143 18.286-5.143"
                        + " 11.429 0 20.571 6.286z",
                Color.WHITE);
        glyph.setSize(20, 20);
        button.setGraphic(glyph);

        // 设置按钮位置
        button.translateYProperty().bind(
                Bindings.createDoubleBinding(() -> header.getBoundsInParent().getHeight() - button.getHeight() / 2,
                        header.boundsInParentProperty(), button.heightProperty())
        );

    }

    public StackPane getRoot() {
        return root;
    }

    public Label getTitle() {
        return title;
    }

    public Label getSubTitle() {
        return subTitle;
    }

    public JFXButton getButton() {
        return button;
    }

    public StackPane getHeader() {
        return header;
    }
}
