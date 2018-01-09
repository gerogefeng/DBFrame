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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;

@ViewController(value = "/layout/db_list_view.fxml")
public class DBListView extends ContextView {

    @FXML private StackPane root;
    @FXML private ScrollPane scrollPane;
    @FXML private JFXMasonryPane masonryPane;

    private JFXSnackbar snackbar;

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
            ViewContext<DBListCardView> viewContext = ViewFactory.getInstance().createByController(DBListCardView.class);
            DBListCardView controller = viewContext.getController();
            controller.setAnnotation(i);

            final JFXButton button = controller.getButton();
            final Label title = controller.getTitle();
            final Label subTitle = controller.getSubTitle();

            // 绑定数据
            final int index = i;
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
