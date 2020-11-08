package com.markloy.markblog.service;

import com.markloy.markblog.dto.GithubUserDTO;
import com.markloy.markblog.dto.LoginDTO;
import com.markloy.markblog.dto.UpdateAdminDTO;
import com.markloy.markblog.dto.UserDTO;

import java.io.IOException;
import java.util.Map;

public interface UserService {

    UserDTO userLogin(LoginDTO loginDTO);

    Map<String, Object> saveGithubUser(GithubUserDTO githubUserDTO);

    Map<String, Object> findAllVisitor();

    Map<String, Object> updateVisitorState(Integer id, Boolean state);

    Map<String, Object> findAllAdmin();

    Map<String, Object> updateAdmin(UpdateAdminDTO updateAdminDTO);

    Map<String, Object> updateAdminState(Integer id, Boolean state);
}
