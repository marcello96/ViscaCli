package pl.edu.agh.kis.visca.model;

import pl.edu.agh.kis.visca.cmd.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandFactory {

    public static List<Cmd> createCommandList(String[] inputCommands) {
        return Stream.of(inputCommands)
                .map(CommandFactory::createCommand)
                .collect(Collectors.toList());
    }

    private static Cmd createCommand(String inputCommand) {
        if (inputCommand.startsWith(CommandName.WAIT.name())) {
            return getWaitCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.CHANGE_ADDRESS.name())) {
            return getChangeAddress(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.SET_DEST.name())) {
            return getSetDest(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.ZOOM_TELE.name())) {
            return getZoomTeleCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.ZOOM_WIDE.name())) {
            return getZoomWideCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.UP.name())) {
            return getPanTiltUpCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.DOWN.name())) {
            return getPanTiltDownCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.LEFT.name())) {
            return getPanTiltLeftCommand(inputCommand);
        }

        if (inputCommand.startsWith(CommandName.RIGHT.name())) {
            return getPanTiltRightCommand(inputCommand);
        }

        return CommandName.valueOf(inputCommand).getCommand();
    }

    private static Cmd getWaitCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");
        String time = inputCommand.substring(pos + 1);
        WaitCmd cmd = (WaitCmd) CommandName.WAIT.getCommand();

        try {
            cmd.setTime(Integer.parseInt(time));
        } catch (NumberFormatException e) {
            System.out.println("Wrong format: should be an integer" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getSetDest(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);
        String address = inputCommand.substring(pos + 1);
        SetDestCmd cmd = (SetDestCmd) CommandName.SET_DEST.getCommand();

        try {
            cmd.setAddress(Byte.parseByte(address));
        } catch (NumberFormatException e) {
            System.out.println("Wrong format: should be an integer" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getChangeAddress(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);
        String address = inputCommand.substring(pos + 1);
        ChangeAddressCmd cmd = (ChangeAddressCmd) CommandName.CHANGE_ADDRESS.getCommand();

        try {
            cmd.setNewAddress(Byte.parseByte(address));
        } catch (NumberFormatException e) {
            System.out.println("Wrong format: should be an integer" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getZoomTeleCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        ZoomTeleStdCmd cmd = (ZoomTeleStdCmd) CommandName.ZOOM_TELE.getCommand();

        if (pos == -1) {
            return cmd;
        }

        String speed = inputCommand.substring(pos + 1);
        try {
            cmd.setSpeed(ZoomTeleStdCmd.CONSTANT_SPEED.valueOf(speed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ZoomTeleStdCmd.Constant_Speed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getZoomWideCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_", inputCommand.indexOf("_") + 1);

        ZoomWideStdCmd cmd = (ZoomWideStdCmd) CommandName.ZOOM_WIDE.getCommand();

        if (pos == -1) {
            return cmd;
        }

        String speed = inputCommand.substring(pos + 1);

        try {
            cmd.setSpeed(ZoomWideStdCmd.CONSTANT_SPEED.valueOf(speed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ZoomWideStdCmd.Constant_Speed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getPanTiltUpCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");

        PanTiltUpCmd cmd = (PanTiltUpCmd) CommandName.UP.getCommand();
        if (pos == -1) {
            return cmd;
        }

        String tiltSpeed = inputCommand.substring(pos + 1);

        try {
            cmd.setSpeed(ConstantTiltSpeed.valueOf(tiltSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getPanTiltDownCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");

        PanTiltDownCmd cmd = (PanTiltDownCmd) CommandName.DOWN.getCommand();

        if (pos == -1) {
            return cmd;
        }

        String tiltSpeed = inputCommand.substring(pos + 1);

        try {
            cmd.setSpeed(ConstantTiltSpeed.valueOf(tiltSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getPanTiltLeftCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");

        PanTiltLeftCmd cmd = (PanTiltLeftCmd) CommandName.LEFT.getCommand();

        if (pos == -1) {
            return cmd;
        }

        String panSpeed = inputCommand.substring(pos + 1);

        try {
            cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }

    private static Cmd getPanTiltRightCommand(String inputCommand) {
        int pos = inputCommand.indexOf("_");


        PanTiltRightCmd cmd = (PanTiltRightCmd) CommandName.RIGHT.getCommand();

        if (pos == -1) {
            return cmd;
        }

        String panSpeed = inputCommand.substring(pos + 1);

        try {
            cmd.setSpeed(ConstantPanSpeed.valueOf(panSpeed));
        } catch (IllegalArgumentException e) {
            System.out.println("Wrong format should be a constant in ConstantPanSpeed and in ConstantTiltSpeed" + e.getMessage());
            e.printStackTrace();
        }

        return cmd;
    }
}
