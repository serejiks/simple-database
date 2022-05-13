package ru.rgordeev.command;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import ru.rgordeev.controller.AppContext;
import ru.rgordeev.controller.Params;
import ru.rgordeev.dao.InmemoryStorageService;
import ru.rgordeev.dao.PersistentStorage;
import ru.rgordeev.dao.StorageService;
import ru.rgordeev.model.Employee;
import ru.rgordeev.model.Organization;
import ru.rgordeev.model.Salary;

public class CommandFactoryImpl implements CommandFactory {

  private static final CommandFactory instance = new CommandFactoryImpl();

  public static CommandFactory getInstance() {
    return instance;
  }

  private final Map<String, CommandBuilder> registar = new HashMap<>();

  public CommandFactoryImpl() {
    register(ExitCommand.NAME, new ExitCommandBuilder());
    register(AddCommand.NAME, new AddCommandBuilder());
    register(ListCommand.NAME, new ListCommandBuilder());
  }

  private void register(String commandName, CommandBuilder commandBuilder) {
    registar.put(commandName, commandBuilder);
  }

  public static class ExitCommand implements Command {

    public static String NAME = "exit";

    @Override
    public void execute() {
      AppContext.getInstance().setExit(true);
    }
  }

  public static class UnknownCommand implements Command {

    @Override
    public void execute() {

    }
  }

  public static class UnknownCommandBuilder implements CommandBuilder {

    @Override
    public Command createCommand(Params params) {
      return new UnknownCommand();
    }
  }

  public static class AddCommand implements Command {

    public static final String NAME = "add";

    private final String orgName;
    private final String empName;
    private final BigDecimal salary;
    private final StorageService storageService;


    public AddCommand(String orgName, String empName, BigDecimal salary,
                      StorageService storageService) {
      this.orgName = orgName;
      this.empName = empName;
      this.salary = salary;
      this.storageService = storageService;
    }

    @Override
    public void execute() {
      Organization organization = new Organization(orgName);
      Employee employee = new Employee();
      employee.setName(empName);
      Salary s = new Salary();
      s.setValue(salary);
      employee.setSalary(s);
      storageService.add(organization, employee);
    }
  }

  public static class AddCommandBuilder implements CommandBuilder {


    @Override
    public Command createCommand(Params params) {

      String oName = params.getArgs()[0];
      String eName = params.getArgs()[1];
      BigDecimal salary = new BigDecimal(params.getArgs()[2]);

      return new AddCommand(oName, eName, salary,
          PersistentStorage.getInstance());
    }
  }

  @Override
  public Command buildCommand(Params params) {

    CommandBuilder commandBuilder = registar.getOrDefault(params.getCommandName(), new UnknownCommandBuilder());

    return commandBuilder.createCommand(params);
  }

  public static class ExitCommandBuilder implements CommandBuilder {
    @Override
    public Command createCommand(Params params) {
      return new ExitCommand();
    }
  }

  public static class ListCommand implements Command {

    public static String NAME = "list";

    private final StorageService storageService;

    public ListCommand(StorageService storageService) {
      this.storageService = storageService;
    }

    @Override
    public void execute() {
      storageService.listAll().stream().forEach(e -> {
        System.out.println(String.format("%s %d", e.getName(), e.getSalary().getValue().longValueExact()));
      });
    }
  }

  public static class ListCommandBuilder implements CommandBuilder {

    @Override
    public Command createCommand(Params params) {
      return new ListCommand(PersistentStorage.getInstance());
    }
  }
}
