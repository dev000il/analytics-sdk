package so.sao.analytics.sdk.common.model.property;

import so.sao.analytics.sdk.common.model.CheckProperty;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

import java.util.HashMap;
import java.util.Map;

/**
 * Win Rewards Property
 *
 * @author senhui.li
 */
public class WinRewards implements CheckProperty {

    /**
     * User win log ID
     */
    private long winnerId;
    /**
     * Reward ID
     */
    private int rewardId;
    /**
     * Reward Type
     */
    private String rewardType;
    /**
     * Rewards Awarded
     */
    private Map<String, String> rewardAdwarded = new HashMap<>();
    /**
     * Rewards Amount
     */
    private String rewardAmount;

    public static WinRewards createInstance() {
        return new WinRewards();
    }

    @Override
    public void validate() throws PropertyInvalidException {
        // the rewards not need valid when enter lottery
        // case 1: there will send not win rewards data.
        // case 2: there had a type just win basic points that means not reward info.
    }

    public void setWinRewards(int rewardId, int rewardType, String rewardAmount, Map<String, String> rewardsAwarded) {
        setRewardId(rewardId);
        setRewardType(Integer.toString(rewardType));
        setRewardAmount(rewardAmount);
        setRewardAdwarded(rewardsAwarded);
    }

    public void setWinRewards(long winnerId, int rewardId, int rewardType, String rewardAmount, Map<String, String> rewardsAwarded) {
        setWinnerId(winnerId);
        setRewardId(rewardId);
        setRewardType(Integer.toString(rewardType));
        setRewardAmount(rewardAmount);
        setRewardAdwarded(rewardsAwarded);
    }

    public long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(long winnerId) {
        this.winnerId = winnerId;
    }

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void setRewardType(String rewardType) {
        this.rewardType = rewardType;
    }

    public Map<String, String> getRewardAdwarded() {
        return rewardAdwarded;
    }

    public void setRewardAdwarded(Map<String, String> rewardAdwarded) {
        if (rewardAdwarded != null && !rewardAdwarded.isEmpty()) {
            this.rewardAdwarded.putAll(rewardAdwarded);
        }
    }

    public void appendRewardAdwarded(String key, String value) {
        this.rewardAdwarded.put(key, value);
    }

    public String getRewardAdwarded(String key) {
        return this.rewardAdwarded.get(key);
    }

    public String getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(String rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    @Override
    public String toString() {
        return  ", wid=" + winnerId +
                ", rid=" + rewardId +
                ", rt='" + rewardType + '\'' +
                ", ra=" + rewardAmount +
                ", rwds='" + rewardAdwarded + '\'';
    }
}
