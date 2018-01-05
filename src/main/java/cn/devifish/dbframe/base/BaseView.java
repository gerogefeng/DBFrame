package cn.devifish.dbframe.base;

import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;

import javax.annotation.PostConstruct;

public abstract class BaseView {

    @PostConstruct
    protected void init() {
        try {
            initView();
            initEvent();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void initView() throws Exception;
    protected void initEvent() throws Exception {};

}
