package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer
import com.google.flatbuffers.FlatBufferBuilder

object FlatBufferExtensions {
  final val SizeOfInt = 4
  final val InitialBufferSize = 1024
  
  def toProtocolMessage(fbb: FlatBufferBuilder, id: Int): Array[Byte] = {
    val buffer = fbb.dataBuffer
    val bufferSize = buffer.capacity - buffer.position
    
    val bytes = new Array[Byte](bufferSize)
    Array.copy(buffer.array, buffer.position, bytes, 0, bufferSize)
    
    return toProtocolMessage(id, bytes)
  }
  
  private def toProtocolMessage(id: Int, body: Array[Byte]): Array[Byte] = {
    val fbb = new FlatBufferBuilder(body.length + InitialBufferSize)
    val bodyPos = ProtocolMessage.createBodyVector(fbb, body)

    val msg = ProtocolMessage.createProtocolMessage(fbb, id, bodyPos)
    ProtocolMessage.finishProtocolMessageBuffer(fbb, msg)
    
    val buffer = fbb.dataBuffer
    val bufferSize = buffer.capacity - buffer.position
    
    val head = ByteBuffer.allocate(SizeOfInt + bufferSize)
    head.putInt(bufferSize)
    head.put(buffer)
    
    return head.array
  }
}