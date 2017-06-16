package com.zeus.comment.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zeus.comment.bean.UserVo;
import com.zeus.comment.mapper.CommentsMapper;
import com.zeus.comment.pojo.Comments;
import com.zeus.common.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class CommentsService {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentsMapper commentsMapper;
    @Value("${TAOTAO_USERINFO_URL}")
    private String TAOTAO_USERINFO_URL;
    @Autowired
    private ApiService apiService;

    public static final String COOKIE_NAME = "TT_TOKEN";
    
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 保存商品评价信息
     * 
     * @param comments
     */
    public void saveComments(Long itemId, Comments comments, HttpServletRequest request, String orderId) {
        // 补充对象中的缺少字段，然后保存到表中
        // 设置评价时间
        comments.setCreatedTime(new Date());
        // 设置评价状态: 0:未评价，1：评价
        comments.setStatus(1);
        // 去cookie中取用户信息
        UserVo userVo = null;
        /*String token = CookieUtils.getCookieValue(request, COOKIE_NAME);
        if (StringUtils.isNotEmpty(token)) {
            User user = this.userService.queryUserByToken(token);
            if (null == user) {
                // 已经处于登录状态
                //用userId去用户信息系统中调用用户个人信息，保存到表格中doget()
                //
                String url = TAOTAO_USERINFO_URL+"/service/userInfo/"+user.getId();
                try {
                    String userData = apiService.doGet(url);
                    if(userData!=null){
                        userVo = MAPPER.readValue(userData, UserVo.class);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/
        //没有接口提供数据，先构造一个虚假的UserVo对象
        userVo = new UserVo();
        userVo.setNickName("小明LoveBaby");
        userVo.setHeadImage("http://image.taotao.com/images/2015/11/25/2015112509391696906965.jpg");
        userVo.setProvince("江苏");
        // 设置用户相关信息,昵称，头像，省份,等级（int类型的，应该从一个数据字典的表中查询对应的等级Id显示什么样的会员标志颜色和会员名称）
        comments.setNickname(userVo.getNickName());
        comments.setUserImage(userVo.getHeadImage());
        comments.setUserProvince(userVo.getProvince());
        
        comments.setOrderId(orderId);
        // 设置商品信息（暂时无法设置）
        comments.setUsefulVoteCount(0);
        comments.setUselessVoteCount(0);
        comments.setUserLevelId(60);
        comments.setProductId(itemId.toString());
        comments.setProductColor("粉色");
        comments.setProductSize("iPhone6S");
        comments.setUserLevelColor("#08800");
        comments.setUserLevelName("白金会员");
        this.commentsMapper.insertSelective(comments);
    }

}
