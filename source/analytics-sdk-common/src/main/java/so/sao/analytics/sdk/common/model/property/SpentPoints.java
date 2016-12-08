package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Point Lottery or Mall exchange spent point
 *
 * @author senhui.li
 */
public class SpentPoints implements CheckProperty {

    /**
     * Debit Point Type ID
     */
    private int pointType;
    /**
     * Debit Point Detail
     */
    private int pointDetail;

    public static SpentPoints createInstance() {
        return new SpentPoints();
    }

    @Override
    public void validate() throws PropertyInvalidException {
        if (pointType == 0 || pointType < -1 || pointDetail <= 0) {
            throw new PropertyInvalidException("SpentPoints property [pointType] and [pointDetail] can't be less than or equals to zero.");
        }
    }

    public void setSpentPoints(int pointType, int pointDetail) {
        this.pointType = pointType;
        this.pointDetail = pointDetail;
    }

    public int getPointType() {
        return pointType;
    }

    public void setPointType(int pointType) {
        this.pointType = pointType;
    }

    public int getPointDetail() {
        return pointDetail;
    }

    public void setPointDetail(int pointDetail) {
        this.pointDetail = pointDetail;
    }

    @Override
    public String toString() {
        return ", dpt=" + pointType +
                ", dpd=" + pointDetail;
    }
}
