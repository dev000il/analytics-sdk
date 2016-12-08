package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Product Info Property
 *
 * @author senhui.li
 */
public class ProductInfo implements CheckProperty {

    /**
     * Product ID
     */
    private int productId;
    /**
     * Spec ID
     */
    private String specId;

    public static ProductInfo createInstance() {
        return new ProductInfo();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setProductInfo(String specId, int prodId) {
        setSpecId(specId);
        setProductId(prodId);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    @Override
    public String toString() {
        return ", pid=" + productId +
                ", sid='" + (specId==null ? "" : specId) + '\'';
    }
}
