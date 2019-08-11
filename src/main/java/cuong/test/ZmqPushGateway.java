package cuong.test;

import io.gridgo.boot.support.annotations.Connector;
import io.gridgo.boot.support.annotations.Gateway;

@Gateway("ZMQ")
@Connector("${arcturus.zmq.producer}")
public class ZmqPushGateway {
}
