package com.cesarpa.microservices.camelmicroserviceb.routers.a;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyFirstTimerRouter extends RouteBuilder {
    // before: Total 0 routes, of which 0 are started
    // after:  Total 1 routes, of which 1 are started
    @Override
    public void configure() throws Exception {
        // timer
        // we are gonna listen a queue: endpoint
        // and log

        // lets use the from method, this is a timer enpdoint we want to sent it to a log
        //  : Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        // the message in this point is null also we can transform an pass a message in the constant
        from("timer:first-timer")
                // .transform().constant("My Constant Message")
                .transform().constant("Time now is " + LocalDateTime.now())
                .to("log:first-timer");

        // output:  Body: Time now is 2022-11-28T23:36:43.172552100]

        // in the future we'll ->
        // transform it  makins some changes in the messages
        // then use a database: has and enpdoint
    }
}
