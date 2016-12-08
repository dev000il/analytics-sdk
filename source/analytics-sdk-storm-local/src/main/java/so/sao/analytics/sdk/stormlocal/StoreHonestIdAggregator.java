package so.sao.analytics.sdk.stormlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import backtype.storm.topology.FailedException;
import so.sao.analytics.sdk.common.model.flatevent.FlatActivity;
import storm.trident.operation.BaseAggregator;
import storm.trident.operation.TridentCollector;
import storm.trident.operation.TridentOperationContext;
import storm.trident.topology.TransactionAttempt;
import storm.trident.tuple.TridentTuple;

/**
 * Store the scan honest id into MySQL
 *
 * @author senhui.li
 */
public class StoreHonestIdAggregator extends BaseAggregator<Object> {

    private static final long serialVersionUID = -7691073875445787126L;

    private static final Logger logger = LoggerFactory.getLogger(StoreHonestIdAggregator.class);

    private LinkedBlockingQueue<FlatActivity> queue;
    private String url;
    private String user;
    private String pswd;

    @SuppressWarnings("rawtypes")
    @Override
    public void prepare(Map conf, TridentOperationContext context) {
        super.prepare(conf, context);
        this.queue = new LinkedBlockingQueue<>();
        this.url = conf.get("db.url").toString();
        this.user = conf.get("db.user").toString();
        this.pswd = conf.get("db.pswd").toString();
        logger.info("=================== MySQL Server Conf ===================");
        logger.info("url: {}, user: {}, pswd: {}", this.url, this.user, this.pswd);
    }

    @Override
    public Object init(Object batchId, TridentCollector collector) {
        return ((TransactionAttempt)batchId).getTransactionId();
    }

    @Override
    public void aggregate(Object val, TridentTuple tuple, TridentCollector collector) {
        Object event = tuple.getValue(0);
        if (event instanceof FlatActivity) {
            this.queue.add((FlatActivity) event);
        }

    }

    @Override
    public void complete(Object val, TridentCollector collector) {
        long batchId = (Long) val;
        if (this.queue.size() > 0) {
            storeToMySQL(batchId);
        }
    }

    private void storeToMySQL(long batchId) {
        logger.info("{} - Now the queue had {} items.", batchId, this.queue.size());
        Connection conn = null;
        try {
            conn = getConn();
            PreparedStatement preStmt = getPreStmt(conn);
            while (this.queue.peek() != null) {
                FlatActivity event = this.queue.poll();
                preStmt.setString(1, event.getR());
                preStmt.setTimestamp(2, new Timestamp(event.getTs().getTime()));
                preStmt.setString(3, event.getOip());
                preStmt.setString(4, event.getUa());
                preStmt.setString(5, event.getD());
                preStmt.setString(6, event.getCl());
                preStmt.setString(7, event.getH());
                preStmt.setString(8, event.getHid());
                preStmt.setInt(9, event.getC());

                preStmt.addBatch();
            }
            int[] result = preStmt.executeBatch();
            logger.info("{} had insert to table success. result: {}", batchId, result.length);
            preStmt.clearBatch();
            preStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new FailedException(e);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private Connection getConn() throws SQLException {
        return DriverManager.getConnection(this.url, this.user, this.pswd);
    }

    private PreparedStatement getPreStmt(Connection conn) throws SQLException {
        String sql = "INSERT INTO activities " +
                "(request_id,`timestamp`,origin_ip,user_agent,deployment,client_id, host_ver,honest_id,company_id) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement preStmt = conn.prepareStatement(sql);
        return preStmt;
    }
}
