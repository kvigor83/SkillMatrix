package by.kastsiuchenka.skillmatrix.web.command;

import by.kastsiuchenka.skillmatrix.web.command.impl.DeleteCommand;
import by.kastsiuchenka.skillmatrix.web.command.impl.InsertCommand;
import by.kastsiuchenka.skillmatrix.web.command.impl.StartCommand;
import by.kastsiuchenka.skillmatrix.web.command.impl.UpdateCommand;

public enum CommandType {
    START("start", new StartCommand()),
    DELETE_ITEM("about", new DeleteCommand()),
    INSERT_ITEM("login",new InsertCommand()),
    UPDATE_ITEM("registration",  new UpdateCommand());


    private String pageName;
    private Command command;

    CommandType(String pageName, Command command) {
        this.pageName = pageName;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public static CommandType getByCommandName(String page) {
        for (CommandType type : CommandType.values()) {
            if (type.pageName.equalsIgnoreCase(page)) {
                return type;
            }
        }
        return START;   // default
    }

}