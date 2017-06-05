package com.taotao.ucenter.service;

import com.taotao.ucenter.mapper.OrderMapper;
import com.taotao.ucenter.pojo.Order;
import com.taotao.ucenter.threadlocal.UserThreadLocal;
import com.taotao.ucenter.vo.OrderSelectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class MyOrderService extends BaseService<Order> {
    @Autowired
    private OrderMapper orderMapper;

//    /**
//     * 使用通用mapper单表查询
//     * @param byDate 时间筛选条件 三个月内:1，今年内：2， 年份减三得到的年份以上:3， 其他为具体的时间
//     * @param byStatus 订单状态条件  全部状态：4096，等待付款：1，等待自提：32，等待收货：128，已完成：1024，已取消：-1
//     * @return
//     */
//    public List<Order> queryOrderPageByDateAndStatus(String byDate, String byStatus) {
//        Example example = new Example(Order.class);
//        Criteria criteria = example.createCriteria();
//        //TODO 还需要加上用户id条件
//        dateCriteria(criteria,byDate);
//        statusCriteria(criteria,byStatus);
//        return  orderMapper.selectByExample(example);
//    }
    
    /**
     * 自定义多表查询
     * @param byDate
     * @param byStatus
     * @return
     */
    public List<Order> queryOrderByUserIdAndCreateDateAndStatus(int page, int rows, String byDate, String byStatus){
        OrderSelectVo osVo = new OrderSelectVo();
        osVo.setUserId(UserThreadLocal.get().getId());
        dateCriteria(osVo,byDate);
        statusCriteria(osVo,byStatus);
        return orderMapper.queryOrderByUserIdAndCreateDateAndStatus(osVo);
    }

    /**
     * 处理时间筛选条件
     * @param date 时间筛选条件 三个月内:1，今年内：2， 年份减三得到的年份以上:3， 其他为具体的时间
     */
    private void dateCriteria(OrderSelectVo vo,String _date){
        int statusOrYear = Integer.valueOf(_date);
        
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatYY = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormatMM = new SimpleDateFormat("MM");
        
        //获取今天的年份
        int dateYear = Integer.valueOf(dateFormatYY.format(new Date()));
        //获取今天的月份
        int dateMonth = Integer.valueOf(dateFormatMM.format(new Date()));
        
        Date date = null;
        
        switch(statusOrYear){
        case 1://查询3个月内的        <= 创建时间
          //Date 的月份是1~12  calendar的月份0~11 所以多减一个月
            float fi = Float.valueOf(dateMonth) - 4;
            if(fi < 0){
                calendar.set(dateYear, Math.round(11 + fi), 1,0,0,0);
            }else{
                calendar.set(dateYear, Math.round(fi), 1,0,0,0);
            }
            date = calendar.getTime();
            vo.setStartDate(date);
            break;
        case 2://查询今年的          <= 创建时间
            calendar.set(dateYear, 0, 1,0,0,0);
            date = calendar.getTime();
            vo.setStartDate(date);
            break;
        case 3://查询今年-3 年以上的            > 创建时间
            calendar.set(statusOrYear, 0, 1,0,0,0);
            date = calendar.getTime();
            vo.setEndDate(date);
            break;
        default: //查询  setStartDate <= 创建时间 and setEndDate > 创建时间
            calendar.set(statusOrYear + 1, 0, 1,0,0,0);
            date = calendar.getTime();
            vo.setEndDate(date);
            
            calendar.set(statusOrYear, 0, 1,0,0,0);
            date = calendar.getTime();
            vo.setStartDate(date);
            break;
        }
    }

    /**
     * 处理订单状态条件
     * @param status 订单状态条件  全部状态：4096，等待付款：1，等待自提：32，等待收货：128，已完成：1024，已取消：-1
     */
    private void statusCriteria(OrderSelectVo vo,String _status){
        switch(_status){
        case "1":
            vo.setOrderStatus(1);
            break;
        case "32":
        case "128":
            vo.setOrderStatus(4);
            break;
        case "1024":
            vo.setOrderStatus(5);
            break;
        case "-1":
            vo.setOrderStatus(6);
            break;
        case "4096":
        default:
            vo.setOrderStatus(null);//传空 不限制状态条件
            break;
        }
    }
}
