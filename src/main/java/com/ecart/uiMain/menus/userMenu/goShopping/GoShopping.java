package com.ecart.uiMain.menus.userMenu.goShopping;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.Commons;
import com.ecart.uiMain.menus.userMenu.manageStores.viewStores.viewStores;

final public class GoShopping {

	private static void drawStore(Store store) {

		Renderable unit = new Renderable(
				store.getTag(),
				new String[] { store.getTag().name() },
				new String[] {
						store.getName(),
						store.getTag().name(),
						store.getDescription(),
						"🌟 🌟 🌟 🌟 🌟"
				});

		Renderer.drawCard(unit, null, new String[] {
				"Name: ", "Tag: ", "Reviews: ", "Description: "
		});

		print(2);

		Commons.drawProducts(store, false);
	}

	public static void call(User user) {
		Renderer.figletBanner("go  shopping!");

		ArrayList<Store> allStores = Store.getInstances();
		ArrayList<Store> userStores = user.getStores();
		ArrayList<Store> availableStores = new ArrayList<>();

		// filter out the stores that the user belongs to
		for (Store store : allStores) {
			if (!userStores.contains(store))
				availableStores.add(store);
		}

		print();

		if (availableStores.isEmpty()) {
			center("Looks like no one has oppened any stores yet!", true);
			sleep(2);
			return;
		}

		for (Store store : availableStores) {
			center("-".repeat(50), true);
			drawStore(store);
			center("-".repeat(50), true);
		}

		print();

		String storeName = conditionalInquiry(
				new String[] { "Please select one stores you would like to manage", "(type its name) 👉 " },
				i -> Store.validate(i, availableStores) == null);

		Store chosenStore = Store.validate(storeName, availableStores);

		clear();
		Renderer.figletBanner(chosenStore.getName());
		drawStore(chosenStore);
		sleep(2);

		// LinkedHashMap<String, Runnable> submenu = new LinkedHashMap<>();
		//
		// submenu.put("🖼️ View your stores", () -> viewStores.call(user));
		// submenu.put("🗃️ Create store", () -> createStore(user));
		// submenu.put("🥋 Join store", () -> joinStore(user));
		//
		// menu("stores", submenu, true);
	}
}
