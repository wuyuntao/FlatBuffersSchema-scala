package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer

import com.google.flatbuffers.Table

abstract class MessageCreator {
  def create(buffer: ByteBuffer): Table;
}