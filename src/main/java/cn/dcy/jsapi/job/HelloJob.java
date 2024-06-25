package cn.dcy.jsapi.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author Kyle
 * @date 2024/06/15
 */
@Slf4j
public class HelloJob extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        System.out.println(context.getJobDetail().isDurable());
        context.getJobDetail().getJobDataMap().forEach((k, v) ->
                log.info("key:{},value:{}", k, v)
        );

        log.info("Hello Job exec time: {}", new Date());
    }
}
