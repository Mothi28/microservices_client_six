package com.client.six;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

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
    
    @GetMapping("/rateLimitMethod")
    @RateLimiter(name="getMessageRateLimit", fallbackMethod="getMessageFallBack")
    public ResponseEntity<String> rateLimitMethod() {
	
    	return ResponseEntity.status(HttpStatus.ACCEPTED).body("got the request !!! ");    	
    	
    	//return "Printing via the rateLimitMethod ";
    }
    
    public ResponseEntity<String> getMessageFallBack(RequestNotPermitted exception) {
    	
    	System.out.println("Inside Messgae Fallback method ");
    	return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("too many request !!! ");    	
    	
    	//return "Printing via the rateLimitMethod ";
    }
    
    
    
}