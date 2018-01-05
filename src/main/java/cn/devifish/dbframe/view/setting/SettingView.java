package cn.devifish.dbframe.view.setting;

import cn.devifish.dbframe.base.BaseView;
import cn.devifish.dbframe.base.ContextView;
import com.jfoenix.controls.JFXTabPane;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.FXML;

@ViewController("/layout/setting_view.fxml")
public class SettingView extends ContextView {

    @FXML private JFXTabPane tabPane;

    @Override
    protected void initVar(ViewFlowContext context) {

    }

    @Override
    protected void initView() throws Exception {

    }

}
