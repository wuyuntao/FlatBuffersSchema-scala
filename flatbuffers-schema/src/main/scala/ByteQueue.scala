package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer
import scala.collection.mutable.Queue

private class ByteQueue {
  private final val SizeOfInt = 4

  private val queue = new Queue[Array[Byte]]
  private var passedBytes = 0

  def enqueue(data: Array[Byte]) = {
    if (data == null)
      throw new IllegalArgumentException("Data cannot be null")

    queue.enqueue(data)
  }

  def dequeue(): Option[Int] = {
    val bytes = dequeue(SizeOfInt)

    if (bytes == null)
      return Option(ByteBuffer.wrap(bytes).getInt)
    else
      return null
  }

  def dequeue(bytes: Int): Array[Byte] = {
    if (!hasBytes(bytes))
      return null

    val dest = new Array[Byte](bytes)
    var destIndex = 0

    while (destIndex < bytes) {
      val src = queue.head

      val copyBytes = Math.min(src.length - passedBytes, bytes - destIndex)
      Array.copy(src, passedBytes, dest, destIndex, copyBytes)

      destIndex += copyBytes
      passedBytes += copyBytes

      if (passedBytes == src.length) {
        queue.dequeue()
        passedBytes = 0
      }
    }

    return dest
  }

  def hasBytes(bytes: Int): Boolean = {
    if (bytes <= 0)
      throw new IllegalArgumentException("Bytes must be > 0")

    if (queue.length == 0)
      return false

    var r = -passedBytes
    return queue.exists { d => r += d.length; r >= bytes }
  }
}