package com.wuyuntao.flatbuffers.schema.tests

import java.nio.ByteBuffer
import org.specs2.mutable.Specification
import com.wuyuntao.flatbuffers.schema.ByteQueue

class ByteQueueSpec extends Specification { 
  val value = 21
  val bytes = ByteBuffer.allocate(4).putInt(21).array()
  val queue = new ByteQueue()
  
  "Test dequeue Int" >> {
    "Enqueue first 2 bytes of Int" >> {
      val buffer = new Array[Byte](2)
      Array.copy(bytes, 0, buffer, 0, 2)
      queue.enqueue(buffer)
      
      queue.hasBytes(2) must beTrue
      queue.hasBytes(3) must beFalse
      queue.dequeue() must beNull
    }
    
    "Enqueue last 2 bytes of Int" >> {
      val buffer = new Array[Byte](2)
      Array.copy(bytes, 2, buffer, 0, 2)
      queue.enqueue(buffer)
      
      queue.hasBytes(4) must beTrue
      queue.hasBytes(5) must beFalse
      queue.dequeue() must_== value
    }
  }
}