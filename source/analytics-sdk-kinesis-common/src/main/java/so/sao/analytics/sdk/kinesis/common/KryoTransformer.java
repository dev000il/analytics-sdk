package so.sao.analytics.sdk.kinesis.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import com.amazonaws.services.kinesis.connectors.interfaces.ITransformer;
import com.amazonaws.services.kinesis.model.Record;
import java.io.ByteArrayInputStream;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

/**
 * This class implements the ITransformer interface and provides an implementation of the toClass()
 * method for deserializing and serializing Record . The constructor takes the class to
 * transform to/from Record. The Record parameter of the toClass() method is expected to contain a
 * byte representation of a Record.
 * 
 * @param <T>
 */
public abstract class KryoTransformer<T, U> implements ITransformer<T, U> {

    protected Class<T> inputClass;
    
    protected final CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();

    public KryoTransformer(Class<T> inputClass) {
        this.inputClass = inputClass;
    }

    /**
     * from kinesis Record To Class
     * 
     * @param Record
     * return T
     */
	@Override
    public T toClass(Record record) throws IOException {
        Kryo kryo = new Kryo();
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
        kryo.register(inputClass);
        try {
        	T t = kryo.readObject(new Input(new ByteArrayInputStream(record.getData().array())), this.inputClass);
            return t;
        } catch (Exception e) {
            String message = String.format("Kryo Transformer failed to record from Kinesis: to %s. Raw: %s" + inputClass.getName(),new String(record.getData().array()));
            throw new IOException(message, e);
        }
    }
}


