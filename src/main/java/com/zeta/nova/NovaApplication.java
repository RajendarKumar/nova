package com.zeta.nova;

import com.zeta.nova.booking.process.Reserve;
import com.zeta.nova.service.impl.CabRideRegService;
import com.zeta.nova.service.impl.CustomerRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@SpringBootApplication
public class NovaApplication {

    @Autowired
    private CabRideRegService cabService;

    @Autowired
    private CustomerRequestService crs;

    public static void main(String[] args) {

        SpringApplication.run(NovaApplication.class, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }

    @Bean
    public CommandLineRunner schedulingRunner(TaskExecutor executor) {
        return new CommandLineRunner() {
            public void run(String... args) throws Exception {
                executor.execute(new Reserve(cabService, crs));
            }
        };
    }
}
