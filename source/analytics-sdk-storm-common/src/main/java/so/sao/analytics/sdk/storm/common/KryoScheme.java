package so.sao.analytics.sdk.storm.common;

import static backtype.storm.utils.Utils.tuple;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

import backtype.storm.spout.Scheme;
import backtype.storm.topology.FailedException;

/**
 * KryoScheme
 *
 * @author senhui.li
 */
public abstract class KryoScheme<T> implements Scheme {

    private static final long serialVersionUID = 6923985190833960706L;

    private Class<T> clazz;

    public KryoScheme(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public List<Object> deserialize(byte[] buffer) {
        Kryo kryo = new Kryo();
        kryo.setDefaultSerializer(CompatibleFieldSerializer.class);
        kryo.register(clazz);
        T scheme;
        try {
            scheme = kryo.readObject(new Input(new ByteArrayInputStream(buffer)), this.clazz);
        } catch (Exception e) {
            String errMsg = String.format("Kryo Scheme failed to deserialize data from Kafka to %s. Raw: %s", clazz.getName(),
                    new String(buffer));
            throw new FailedException(errMsg, e);
        }

        return tuple(scheme);
    }
}
