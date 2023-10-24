package com.ecart.baseDatos;

import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.transactions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializador {
	static File archivo = new File("");

	public static void serializarUser() {
		try (FileOutputStream fileOut = new FileOutputStream("users.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {

			out.writeObject(User.getInstances());
			out.close();
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void saveObjects() {
		serializarUser();
	}
}
