package cmd;

import exceptions.CmdArgsAmountException;
import exceptions.ExecuteException;
import exceptions.ValidException;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CmdHandler {

    private final HashMap<String, Command> cmds;
    private final LinkedList<Command> cmdHistory;    /// Походу это нужно только на сервере

    public CmdHandler() {
        this.cmds = new HashMap<>();
        this.cmdHistory = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            cmdHistory.add(null);
        }
    }

    public void addComm(Command c) {
        final String name = c.getName();
        if (!isInCmds(name)) {
            this.cmds.put(name, c);
        }
    }

    public boolean isInCmds(String name) {
        return this.cmds.containsKey(name);
    }

    public void addCmds(Command... comms) {
        for (Command c : comms) {
            addComm(c);
        }
    }

    public HashMap<String, Command> getCmds() {
        return cmds;
    }

    public LinkedList<Command> getCmdHistory() {
        return cmdHistory;
    }     // и это тоже

    public CmdResponse executeCmd(String[] args) throws CmdArgsAmountException, ExecuteException, FileNotFoundException, ValidException, InvocationTargetException, IllegalAccessException {
        Command command = getCmds().get(args[0]);
        if (!checkingTheList(args[0])) {
            throw new ExecuteException("No such command");
        }
        if (command.getCmdType() != CmdType.NO_ARGS && args.length == 0){                 //// и к моему огромному удивлению это тоже
            throw new ExecuteException("Missing argument");
        }else {
            ActionResult result;
            if (args.length == 1) {
                result = command.action(new CmdArgs());
            } else {
                List<String> list = new ArrayList<>(Arrays.asList(args));
                list.remove(0);
                result = command.action(new CmdArgs(list.toArray(new String[0])));
            }
            cmdHistory.addLast(command);
            cmdHistory.removeFirst();
            return new CmdResponse(result);
        }
    }
    public boolean checkingTheList(String cmd){
        boolean checker = false;
        Set<String> keys = cmds.keySet();
        for (String key : keys){
            if (Objects.equals(cmd, key)) {
                checker = true;
                break;
            }
        }
        return checker;
    }
}
}
