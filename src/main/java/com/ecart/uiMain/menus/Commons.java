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

	public static void drawProducts(Store userStore) {
		ArrayList<Product> storeProducts = userStore.getProducts();
		int batchSize = storeProducts.size() < 3 ? storeProducts.size() : 3;

		if (storeProducts.isEmpty()) {
			center("Looks like you don't have any products yet on the store!", true);
			sleep(2);
			return;
		}

		// columns
		for (int i = 0; i < storeProducts.size(); i += batchSize) {
			int endIndex = i + batchSize;
			Renderable[] unitsBatch = new Renderable[endIndex - i];

			// rows
			for (int j = i, k = 0; j < endIndex; j++, k++) {
				Product sp = storeProducts.get(j);

				unitsBatch[k] = new Renderable(
						sp.getTag(),
						new String[] {
							sp.getName(),
							"$" + Double.toString(sp.getPrice()),
							Boolean.toString(sp.isListed())
						},
						null);
			}

			Renderer.drawTiledPattern(
					unitsBatch,
					new String[] {
						"Name: ", "Price: ", "Is listed: "
					},
					null, batchSize, 0, 1, true);
		}

		print(2);
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

