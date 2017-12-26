package cn.devifish.dbframe.base;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseView implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            initView(resources);
            initEvent();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void initView(ResourceBundle resources) throws Exception;
    protected abstract void initEvent() throws Exception;

}
