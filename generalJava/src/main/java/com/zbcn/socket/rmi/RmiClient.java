package com.zbcn.socket.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDateTime;

/**
 *  rmi 客户端: 使用时,需要将 接口和 客户端复制到 对应的项目.例如 demos 项目下的 com.zbcn.socket.rmi.RmiClient
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/30 15:19
 */
public class RmiClient {

    public static void main(String[] args) {
        try {
            // 连接到服务器localhost，端口1099:
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            // 查找名称为"WorldClock"的服务并强制转型为WorldClock接口:
            WorldClock worldClock = (WorldClock) registry.lookup("WorldClock");
            // 正常调用接口方法:
            LocalDateTime now = worldClock.getLocalDateTime("Asia/Shanghai");
            // 打印调用结果:
            System.out.println(now);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
