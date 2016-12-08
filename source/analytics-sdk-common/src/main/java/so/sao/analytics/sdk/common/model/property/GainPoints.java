package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

import java.util.HashMap;
import java.util.Map;

/**
 * Gain Points Property
 *
 * @author senhui.li
 */
public class GainPoints implements CheckProperty {

    /**
     * Point Rule ID
     */
    private int pointRuleId;
    /**
     * Points Awarded
     */
    private Map<String, Integer> pointsAwarded = new HashMap<>();

    public static GainPoints createInstance() {
        return new GainPoints();
    }

    @Override
    public void validate() throws PropertyInvalidException {
    }

    public void setGainPoints(int ruleId, Map<String, Integer> points) {
        setPointRuleId(ruleId);
        setPointsAwarded(points);
    }

    public int getPointRuleId() {
        return pointRuleId;
    }

    public void setPointRuleId(int pointRuleId) {
        this.pointRuleId = pointRuleId;
    }

    public Map<String, Integer> getPointsAwarded() {
        return pointsAwarded;
    }

    public void setPointsAwarded(Map<String, Integer> pointsAwarded) {
        if (pointsAwarded != null && !pointsAwarded.isEmpty()) {
            this.pointsAwarded.putAll(pointsAwarded);
        }
    }

    public void appendPointsAwarded(String key, Integer value) {
        this.pointsAwarded.put(key, value);
    }

    @Override
    public String toString() {
        return ", ptri=" + pointRuleId +
                ", pts=" + pointsAwarded;
    }
}
