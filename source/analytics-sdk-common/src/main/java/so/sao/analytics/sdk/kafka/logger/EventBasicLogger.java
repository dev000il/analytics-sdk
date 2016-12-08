package so.sao.analytics.sdk.kafka.logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;

import so.sao.analytics.sdk.exception.PropertyInvalidException;
import so.sao.analytics.sdk.common.model.event.Activity;
import so.sao.analytics.sdk.common.model.event.AppSystem;
import so.sao.analytics.sdk.common.model.event.BasicEvent;
import so.sao.analytics.sdk.common.model.event.InvalidMessage;
import so.sao.analytics.sdk.common.model.event.MgmtAction;
import so.sao.analytics.sdk.common.model.event.OpenAPILog;
import so.sao.analytics.sdk.common.model.event.Thirdparty;
import so.sao.analytics.sdk.common.model.event.Transaction;
import so.sao.analytics.sdk.common.model.flatevent.FlatActivity;
import so.sao.analytics.sdk.common.model.flatevent.FlatAppSystem;
import so.sao.analytics.sdk.common.model.flatevent.FlatBasicEvent;
import so.sao.analytics.sdk.common.model.flatevent.FlatMgmtAction;
import so.sao.analytics.sdk.common.model.flatevent.FlatOpenAPILog;
import so.sao.analytics.sdk.common.model.flatevent.FlatThirdparty;
import so.sao.analytics.sdk.common.model.flatevent.FlatTransaction;
import so.sao.analytics.sdk.common.model.property.InternalTracing;

/**
 * Event super class
 *
 * @author senhui.li
 */
public abstract class EventBasicLogger {

    private static final Logger logger = LoggerFactory.getLogger(EventBasicLogger.class);

    public static final ThreadLocal<Kryo> KRYO_THREAD_LOCAL = new ThreadLocal<Kryo>() {

        @Override
        protected Kryo initialValue() {
            Kryo kryo = new Kryo();

            // Use the compatible serializer
            kryo.setDefaultSerializer(CompatibleFieldSerializer.class);

            // open API POJO
            kryo.register(Activity.class);
            kryo.register(AppSystem.class);
            kryo.register(MgmtAction.class);
            kryo.register(Transaction.class);
            kryo.register(Thirdparty.class);
            kryo.register(OpenAPILog.class);

            // flat POJO
            kryo.register(FlatActivity.class);
            kryo.register(FlatAppSystem.class);
            kryo.register(FlatMgmtAction.class);
            kryo.register(FlatTransaction.class);
            kryo.register(FlatThirdparty.class);
            kryo.register(FlatOpenAPILog.class);
            return kryo;
        }
    };

    protected BlockingQueue<byte[]> queue = new LinkedBlockingDeque<>();

    protected abstract void loggerInfo(String message);

    protected void loggerError(String message){
        logger.error(message);
    }

    protected void loggerError(String message, Throwable e){
        logger.error(message, e);
    }

    public List<byte[]> pull(int pullSize) {
        List<byte[]> events = new ArrayList<>(Math.min(queue.size(), pullSize));
        while (!queue.isEmpty() && --pullSize >= 0) {
            events.add(queue.poll());
        }
        return events;
    }

    public <T extends FlatBasicEvent> void push(T flatEvent) {
        try {
            byte[] message = getKryoSerialize(flatEvent);
            queue.add(message);
        } catch (Exception e) {
            loggerError("["+flatEvent.getClass().getSimpleName()+"]Add event byte array data to queue failed.", e);
        }
    }

    public <T extends BasicEvent> void push(T event) {

        if (event == null) {
            throw new NullPointerException("Event message data can't not be NULL.");
        }

        try {
            event.validate();
            loggerInfo(event.toString());
            try {
                byte[] message = getKryoSerialize(event.getFlat());
                queue.add(message);
            } catch (Exception e) {
                loggerError("Add event byte array data to queue failed.", e);
            }
        } catch (PropertyInvalidException e){

            String errMsg = event.getClass().getSimpleName()+" : " + e.getLocalizedMessage();
            loggerError(errMsg);

            // Transform the error event to app system event
            // which use for collect error message event.
            InvalidMessage invalidMsg = InvalidMessage.createInstance();

            InternalTracing eventIT = event.createOrGetInternalTracing();
            InternalTracing it = invalidMsg.createOrGetInternalTracing();
            it.setClientId(eventIT.getClientId());
            it.setHostInstanceVer(eventIT.getHostInstanceVer());
            it.copyHostInstance(eventIT.getHostInstance());
            it.setServiceDeployment(eventIT.getServiceDeployment());

            // Need fill up special value when event without company id
            int companyId = event.createOrGetCommon().getCompanyId();
            invalidMsg.setCompanyId(companyId==0 ? -2 : companyId);

            // Need backup invalid message source data
            invalidMsg.setEventType(event.getClass().getSimpleName());
            invalidMsg.setFlatEventSource(JSON.toJSONString(event.getFlat().getAll()));
            invalidMsg.setErrorMessage(e.getLocalizedMessage());

            InvalidMsgLogger.log(invalidMsg);
        }
    }

    /**
     * This sleep just means random time in
     * [1-5] seconds make the thread sleep
     */
    public void sleep() {
        try {
            Thread.sleep((new Random().nextInt(5) + 1) * 1000);
        } catch (InterruptedException e) {
            loggerError("Thread sleep failed when close Kafka producer.", e);
        }
    }

    public void clear() {
        try {
            this.queue.clear();
        } catch (Exception e) {
            loggerError("Clear queue message failed.", e);
        }
    }

    public int size() {
        return this.queue.size();
    }

    /**
     * Use Kryo framework serialize the event object data
     *
     * @param event event object
     * @return  byte array
     */
    protected <T extends FlatBasicEvent> byte[] getKryoSerialize(T event) {
        byte[] bytes = null;
        Kryo kryo = EventBasicLogger.KRYO_THREAD_LOCAL.get();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Output output = new Output(baos);

        try {
            kryo.writeObject(output, event);
            output.flush();
            baos.flush();

            bytes = baos.toByteArray();

        } catch (Exception e) {
            loggerError("Kryo serialize failed.", e);
        } finally {
            try {
                output.close();
                baos.close();
            } catch (IOException e) {
                loggerError("Close byte array out stream failed.", e);
            }
        }
        return bytes;
    }

}
