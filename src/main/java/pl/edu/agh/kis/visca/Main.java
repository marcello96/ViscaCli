//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package pl.edu.agh.kis.visca;

import jssc.SerialPort;
import jssc.SerialPortException;
import pl.edu.agh.kis.visca.cmd.AddressCmd;
import pl.edu.agh.kis.visca.cmd.ChangeAddressCmd;
import pl.edu.agh.kis.visca.cmd.Cmd;
import pl.edu.agh.kis.visca.cmd.PanTiltHomeCmd;
import pl.edu.agh.kis.visca.model.CommandFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static pl.edu.agh.kis.visca.ViscaCommandHelper.sleep;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            return;
        }
        String commName = args[0];
        SerialPort serialPort = new SerialPort(commName);

        MacroService macroService = MacroService.getInstance();

        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            configDevice(serialPort);

            System.out.print("Enter command:\n~");
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null && !line.equals("exit") ) {
                try {
                    if (macroService.checkIfMacroDefinition(line)) {
                        macroService.parseMacro(line);
                        System.out.println("Macro defined!\n~");
                        continue;
                    }
                    List<Cmd> commands = CommandFactory.createCommandList(new String[] {line});

                    for (Cmd command : commands) {
                        ViscaCommandHelper.sendCommand(serialPort, command);
                        if(command.isExecutable() && ViscaCommandHelper.readResponse(serialPort)) {
                            ViscaCommandHelper.readResponse(serialPort);
                        }
                    }
                } catch (Exception ex) {
                    System.out.println("Error!!!\n");
                }

                System.out.print("Enter command:\n~");
            }

            isr.close();
            serialPort.closePort();
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
        ViscaCommandHelper.readResponse(serialPort);
        sleep(2);
    }
}
