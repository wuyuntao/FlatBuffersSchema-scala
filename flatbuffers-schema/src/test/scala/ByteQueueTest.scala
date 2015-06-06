package com.wuyuntao.flatbuffers.schema

import java.nio.ByteBuffer
import org.junit.Test
import junit.framework.TestCase
import org.junit.Assert
import scala.collection.immutable.StringOps

final class ByteQueueTest extends TestCase {
  private final val intValue = 21
  private final val stringValue = "testDequeueBytes"
  private val queue = new ByteQueue()

  def testDequeueInt = {
    val bytes = ByteBuffer.allocate(4).putInt(21).array()
    // Enqueue first 2 bytes of Int
    var buffer = new Array[Byte](2)
    Array.copy(bytes, 0, buffer, 0, 2)
    queue.enqueue(buffer)

    Assert.assertTrue(queue.hasBytes(2))
    Assert.assertFalse(queue.hasBytes(3))
    Assert.assertNull(queue.dequeue())

    // Enqueue last 2 bytes of Int
    buffer = new Array[Byte](2)
    Array.copy(bytes, 2, buffer, 0, 2)
    queue.enqueue(buffer)

    Assert.assertTrue(queue.hasBytes(4))
    Assert.assertFalse(queue.hasBytes(5))
    Assert.assertEquals(intValue, queue.dequeue().get)
  }
  
  def testDequeueBytes = {
    val bytes = new StringOps(stringValue).getBytes
    queue.enqueue(bytes)
    
    Assert.assertTrue(queue.hasBytes(bytes.length))
    Assert.assertFalse(queue.hasBytes(bytes.length + 1))
    
    val dequeuedBytes = queue.dequeue(bytes.length)
    var dequeuedValue = new String(dequeuedBytes, "UTF-8")
    Assert.assertEquals(stringValue, dequeuedValue)
  }
}