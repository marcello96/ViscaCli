//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

import pl.edu.agh.kis.visca.model.Constants;

public final class AddressCmd extends Cmd {
    private static final byte[] adrCommmandData = new byte[]{48, 1};

    public AddressCmd() {
        super(Constants.SERVER_ADDRESS);
    }

    @Override
    public byte[] createCommandData() {
        return duplicateArray(adrCommmandData);
    }
}
