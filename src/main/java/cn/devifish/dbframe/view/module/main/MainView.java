package cn.devifish.dbframe.view.module.main;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.view.module.dblist.DBListView;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ResourceBundle;

@ViewController(value = "/layout/main_view.fxml")
public class MainView extends BaseView {

    @FXML private StackPane root;
    @FXML private StackPane titleBurgerContainer;
    @FXML private StackPane optionsBurger;
    @FXML private JFXHamburger titleBurger;
    @FXML private JFXDrawer drawer;

    private JFXPopup toolbarPopup;

    @Override
    protected void initView() throws Exception {
        ViewFlowContext context = getContext();
        final Duration containerAnimationDuration = Duration.millis(500);

        // 加载首页面内容
        Flow innerFlow = new Flow(DBListView.class);
        final FlowHandler flowHandler = innerFlow.createHandler(context);
        drawer.setContent(flowHandler.start(new AnimatedFlowContainer(containerAnimationDuration, ContainerAnimations.SWIPE_LEFT)));

        // 加载POP菜单
        Region region = FXMLLoader.load(getClass().getResource("/layout/pop_menu_main.fxml"));
        toolbarPopup = new JFXPopup(region);

        // 加载Nav菜单
        Flow sideMenuFlow = new Flow(NavMenuView.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        drawer.setSidePane(sideMenuFlowHandler.start(new AnimatedFlowContainer(containerAnimationDuration, ContainerAnimations.SWIPE_LEFT)));

        // 注册相关属性
        context.register("ContentFlow", innerFlow);
        context.register("ContentPane", drawer.getContent().get(0));
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

        // 工具栏菜单加载
        optionsBurger.setOnMouseClicked(event -> {
            final int offsetX = 0, offsetY = 8;
            toolbarPopup.show(optionsBurger, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT, offsetX, offsetY);
        });

    }
}