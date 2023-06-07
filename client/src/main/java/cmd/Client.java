package cmd;

import builders.HumanDirector;

public class Client {
    private final HumanDirector humanDirector;
    Client(HumanDirector humanDirector){
        this.humanDirector = humanDirector;
    }
    public void send(CmdRequest request){
        /// Отправляет Request в Сервер
    }
    public void exit(){
        System.out.println("Adios");
        System.exit(0);
    }
}
