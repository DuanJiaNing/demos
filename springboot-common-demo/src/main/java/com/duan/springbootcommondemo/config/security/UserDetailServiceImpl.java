package com.duan.springbootcommondemo.config.security;

import com.duan.springbootcommondemo.dao.RoleRepository;
import com.duan.springbootcommondemo.dao.UserRepository;
import com.duan.springbootcommondemo.dao.UserRoleRepository;
import com.duan.springbootcommondemo.entity.Role;
import com.duan.springbootcommondemo.entity.User;
import com.duan.springbootcommondemo.entity.UserRole;
import com.duan.springbootcommondemo.enums.UserStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2018/9/19.
 *
 * @author DuanJiaNing
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> users = userRepository.findByName(username);
        User user = users.get(0);
        List<UserRole> userRoles = userRoleRepository.findAllByUId(user.getId());

        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoles.forEach(role -> {
            Role ro = roleRepository.findOne(role.getRId());
            authorities.add(new SimpleGrantedAuthority(ro.getCode()));
        });

        org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.
                User(user.getName(), user.getPassword(), user.getStatus().equals(UserStatusEnum.ENABLE.getCode()),
                true, true, true, authorities);

        return new UserDetailsImpl(secUser);
    }
}
