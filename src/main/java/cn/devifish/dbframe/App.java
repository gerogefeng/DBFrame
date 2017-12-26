package cn.devifish.dbframe;

import cn.devifish.dbframe.view.MainView;
import com.jfoenix.controls.JFXDecorator;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.container.DefaultFlowContainer;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @FXMLViewFlowContext
    private ViewFlowContext flowContext;

    public App() {
        this.flowContext = new ViewFlowContext();;
    }

    @Override
    public void start(Stage stage) throws Exception {
        Flow flow = new Flow(MainView.class);
        DefaultFlowContainer container = new DefaultFlowContainer();

        // 注册Stage到Context
        flowContext.register("Stage", stage);
        flow.createHandler(flowContext).start(container);

        // 使用Material Design风格标题栏
        JFXDecorator decorator = new JFXDecorator(stage, container.getView());
        decorator.setCustomMaximize(true);
        decorator.setText("DB Frame 数据库工具");

        // 注册CSS样式
        Scene scene = new Scene(decorator, 1270, 800);
        final ObservableList<String> stylesheets = scene.getStylesheets();
        stylesheets.addAll(App.class.getResource("/css/main.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

}
