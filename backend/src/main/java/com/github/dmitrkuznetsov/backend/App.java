package com.github.dmitrkuznetsov.backend;

import com.github.dmitrkuznetsov.backend.configuration.MyConfig;
import com.github.dmitrkuznetsov.backend.service.BackendService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        BackendService backendService = context.getBean("backendServiceImpl", BackendService.class);
        backendService.run();
    }
}
