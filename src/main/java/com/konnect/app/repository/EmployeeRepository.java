package com.konnect.app.repository;

import com.konnect.app.domain.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("select employee from Employee employee where employee.user.login = ?#{authentication.name}")
    List<Employee> findByUserIsCurrentUser();
}
