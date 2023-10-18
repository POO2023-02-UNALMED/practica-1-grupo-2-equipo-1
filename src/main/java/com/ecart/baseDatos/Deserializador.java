package com.ecart.baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
public class Deserializador {
    private static File rutaTemp = new File("src\\baseDatos\\temp");

    public static void deserializar() {
        File[] docs = rutaTemp.listFiles();
        FileInputStream fis;
        ObjectInputStream ois;

        for (File file : docs) {
            if (file.getAbsolutePath().contains("algo")) {
                try {
                    fis = new FileInputStream(file);
                    ois = new ObjectInputStream(fis);
                    //asignar como atributo de la clase las cosas que lea
                    //dpto.setAsignaturas((List<Asignatura> ois.readObject());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
