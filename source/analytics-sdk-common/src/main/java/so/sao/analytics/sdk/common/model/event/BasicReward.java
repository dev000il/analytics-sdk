package so.sao.analytics.sdk.common.model.event;

import so.sao.analytics.sdk.common.model.property.*;
import so.sao.analytics.sdk.common.model.flatevent.FlatBasicEvent;
import so.sao.analytics.sdk.common.model.flatevent.FlatBasicReward;
import so.sao.analytics.sdk.exception.PropertyInvalidException;

/**
 * Collect all rewards status
 *
 * @author senhui.li
 */
public abstract class BasicReward extends BasicEvent {

    // user identifier
    protected UserIdentifier userIdentifier;

    // Win rewards Properties
    protected WinRewards winRewards;
    // Claim rewards require Properties
    protected MonetaryRewardInfo monetaryRewardInfo;
    protected PhysicalRewardInfo physicalRewardInfo;
    protected ActionResult actionResult;

    public UserIdentifier createOrGetUserIdentifier() {
        if (this.userIdentifier == null) {
            this.userIdentifier = UserIdentifier.createInstance();
        }

        return this.userIdentifier;
    }

    public WinRewards createOrGetWinRewards() {
        if (this.winRewards == null) {
            this.winRewards = WinRewards.createInstance();
        }

        return this.winRewards;
    }

    public MonetaryRewardInfo createOrGetMonetaryRewardInfo() {
        if (this.monetaryRewardInfo == null) {
            this.monetaryRewardInfo = MonetaryRewardInfo.createInstance();
        }

        return this.monetaryRewardInfo;
    }

    public PhysicalRewardInfo createOrGetPhysicalRewardInfo() {
        if (this.physicalRewardInfo == null) {
            this.physicalRewardInfo = PhysicalRewardInfo.createInstance();
        }

        return this.physicalRewardInfo;
    }

    public ActionResult createOrGetActionResult() {
        if (this.actionResult == null) {
            this.actionResult = ActionResult.createInstance();
        }

        return this.actionResult;
    }

    @Override
    public void validate() throws PropertyInvalidException {
        super.validate();

        if (winRewards != null) {
            winRewards.validate();
        }

    }

    @Override
    public abstract FlatBasicEvent getFlat();

    protected void setFlat(FlatBasicReward flat) {
        super.setFlat(flat);

        flat.setUser(this.userIdentifier);
        flat.setWinReward(this.winRewards);
        flat.setMonetaryReward(this.monetaryRewardInfo);
        flat.setPhysicalReward(this.physicalRewardInfo);
        flat.setActionResult(this.actionResult);
    }

    @Override
    public String toString() {
        return super.toString() +
                (userIdentifier != null ? userIdentifier : "") +
                (winRewards != null ? winRewards : "") +
                (monetaryRewardInfo != null ? monetaryRewardInfo : "") +
                (physicalRewardInfo != null ? physicalRewardInfo : "") +
                (actionResult != null ? actionResult : "");
    }
}
