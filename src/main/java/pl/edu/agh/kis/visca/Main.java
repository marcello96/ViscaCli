//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.kis.visca.ViscaResponseReader.TimeoutException;
import pl.edu.agh.kis.visca.cmd.*;
import pl.edu.agh.kis.visca.model.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        String commName = args[0];
        SerialPort serialPort = new SerialPort(commName);

        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            configDevice(serialPort);

            System.out.print("Enter command:\n~");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null && !line.equals("exit") ) {
                List<Cmd> commands = CommandFactory.createCommandList(parseInput(line));
                for (Cmd command : commands) {
                    ViscaCommandHelper.sendCommand(serialPort, command);
                    ViscaCommandHelper.readResponse(serialPort);
                }
                System.out.print("Enter command:\n~");
            }

            isr.close();
        } catch (IOException | SerialPortException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void configDevice(SerialPort serialPort) {
        ViscaCommandHelper.sendCommand(serialPort, new AddressCmd());
        ViscaCommandHelper.readResponse(serialPort);
        sleep(2);

        ViscaCommandHelper.sendCommand(serialPort, new PanTiltHomeCmd());
        ViscaCommandHelper.readResponse(serialPort);
        sleep(2);
    }

    private static String[] parseInput(String userInput) {
        if (userInput.startsWith("macro:")) {
            String commands = userInput.substring(6);

            return commands.split(";");
        }
        return new String[] {userInput};
    }

    private static void sleep(int timeSec) {
        try {
            Thread.sleep((long)(timeSec * 1000));
        } catch (InterruptedException var2) {
            var2.printStackTrace();
        }

    }
}
