package com.ecart.uiMain.menus;

import static com.ecart.uiMain.Utils.*;

import java.util.ArrayList;

import com.ecart.gestorAplicacion.merchandise.Product;
import com.ecart.gestorAplicacion.merchandise.Store;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.ecart.gestorAplicacion.meta.Retval;
import com.ecart.uiMain.Renderable;
import com.ecart.uiMain.Renderer;

final public class Commons {

	public static void dialog(Retval retval) {
		dialog(retval.getMessage());
	}

	public static void dialog(String str) {
		print(2);
		center(str, true);
		sleep(2);
	}

	public static boolean drawProducts(ArrayList<Product> storeProducts) {
		return drawProducts(storeProducts, true, 2);
	}

	public static boolean drawProducts(Store userStore) {
		return drawProducts(userStore.getProducts(), true, 2);
	}

	public static boolean drawProducts(Store userStore, boolean includeListed, int postSpaces) {
		return drawProducts(userStore.getProducts(), includeListed, postSpaces);
	}

	public static boolean drawProducts(ArrayList<Product> storeProducts, boolean includeListed, int postSpaces) {
		int batchSize = storeProducts.size() < 3 ? storeProducts.size() : 3;

		if (storeProducts.isEmpty()) {
			center("Looks like you don't have any products yet on the store!", true);
			sleep(2);
			return false;
		}

		// columns
		for (int i = 0; i < storeProducts.size(); i += batchSize) {
			int endIndex = i + batchSize;
			Renderable[] unitsBatch = new Renderable[endIndex - i];

			// rows
			for (int j = i, k = 0; j < endIndex; j++, k++) {
				Product sp = storeProducts.get(j);

				ArrayList<String> captions = new ArrayList<String>() {
					{
						add(sp.getName());
						add("$" + Double.toString(sp.getPrice()));
					}
				};

				if (includeListed)
					captions.add(Boolean.toString(sp.isListed()));
				else
					captions.add(String.valueOf(sp.getQuantity()));

				unitsBatch[k] = new Renderable(
						sp.getTag(),
						captions.toArray(new String[captions.size()]),
						null);
			}

			ArrayList<String> captionLabels = new ArrayList<String>() {
				{
					add("Name: ");
					add("Price: ");
				}
			};

			if (includeListed)
				captionLabels.add("Is listed: ");
			else
				captionLabels.add("Quantity: ");

			Renderer.drawTiledPattern(
					unitsBatch,
					captionLabels.toArray(new String[captionLabels.size()]),
					null, batchSize, 0, 1, true);
		}

		print(postSpaces);
		return true;
	}

	public static void drawAllTags() {
		drawAllTags(3);
	}

	public static void drawAllTags(int batchSize) {

		Tags[] allTags = Tags.values();

		for (int i = 0; i < allTags.length; i += batchSize) {

			int endIndex = Math.min(i + batchSize, allTags.length);
			Renderable[] unitsBatch = new Renderable[endIndex - i];

			for (int j = i, k = 0; j < endIndex; j++, k++) {
				unitsBatch[k] = new Renderable(
						allTags[j],
						new String[] { allTags[j].name() },
						null);
			}

			Renderer.drawTiledPattern(
					unitsBatch,
					new String[] { "Tag name: " },
					null, 3, 0, 1, true);
		}
	}
}
