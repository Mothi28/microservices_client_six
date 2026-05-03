package com.client.six;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    @Value("${server.port}")
    private String port;
    
    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/info")
    public ResponseEntity<String> showOrderInfo() {
       return ResponseEntity.ok("FROM ORDER SERVICE, Port# is: " + port);
    }
    
    @GetMapping("/instanceId")
    public ResponseEntity<String> showOrderInstanceId() {
       return ResponseEntity.ok("FROM ORDER SERVICE, Instance Id is: " + instanceId);
    }
}