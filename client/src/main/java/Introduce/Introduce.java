package Introduce;

import cmd.*;
import exceptions.ExecuteException;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Introduce {
    private final Client client;
    private final CmdHandler cmdHandler;
    private final Scanner scanner = new Scanner(System.in);

    public Introduce(CmdHandler cmdHandler, Client client) {
        this.cmdHandler = cmdHandler;
        this.client = client;
    }

    private CmdRequest getUserInput() {
        String[] input = null;
        CmdRequest cmdRequest = null;
        while (Objects.isNull(input)) {
            System.out.print("Ведите команду: \n > ");
            String line = null;
            try {
                line = scanner.nextLine().trim();
            } catch (NoSuchElementException e) {
                System.exit(0);
            }
            if (!line.isEmpty()) {
                input = line.split(" ", 2);
                cmdRequest = getRequest(input);
            } else {
                System.out.println("Uncorrected input");
            }
        }
        return cmdRequest;
    }

    public void run() throws CmdArgsAmountException, FileNotFoundException, InvocationTargetException, IllegalAccessException, NullPointerException {
        while (true) {
            client.send(getUserInput());
        }
    }
    public CmdRequest getRequest(String[] args){
        if (!cmdHandler.checkingTheList(args[0])){
            System.out.println("No such command");
        } else {
            List<String> list = new ArrayList<>(Arrays.asList(args));
            list.remove(0);
            Command command = cmdHandler.getCmds().get(args[0]);
            CmdArgs cmdArgs= new CmdArgs(list.toArray(new String[0]));
            CmdRequest request = new CmdRequest(command, cmdArgs);
            return request;
        }
    }
}