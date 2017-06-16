package com.zeus.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="tb_comments")
public class Comments {

    @JsonInclude(Include.NON_NULL)
    Integer id; // 评价id
    @JsonInclude(Include.NON_NULL)
    @Transient
    String guid;
    @JsonInclude(Include.NON_NULL)
    String content; // 不错的一款手机，第二部购买",
    @JsonInclude(Include.NON_NULL)
    @Transient
    String creationTime;
    @JsonInclude(Include.NON_NULL)
    @JsonIgnore
    Date createdTime; // 2015-04-18 10:16:15",
    @JsonInclude(Include.NON_NULL)
    @Transient
    Boolean isTop; // false,
    
    @JsonIgnore
    @Transient
    Integer referenceId; // ": "1138529", ？？id
    @JsonInclude(Include.NON_NULL)
    String referenceImage; // ": "g16/M00/0C/07/rBEbRlOIP_MIAAAAAAFWXorXF4YAACYlQLZhz0AAVZ2428.jpg",
                           // ？？图片
    @JsonInclude(Include.NON_NULL)
    @Transient
                           String referenceName; // ": "三星 Galaxy Grand 2 (G7108V) 白色 移动4G手机", ？？名称
    @JsonIgnore
    @Transient
    Date referenceTime; // ": "2015-04-14 10:22:44",时间            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    String referenceType; // ": "Product",     类型            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer referenceTypeId; // ": 0,   类型id            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer firstCategory; // ": 9987,   一次分类           "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer secondCategory; // ": 653,  二次分类            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer thirdCategory;// ": 655,  三次分类             "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer replyCount;// ": 0,     答复数量            "
    @JsonInclude(Include.NON_NULL)
    Integer score;// ": 5,   分数            "
    @JsonInclude(Include.NON_NULL)
    Integer status; // ": 1,    状态            "
    @JsonInclude(Include.NON_NULL)
    Integer usefulVoteCount; // ": 0,  有用的投票个数            "
    @JsonInclude(Include.NON_NULL)
    Integer uselessVoteCount; // ": 0,   无效的投票个数            "
    @JsonInclude(Include.NON_NULL)
    String userImage; // ": "storage.jd.com/i.imageUpload/35323039393731302d353935343531333031343135383734363339343933_sma.jpg",
                      // 用户图片 "
    @JsonInclude(Include.NON_NULL)
    @Transient
                      String userImageUrl; // ": "storage.jd.com/i.imageUpload/35323039393731302d353935343531333031343135383734363339343933_sma.jpg",
                         // 用户图片地址 "
    @JsonInclude(Include.NON_NULL)
    @JsonIgnore
                         Integer userLevelId; // ": "62",    用户等级id            "
    @JsonInclude(Include.NON_NULL)
    String userProvince; // ": "山西",     省份            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer viewCount; // ": 0,  显示个数            "
    
    @JsonIgnore
    String orderId; // ": 0,  订单id            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Boolean isReplyGrade; // ": false,   是否答复等级            "
    @JsonInclude(Include.NON_NULL)
    String nickname; // ": "宁***1",   用户昵称            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer userClient; // ": 0,   用户客户端            "
    
    
    @JsonIgnore
    String tags; // "  :   存储该评价中的提交的标签的id,以逗号分隔
    @JsonInclude(Include.NON_NULL)
    String productColor; // ": "釉白",  商品颜色            "
    @JsonInclude(Include.NON_NULL)
    String productSize; // ": "移动4G",   商品运营商            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer integral; // ": -30,  积分            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Integer anonymousFlag; // ": 1,   匿名标记            "
    @JsonInclude(Include.NON_NULL)
    String userLevelName; // ": "金牌会员",  用户等级名称            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Boolean recommend; // ": false,  建议            "
    @JsonInclude(Include.NON_NULL)
    String userLevelColor; // ": "#088000",    用户等级颜色            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    String userClientShow; // ": "",    用户客户端显示            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    Boolean isMobile; // ": false是否为手机客户端            "
    @JsonInclude(Include.NON_NULL)
    @Transient
    List<CommentTags> commentTags;
    
    @JsonIgnore
    String productId;
    

    public String getProductId() {
        return productId;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public List<CommentTags> getCommentTags() {
        return commentTags;
    }

    public void setCommentTags(List<CommentTags> commentTags) {
        this.commentTags = commentTags;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean isTop) {
        this.isTop = isTop;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public String getReferenceImage() {
        return referenceImage;
    }

    public void setReferenceImage(String referenceImage) {
        this.referenceImage = referenceImage;
    }

    public String getReferenceName() {
        return referenceName;
    }

    public void setReferenceName(String referenceName) {
        this.referenceName = referenceName;
    }

    public Date getReferenceTime() {
        return referenceTime;
    }

    public void setReferenceTime(Date referenceTime) {
        this.referenceTime = referenceTime;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public Integer getReferenceTypeId() {
        return referenceTypeId;
    }

    public void setReferenceTypeId(Integer referenceTypeId) {
        this.referenceTypeId = referenceTypeId;
    }

    public Integer getFirstCategory() {
        return firstCategory;
    }

    public void setFirstCategory(Integer firstCategory) {
        this.firstCategory = firstCategory;
    }

    public Integer getSecondCategory() {
        return secondCategory;
    }

    public void setSecondCategory(Integer secondCategory) {
        this.secondCategory = secondCategory;
    }

    public Integer getThirdCategory() {
        return thirdCategory;
    }

    public void setThirdCategory(Integer thirdCategory) {
        this.thirdCategory = thirdCategory;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUsefulVoteCount() {
        return usefulVoteCount;
    }

    public void setUsefulVoteCount(Integer usefulVoteCount) {
        this.usefulVoteCount = usefulVoteCount;
    }

    public Integer getUselessVoteCount() {
        return uselessVoteCount;
    }

    public void setUselessVoteCount(Integer uselessVoteCount) {
        this.uselessVoteCount = uselessVoteCount;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public Integer getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(Integer userLevelId) {
        this.userLevelId = userLevelId;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getIsReplyGrade() {
        return isReplyGrade;
    }

    public void setIsReplyGrade(Boolean isReplyGrade) {
        this.isReplyGrade = isReplyGrade;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getUserClient() {
        return userClient;
    }

    public void setUserClient(Integer userClient) {
        this.userClient = userClient;
    }


    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getProductColor() {
        return productColor;
    }

    public void setProductColor(String productColor) {
        this.productColor = productColor;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getAnonymousFlag() {
        return anonymousFlag;
    }

    public void setAnonymousFlag(Integer anonymousFlag) {
        this.anonymousFlag = anonymousFlag;
    }

    public String getUserLevelName() {
        return userLevelName;
    }

    public void setUserLevelName(String userLevelName) {
        this.userLevelName = userLevelName;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public String getUserLevelColor() {
        return userLevelColor;
    }

    public void setUserLevelColor(String userLevelColor) {
        this.userLevelColor = userLevelColor;
    }

    public String getUserClientShow() {
        return userClientShow;
    }

    public void setUserClientShow(String userClientShow) {
        this.userClientShow = userClientShow;
    }

    public Boolean getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(Boolean isMobile) {
        this.isMobile = isMobile;
    }
    
}
