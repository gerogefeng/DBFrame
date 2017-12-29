package cn.devifish.dbframe.view.setting;

import cn.devifish.dbframe.base.BaseView;
import com.jfoenix.controls.JFXTabPane;
import io.datafx.controller.ViewController;
import javafx.fxml.FXML;

@ViewController("/layout/setting_view.fxml")
public class SettingView extends BaseView {

    @FXML private JFXTabPane tabPane;

    @Override
    protected void initView() throws Exception {

    }

}
