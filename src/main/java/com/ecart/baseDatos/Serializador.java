package com.ecart.baseDatos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class Serializador {
    private static File rutaTemp = new File("src\\baseDatos\\temp");

    public static void serializar() {
        FileOutputStream f;
        ObjectOutputStream o;
        File[] docs = rutaTemp.listFiles();
        PrintWriter pw;

        for (File file : docs) {
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        for (File file : docs) {
            if (file.getAbsolutePath().contains("algo")) {
                try {
                    f = new FileOutputStream(file);
                    o = new ObjectOutputStream(f);
                    //como parametro va aquello que yo quiero escribir de las clases
                    //o.writeObject("algo");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}