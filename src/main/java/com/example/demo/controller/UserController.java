package com.example.demo.controller;

import com.example.demo.dao.UserDO;
import com.example.demo.model.req.UserAddReq;
import com.example.demo.model.req.UserGetReq;
import com.example.demo.model.res.PageResult;
import com.example.demo.model.res.UserRes;
import com.example.demo.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jensen_deng
 */
@CrossOrigin
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class UserController {
  private final UserService userService;

  // region add
  @PostMapping
  public void add(@RequestBody @Validated UserAddReq req) {
    userService.save(req.to());
  }
  // endregion

  // region delete
  @DeleteMapping
  public void delete(@RequestBody @Size(min = 1) List<Long> ids) {
    ids.forEach(userService::deleteById);
  }

  // endregion

  // region get one
  @GetMapping("/{id}")
  public UserRes getOne(@PathVariable @NotNull Long id) {
    UserDO params = UserDO.builder().id(id).build();
    return userService.findOne(params).map(UserRes::from).orElse(null);
  }
  // endregion

  // region get page
  @GetMapping
  public PageResult<Object> getAll(UserGetReq req) {
    Page<UserDO> page = userService.findAll(req);
    List<UserRes> list = page.stream().map(UserRes::from).collect(Collectors.toList());
    return PageResult.builder().dataList(list).countTotal(page.getTotalElements()).build();
  }
  // endregion

}
