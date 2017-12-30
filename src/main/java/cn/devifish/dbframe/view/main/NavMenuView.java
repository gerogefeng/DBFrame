package cn.devifish.dbframe.view.main;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.util.StringUtil;
import cn.devifish.dbframe.view.dblist.DBListView;
import cn.devifish.dbframe.view.setting.SettingView;
import com.jfoenix.controls.JFXListView;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.HashMap;
import java.util.Map;

@ViewController(value = "/layout/nav_menu_main.fxml")
public class NavMenuView extends BaseView {

    @FXML private StackPane root;
    @FXML private JFXListView<Label> list;

    private Label toolbarTitle;

    private Flow contentFlow;
    private FlowHandler contentFlowHandler;

    private static final Map<String, Class> viewMap;

    static {
        viewMap = new HashMap<>();
        viewMap.put("home", DBListView.class);
        viewMap.put("setting", SettingView.class);
    }

    @Override
    protected void initVar(ViewFlowContext context) throws Exception {
        toolbarTitle = (Label) context.getRegisteredObject("toolbarTitle");
        contentFlow = (Flow) context.getRegisteredObject("ContentFlow");
        contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
    }

    @Override
    protected void initView() throws Exception {
        viewMap.forEach((s, aClass) -> contentFlow.withGlobalLink(s, aClass)); // 绑定View
    }

    @Override
    protected void initEvent() throws Exception {
        list.propagateMouseEventsToParent();
        list.getSelectionModel().selectFirst();
        list.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                if (!newValue.equals(oldValue)) {
                    String id = newValue.getId();
                    if (StringUtil.isNotEmpty(id) && viewMap.containsKey(id)) {
                        contentFlowHandler.handle(id);
                        toolbarTitle.setText(newValue.getText());
                    }
                }
            } catch (VetoException | FlowException e) {
                e.printStackTrace();
            }
        });
    }

}
