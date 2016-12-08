package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Tag Info Property
 *
 * @author senhui.li
 */
public class TagInfo implements CheckProperty {

    /**
     * Honest ID
     */
    private String honestId;
    /**
     * Sequence Number
     */
    private String sequenceNumber;
    /**
     * Batch ID
     */
    private String batchId;
    /**
     * Distributor ID
     */
    private int distributorId;

    /**
     * Order Number
     */
    private String orderNumber;
    /**
     * Sole Distributor ID
     */
    private long soleDistributorId;

    public static TagInfo createInstance() {
        return new TagInfo();
    }

    @Override
    public void validate() throws PropertyInvalidException {
        if (honestId == null || honestId.isEmpty() || sequenceNumber == null || sequenceNumber.isEmpty()) {
            throw new PropertyInvalidException("TagInfo property [honestId] and [sequenceNumber] can't be NULL or empty.");
        }
    }

    public void setTagInfo(String honestId, String batchId, String seqNumber, int disId, String orderNum, long soleDisId) {
        setHonestId(honestId);
        setBatchId(batchId);
        setSequenceNumber(seqNumber);
        setDistributorId(disId);
        setOrderNumber(orderNum);
        setSoleDistributorId(soleDisId);
    }

    public String getHonestId() {
        return honestId;
    }

    public void setHonestId(String honestId) {
        this.honestId = honestId;
    }

    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public int getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(int distributorId) {
        this.distributorId = distributorId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getSoleDistributorId() {
        return soleDistributorId;
    }

    public void setSoleDistributorId(long soleDistributorId) {
        this.soleDistributorId = soleDistributorId;
    }

    @Override
    public String toString() {
        return ", hid='" + honestId + '\'' +
                ", num='" + (sequenceNumber==null ? "" : sequenceNumber) + '\'' +
                ", bid='" + (batchId==null ? "" : batchId) + '\'' +
                ", did=" + distributorId +
                ", orn='" + (orderNumber==null ? "" : orderNumber) + '\'' +
                ", sdid=" + soleDistributorId;
    }
}
