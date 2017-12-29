package cn.devifish.dbframe.view.module.dblist;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.view.widget.DBListCardView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.effects.JFXDepthManager;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

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
    protected void initVar(ViewFlowContext context) throws Exception {
    }

    @Override
    protected void initView() throws Exception {
        Flow cardViewFlow = new Flow(DBListCardView.class);
        ArrayList<Node> children = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            final int index = i;
            ViewFlowContext tempContext = new ViewFlowContext();

            DefaultFlowContainer container = new DefaultFlowContainer();
            cardViewFlow.createHandler(tempContext).start(container);
            StackPane cardView = (StackPane) container.getView().getChildren().get(0);

            JFXDepthManager.setDepth(cardView, 1);

            JFXButton button = (JFXButton) cardView.lookup("#button");
            Label title = (Label) cardView.lookup("#title");
            Label subTitle = (Label) cardView.lookup("#subTitle");

            button.setStyle("-fx-background-color: " + DefaultColor[(int) ((Math.random() * 12) % 12)]);
            button.setRipplerFill(Color.valueOf(DefaultColor[index % 12]));

            // 对Button设置动画 从0到pref大小
            button.setScaleX(0);
            button.setScaleY(0);
            Timeline animation = new Timeline(new KeyFrame(
                    Duration.millis(240),
                    new KeyValue(button.scaleXProperty(), 1, EASE_BOTH),
                    new KeyValue(button.scaleYProperty(), 1, EASE_BOTH))
            );
            animation.setDelay(Duration.millis(100 * index + 1000));
            animation.play();

            title.setText("阿里云");
            subTitle.setText("127.0.0.1 (MySql)");
            button.setOnAction(event -> {
                System.out.println(index);
            });

            children.add(cardView);
        }
        masonryPane.getChildren().addAll(children);
        Platform.runLater(() -> scrollPane.requestLayout());

        JFXScrollPane.smoothScrolling(scrollPane);
    }

    @Override
    protected void initEvent() throws Exception {
    }

}
