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
public class Customer extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -6438917321235212195L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Customer\",\"namespace\":\"nl.nightcrawler.spring.kafkastandalone.avro.model\",\"fields\":[{\"name\":\"customerName\",\"type\":\"string\"},{\"name\":\"address\",\"type\":\"string\"},{\"name\":\"pinCode\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<Customer> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<Customer> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<Customer> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<Customer> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<Customer> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this Customer to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a Customer from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a Customer instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static Customer fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence customerName;
  private java.lang.CharSequence address;
  private int pinCode;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public Customer() {}

  /**
   * All-args constructor.
   * @param customerName The new value for customerName
   * @param address The new value for address
   * @param pinCode The new value for pinCode
   */
  public Customer(java.lang.CharSequence customerName, java.lang.CharSequence address, java.lang.Integer pinCode) {
    this.customerName = customerName;
    this.address = address;
    this.pinCode = pinCode;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return customerName;
    case 1: return address;
    case 2: return pinCode;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: customerName = (java.lang.CharSequence)value$; break;
    case 1: address = (java.lang.CharSequence)value$; break;
    case 2: pinCode = (java.lang.Integer)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'customerName' field.
   * @return The value of the 'customerName' field.
   */
  public java.lang.CharSequence getCustomerName() {
    return customerName;
  }


  /**
   * Sets the value of the 'customerName' field.
   * @param value the value to set.
   */
  public void setCustomerName(java.lang.CharSequence value) {
    this.customerName = value;
  }

  /**
   * Gets the value of the 'address' field.
   * @return The value of the 'address' field.
   */
  public java.lang.CharSequence getAddress() {
    return address;
  }


  /**
   * Sets the value of the 'address' field.
   * @param value the value to set.
   */
  public void setAddress(java.lang.CharSequence value) {
    this.address = value;
  }

  /**
   * Gets the value of the 'pinCode' field.
   * @return The value of the 'pinCode' field.
   */
  public int getPinCode() {
    return pinCode;
  }


  /**
   * Sets the value of the 'pinCode' field.
   * @param value the value to set.
   */
  public void setPinCode(int value) {
    this.pinCode = value;
  }

  /**
   * Creates a new Customer RecordBuilder.
   * @return A new Customer RecordBuilder
   */
  public static nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder newBuilder() {
    return new nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder();
  }

  /**
   * Creates a new Customer RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new Customer RecordBuilder
   */
  public static nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder newBuilder(nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder other) {
    if (other == null) {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder();
    } else {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder(other);
    }
  }

  /**
   * Creates a new Customer RecordBuilder by copying an existing Customer instance.
   * @param other The existing instance to copy.
   * @return A new Customer RecordBuilder
   */
  public static nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder newBuilder(nl.nightcrawler.spring.kafkastandalone.avro.model.Customer other) {
    if (other == null) {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder();
    } else {
      return new nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder(other);
    }
  }

  /**
   * RecordBuilder for Customer instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Customer>
    implements org.apache.avro.data.RecordBuilder<Customer> {

    private java.lang.CharSequence customerName;
    private java.lang.CharSequence address;
    private int pinCode;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.customerName)) {
        this.customerName = data().deepCopy(fields()[0].schema(), other.customerName);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.address)) {
        this.address = data().deepCopy(fields()[1].schema(), other.address);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.pinCode)) {
        this.pinCode = data().deepCopy(fields()[2].schema(), other.pinCode);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing Customer instance
     * @param other The existing instance to copy.
     */
    private Builder(nl.nightcrawler.spring.kafkastandalone.avro.model.Customer other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.customerName)) {
        this.customerName = data().deepCopy(fields()[0].schema(), other.customerName);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.address)) {
        this.address = data().deepCopy(fields()[1].schema(), other.address);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.pinCode)) {
        this.pinCode = data().deepCopy(fields()[2].schema(), other.pinCode);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'customerName' field.
      * @return The value.
      */
    public java.lang.CharSequence getCustomerName() {
      return customerName;
    }


    /**
      * Sets the value of the 'customerName' field.
      * @param value The value of 'customerName'.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder setCustomerName(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.customerName = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'customerName' field has been set.
      * @return True if the 'customerName' field has been set, false otherwise.
      */
    public boolean hasCustomerName() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'customerName' field.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder clearCustomerName() {
      customerName = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'address' field.
      * @return The value.
      */
    public java.lang.CharSequence getAddress() {
      return address;
    }


    /**
      * Sets the value of the 'address' field.
      * @param value The value of 'address'.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder setAddress(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.address = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'address' field has been set.
      * @return True if the 'address' field has been set, false otherwise.
      */
    public boolean hasAddress() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'address' field.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder clearAddress() {
      address = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'pinCode' field.
      * @return The value.
      */
    public int getPinCode() {
      return pinCode;
    }


    /**
      * Sets the value of the 'pinCode' field.
      * @param value The value of 'pinCode'.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder setPinCode(int value) {
      validate(fields()[2], value);
      this.pinCode = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'pinCode' field has been set.
      * @return True if the 'pinCode' field has been set, false otherwise.
      */
    public boolean hasPinCode() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'pinCode' field.
      * @return This builder.
      */
    public nl.nightcrawler.spring.kafkastandalone.avro.model.Customer.Builder clearPinCode() {
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Customer build() {
      try {
        Customer record = new Customer();
        record.customerName = fieldSetFlags()[0] ? this.customerName : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.address = fieldSetFlags()[1] ? this.address : (java.lang.CharSequence) defaultValue(fields()[1]);
        record.pinCode = fieldSetFlags()[2] ? this.pinCode : (java.lang.Integer) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<Customer>
    WRITER$ = (org.apache.avro.io.DatumWriter<Customer>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<Customer>
    READER$ = (org.apache.avro.io.DatumReader<Customer>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.customerName);

    out.writeString(this.address);

    out.writeInt(this.pinCode);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.customerName = in.readString(this.customerName instanceof Utf8 ? (Utf8)this.customerName : null);

      this.address = in.readString(this.address instanceof Utf8 ? (Utf8)this.address : null);

      this.pinCode = in.readInt();

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.customerName = in.readString(this.customerName instanceof Utf8 ? (Utf8)this.customerName : null);
          break;

        case 1:
          this.address = in.readString(this.address instanceof Utf8 ? (Utf8)this.address : null);
          break;

        case 2:
          this.pinCode = in.readInt();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










