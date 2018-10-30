package com.owen.websocketbean;/**
 * @Auther: Administrator
 * @Date: 2018/10/30 09:42
 * @Description:
 */

import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.yeauty.annotation.*;
import org.yeauty.pojo.Session;

/**
 *@ClassName WebSocketCom
 *@Description 整合Netty的websocket
 *@Author zhusm
 *@Date 2018/10/30 9:42    
 *@Version 1.0
 */
@Slf4j
@ServerEndpoint
@Component
public class WebSocketCom {


    @OnOpen
    public void onOpen(Session session, HttpHeaders  httpHeaders){
        log.info(session.localAddress().toString());
        log.info(session.remoteAddress().toString());
        log.info("new connetion");
    }

    @OnClose
    public void onClose(Session session){
        log.info("one connetion close");
    }

    @OnError
    public void onError(Session session,Throwable throwable){
        throwable.printStackTrace();
    }


    @OnMessage
    public void OnMessage(Session session, String message){
        log.info(session.localAddress().toString());
        log.info(session.remoteAddress().toString());
        log.info(message);
        session.sendText("hello Netty");
    }

    @OnBinary
    public void OnBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    log.info("read idle");
                    break;
                case WRITER_IDLE:
                    log.info("write idle");
                    break;
                case ALL_IDLE:
                    log.info("all idle");
                    break;
                default:
                    break;
            }
        }
    }
}
