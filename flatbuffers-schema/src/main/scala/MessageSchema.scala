package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer
import scala.collection.mutable.HashMap

final class MessageSchema {
  private val messages = new HashMap[Int, MessageCreator]()

  def register(messageId: Int, creator: MessageCreator) = {
    if (messages.contains(messageId))
      throw new IllegalArgumentException("Message #%d is already defined".format(messageId))

    messages.put(messageId, creator)
  }

  def parse(messageId: Int, data: Array[Byte]): Message = {
    val creator = messages.get(messageId)
    if (creator.nonEmpty) {
      val body = creator.get.create(ByteBuffer.wrap(data))

      return new Message(messageId, body)
    } else {
      throw new IllegalArgumentException("Missing creator for message #%d".format(messageId))
    }
  }
}