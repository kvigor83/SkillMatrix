package by.kastsiuchenka.skillmatrix.web.command;

import by.kastsiuchenka.skillmatrix.web.command.impl.DeleteCommand;
import by.kastsiuchenka.skillmatrix.web.command.impl.InsertCommand;
import by.kastsiuchenka.skillmatrix.web.command.impl.StartCommand;
import by.kastsiuchenka.skillmatrix.web.command.impl.UpdateCommand;

public enum CommandType {
    START("start", new StartCommand()),
    DELETE_SKILL("deleteData", new DeleteCommand()),
    INSERT_SKILL("addNewData",new InsertCommand()),
    UPDATE_SKILL("updateData",  new UpdateCommand());


    private String pageName;
    private Command command;

    CommandType(String pageName, Command command) {
        this.pageName = pageName;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getPageName() {
        return pageName;
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