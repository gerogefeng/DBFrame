package cn.devifish.dbframe.view.main;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.view.dblist.DBListView;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

@ViewController(value = "/layout/main_view.fxml")
public class MainView extends BaseView {

    @FXML private StackPane root;
    @FXML private StackPane titleBurgerContainer;
    @FXML private StackPane optionsBurger;
    @FXML private Label title;
    @FXML private JFXHamburger titleBurger;
    @FXML private JFXDrawer drawer;

    private JFXPopup toolbarPopup;
    private StackPane sidePane;

    @Override
    protected void initView() throws Exception {
        ViewFlowContext context = getContext();
        ViewFlowContext navContent = new ViewFlowContext();
        final Duration containerAnimationDuration = Duration.millis(500);

        // 加载首页面内容
        final Flow innerFlow = new Flow(DBListView.class);
        final FlowHandler flowHandler = innerFlow.createHandler(context);
        drawer.setContent(flowHandler.start(new AnimatedFlowContainer(containerAnimationDuration, ContainerAnimations.SWIPE_LEFT)));

        // 加载POP菜单
        final Flow toolbarPopupFlow = new Flow(MainToolbarPopup.class);
        final FlowHandler popupFlowHandler = toolbarPopupFlow.createHandler(context);
        toolbarPopup = new JFXPopup(popupFlowHandler.start());

        // 加载Nav菜单
        final Flow sideMenuFlow = new Flow(NavMenuView.class);
        navContent.register("toolbarTitle", title);
        navContent.register("ContentFlowHandler", flowHandler);
        navContent.register("ContentFlow", innerFlow);
        navContent.register("ContentPane", drawer.getContent().get(0));
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(navContent);
        StackPane stackPane = sideMenuFlowHandler.start(new AnimatedFlowContainer(containerAnimationDuration, ContainerAnimations.SWIPE_LEFT));
        drawer.setSidePane(stackPane);
        sidePane = (StackPane) stackPane.getChildren().get(0);
    }

    @Override
    protected void initEvent() throws Exception {

        // 汉堡菜单与Nav菜单交互
        titleBurgerContainer.setOnMouseClicked(event -> {
            if (drawer.isShown()) drawer.close();
            else drawer.open();
        });
        drawer.setOnDrawerClosing(event -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(-1);
            animation.play();
        });
        drawer.setOnDrawerOpening(event -> {
            final Transition animation = titleBurger.getAnimation();
            animation.setRate(1);
            animation.play();
        });

        ((JFXListView<?>)sidePane.lookup("#list")).getSelectionModel().selectedItemProperty().addListener(observable -> {
            new Thread(() -> {
                try {
                    Thread.sleep(150);
                    drawer.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        });

        // 工具栏菜单加载
        optionsBurger.setOnMouseClicked(event -> {
            final int offsetX = 0, offsetY = 8;
            toolbarPopup.show(optionsBurger, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, offsetX, offsetY);
        });

    }
}