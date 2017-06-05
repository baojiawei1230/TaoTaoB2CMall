package com.taotao.store.order.pojo;

public class Consignee {
    private Long id;// 主键字段

    private Long userId;// 用户id

    private Integer type;// 类型

    private Long provinceId;// 省id

    private Long cityId;// 市id

    private Long countryId;// 区id

    private Long townId;// 镇id

    private String name;// 收货人

    private String email;// 邮箱

    private String address;// 详细地址

    private String mobile;// 手机号码

    private String phone;// 固定电话

    private String provinceName;// 省名

    private String cityName;// 市名

    private String countryName;// 区名

    private String townName;// 镇名

    private Integer isUpdateCommonAddress;// 是否添加常用地址列表(1:要添加常用地址列表,0代表不要添加)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getTownId() {
        return townId;
    }

    public void setTownId(Long townId) {
        this.townId = townId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getIsUpdateCommonAddress() {
        return isUpdateCommonAddress;
    }

    public void setIsUpdateCommonAddress(Integer isUpdateCommonAddress) {
        this.isUpdateCommonAddress = isUpdateCommonAddress;
    }
}
