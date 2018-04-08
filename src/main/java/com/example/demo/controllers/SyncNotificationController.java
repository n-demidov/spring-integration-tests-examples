package com.example.demo.controllers;

import com.example.demo.dto.SyncNotificationDto;
import com.example.demo.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("notify")
public class SyncNotificationController {

  @Autowired
  private NotificationService notificationService;

  @RequestMapping(method = RequestMethod.GET)
  public SyncNotificationDto greeting() {
    log.debug("Incoming request...");
    return notificationService.processSyncNotification();
  }

}
