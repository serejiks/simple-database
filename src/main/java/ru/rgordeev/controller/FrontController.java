package ru.rgordeev.controller;

import ru.rgordeev.command.Command;
import ru.rgordeev.command.CommandFactory;
import ru.rgordeev.command.CommandFactoryImpl;
import ru.rgordeev.view.ConsoleView;

public class FrontController {

  private static final FrontController instance = new FrontController();

  public static FrontController getInstance() {
    return instance;
  }

  private ConsoleView consoleView;
  private Controller controller;

  private AppContext appContext = AppContext.getInstance();

  private CommandFactory commandFactory = CommandFactoryImpl.getInstance();

  public void init(ConsoleView consoleView, Controller controller) {
    this.consoleView = consoleView;
    this.controller = controller;
  }

  public void process() {
    String newLine = null;
    while (!appContext.isExit()) {
      newLine = consoleView.nextLine();
      Params params = controller.parseCommandLine(newLine);
      Command command = commandFactory.buildCommand(params);
      command.execute();
    }
  }

}
