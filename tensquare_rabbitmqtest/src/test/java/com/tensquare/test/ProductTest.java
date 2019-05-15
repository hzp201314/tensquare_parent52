package com.tensquare.test;

import com.tensquare.rabbit.RabbitApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitApplication.class)
public class ProductTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 直接测试模式
     */
    @Test
    public void sendMsg1(){
        rabbitTemplate.convertAndSend("itcast","直接测试模式");
    }

    /**
     * 分裂模式测试
     */
    @Test
    public void sendMsg2(){
        rabbitTemplate.convertAndSend("chuanzi","","分裂模式测试");
    }

    /**
     * 主题模式测试
     */
    @Test
    public void sendMsg3(){
        rabbitTemplate.convertAndSend("topic84","good.abc","主题模式测试good.#");
    }

    /**
     * 主题模式测试
     */
    @Test
    public void sendMsg4(){
        rabbitTemplate.convertAndSend("topic84","good.abc.log","主题模式测试good.#/#.log");
    }

    /**
     * 主题模式测试
     */
    @Test
    public void sendMsg5(){
        rabbitTemplate.convertAndSend("topic84","abc.log","主题模式测试#.log");
    }


    /**
     * 主题模式测试
     */
    @Test
    public void sendMsg6(){
        rabbitTemplate.convertAndSend("topic84","good.log","主题模式测试good.log");
    }
}
