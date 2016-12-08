package so.sao.analytics.sdk.common.model.event;

import java.util.Date;

import so.sao.analytics.sdk.common.model.flatevent.FlatUserBasicInfo;
import so.sao.analytics.sdk.common.model.property.UserIdentifier;
import so.sao.analytics.sdk.common.model.flatevent.FlatBasicEvent;
import so.sao.analytics.sdk.exception.PropertyInvalidException;
import so.sao.analytics.sdk.util.DateUtil;

/**
 * This is special event use to save
 * user's info, like nick name, age, sex etc
 *
 * @author senhui.li
 */
public class UserBasicInfo extends BasicEvent {

    private UserIdentifier userIdentifier;

    /**
     * User's nick name
     */
    private String nickName;
    /**
     * User's sex
     */
    private int sex;
    /**
     * User's age
     */
    private int age;
    /**
     * User's born date
     */
    private Date birthdayDate;
    /**
     * User's head image
     */
    private String avatarImgUrl;
    /**
     * User's live country name
     */
    private String countryName;
    /**
     * User's live province name
     */
    private String provinceName;
    /**
     * User's live city name
     */
    private String cityName;
    /**
     * City area
     */
    private String area;
    /**
     * User's live detail address
     */
    private String detailAddr;
    /**
     * User's email address
     */
    private String emailAddr;


    public static UserBasicInfo createInstance() {
        return new UserBasicInfo();
    }

    public UserIdentifier createOrGetUserIdentifier() {
        if (this.userIdentifier == null) {
            this.userIdentifier = UserIdentifier.createInstance();
        }

        return this.userIdentifier;
    }

    @Override
    public void validate() throws PropertyInvalidException {
        super.validate();
    }

    @Override
    public FlatBasicEvent getFlat() {
        FlatUserBasicInfo flat = new FlatUserBasicInfo();
        super.setFlat(flat);

        if (userIdentifier != null) {
            flat.setU(userIdentifier.getUserId());
            flat.setPn(userIdentifier.getPhoneNumber());
            flat.setWu(userIdentifier.getWechatUserId());
            flat.setTu(userIdentifier.getTaobaoUserId());
        }

        flat.setNkn(this.nickName);
        flat.setSex(this.sex);
        flat.setAge(this.age);
        flat.setBd(this.birthdayDate);
        flat.setAiu(this.avatarImgUrl);
        flat.setCyn(this.countryName);
        flat.setPron(this.provinceName);
        flat.setCin(this.cityName);
        flat.setArea(this.area);
        flat.setDadr(this.detailAddr);
        flat.setEadr(this.emailAddr);

        return flat;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public String getAvatarImgUrl() {
        return avatarImgUrl;
    }

    public void setAvatarImgUrl(String avatarImgUrl) {
        this.avatarImgUrl = avatarImgUrl;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetailAddr() {
        return detailAddr;
    }

    public void setDetailAddr(String detailAddr) {
        this.detailAddr = detailAddr;
    }

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }

    @Override
    public String toString() {
        return "UserBasicInfo{" +
                super.toString() +
                (userIdentifier != null ? userIdentifier : "") +
                ", nkn='" + nickName + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", bd='" + (birthdayDate == null ? "" : DateUtil.getDefShortDateFormat(birthdayDate)) +
                "', aiu='" + (avatarImgUrl == null ? "" : avatarImgUrl) + '\'' +
                ", cyn='" + (countryName == null ? "" : countryName) + '\'' +
                ", pron='" + (provinceName == null ? "" : provinceName) + '\'' +
                ", cin='" + (cityName == null ? "" : cityName) + '\'' +
                ", area='" + (area == null ? "" : area) + '\'' +
                ", dadr='" + (detailAddr == null ? "" : detailAddr) + '\'' +
                ", eadr='" + (emailAddr == null ? "" : emailAddr) + '\'' +
                '}';
    }
}
