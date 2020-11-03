package com.br.tvlicai.firstchinanet.app.controller;

import java.io.Serializable;
import java.util.List;

public class UserPersonalUIBean  implements Serializable {

    private static final long serialVersionUID = -6516267650487782076L;

    //用户Aid
    private String userAid;
    //用户id
    private String userId;

    private String userType;//用户类型

    //用户名称
    private String userName;
    //用户昵称
    private String nickName;
    //联系电话
    private String phone;
    //联系邮箱
    private String contactEmail;
    //性别
    private String sex;
    //所在城市
    private String location;
    //城市名称
    private String locationName;
    //详细地址
    private String addressDetail;
    //删除标识
    private String deleteType;
    //已加入的群体用户AId
    private List<String> userAidList;
    //驾驶证标记
    private boolean driver_flag;
    //角色类型
    private String roleType;
    //角色名称
    private String roleName;
    //资质
    private List<UsrQualificationUIBean>  qualificationUIBeanList;
    //头像
    private String header_url;
    //平台审核状态
    private String mallApproveStsCd;
    //政府审核状态
    private String govApproveStsCd;
    //商城id
    private String mallId;
    //平台
    private String platformId;
    //在其他群体用户下的权限
    private String otherRoleIds;
    //当前所在群体的Aid
    private String groupAid;
    //省级平台查询数据标识  true:省级平台查询
    private boolean govQueryFlag;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getGroupAid() {
        return groupAid;
    }

    public void setGroupAid(String groupAid) {
        this.groupAid = groupAid;
    }

    public String getOtherRoleIds() {
        return otherRoleIds;
    }

    public void setOtherRoleIds(String otherRoleIds) {
        this.otherRoleIds = otherRoleIds;
    }

    public String getUserAid() {
        return userAid;
    }

    public void setUserAid(String userAid) {
        this.userAid = userAid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public List<String> getUserAidList() {
        return userAidList;
    }

    public void setUserAidList(List<String> userAidList) {
        this.userAidList = userAidList;
    }

    public boolean isDriver_flag() {
        return driver_flag;
    }

    public void setDriver_flag(boolean driver_flag) {
        this.driver_flag = driver_flag;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<UsrQualificationUIBean> getQualificationUIBeanList() {
        return qualificationUIBeanList;
    }

    public void setQualificationUIBeanList(List<UsrQualificationUIBean> qualificationUIBeanList) {
        this.qualificationUIBeanList = qualificationUIBeanList;
    }

    public String getHeader_url() {
        return header_url;
    }

    public void setHeader_url(String header_url) {
        this.header_url = header_url;
    }

    public String getMallApproveStsCd() {
        return mallApproveStsCd;
    }

    public void setMallApproveStsCd(String mallApproveStsCd) {
        this.mallApproveStsCd = mallApproveStsCd;
    }

    public String getGovApproveStsCd() {
        return govApproveStsCd;
    }

    public void setGovApproveStsCd(String govApproveStsCd) {
        this.govApproveStsCd = govApproveStsCd;
    }

    public String getMallId() {
        return mallId;
    }

    public void setMallId(String mallId) {
        this.mallId = mallId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public boolean isGovQueryFlag() {
        return govQueryFlag;
    }

    public void setGovQueryFlag(boolean govQueryFlag) {
        this.govQueryFlag = govQueryFlag;
    }
}