package com.wuyuntao.flatbuffers.schema

import org.junit.Assert
import com.google.flatbuffers.FlatBufferBuilder
import junit.framework.TestCase

final class MessageQueueTest extends TestCase {
  private val schema = new MessageSchema()
  private val queue = new MessageQueue(schema)

  def testPingMessage = {
    // Register message creators
    schema.register(MessageIds.Ping, PingMessage.getRootAsPingMessage);
    schema.register(MessageIds.Pong, PongMessage.getRootAsPongMessage);

    val count = 10;
    val msg = "TestPing10";
    val ping = createPingMessage(count, msg);
    queue.enqueue(FlatBufferExtensions.toProtocolMessage(ping, MessageIds.Ping));

    val message = queue.dequeue;
    Assert.assertEquals(MessageIds.Ping, message.id);

    val pingBody = message.body.asInstanceOf[PingMessage];
    Assert.assertTrue(pingBody != null);
    Assert.assertEquals(count, pingBody.count);
    Assert.assertEquals(msg, pingBody.msg);
  }

  def createPingMessage(count: Int, msg: String): FlatBufferBuilder = {
    val fbb = new FlatBufferBuilder(1024);

    val ping = PingMessage.createPingMessage(fbb, count, fbb.createString(msg));
    PingMessage.finishPingMessageBuffer(fbb, ping);

    return fbb;
  }
}