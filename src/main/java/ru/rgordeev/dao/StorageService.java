package ru.rgordeev.dao;

import java.util.Set;
import ru.rgordeev.model.Employee;
import ru.rgordeev.model.Organization;

public interface StorageService {

  Organization findOrganization(String title);
  void add(Organization org, Employee employee);
  Employee get(String name);
  Set<Employee> listAll();
  Employee delete(String name);
  Employee update(Employee newOne);
}
