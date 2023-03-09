package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.dao.UserDO;
import com.example.demo.model.req.PageBase;
import com.example.demo.model.req.UserGetReq;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public Page<UserDO> findAll(final UserGetReq req) {
    String keyword = Optional.ofNullable(req).map(UserGetReq::getKeyword).orElse(null);
    int page = Optional.ofNullable(req).map(x -> x.getPageIndex() - 1).orElse(0);
    int size = Optional.ofNullable(req).map(PageBase::getPageSize).orElse(0);

    Specification<UserDO> specification =
        (root, query, builder) -> {
          List<Predicate> predicates = new ArrayList<>();
          if (StringUtils.hasText(keyword)) {
            predicates.add(
                builder.or(
                    builder.like(root.get(UserDO.Fields.name), "%" + keyword + "%"),
                    builder.like(root.get(UserDO.Fields.tel), "%" + keyword + "%")));
          }
          return builder.and(predicates.toArray(new Predicate[0]));
        };

    PageRequest pageRequest = PageRequest.of(page, size).withSort(Direction.DESC, UserDO.Fields.id);
    return repository.findAll(specification, pageRequest);
  }

  @Override
  public Optional<UserDO> save(UserDO object) {
    UserDO save = repository.save(object);
    object.setId(save.getId());
    return Optional.of(object);
  }

  @Override
  public Optional<UserDO> findOne(UserDO object) {
    Example<UserDO> example = Example.of(object);
    return repository.findOne(example);
  }

  @Override
  public void truncateTable() {
    repository.truncateTable();
  }

  @Override
  public void deleteById(Long id) {
    repository.deleteById(id);
  }
}
