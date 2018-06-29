package com.duan.quartzdemo.xxl;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/6/29.
 *
 * @author DuanJiaNing
 */
@Component
@JobHandler("xxlJobOne")
public class XxlJobOne extends IJobHandler {

    @Override
    public ReturnT<String> execute(String s) throws Exception {
        ReturnT<String> success = IJobHandler.SUCCESS;
        success.setMsg("... 成功了 ...");

        return success;
    }

}
