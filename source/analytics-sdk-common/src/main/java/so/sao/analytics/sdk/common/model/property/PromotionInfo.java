package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Promotion Info Property
 *
 * @author senhui.li
 */
public class PromotionInfo implements CheckProperty {

    /**
     * Promotion Rule ID
     */
    private int promotionId;
    /**
     * Promotion New User
     */
    private boolean promotionNewUser;

    public static PromotionInfo createInstance() {
        return new PromotionInfo();
    }

    @Override
    public void validate() throws PropertyInvalidException {
        if (promotionId <= 0) {
            throw new PropertyInvalidException("Promotion Info property [promotionId] can't be less than or equals to zero.");
        }
    }

    public void setPromotionInfo(int promId, boolean newUser) {
        setPromotionId(promId);
        setPromotionNewUser(newUser);
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public boolean isPromotionNewUser() {
        return promotionNewUser;
    }

    public void setPromotionNewUser(boolean promotionNewUser) {
        this.promotionNewUser = promotionNewUser;
    }

    @Override
    public String toString() {
        return ", pri=" + promotionId +
                ", pnu=" + promotionNewUser;
    }
}
