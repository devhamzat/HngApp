package org.devhamzat.hng_task1.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.devhamzat.hng_task1.Dto.UserResponseDto;
import org.devhamzat.hng_task1.Service.UserGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api")
public class GreetingController {

    @Autowired
    private UserGreetingService userGreetingService;
    @GetMapping("/hello")
    public UserResponseDto userResponseDto(@RequestParam String visitor_name, HttpServletRequest httpServletRequest) {
        return userGreetingService.userResponse(visitor_name, httpServletRequest);
    }


}
