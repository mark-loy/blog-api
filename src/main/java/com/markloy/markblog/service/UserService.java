package com.markloy.markblog.service;

import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.UserDTO;

public interface UserService {

    UserDTO userLogin(LoginDTO loginDTO);
}
