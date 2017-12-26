package cn.devifish.dbframe.view;

import cn.devifish.dbframe.base.BaseView;
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

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @FXML private StackPane root;
    @FXML private StackPane titleBurgerContainer;
    @FXML private StackPane optionsBurger;
    @FXML private JFXHamburger titleBurger;
    @FXML private JFXDrawer drawer;

    private JFXPopup toolbarPopup;

    @Override
    protected void initView(ResourceBundle resources) throws Exception {
        context = new ViewFlowContext();

        // 加载POP菜单
        Region region = FXMLLoader.load(getClass().getResource("/layout/pop_menu_main.fxml"));
        toolbarPopup = new JFXPopup(region);

        // 加载Nav菜单
        Flow sideMenuFlow = new Flow(NavMenuView.class);
        final FlowHandler sideMenuFlowHandler = sideMenuFlow.createHandler(context);
        final Duration containerAnimationDuration = Duration.millis(320);
        drawer.setSidePane(sideMenuFlowHandler.start(new AnimatedFlowContainer(containerAnimationDuration, ContainerAnimations.SWIPE_LEFT)));
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