package com.zbcn.socket.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * rmi 服务提供者
 */
public class RmiServer {

    public static void main(String[] args) {
        System.out.println("create World clock remote service...");
        // 实例化一个WorldClock:
        WorldClock worldClock = new WorldClockService();
        // 将此服务转换为远程服务接口:
        try {
            WorldClock skeleton = (WorldClock) UnicastRemoteObject.exportObject(worldClock, 0);
            // 将RMI服务注册到1099端口:
            Registry registry = LocateRegistry.createRegistry(1099);
            // 注册此服务，服务名为"WorldClock":
            registry.rebind("WorldClock", skeleton);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
