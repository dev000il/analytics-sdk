package so.sao.analytics.sdk.stormlocal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import storm.trident.operation.BaseFunction;
import storm.trident.operation.TridentCollector;
import storm.trident.tuple.TridentTuple;

/**
 * Print the deserialize scheme data
 *
 * @author senhui.li
 */
public class PrintFunction extends BaseFunction {

    private static final long serialVersionUID = 3359934659857781493L;

    private static final Logger logger = LoggerFactory.getLogger(PrintFunction.class);

    @Override
    public void execute(TridentTuple tuple, TridentCollector collector) {

        List<Object> data = tuple.getValues();

        if (data != null) {
            logger.info("Scheme data size: {}", data.size());
            for (Object value : data) {
                logger.info("{}-{}", value.getClass().getSimpleName(), value);
            }
        }

    }

}
