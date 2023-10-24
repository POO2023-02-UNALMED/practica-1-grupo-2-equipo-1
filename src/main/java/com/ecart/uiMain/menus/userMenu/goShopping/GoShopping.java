package com.ecart.uiMain.menus.userMenu.goShopping;

import java.util.ArrayList;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.gestorAplicacion.transactions.ShoppingCart;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.Commons;

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

		Commons.drawProducts(store, false, 0);
	}

	private static Product askProduct(String storeName, ArrayList<Store> availableStores) {
		Store chosenStore = Store.validate(storeName, availableStores);

		clear();
		Renderer.drawBanner(chosenStore.getName());
		Commons.drawProducts(chosenStore, false, 2);

		print();

		ArrayList<Product> allStoreProducts = chosenStore.getProducts();
		ArrayList<Product> availableProducts = new ArrayList<>();

		for (Product product : allStoreProducts) {
			if (product.isListed())
				availableProducts.add(product);
		}

		String productName = conditionalInquiry(
				new String[] { "Which product would you like to view?",
						"(type the name) 👉 " },
				i -> Product.validate(i, chosenStore.getProducts()) == null);

		Product storeProduct = Product.validate(productName, availableProducts);

		return storeProduct;
	}

	private static void drawProduct(Product storeProduct) {
		Renderer.drawBanner(storeProduct.getName());

		Renderable unit = new Renderable(
				storeProduct.getTag(),
				new String[] { storeProduct.getTag().name() },
				new String[] {
						storeProduct.getName(),
						String.valueOf(storeProduct.getQuantity()),
						"🌟 🌟 🌟 🌟 🌟",
						storeProduct.getDescription(),
				});

		Renderer.drawCard(
				unit,
				new String[] { "Tag name: " },
				new String[] { "Name: ", "Available stock: ", "Reviews: ", "Description: " },
				true);

		print(2);
	}

	public static void call(User user) {
		ArrayList<Store> allStores = Store.getInstances();
		ArrayList<Store> userStores = user.getStores();
		ArrayList<Store> availableStores = new ArrayList<>();

		// filter out the stores that the user belongs to
		for (Store store : allStores) {
			if (!userStores.contains(store))
				availableStores.add(store);
		}

		if (availableStores.isEmpty()) {
			Renderer.drawBanner("go  shopping!");
			center("Looks like no one has oppened any stores yet!", true);
			sleep(2);
			return;
		}

		while (true) {
			clear();
			Renderer.drawBanner("go  shopping!", 12);

			int columns = getDimensions()[1];
			for (Store store : availableStores) {
				center("-".repeat(columns), true);
				drawStore(store);
				center("-".repeat(columns), true);
				print(2);
			}

			String storeName = conditionalInquiry(
					new String[] { "Please select one stores you would like to view (or press entre to go back)",
							"(type its name or press entre) 👉 " },
					i -> Store.validate(i, availableStores) == null && i.equals("") == false);

			if (storeName.equals(""))
				return;

			Product storeProduct = askProduct(storeName, availableStores);

			clear();
			drawProduct(storeProduct);

			String response = conditionalInquiry(
					new String[] { "Would you like to buy the product?",
							"[yes|no] 👉 " },
					i -> !(i.equals("yes") == true || i.equals("no") == true));

			if (response.equals("yes")) {
				int actualProductQuantity = storeProduct.getQuantity();
				String quantity = conditionalInquiry(
						new String[] { "Hoy many would you like to buy?",
								"(type the amount or press entre to go back) 👉 " },
						i -> ShoppingCart.isProductAvailable(storeProduct, Integer.valueOf(i)) == false && i != "0");

				if (quantity == "0")
					continue;

				Retval retval = user.addToShoppingCart(storeProduct.getName(), Integer.valueOf(quantity));
				Commons.dialog(retval);
			}
		}
	}
}
