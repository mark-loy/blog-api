package com.markloy.markblog.service;

import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.UpdateAdminDTO;
import com.markloy.markblog.dto.UserDTO;
import com.markloy.markblog.dto.VisitorLoginDTO;

import java.util.Map;

public interface UserService {

    UserDTO userLogin(LoginDTO loginDTO);

    Map<String, Object> saveVisitorUser(VisitorLoginDTO visitorLoginDTO, int type);

    Map<String, Object> findAllVisitor();

    Map<String, Object> updateVisitorState(Integer id, Boolean state);

    Map<String, Object> findAllAdmin();

    Map<String, Object> updateAdmin(UpdateAdminDTO updateAdminDTO);

    Map<String, Object> updateAdminState(Integer id, Boolean state);
}
