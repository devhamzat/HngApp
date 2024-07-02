package org.devhamzat.hng_task1.Service;

import jakarta.servlet.http.HttpServletRequest;
import org.devhamzat.hng_task1.Dto.UserResponseDto;

public interface UserGreetingService {
    UserResponseDto userResponse(String visitor_name, HttpServletRequest httpServletRequest);
}
