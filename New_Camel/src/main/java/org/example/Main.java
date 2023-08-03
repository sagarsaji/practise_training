package org.example;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

class Camel extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        from("direct:start").log("${body}");
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new Camel());
        camelContext.start();
        ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
        producerTemplate.sendBody("direct:start","Hello World");
        Thread.sleep(2000);
        camelContext.stop();
    }
}