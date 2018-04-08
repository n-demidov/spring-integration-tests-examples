package com.example.demo.controllers;

import com.example.demo.dto.SyncNotificationDto;
import com.example.demo.services.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("async_notify")
public class AsyncNotificationController {

  @Autowired
  private NotificationService notificationService;

  @RequestMapping(method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.ACCEPTED)  // 202 Accepted: https://ru.wikipedia.org/wiki/Список_кодов_состояния_HTTP#202
  public void asyncNotify() {
    log.debug("asyncNotify");
    notificationService.processAsyncNotification();
  }

}
