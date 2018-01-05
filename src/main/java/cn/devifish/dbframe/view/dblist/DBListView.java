package cn.devifish.dbframe.view.dblist;

import cn.devifish.dbframe.base.ContextView;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSnackbar;
import io.datafx.controller.ViewController;
import io.datafx.controller.ViewFactory;
import io.datafx.controller.context.ViewContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Interpolator;
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

@ViewController(value = "/layout/db_list_view.fxml")
public class DBListView extends ContextView {

    @FXML private StackPane root;
    @FXML private ScrollPane scrollPane;
    @FXML private JFXMasonryPane masonryPane;

    private JFXSnackbar snackbar;

    private static final String[] DefaultColor;

    static {
        DefaultColor = new String[]{
                "#8F3F7E", "#B5305F", "#CE584A", "#DB8D5C", "#DA854E",
                "#E9AB44", "#FEE435", "#99C286", "#01A05E", "#4A8895",
                "#16669B", "#2F65A5", "#4E6A9C"
        };
    }

    @Override
    protected void initVar(ViewFlowContext context) {
        snackbar = new JFXSnackbar(root);
        snackbar.setPrefWidth(300);
    }

    @Override
    protected void initView() throws Exception {

        // 加载数据库列表
        ArrayList<Node> children = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            final int index = i;
            ViewContext<DBListCardView> viewContext = ViewFactory.getInstance().createByController(DBListCardView.class);
            DBListCardView controller = viewContext.getController();

            final JFXButton button = controller.getButton();
            final Label title = controller.getTitle();
            final Label subTitle = controller.getSubTitle();

            button.setStyle("-fx-background-color: " + DefaultColor[(int) ((Math.random() * 12) % 12)]);
            button.setRipplerFill(Color.valueOf(DefaultColor[index % 12]));

            // 对Button设置动画 从0到pref大小
            button.setScaleX(0);
            button.setScaleY(0);
            Timeline animation = new Timeline(new KeyFrame(
                    Duration.millis(240),
                    new KeyValue(button.scaleXProperty(), 1, Interpolator.EASE_BOTH),
                    new KeyValue(button.scaleYProperty(), 1, Interpolator.EASE_BOTH))
            );
            animation.setDelay(Duration.millis(100 * index + 1000));
            animation.play();

            // 绑定数据
            title.setText("阿里云");
            subTitle.setText("127.0.0.1 (MySQL)");
            button.setOnAction(event -> {
                snackbar.fireEvent(new JFXSnackbar.SnackbarEvent("你点击了 " + index));
            });

            children.add(viewContext.getRootNode());
        }
        masonryPane.getChildren().addAll(children);
        Platform.runLater(() -> scrollPane.requestLayout());

        JFXScrollPane.smoothScrolling(scrollPane);
    }

    @Override
    protected void initEvent() throws Exception {
    }

}
