package com.wuyuntao.flatbuffers.schema

import com.google.flatbuffers.Table

final class Message(i: Int, b: Table) {
  val id = i
  val body = b
}