package ru.rgordeev.dao;

import java.util.Set;
import ru.rgordeev.model.Employee;
import ru.rgordeev.model.Organization;

/**
 * Интерфейс, декларирующий возможные методы работы с данными в хранилище.
 *
 * В данном пример присутствуют две реализации данного интерфейса:
 * 1. InmemoryStorageService - для хранения данных в оперативной памяти (в Map)
 * 2. PersistentStorage - для хранения данных в базе данных
 * */
public interface StorageService {

  Organization findOrganization(String title);
  void add(Organization org, Employee employee);
  Employee get(String name);
  Set<Employee> listAll();
  Employee delete(String name);
  Employee update(Employee newOne);
}
