package pl.edu.agh.kis.visca.cmd;

import pl.edu.agh.kis.visca.model.Constants;

import static pl.edu.agh.kis.visca.ViscaCommandHelper.sleep;

public class WaitCmd extends Cmd {
    private static final int DEFAULT_TIME_WAITING = 5;
    private int time;

    public WaitCmd() {
        super(Constants.DESTINATION_ADDRESS);
        this.time = DEFAULT_TIME_WAITING;
    }

    @Override
    public byte[] createCommandData() {
        sleep(time);
        return new byte[0];
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time;
    }

}
