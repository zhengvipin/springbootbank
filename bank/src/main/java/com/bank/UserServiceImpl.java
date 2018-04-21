package com.bank;

import com.bank.domain.User;
import com.bank.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
public class UserServiceImpl {
    @Resource
    private UserMapper userMapper;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int changePassword(Long id,
                              String password) {
        return userMapper.changePassword(id, password);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int add(User user) {
        return userMapper.add(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int modify(User user) {
        return userMapper.modify(user);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public int addUserAuthority(Long userId, Long[] authorityIds) {
        // 删除用户-角色
        userMapper.removeUserAuthority(userId);
        // 新增用户-角色
        int count = 0;
        if (authorityIds.length > 0) {
            count = userMapper.addUserAuthority(userId, authorityIds);
        }
        return count;
    }

    public List<Integer> findUserAuthority(Long userId) {
        return userMapper.findUserAuthority(userId);
    }

    public List<User> find() {
        return userMapper.find();
    }

    public User find(Long id) {
        return userMapper.findById(id);
    }
}
