package cn.houtaroy.java.lab;

import cn.houtaroy.java.lab.mapper.OrderMapper;
import cn.houtaroy.java.lab.model.OrderDO;
import org.apache.shardingsphere.api.hint.HintManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author Houtaroy
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ShardingDatasourceTwoApplication.class)
public class OrderMapperTest {
  @Autowired
  private OrderMapper orderMapper;

  @Test
  public void testSelectById() { // 测试从库的负载均衡
    for (int i = 0; i < 2; i++) {
      OrderDO order = orderMapper.selectById(1);
      System.out.println(order);
    }
  }

  @Test
  public void testSelectById02() { // 测试强制访问主库
    try (HintManager hintManager = HintManager.getInstance()) {
      // 设置强制访问主库
      hintManager.setMasterRouteOnly();
      // 执行查询
      OrderDO order = orderMapper.selectById(1);
      System.out.println(order);
    }
  }

  @Test
  public void testInsert() { // 插入
    OrderDO order = new OrderDO();
    order.setUserId(10);
    orderMapper.insert(order);
  }
}
