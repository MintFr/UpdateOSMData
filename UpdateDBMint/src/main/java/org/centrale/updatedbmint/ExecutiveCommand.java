/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.updatedbmint;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Mint
 */
class AfficheurFlux implements Runnable {

    private final InputStream inputStream;

    AfficheurFlux(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private BufferedReader getBufferedReader(InputStream is) {
        return new BufferedReader(new InputStreamReader(is));
    }

    @Override
    public void run() {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = getBufferedReader(inputStream);
        String ligne = "";
        try {
            while ((ligne = br.readLine()) != null) {
                System.out.println(ligne);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class ExecutiveCommand {

    /**
     * 
     * @param cmd
     * @return
     * @throws IOException
     * @throws InterruptedException 
     */
    public int exectuteCommand(String[] cmd) throws IOException, InterruptedException {
        Runtime rt = Runtime.getRuntime();
        Process process = rt.exec(cmd);
        AfficheurFlux fluxSortie = new AfficheurFlux(process.getInputStream());
        AfficheurFlux fluxErreur = new AfficheurFlux(process.getErrorStream());

        Thread thread1 = new Thread(fluxSortie);
        Thread thread2 = new Thread(fluxErreur);

        thread1.start();
        Thread.State state1 = thread1.getState();
        thread2.start();
        Thread.State state2 = thread2.getState();

        process.waitFor();

        int exitValue = process.exitValue();
        while ((!state1.equals(Thread.State.TERMINATED) && (!state2.equals(Thread.State.TERMINATED)))) {
            state1 = thread1.getState();            
            state2 = thread2.getState();
        }
        System.out.println("state1 : " + state1.toString());
        System.out.println("state2 : " + state2.toString());
        process.destroy();
        return exitValue;
    }

}
