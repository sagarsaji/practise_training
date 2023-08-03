package org.example;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

class Camel extends RouteBuilder {

    @Override
    public void configure() throws Exception {
//        from("file:C:\\camel\\input?noop=true").to("file:C:\\camel\\output");

        from("file:C:\\camel\\input?noop=true").to("jms:incomingQueue");

        from("jms:incomingQueue")
                .choice()
                .when(header("CamelFileName").endsWith(".xml"))
                    .to("jms:xmlqueue")
                .when(header("CamelFileName").regex("^.*(csv|csl)$"))
                    .to("jms:csvqueue")
                .otherwise()
                    .to("jms:badqueue").stop()
        .end();

        from("jms:xmlqueue").filter(xpath("/order[not(@test)]"))
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println(exchange.getIn().getBody(String.class));
                    }
                });

    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm//localhost");
        camelContext.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        camelContext.addRoutes(new Camel());
        camelContext.start();
        Thread.sleep(5000);
        camelContext.stop();
    }
}