package ru.rgordeev.command;

import ru.rgordeev.controller.Params;

/**
 * Интерфейс фабрики команд.
 * Отвечает за получение конкретной команды на основе данных из командной строки (Params)
 *
 * Для получения конкретной команды используется CommandBuilder
 */
public interface CommandFactory {
  Command buildCommand(Params params);
}
