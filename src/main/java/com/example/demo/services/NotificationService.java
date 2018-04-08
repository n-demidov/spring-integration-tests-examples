package com.example.demo.services;

import com.example.demo.dto.SyncNotificationDto;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

  public SyncNotificationDto processSyncNotification() {
    SyncNotificationDto syncNotificationDto = new SyncNotificationDto();

    syncNotificationDto.setMessage("hi");

    return syncNotificationDto;
  }

  public void processAsyncNotification() {
    SyncNotificationDto syncNotificationDto = new SyncNotificationDto();

    syncNotificationDto.setMessage("hi");
  }

}
