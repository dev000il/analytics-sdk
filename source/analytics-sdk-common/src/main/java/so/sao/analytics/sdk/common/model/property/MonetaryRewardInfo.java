package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Monetary Reward Info Property
 *
 * @author senhui.li
 */
public class MonetaryRewardInfo implements CheckProperty {

    /**
     * Vendor ID
     */
    private String vendorId;
    /**
     * Bank Account Number / Card
     */
    private String bankActNumber;
    /**
     * Bank Account Holder Name
     */
    private String bankActHolderName;
    /**
     * Bank Account Holder ID
     */
    private String bankActHolderId;
    /**
     * Bank Account Holder ID Type
     */
    private String bankActHolderIdType;
    /**
     * Bank Code
     */
    private String bankCode;

    public static MonetaryRewardInfo createInstance() {
        return new MonetaryRewardInfo();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setMonetaryRewardInfo(String vendorId, String bankActNumber, String bankActHolderName, String bankActHolderId,
            String bankActHolderIdType, String bankCode) {
        setVendorId(vendorId);
        setBankActNumber(bankActNumber);
        setBankActHolderName(bankActHolderName);
        setBankActHolderId(bankActHolderId);
        setBankActHolderIdType(bankActHolderIdType);
        setBankCode(bankCode);
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getBankActNumber() {
        return bankActNumber;
    }

    public void setBankActNumber(String bankActNumber) {
        this.bankActNumber = bankActNumber;
    }

    public String getBankActHolderName() {
        return bankActHolderName;
    }

    public void setBankActHolderName(String bankActHolderName) {
        this.bankActHolderName = bankActHolderName;
    }

    public String getBankActHolderId() {
        return bankActHolderId;
    }

    public void setBankActHolderId(String bankActHolderId) {
        this.bankActHolderId = bankActHolderId;
    }

    public String getBankActHolderIdType() {
        return bankActHolderIdType;
    }

    public void setBankActHolderIdType(String bankActHolderIdType) {
        this.bankActHolderIdType = bankActHolderIdType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String toString() {
        return ", vid='" + (vendorId==null ? "" : vendorId) + '\'' +
                ", bkn='" + (bankActNumber==null ? "" : bankActNumber) + '\'' +
                ", bkhn='" + (bankActHolderName==null ? "" : bankActHolderName) + '\'' +
                ", bkhi='" + (bankActHolderId==null ? "" : bankActHolderId) + '\'' +
                ", bkht='" + (bankActHolderIdType==null ? "" : bankActHolderIdType) + '\'' +
                ", bkc='" + (bankCode==null ? "" : bankCode) + '\'';
    }
}
