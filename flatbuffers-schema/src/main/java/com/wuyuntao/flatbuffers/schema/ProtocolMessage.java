// automatically generated, do not modify

package com.wuyuntao.flatbuffers.schema;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class ProtocolMessage extends Table {
  public static ProtocolMessage getRootAsProtocolMessage(ByteBuffer _bb) { return getRootAsProtocolMessage(_bb, new ProtocolMessage()); }
  public static ProtocolMessage getRootAsProtocolMessage(ByteBuffer _bb, ProtocolMessage obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public ProtocolMessage __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int id() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public int body(int j) { int o = __offset(6); return o != 0 ? bb.get(__vector(o) + j * 1) & 0xFF : 0; }
  public int bodyLength() { int o = __offset(6); return o != 0 ? __vector_len(o) : 0; }
  public ByteBuffer bodyAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }

  public static int createProtocolMessage(FlatBufferBuilder builder,
      int id,
      int body) {
    builder.startObject(2);
    ProtocolMessage.addBody(builder, body);
    ProtocolMessage.addId(builder, id);
    return ProtocolMessage.endProtocolMessage(builder);
  }

  public static void startProtocolMessage(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addId(FlatBufferBuilder builder, int id) { builder.addInt(0, id, 0); }
  public static void addBody(FlatBufferBuilder builder, int bodyOffset) { builder.addOffset(1, bodyOffset, 0); }
  public static int createBodyVector(FlatBufferBuilder builder, byte[] data) { builder.startVector(1, data.length, 1); for (int i = data.length - 1; i >= 0; i--) builder.addByte(data[i]); return builder.endVector(); }
  public static void startBodyVector(FlatBufferBuilder builder, int numElems) { builder.startVector(1, numElems, 1); }
  public static int endProtocolMessage(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishProtocolMessageBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

