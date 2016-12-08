package so.sao.analytics.sdk.stormlocal;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.tuple.Fields;
import so.sao.analytics.sdk.storm.common.KryoScheme;
import storm.kafka.BrokerHosts;
import storm.kafka.ZkHosts;
import storm.kafka.trident.TransactionalTridentKafkaSpout;
import storm.kafka.trident.TridentKafkaConfig;
import storm.trident.TridentTopology;

/**
 * All Local topology test
 * start with Storm command and java args: [TopicName] [SchemaClassName] [OutPutFiledName]
 * eg: storm jar xxx.jar activity_ja ActivityScheme act_ja
 *
 * @author senhui.li
 */
public class StormLocalTopology {

//    static {
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Can't not load MySQL driver class.");
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {


        if (args.length < 3) {
            System.out.println("Please input getTopicName name, schema class name and function field name.");
            return;
        }
        String topic = args[0];
        String className = args[1];
        String spoutFieldName = args[2];
        Class<?> clazz = null;
        KryoScheme<?> scheme = null;

        try {
            clazz = Class.forName("so.sao.analytics.sdk.storm.events." + className);
            scheme = (KryoScheme<?>) clazz.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

        BrokerHosts zk = new ZkHosts("test1.office.sao.so");
        Config stormConf = new Config();
        stormConf.put(Config.TOPOLOGY_DEBUG, false);
        stormConf.put(Config.TOPOLOGY_TRIDENT_BATCH_EMIT_INTERVAL_MILLIS, 1000 * 5);
        stormConf.put(Config.TOPOLOGY_WORKERS, 1);
        stormConf.put(Config.TOPOLOGY_MESSAGE_TIMEOUT_SECS, 5);
        stormConf.put(Config.TOPOLOGY_TASKS, 1);
        // add mysql configure
//        stormConf.put("db.url", "jdbc:mysql://test1.office.sao.so:3306/stress_test");
//        stormConf.put("db.user", "root");
//        stormConf.put("db.pswd", "123456");

        TridentKafkaConfig actSpoutConf = new TridentKafkaConfig(zk, topic);
        actSpoutConf.fetchSizeBytes = 5 * 1024 * 1024;
        actSpoutConf.bufferSizeBytes = 5 * 1024 * 1024;
        actSpoutConf.scheme = new SchemeAsMultiScheme(scheme);
        actSpoutConf.startOffsetTime = kafka.api.OffsetRequest.LatestTime();

        TridentTopology topology = new TridentTopology();
        TransactionalTridentKafkaSpout actSpout = new TransactionalTridentKafkaSpout(actSpoutConf);

        topology.newStream(topic, actSpout)
                .parallelismHint(1)
                .shuffle()
                .each(new Fields(spoutFieldName), new PrintFunction(), new Fields());

        // store the scan event into MySQL
//        topology.newStream(topic, actSpout)
//                .parallelismHint(1).shuffle()
//                .partitionAggregate(new Fields(spoutFieldName), new StoreHonestIdAggregator(), new Fields());

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(topic + "Topology", stormConf, topology.build());
    }
}
