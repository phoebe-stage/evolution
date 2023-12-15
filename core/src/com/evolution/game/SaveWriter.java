package com.evolution.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class SaveWriter {
    public static void save(ArrayList<ArrayList<Thread>> threadList) {
        String path = "../core/src/com/evolution/game/saves";
        File file = new File(path + "/"+java.time.LocalTime.now());
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(file.getPath());
            String line = "";
            for (ArrayList<Thread> threads : threadList) {
                for (Thread thread : threads) {
                    line += thread.toString();
                    line += "\n";
                }
                line += "\n";
            }
            myWriter.write(line);
            myWriter.close();
        } catch (IOException e) {
            System.out.println(" -> Invalid save path, download unsuccessful");
        }
    }
}
