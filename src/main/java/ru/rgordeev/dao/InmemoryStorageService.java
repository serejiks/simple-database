package ru.rgordeev.dao;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import ru.rgordeev.model.Employee;
import ru.rgordeev.model.Organization;

public class InmemoryStorageService implements StorageService {

  private static StorageService instance = new InmemoryStorageService();

  public static StorageService getInstance() {
    return instance;
  }

  private InmemoryStorageService() {}

  public static Set<Organization> db = new HashSet<>();

  @Override
  public Organization findOrganization(String title) {
    return db.stream().filter(o -> o.getTitle().equals(title)).findFirst().orElse(new Organization(title));
  }

  @Override
  public void add(Organization org, Employee employee) {
    org.getEmployees().add(employee);
    db.add(org);
  }

  @Override
  public Employee get(String name) {
    return null;
  }

  @Override
  public Set<Employee> listAll() {
    return db.stream().flatMap(o -> o.getEmployees().stream()).collect(Collectors.toSet());
  }

  @Override
  public Employee delete(String name) {
    return null;
  }

  @Override
  public Employee update(Employee newOne) {
    return null;
  }
}
