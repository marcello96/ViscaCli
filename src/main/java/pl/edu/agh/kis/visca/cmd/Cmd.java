//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca.cmd;

public abstract class Cmd {
    public Cmd(boolean isBroadcast, boolean isExecutable) {
        this.isBroadcast = isBroadcast;
        this.isExecutable = isExecutable;
    }

    private boolean isBroadcast;
    private boolean isExecutable;

    public abstract byte[] prepareContent();

    public boolean isBroadcast() {
        return isBroadcast;
    }
    public boolean isExecutable() { return isExecutable; }

    protected static byte[] duplicateArray(byte[] src) {
        byte[] dest = new byte[src.length];
        System.arraycopy(src, 0, dest, 0, src.length);
        return dest;
    }
}
