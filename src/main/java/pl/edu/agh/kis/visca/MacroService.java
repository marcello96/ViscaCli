package pl.edu.agh.kis.visca;

import pl.edu.agh.kis.visca.cmd.Cmd;
import pl.edu.agh.kis.visca.model.CommandFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MacroService {
    private static final String MARCO_COMMAND_PREFIX = "macro:";
    private static final String SEMICOLON_REGEX = "\\s*;\\s*";
    private static final String COLON_REGEX = "\\s*:\\s*";

    private static MacroService instance;
    private Map<String, List<Cmd>> macrosMap = new HashMap<>();

    private MacroService() {}

    public static MacroService getInstance() {
        if (instance == null) {
            instance = new MacroService();
        }
        return instance;
    }

    public boolean isMacroDefined(String macro) {
        return macrosMap.containsKey(macro);
    }

    public List<Cmd> getCommands(String macro) {
        return macrosMap.get(macro);
    }

    public void parseMacro(String input) {
        String macroDefinition = input.substring(MARCO_COMMAND_PREFIX.length());
        String[] macroParts = macroDefinition.trim().split(COLON_REGEX);
        String name = macroParts[0];
        String commands = macroParts[1];
        String[] commandNames =  commands.split(SEMICOLON_REGEX);

        List<Cmd> commandList = CommandFactory.createCommandList(commandNames);
        macrosMap.put(name, commandList);
    }

    public boolean checkIfMacroDefinition(String input) {
        return input.startsWith(MARCO_COMMAND_PREFIX);
    }
}
