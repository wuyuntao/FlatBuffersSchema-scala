package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer
import com.google.flatbuffers.Table

private object MessageCreator {
  def apply(creator: (ByteBuffer) => Table) = new MessageCreator(creator)
}

private final class MessageCreator(creator: (ByteBuffer) => Table) {
  def create(buffer: ByteBuffer) = creator(buffer)
}