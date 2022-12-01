package com.cesarpa.microservices.camelmicroservicea.routes.a;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private  SimpleLoggingProcessingComponent simpleLoggingProcessingComponent;

    @Override
    public void configure() throws Exception {
        from("timer:first-timer")
                .log("${body}")
                .transform().constant("My Constant Message")
                .log("${body}")
                .bean(getCurrentTimeBean, "getCurrentTime") // we can specify the method that is gonna use after the comma
                .log("${body}")
                .bean(simpleLoggingProcessingComponent)
                .log("${body}")
                .to("log:first-timer");
    }
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime(){
        return "Time now is "+LocalDateTime.now();
    }
}

@Component
class SimpleLoggingProcessingComponent {

    private Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingComponent.class);

    public void process(String message){
       logger.info("SimpleLoggingProcessingComponent {}", message); //{} is the body
    }
}

