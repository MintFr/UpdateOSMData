/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.updatedbmint;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jordan - Mint
 */
public class UpdateDB {

    private ExecutiveCommand executiveCommand;
    private String directory;
    private String fileName;
    private String[] command;

    public UpdateDB() {
        this.executiveCommand = new ExecutiveCommand();
        this.directory = "";
        this.fileName = "";
        this.command = new String[4];
        String[] cmd = {"cmd.exe", "/C", "Start", this.directory + this.fileName};
        this.command = cmd.clone();
        /*this.command[0] = "cmd.exe";
        this.command[1] = new String("/C");
        this.command[2] = new String("Start");
        this.command[3] = new String(this.directory + this.fileName);
         */
    }

    public void update() {

        ResourceBundle parameters = ResourceBundle.getBundle("org.centrale.updatetanmap.config");

        String dir = parameters.getString("directory");
        
        setDirectory(dir);
        try {
            System.out.println("Start export");
            int a = extractOSMData();
            System.out.println("End export");
            String exitValue = "no error";

            if (a == 0) {
                System.out.println("Start convert");
                int b = convertOSMData();
                System.out.println("End convert");

                if (b == 0) {
                    System.out.println("Start import");
                    int c = importOSMData();
                    System.out.println("End import");

                    if (c != 0) {
                        exitValue = c + " -> error in import";
                    }
                } else {
                    exitValue = b + " -> error in import";
                }
            } else {
                exitValue = a + " -> error in extract";
            }
            System.out.println("exit : " + exitValue);

        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("IOException: " + ex.getMessage());
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getDirectory() {
        return directory;
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getCommand() {
        return command;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
        this.setCommand();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
        this.setCommand();

    }

    public void setCommand(String[] command) {
        this.command = command;
    }

    public void setCommand() {
        //refresh value of command 
        this.command[3] = this.directory + this.fileName;
    }

    private int extractOSMData() throws IOException, InterruptedException {
        setFileName("export.bat");
        return executiveCommand.exectuteCommand(command);

    }

    private int convertOSMData() throws IOException, InterruptedException {
        setFileName("convert.bat");
        return executiveCommand.exectuteCommand(command);
    }

    private int importOSMData() throws IOException, InterruptedException {
        setFileName("import.bat");
        return executiveCommand.exectuteCommand(command);
    }

}
