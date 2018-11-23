//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public final class PanTiltDownCmd extends Cmd {
    private static final byte[] ptUpCommandData = new byte[]{1, 6, 1, 0x00, 0x00, 3, 2};
    private ConstantTiltSpeed tiltSpeed;

    public PanTiltDownCmd() {
        super(false, true);
        setSpeed(ConstantTiltSpeed.L02);
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(ptUpCommandData);
    }

    public void setSpeed(ConstantTiltSpeed tiltSpeed) {
        this.tiltSpeed = tiltSpeed;
        ptUpCommandData[4] = tiltSpeed.getByteValue();
    }

    @Override
    public String toString() {
        return tiltSpeed != null ? "DOWN_" + tiltSpeed.name() : "DOWN";
    }
}
