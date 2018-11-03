package pl.edu.agh.kis.visca.model;

import pl.edu.agh.kis.visca.cmd.Cmd;
import pl.edu.agh.kis.visca.cmd.WaitCmd;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandFactory {

    public static Cmd createCommand(String inputCommand) {
        if (inputCommand.startsWith(CommandName.WAIT.toString())) {
            int pos = inputCommand.indexOf("_");
            String time = inputCommand.substring(pos+1);
            WaitCmd cmd = (WaitCmd)CommandName.WAIT.getCommand();
            cmd.setTime(Integer.parseInt(time));
            return cmd;
        }
        return CommandName.valueOf(inputCommand).getCommand();
    }

    public static List<Cmd> createCommandList(String[] inputcommands) {
        return Stream.of(inputcommands)
                        .map(CommandFactory::createCommand)
                        .collect(Collectors.toList());

    }
}
