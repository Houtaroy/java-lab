package cn.houtaroy.java.lab.service;

import cn.houtaroy.java.lab.mapper.OrderMapper;
import cn.houtaroy.java.lab.model.OrderDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Houtaroy
 */
@Service
public class OrderService {
  @Autowired
  private OrderMapper orderMapper;

  @Transactional(rollbackFor = Exception.class)
  public void add(OrderDO order) {
    // <1.1> 这里先假模假样的读取一下。读取从库
    OrderDO exists = orderMapper.selectById(1);
    System.out.println(exists);

    // <1.2> 插入订单
    orderMapper.insert(order);

    // <1.3> 这里先假模假样的读取一下。读取主库
    exists = orderMapper.selectById(1);
    System.out.println(exists);
  }

  public OrderDO findById(Integer id) {
    return orderMapper.selectById(id);
  }
}
