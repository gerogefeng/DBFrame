package cn.devifish.dbframe;

import cn.devifish.dbframe.util.Config;
import cn.devifish.dbframe.util.SettingUtil;
import cn.devifish.dbframe.view.main.MainView;
import cn.devifish.dbframe.widget.MDDialog;
import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @Override
    public void init() throws Exception {
        context = new ViewFlowContext();
        SettingUtil.init();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Flow flow = new Flow(MainView.class);
        DefaultFlowContainer container = new DefaultFlowContainer();
        flow.createHandler(context).start(container);

        // 使用Material Design风格标题栏
        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
        decorator.setText(Config.APP_TITLE);
        decorator.setOnCloseButtonAction(() -> MDDialog.build()
                .setTitle("退出程序")
                .setContent("你确定要退出程序吗？")
                .setAcceptActionEvent(e -> Platform.exit())
                .getDialog()
                .show(container.getView())
        );

        // 注册CSS样式
        Scene scene = new Scene(decorator, 1205, 800);
        scene.getStylesheets().addAll(getClass().getResource("/css/main.css").toExternalForm());

        // 注册相关属性
        context.register("Stage", stage);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
