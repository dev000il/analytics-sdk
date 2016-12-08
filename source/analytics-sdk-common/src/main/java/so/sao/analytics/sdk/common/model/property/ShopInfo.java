package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Shop information
 *
 * @author senhui.li
 */
public class ShopInfo implements CheckProperty {

    private long shopId;

    public static ShopInfo createInstance() {
        return new ShopInfo();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public long getShopId() {
        return shopId;
    }

    public void setShopId(long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return ", shi=" + shopId;
    }
}
