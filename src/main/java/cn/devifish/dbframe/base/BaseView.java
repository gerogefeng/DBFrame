package cn.devifish.dbframe.base;

import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import javafx.fxml.Initializable;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class BaseView {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @PostConstruct
    public void init() {
        try {
            initView();
            initEvent();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void initView() throws Exception;
    protected abstract void initEvent() throws Exception;

    protected ViewFlowContext getContext() {
        return context;
    }

}
