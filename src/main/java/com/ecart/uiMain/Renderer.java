package com.ecart.uiMain;

import static com.ecart.uiMain.Utils.*;
import com.ecart.gestorAplicacion.merchandise.Tags;
import com.github.lalyos.jfiglet.FigletFont;

import java.util.ArrayList;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

public final class Renderer {

	public static void centerBanner(Banners banner) {
		int averageLength = averageLength(banner.split(), true);

		for (String line : banner.split())
			center(line, averageLength);
	}

	public static void figletBanner(String banner) {
		figletBanner(banner, 15);
	}

	public static void figletBanner(String banner, int vcentr) {
		figletBanner(banner, vcentr, 3);
	}

	public static void figletBanner(String banner, int vcentr, int postSpaces) {
		if (vcentr > 0)
			vcenter(vcentr);

		try {
			banner = FigletFont.convertOneLine(banner);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int averageLength = averageLength(banner.split("\n"), true);

		for (String line : banner.split("\n"))
			center(line, averageLength);

		print(postSpaces);
	}

	public static int minBoxSize(Tags tag, String subscript) {
		int biggest = getBiggestStringSize(tag.split());

		subscript = subscript + tag.name();
		if (subscript.length() > biggest)
			biggest = subscript.length();

		if (biggest % 2 != 0)
			biggest++;

		return biggest;
	}

	private static int[] boxPadding(int mbs, String line) {
		int[] padding = new int[2];
		// padding[0] -> left
		// padding[1] -> right

		// normalize the padding
		if (line.length() < mbs) {
			mbs = mbs - line.length();
			padding[0] = (int) Math.floor(mbs / 2);
			padding[1] = (int) Math.ceil((double) mbs / 2);
		}

		return padding;
	}

	private static String generateHorizontalLine(Tags[] tags, int numCols, String subscript, String marker,
			String border) {
		return generateHorizontalLine(tags, numCols, subscript, marker, border, false);
	}

	private static String generateHorizontalLine(Tags[] tags, int numCols, String subscript, String marker,
			String border, Boolean addBottomText) {
		StringBuilder line = new StringBuilder();
		int counter = 1;

		if (!addBottomText) {
			for (Tags tag : tags) {
				int mbs = minBoxSize(tag, subscript);

				line.append(border + marker.repeat(mbs) + border);
				line.append("  ");

				if (counter == numCols)
					break;
				counter++;
			}
		} else {
			for (Tags tag : tags) {
				int[] padding = boxPadding(minBoxSize(tag, subscript), subscript + tag.name());

				line.append(border + " ".repeat(padding[0]) + subscript + tag.name().toLowerCase() + " ".repeat(padding[1])
						+ border);
				line.append("  ");

				if (counter == numCols)
					break;
				counter++;
			}
		}

		return line.toString();
	}

	public static void renderAllTags() {

		Tags[] allTags = Tags.values();
		int batchSize = 3; // number of Tags per batch

		for (int i = 0; i < allTags.length; i += batchSize) {
			int endIndex = Math.min(i + batchSize, allTags.length);
			Tags[] tagsBatch = new Tags[endIndex - i];

			for (int j = i, k = 0; j < endIndex; j++, k++) {
				tagsBatch[k] = allTags[j];
			}

			renderTiledPattern(tagsBatch, 3, null, "Tag name: ", true, 2);
		}
	}

	public static void renderCard(Tags tag, LinkedHashMap<String, String> data) {
		renderTiledPattern(new Tags[] { tag }, 1, data, "bottoplceholdr", false, 3, 8);
	}

	public static void renderTiledPattern(Tags[] tags, int numCols, LinkedHashMap<String, String> data, String subscript,
			boolean addBottomText,
			int verticalSpaces) {
		renderTiledPattern(tags, numCols, data, subscript, addBottomText, verticalSpaces, 0);
	}

	public static void renderTiledPattern(Tags[] tags, int numCols, LinkedHashMap<String, String> data, String subscript,
			boolean addBottomText,
			int verticalSpaces, int offset) {

		int linesCount = tags[0].split().length;
		String horizontalLine = generateHorizontalLine(tags, numCols, subscript, "-", "+");
		String horizontalSpaces = generateHorizontalLine(tags, numCols, subscript, " ", "|");
		String bottomText = generateHorizontalLine(tags, numCols, subscript, " ", "|", true);

		int dataIndex = -1;
		List<String> keys = new ArrayList<>();
		List<String> values = new ArrayList<>();
		if (data != null) {
			keys = new ArrayList<String>(data.keySet());
			values = new ArrayList<String>(data.values());
		}

		String entireLeftPadding = " ".repeat(center(horizontalLine, 0, true, false, true).length() - offset);
		print(entireLeftPadding + horizontalLine);

		for (int i = 0; i < verticalSpaces; i++)
			print(entireLeftPadding + horizontalSpaces);

		for (int row = 0; row < linesCount; row++) {
			for (int col = 0; col < numCols; col++) {
				if (col > 0) {
					print("  ", false);
				}
				String[] lines = tags[col].split();
				String line = lines[row];

				int[] padding = boxPadding(minBoxSize(tags[col], subscript), line);
				String currLine = "|" + " ".repeat(padding[0]) + line + " ".repeat(padding[1]) + "|";

				if (col == 0)
					print(entireLeftPadding + currLine, false);
				else
					print(currLine, false);

				if (data != null) {
					if (dataIndex >= 0 && dataIndex < keys.size())
						print("\t" + keys.get(dataIndex) + values.get(dataIndex), false);
				}

				dataIndex++;

			}
			print();
		}

		for (int i = 0; i < verticalSpaces; i++)
			print(entireLeftPadding + horizontalSpaces);

		if (addBottomText)
			print(entireLeftPadding + bottomText);

		print(entireLeftPadding + horizontalLine);
	}

}
