/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package nl.nightcrawler.spring.kafkastandalone.avro.model;

import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.SchemaStore;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;

@org.apache.avro.specific.AvroGenerated
public class UUID extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 5961112952748146646L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"UUID\",\"namespace\":\"nl.nightcrawler.spring.kafkastandalone.avro.model\",\"fields\":[{\"name\":\"uniqueId\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<UUID> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<UUID> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<UUID> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<UUID> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<UUID> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this UUID to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a UUID from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a UUID instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static UUID fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence uniqueId;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public UUID() {}

  /**
   * All-args constructor.
   * @param uniqueId The new value for uniqueId
   */
  public UUID(java.lang.CharSequence uniqueId) {
    this.uniqueId = uniqueId;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return uniqueId;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: uniqueId = (java.lang.CharSequence)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'uniqueId' field.
   * @return The value of the 'uniqueId' field.
   */
  public java.lang.CharSequence getUniqueId() {
    return uniqueId;
  }


  /**
   * Sets the value of the 'uniqueId' field.
   * @param value the value to set.
   */
  public void setUniqueId(java.lang.CharSequence value) {
    this.uniqueId = value;
  }

  /**
   * Creates a new UUID RecordBuilder.
   * @return A new UUID RecordBuilder
   */
  public static nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder newBuilder() {
    return new nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder();
  }

  /**
   * Creates a new UUID RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new UUID RecordBuilder
   */
  public static nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder newBuilder(nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder other) {
    if (other == null) {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder();
    } else {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder(other);
    }
  }

  /**
   * Creates a new UUID RecordBuilder by copying an existing UUID instance.
   * @param other The existing instance to copy.
   * @return A new UUID RecordBuilder
   */
  public static nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder newBuilder(nl.nightcrawler.spring.kafkastandalone.avro.model.UUID other) {
    if (other == null) {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder();
    } else {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder(other);
    }
  }

  /**
   * RecordBuilder for UUID instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<UUID>
    implements org.apache.avro.data.RecordBuilder<UUID> {

    private java.lang.CharSequence uniqueId;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.uniqueId)) {
        this.uniqueId = data().deepCopy(fields()[0].schema(), other.uniqueId);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
    }

    /**
     * Creates a Builder by copying an existing UUID instance
     * @param other The existing instance to copy.
     */
    private Builder(nl.nightcrawler.spring.kafkastandalone.avro.model.UUID other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.uniqueId)) {
        this.uniqueId = data().deepCopy(fields()[0].schema(), other.uniqueId);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'uniqueId' field.
      * @return The value.
      */
    public java.lang.CharSequence getUniqueId() {
      return uniqueId;
    }


    /**
      * Sets the value of the 'uniqueId' field.
      * @param value The value of 'uniqueId'.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder setUniqueId(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.uniqueId = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'uniqueId' field has been set.
      * @return True if the 'uniqueId' field has been set, false otherwise.
      */
    public boolean hasUniqueId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'uniqueId' field.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.UUID.Builder clearUniqueId() {
      uniqueId = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public UUID build() {
      try {
        UUID record = new UUID();
        record.uniqueId = fieldSetFlags()[0] ? this.uniqueId : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<UUID>
    WRITER$ = (org.apache.avro.io.DatumWriter<UUID>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<UUID>
    READER$ = (org.apache.avro.io.DatumReader<UUID>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.uniqueId);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.uniqueId = in.readString(this.uniqueId instanceof Utf8 ? (Utf8)this.uniqueId : null);

    } else {
      for (int i = 0; i < 1; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.uniqueId = in.readString(this.uniqueId instanceof Utf8 ? (Utf8)this.uniqueId : null);
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










