package com.dao;

import com.bean.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  SysUserRepository extends JpaRepository<SysUser,Long> {
    SysUser findByUsername(String username);

}
