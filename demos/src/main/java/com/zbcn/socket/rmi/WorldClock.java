package com.zbcn.socket.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;

/**
 *  世界时钟
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/30 15:05
 */
public interface WorldClock extends Remote {

    LocalDateTime getLocalDateTime(String zoneId) throws RemoteException;
}
