package cuong.test;

import io.gridgo.boot.support.annotations.Connector;
import io.gridgo.boot.support.annotations.Gateway;
import io.gridgo.boot.support.annotations.GatewayInject;
import io.gridgo.core.GridgoContext;
import io.gridgo.core.impl.AbstractProcessor;
import io.gridgo.core.support.RoutingContext;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import static io.gridgo.connector.kafka.KafkaConstants.OFFSET;

@Gateway("ArcturusConsumer")
@Connector("${arcturus.kafka.consumer}")
@Setter
@Log4j2
public class KafkaConsumer extends AbstractProcessor {
    long count = 0;
    @GatewayInject("ZMQ")
    io.gridgo.core.Gateway zmq;

    public void process(RoutingContext routingContext, GridgoContext gridgoContext) {
        var message = routingContext.getMessage();
        log.info("Send to client: id: {} message ordered: {}, offset: {}", message.headers().getLong("correlation_id"),count++, message.headers().getLong(OFFSET));;
        zmq.sendWithAck(routingContext.getMessage()).forward(routingContext.getDeferred());
    }
}
