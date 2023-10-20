package com.ecart.gestorAplicacion.merchandise;

import java.util.ArrayList;

import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.meta.Entity;
import com.ecart.gestorAplicacion.meta.Retval;

public class Store extends Entity {
	private String description;
	private Tags tag;

	// private ArrayList<ID> products;
	private ArrayList<User> members;

	private static ArrayList<Store> instances = new ArrayList<>();

	public Store(String name, String password, String description) {
		super(name, password);
		this.description = description;

		this.members = new ArrayList<>();
		instances.add(this);
	}

	/**
	 * Conditional constructor
	 *
	 * @param name
	 * @param passcode
	 * @param description
	 * @return
	 */
	public static Store create(String name, String passcode, String description) {
		Store newStore = validate(name);
		if (newStore != null)
			return null;

		newStore = new Store(name, passcode, description);

		instances.add(newStore);
		return newStore;
	}

	public static Store validate(String name) {
		return validate(name, null, instances, false);
	}

	public static Store validate(String name, String password) {
		return validate(name, password, instances, true);
	}

	public static Store validate(String name, ArrayList<Store> arr) {
		return validate(name, null, arr, false);
	}

	public static Store validate(String name, String password, ArrayList<Store> arr, boolean checkPassword) {
		for (Store store : arr) {
			if (store.getName().equals(name)) {
				if (checkPassword) {
					if (!store.getPassword().equals(password))
						continue;
				}

				return store;
			}
		}
		return null;
	}

	public Retval addUser(User user) {
		return addUser(user.getName());
	}

	public Retval addUser(String name) {
		User newUser = User.validate(name, members);
		if (newUser != null)
			return new Retval("Failed to join store. User is already a member of the store", false);

		return new Retval("Joined store successfully");
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Tags getTag() {
		return tag;
	}

	public void setTag(Tags tag) {
		this.tag = tag;
	}

	//
	// // meta constructor for dup name validation
	// public static Store createStore(String name, String passcode, String
	// description) {
	// for (Store store : instances) {
	// if (store.name.equals(name)) {
	// return null; // Store with the same name already exists
	// }
	// }
	//
	// Store newStore = new Store(name, passcode, description);
	// instances.add(newStore);
	// return newStore;
	// }
	//
	// public String getName() {
	// return name;
	// }
	//
	// public void setName(String name) {
	// this.name = name;
	// }
	//
	// public String getPasscode() {
	// return passcode;
	// }
	//
	// public void setPasscode(String passcode) {
	// this.passcode = passcode;
	// }
	// // public static Store getById(UUID id) {
	// // for (Store user : instances) {
	// // if (user.getId().equals(id)) {
	// // return user;
	// // }
	// // }
	// // return null;
	// // }
	//
	// public static Store getByCredentials(String name, String passcode) {
	// for (Store store : instances) {
	// if (store.getName().equals(name) && store.getPasscode().equals(passcode)) {
	// return store;
	// }
	// }
	// return null;
	// }
	//
	// public String getDescription() {
	// return description;
	// }
	//
	// public void setDescription(String description) {
	// this.description = description;
	// }
	//
	// public void addMember(User user) {
	// if (!members.contains(user))
	// members.add(user);
	// }
	//
	// // public ArrayList<ID> getProducts() {
	// // return products;
	// // }

}
