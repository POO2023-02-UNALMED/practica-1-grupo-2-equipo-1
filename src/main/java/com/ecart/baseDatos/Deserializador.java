package com.ecart.baseDatos;

import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.transactions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Deserializador {
	static File archivo = new File("");

	public static void deserializarUser() {
		ArrayList<User> loadedUsers = null;

		try {
			FileInputStream fileIn = new FileInputStream("users.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			System.out.print("here");
			loadedUsers = (ArrayList<User>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (loadedUsers != null) {
			User.setInstances(loadedUsers);
		}
	}

	public static void loadObjects() {
		deserializarUser();
	}

}
