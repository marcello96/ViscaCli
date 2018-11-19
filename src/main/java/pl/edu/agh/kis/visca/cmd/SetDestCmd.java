package pl.edu.agh.kis.visca.cmd;

import pl.edu.agh.kis.visca.model.Constants;

public class SetDestCmd extends Cmd {
    private byte address;

    public SetDestCmd() {
        super(false, false);
    }

    @Override
    public byte[] prepareContent() {
        Constants.DESTINATION_ADDRESS = address;
        return new byte[0];
    }

    public void setAddress(byte address) {
        this.address = address;
    }

    public byte getAddress() {
        return address;
    }
}
