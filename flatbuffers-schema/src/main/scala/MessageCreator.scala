package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer

import com.google.flatbuffers.Table

object MessageCreator {
  def apply(creator: (ByteBuffer) => Table) = new MessageCreator(creator)
}

final class MessageCreator(creator: (ByteBuffer) => Table) {
  def create(buffer: ByteBuffer) = creator(buffer)
}