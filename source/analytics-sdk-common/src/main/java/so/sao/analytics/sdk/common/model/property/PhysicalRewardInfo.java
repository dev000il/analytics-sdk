package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Physical Reward Info Property
 *
 * @author senhui.li
 */
public class PhysicalRewardInfo implements CheckProperty {

    /**
     * Shipping Recipient Name
     */
    private String shipRecipientName;
    /**
     * Shipping Phone Number
     */
    private String shipPhoneNumber;
    /**
     * Shipping Province
     */
    private String shipProvince;
    /**
     * Shipping City
     */
    private String shipCity;
    /**
     * Shipping Address
     */
    private String shipAddress;
    /**
     * Shipping Identity Card
     */
    private String shipIdCard;

    public static PhysicalRewardInfo createInstance() {
        return new PhysicalRewardInfo();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setPhysicalRewardInfo(String shipRecipientName, String shipPhoneNum, String shipProvince, String shipCity,
            String shipAddress, String shipIdCard) {
        setShipRecipientName(shipRecipientName);
        setShipPhoneNumber(shipPhoneNum);
        setShipProvince(shipProvince);
        setShipCity(shipCity);
        setShipAddress(shipAddress);
        setShipIdCard(shipIdCard);
    }

    public String getShipRecipientName() {
        return shipRecipientName;
    }

    public void setShipRecipientName(String shipRecipientName) {
        this.shipRecipientName = shipRecipientName;
    }

    public String getShipPhoneNumber() {
        return shipPhoneNumber;
    }

    public void setShipPhoneNumber(String shipPhoneNumber) {
        this.shipPhoneNumber = shipPhoneNumber;
    }

    public String getShipProvince() {
        return shipProvince;
    }

    public void setShipProvince(String shipProvince) {
        this.shipProvince = shipProvince;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipIdCard() {
        return shipIdCard;
    }

    public void setShipIdCard(String shipIdCard) {
        this.shipIdCard = shipIdCard;
    }

    @Override
    public String toString() {
        return ", srn='" + (shipRecipientName==null ? "" : shipRecipientName) + '\'' +
                ", spn='" + (shipPhoneNumber==null ? "" : shipPhoneNumber) + '\'' +
                ", sp='" + (shipProvince==null ? "" : shipProvince) + '\'' +
                ", sc='" + (shipCity==null ? "" : shipCity) + '\'' +
                ", sa='" + (shipAddress==null ? "" : shipAddress) + '\'' +
                ", sic='" + (shipIdCard==null ? "" : shipIdCard) + '\'';
    }
}
