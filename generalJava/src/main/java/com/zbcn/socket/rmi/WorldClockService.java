package com.zbcn.socket.rmi;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 *  世界时钟
 *  <br/>
 *  @author zbcn8
 *  @since  2020/9/30 15:11
 */
public class WorldClockService implements WorldClock {
    @Override
    public LocalDateTime getLocalDateTime(String zoneId) throws RemoteException {
        return LocalDateTime.now(ZoneId.of(zoneId)).withNano(0);
    }
}
