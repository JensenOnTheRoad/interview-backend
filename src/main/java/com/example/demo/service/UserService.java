package com.example.demo.service;

import com.example.demo.dao.UserDO;
import com.example.demo.model.req.UserGetReq;
import java.util.Optional;
import org.springframework.data.domain.Page;

/**
 * @author jensen_deng
 */
public interface UserService {

  Optional<UserDO> findOne(UserDO object);

  Page<UserDO> findAll(UserGetReq req);

  Optional<UserDO> save(UserDO object);

  void deleteById(Long id);

  void truncateTable();
}
