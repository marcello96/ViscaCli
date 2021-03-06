//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public final class ZoomWideStdCmd extends Cmd {
    private static final byte[] ptWideStdCommandData = new byte[]{1, 4, 7, 0x00};
    private CONSTANT_SPEED speed;

    public ZoomWideStdCmd() {
        super(false, true);
        setSpeed(CONSTANT_SPEED.DEFAULT_SPEED);
    }

    public void setSpeed(final CONSTANT_SPEED speed) {
        ptWideStdCommandData[3] = speed.value;
    }

    @Override
    public byte[] prepareContent() {
        return duplicateArray(ptWideStdCommandData);
    }

    public enum CONSTANT_SPEED {
        DEFAULT_SPEED((byte)0x03),
        SPEED0((byte)0x30),
        SPEED1((byte)0x31),
        SPEED2((byte)0x32),
        SPEED3((byte)0x33),
        SPEED4((byte)0x34),
        SPEED5((byte)0x35),
        SPEED6((byte)0x36),
        SPEED7((byte)0x37);

        private byte value;

        CONSTANT_SPEED(byte value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        return "ZOOM_WIDE" + speed.name();
    }
}
