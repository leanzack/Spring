package dev.pans.React_Spring.Repository;

import dev.pans.React_Spring.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
}
