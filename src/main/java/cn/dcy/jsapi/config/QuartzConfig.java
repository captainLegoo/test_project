package cn.dcy.jsapi.config;

import cn.dcy.jsapi.job.HelloJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kyle
 * @date 2024/06/15
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail helloJobDetail() {
        return JobBuilder
                .newJob(HelloJob.class)
                .withIdentity("TestQuartzJob")
                .usingJobData("msg", "Hello Quartz")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/5 * * * * ?");
        return TriggerBuilder
                .newTrigger()
                .forJob(helloJobDetail())
                .withIdentity("TestQuartzJobTrigger")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
