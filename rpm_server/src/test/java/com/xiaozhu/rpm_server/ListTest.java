package com.xiaozhu.rpm_server;/**
 * @Auther: Administrator
 * @Date: 2018/10/30 14:33
 * @Description:
 */

import com.xioazhu.rpccommon.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 *@ClassName ListTest
 *@Description TODO
 *@Author zhusm
 *@Date 2018/10/30 14:33    
 *@Version 1.0
 */
public class ListTest {


    @Test
    public void test(){
        User user1 = new User();
        user1.setId(1L);
        user1.setAge(12);
        User user2 = new User();
        user2.setId(1L);
        user2.setAge(13);
        User user3 = new User();
        user3.setId(2L);
        user3.setAge(14);
        User user4 = new User();
        user4.setId(2L);
        user4.setAge(15);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        List<User> users2 = new ArrayList<>();
        System.out.println(users);
        for (User fuyouIR: users) {
            boolean state = false;
            for (User newFuYouIR:users2) {
                if (newFuYouIR.getId()==fuyouIR.getId()){
                    int base64 = newFuYouIR.getAge();
                    base64 += fuyouIR.getAge();
                    newFuYouIR.setAge(base64);
                    state = true;
                }
            }
            if (!state){
                users2.add(fuyouIR);
            }
        }
        System.out.println(users2);
    }
}
