package ru.rgordeev.command;

import ru.rgordeev.controller.Params;

public interface CommandBuilder {
  Command createCommand(Params params);
}
