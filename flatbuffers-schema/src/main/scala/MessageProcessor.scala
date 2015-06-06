package com.wuyuntao.flatbuffers.schema

import scala.collection.mutable.{ HashMap, ListBuffer }

class MessageProcessor(schema: MessageSchema) {
  private val messages = new MessageQueue(schema)
  private val processorSets = new HashMap[Int, ProcessorSet]()

  private class Processor(ref: Int, processor: (Message) => Unit, once: Boolean) {
    val reference = ref

    def invoke(message: Message): Boolean = {
      processor(message)

      return once
    }
  }

  private class ProcessorSet {
    private var processors = new ListBuffer[Processor]()

    def process(message: Message): Unit = processors = processors.filter { p => !p.invoke(message) }

    def attach(processor: Processor): Unit = processors += processor

    def detach(processor: Int): Unit = processors = processors.filter { p => p.reference != processor }

    def detachAll: Unit = processors.clear()
  }

  def enqueue(data: Array[Byte]) = messages.enqueue(data)

  def process = {
    for (message <- messages.dequeueAll) {
      val processors = getProcessors(message.id, false)

      if (processors != null)
        processors.process(message)
    }
  }

  def attach(messageId: Int, processor: (Message) => Unit) = {
    val processors = getProcessors(messageId, true)

    processors.attach(new Processor(processor.hashCode, processor, false))
  }

  def attachOnce(messageId: Int, processor: (Message) => Unit) = {
    val processors = getProcessors(messageId, true)

    processors.attach(new Processor(processor.hashCode, processor, true))
  }

  def detach(messageId: Int, processor: (Message) => Unit) = {
    val processors = getProcessors(messageId, false)
    if (processors != null)
      processors.detach(processor.hashCode())
  }

  def detach(messageId: Int) = {
    val processors = getProcessors(messageId, false)
    if (processors != null)
      processors.detachAll
  }

  private def getProcessors(messageId: Int, createWhenNotExist: Boolean): ProcessorSet = {
    val processors = processorSets.get(messageId)
    if (processors.isEmpty) {
      if (createWhenNotExist) {
        val newProcessors = new ProcessorSet()
        processorSets.put(messageId, newProcessors)
        return newProcessors
      } else
        return null
    } else
      return processors.get
  }
}