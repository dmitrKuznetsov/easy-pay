package com.github.dmitrkuznetsov.backend;

import com.github.dmitrkuznetsov.backend.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.listener.MessageListenerContainer;

public class BackendApplication
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(MyConfig.class);

        MessageListenerContainer messageListenerContainer =
                context.getBean("messageListenerContainer", MessageListenerContainer.class);
        messageListenerContainer.start();

//        context.close();
    }
}
