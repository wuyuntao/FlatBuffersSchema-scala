// automatically generated, do not modify

package FlatBuffers.Schema.Tests;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

public final class PongMessage extends Table {
  public static PongMessage getRootAsPongMessage(ByteBuffer _bb) { return getRootAsPongMessage(_bb, new PongMessage()); }
  public static PongMessage getRootAsPongMessage(ByteBuffer _bb, PongMessage obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__init(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public PongMessage __init(int _i, ByteBuffer _bb) { bb_pos = _i; bb = _bb; return this; }

  public int count() { int o = __offset(4); return o != 0 ? bb.getInt(o + bb_pos) : 0; }
  public String msg() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer msgAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }

  public static int createPongMessage(FlatBufferBuilder builder,
      int count,
      int msg) {
    builder.startObject(2);
    PongMessage.addMsg(builder, msg);
    PongMessage.addCount(builder, count);
    return PongMessage.endPongMessage(builder);
  }

  public static void startPongMessage(FlatBufferBuilder builder) { builder.startObject(2); }
  public static void addCount(FlatBufferBuilder builder, int count) { builder.addInt(0, count, 0); }
  public static void addMsg(FlatBufferBuilder builder, int msgOffset) { builder.addOffset(1, msgOffset, 0); }
  public static int endPongMessage(FlatBufferBuilder builder) {
    int o = builder.endObject();
    return o;
  }
  public static void finishPongMessageBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
};

