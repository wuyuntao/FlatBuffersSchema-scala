package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer
import scala.collection.mutable.ListBuffer

final class MessageQueue(schema: MessageSchema) {
  private val bytes = new ByteQueue()
  private var pendingMessageSize = 0

  def enqueue(data: Array[Byte]) = bytes.enqueue(data)

  def dequeue: Message = {
    // Get size of next message
    if (pendingMessageSize == 0) {
      val size = bytes.dequeue()
      if (size.isEmpty)
        return null
      else
        pendingMessageSize = size.get
    }

    // Get body of next message
    if (pendingMessageSize > 0) {
      val data = bytes.dequeue(pendingMessageSize)
      if (data != null) {
        val message = ProtocolMessage.getRootAsProtocolMessage(ByteBuffer.wrap(data))

        val body = new Array[Byte](message.bodyLength)
        message.bodyAsByteBuffer().get(body)

        return schema.parse(message.id, body)
      }
    }

    return null
  }

  def dequeueAll: Seq[Message] = {
    val messages = ListBuffer[Message]()

    var m = dequeue
    while (m != null) {
      messages += m

      m = dequeue
    }

    return messages.toSeq
  }
}