package cn.devifish.dbframe.base;

import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;

import javax.annotation.PostConstruct;

public abstract class BaseView {

    @FXMLViewFlowContext
    private ViewFlowContext context;

    @PostConstruct
    private void init() {
        try {
            initVar(context);
            initView();
            initEvent();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void initVar(ViewFlowContext context) throws Exception {}
    protected abstract void initView() throws Exception;
    protected void initEvent() throws Exception {}

    protected ViewFlowContext getContext() {
        return context;
    }

}
