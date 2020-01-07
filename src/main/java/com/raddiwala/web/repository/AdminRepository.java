package com.raddiwala.web.repository;

import com.raddiwala.web.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
    Admin findAdminByUsername(String username);
}
