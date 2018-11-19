package pl.edu.agh.kis.visca.model;

import pl.edu.agh.kis.visca.cmd.*;

public enum CommandName {
    ADDRESS(new AddressCmd()),
    CLEAR_ALL(new ClearAllCmd()),
    GET_MAX_SPEED(new GetPanTiltMaxSpeedCmd()),
    DOWN(new PanTiltDownCmd()),
    HOME(new PanTiltHomeCmd()),
    LEFT(new PanTiltLeftCmd()),
    RIGHT(new PanTiltRightCmd()),
    UP(new PanTiltUpCmd()),
    ZOOM_TELE(new ZoomTeleStdCmd()),
    ZOOM_WIDE(new ZoomWideStdCmd()),
    WAIT(new WaitCmd()),
    SET_DEST(new SetDestCmd()),
    CHANGE_ADDRESS(new ChangeAddressCmd());

    private Cmd command;

    public Cmd getCommand() {
        return command;
    }

    CommandName(Cmd command) {
        this.command = command;
    }
}
