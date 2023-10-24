package com.ecart.uiMain.menus.userMenu.suggestProducts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ecart.uiMain.Utils.*;
import static com.ecart.uiMain.Input.*;
import com.ecart.gestorAplicacion.entites.User;
import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;
import com.ecart.uiMain.menus.Commons;

final public class SuggestProducts {

	private static Tags[] getListOfValidTags(String input) {
		// split input by commas and trim the tag names
		String[] tagNames = input.split(",");
		for (int i = 0; i < tagNames.length; i++) {
			tagNames[i] = tagNames[i].trim();
		}

		ArrayList<Tags> validTagsList = new ArrayList<>();

		for (String tagName : tagNames) {
			Tags tag = Tags.getByName(tagName);
			if (tag != null) {
				validTagsList.add(tag);
			}
		}

		// convert the ArrayList to a Tags[] array
		Tags[] validTags = validTagsList.toArray(new Tags[0]);

		return validTags;
	}

	public static void call(User user) {
		Renderer.drawBanner("Suggest Products");

		String[] r = questionnaire(
				new String[] {
						"💁 Tag names (comma separated)",
						"💲 Max price"
				});

		if (r[0].equals("") || r[1].equals("")) {
			Commons.dialog(new Retval("Not enough input was provided", false));
			return;
		}

		Tags[] validTags = getListOfValidTags(r[0]);

		if (validTags.length == 0) {
			Commons.dialog(new Retval("No valid tag was provided", false));
			return;
		}

		clear();

		Renderer.drawBanner("Suggested Stores");
		ArrayList<Product> suggestedProducts = user.suggestProducts(validTags, Double.valueOf(r[1]));

		if (suggestedProducts.isEmpty()) {
			center("Looks like there is no product that satisfies your requirements!", true);
			sleep(2);
			return;
		}

		int columns = getDimensions()[1];
		ArrayList<String> printedStores = new ArrayList<>();

		for (Product product : suggestedProducts) {
			Store storeHolder = null;
			for (Store store : Store.getInstances()) {
				if (Product.validate(product.getName(), store.getProducts()) != null)
					storeHolder = store;

			}

			if (printedStores.contains(storeHolder.getName()))
				continue;

			printedStores.add(storeHolder.getName());

			center("-".repeat(columns), true);
			Renderable unit = new Renderable(
					storeHolder.getTag(),
					new String[] { storeHolder.getTag().name() },
					new String[] {
							storeHolder.getName(),
							storeHolder.getTag().name(),
							storeHolder.getDescription(),
					});

			Renderer.drawCard(unit, null, new String[] {
					"Name: ", "Tag: ", "Description: "
			});

			print(2);

			ArrayList<Product> storeProducts = new ArrayList<>();

			for (Product _product : suggestedProducts) {
				if (Product.validate(_product.getName(), storeHolder.getProducts()) != null)
					storeProducts.add(product);
			}

			Commons.drawProducts(storeProducts, false, 0);
			center("-".repeat(columns), true);
		}

		String response = conditionalInquiry(
				new String[] { "Ready to go back a go shopping?",
						"[yes] 👉 " },
				i -> !i.equals("yes") == true && i.equals("") == false);

		if (response.equals("") || response.equals("yes"))
			;
		return;
	}
}
