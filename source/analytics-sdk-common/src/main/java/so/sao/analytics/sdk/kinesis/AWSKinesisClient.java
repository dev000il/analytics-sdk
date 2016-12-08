package so.sao.analytics.sdk.kinesis;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.RegionUtils;
import com.amazonaws.services.kinesis.AmazonKinesis;
import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesis.model.PutRecordRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequest;
import com.amazonaws.services.kinesis.model.PutRecordsRequestEntry;
import com.amazonaws.services.kinesis.model.PutRecordsResult;
import com.amazonaws.services.kinesis.model.PutRecordsResultEntry;
import so.sao.analytics.sdk.util.ConfigReader;


/**
 * 
 * @Description : Kinesis tools class
 * @author chengjian.liang,senhui.li
 * @2016年10月12日
 */
public class AWSKinesisClient {
	
    //Create KinesisClient
    public static AmazonKinesis getAmazonKinesisClient() {

        ConfigReader configReader = ConfigReader.createOrGetInstance(ConfigReader.AWS_DEF_CONFIG_PATH);

        try {
            AWSCredentialsProvider credentialsProvider = CestbonCredentialsProvider.getCredentialsProvider();
            AmazonKinesis kinesisClient = new AmazonKinesisClient(credentialsProvider);
            Region region = RegionUtils.getRegion(configReader.getAWSRegionName());
            kinesisClient.setRegion(region);
            return kinesisClient;
        } catch (Exception e){
            throw new RuntimeException("Get AWS Kinesis client failed.",e);
        }
    }
    
    //Create PutRecoredRequest
    public static PutRecordRequest getPutRecordRequest (){
        
        PutRecordRequest putRecordRequest  = new PutRecordRequest();
        
        return putRecordRequest;
        
    }
    
    //Create PutRecoredsRequest
    public static PutRecordsRequest getPutRecordsRequest (){
        
        PutRecordsRequest putRecordsRequest  = new PutRecordsRequest();
        
        return putRecordsRequest;
        
    }
    
    //Create PutRecordsRequestEntry
    public static PutRecordsRequestEntry  getPutRecordsRequestEntry(ByteBuffer data,String partitionKey){
        
        PutRecordsRequestEntry putRecordsRequestEntry  = new PutRecordsRequestEntry();
        putRecordsRequestEntry.setData(data);
        putRecordsRequestEntry.setPartitionKey(partitionKey);
        
        return putRecordsRequestEntry;
    }
    
    //send putRecord to kinesis
    public static void sendKinesisPutRecord(AmazonKinesis amazonKinesis,PutRecordRequest putRecordRequest){
        
        amazonKinesis.putRecord(putRecordRequest);
    }
    
    //set putRecords to kinesis
    public static void sendKinesisPutRecords(AmazonKinesis amazonKinesis,PutRecordsRequest putRecordsRequest,
            List<PutRecordsRequestEntry> putRecordsRequestEntryList, String streamName){
        
        putRecordsRequest.setStreamName(streamName);
        putRecordsRequest.setRecords(putRecordsRequestEntryList);
        PutRecordsResult putRecordsResult = amazonKinesis.putRecords(putRecordsRequest);
        
        //Failure process
        FaildPutRecordsRequestEntry(amazonKinesis, putRecordsRequest, putRecordsRequestEntryList, putRecordsResult,streamName);
    }
    
    //PutRecrodsRequest send failed processing
    public static  void FaildPutRecordsRequestEntry(AmazonKinesis amazonKinesis,PutRecordsRequest putRecordsRequest,
            List<PutRecordsRequestEntry> putRecordsRequestEntryList, PutRecordsResult putRecordsResult, String streamName){
        
        while (putRecordsResult.getFailedRecordCount() > 0) {
            final List<PutRecordsRequestEntry> failedRecordsList = new ArrayList<>();
            final List<PutRecordsResultEntry> putRecordsResultEntryList = putRecordsResult.getRecords();
            
            for (int i = 0; i < putRecordsResultEntryList.size(); i++) {
                final PutRecordsRequestEntry putRecordRequestEntry = putRecordsRequestEntryList.get(i);
                final PutRecordsResultEntry putRecordsResultEntry = putRecordsResultEntryList.get(i);
                if (putRecordsResultEntry.getErrorCode() != null) {
                    failedRecordsList.add(putRecordRequestEntry);
                }
            }
            putRecordsRequestEntryList = failedRecordsList;
            
            sendKinesisPutRecords(amazonKinesis, putRecordsRequest, putRecordsRequestEntryList, streamName);
        }
        
    }
}
