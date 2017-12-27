package cn.devifish.dbframe.view.module.dblist;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.view.widget.CardView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.effects.JFXDepthManager;
import com.jfoenix.svg.SVGGlyph;
import io.datafx.controller.ViewController;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.animation.Interpolator.EASE_BOTH;

@ViewController(value = "/layout/db_list_view.fxml")
public class DBListView extends BaseView {

    @FXML private ScrollPane scrollPane;
    @FXML private JFXMasonryPane masonryPane;

    private static final String[] DefaultColor;

    static {
        DefaultColor = new String[]{
                "#8F3F7E", "#B5305F", "#CE584A", "#DB8D5C", "#DA854E",
                "#E9AB44", "#FEE435", "#99C286", "#01A05E", "#4A8895",
                "#16669B", "#2F65A5", "#4E6A9C"
        };
    }

    @Override
    protected void initView(ResourceBundle resources) throws Exception {
        new CardView();
        ArrayList<Node> children = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            StackPane child = new StackPane();
            double width = 225;
            child.setMinWidth(width);
            child.setMaxWidth(width);
            child.setPrefWidth(width);
            double height = 180;
            child.setMinHeight(height);
            child.setMaxHeight(height);
            child.setPrefHeight(height);
            JFXDepthManager.setDepth(child, 1);
            children.add(child);

            // create content
            StackPane header = new StackPane();
            String headerColor = DefaultColor[i % 12];
            header.setStyle("-fx-background-radius: 5 5 0 0; -fx-background-color: " + headerColor);
            VBox.setVgrow(header, Priority.ALWAYS);
            StackPane body = new StackPane();
            body.setMinHeight(70);
            VBox content = new VBox();
            content.getChildren().addAll(header, body);
            body.setStyle("-fx-background-radius: 0 0 5 5; -fx-background-color: rgb(255,255,255,0.87);");


            // create button
            JFXButton button = new JFXButton("");
            button.setButtonType(JFXButton.ButtonType.RAISED);
            button.setStyle("-fx-background-radius: 40;-fx-background-color: " + DefaultColor[(int) ((Math.random() * 12) % 12)]);
            button.setPrefSize(40, 40);
            button.setRipplerFill(Color.valueOf(headerColor));
            button.setScaleX(0);
            button.setScaleY(0);
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
            button.translateYProperty().bind(Bindings.createDoubleBinding(() -> {
                return header.getBoundsInParent().getHeight() - button.getHeight() / 2;
            }, header.boundsInParentProperty(), button.heightProperty()));
            StackPane.setMargin(button, new Insets(0, 12, 0, 0));
            StackPane.setAlignment(button, Pos.TOP_RIGHT);

            Timeline animation = new Timeline(new KeyFrame(Duration.millis(240),
                    new KeyValue(button.scaleXProperty(),
                            1,
                            EASE_BOTH),
                    new KeyValue(button.scaleYProperty(),
                            1,
                            EASE_BOTH)));
            animation.setDelay(Duration.millis(100 * i + 1000));
            animation.play();
            child.getChildren().addAll(content, button);
        }
        masonryPane.getChildren().addAll(children);
        Platform.runLater(() -> scrollPane.requestLayout());

        JFXScrollPane.smoothScrolling(scrollPane);
    }

    @Override
    protected void initEvent() throws Exception {

    }

}
