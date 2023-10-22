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

			drawTiledPattern_(
					unitsBatch,
					new String[] { "Tag name: " },
					null, 3, 0, 2, true);
		}
	}

	public static int minBoxSize(Tags tag, String captionLabel, String caption) {
		int biggest = getBiggestStringSize(tag.split());

		String finalCaption = captionLabel + caption;
		if (finalCaption.length() > biggest)
			biggest = finalCaption.length();

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

	private static String generateHorizontalLine(Renderable[] units, String biggestCaptionLabel, int cols, String border, String spacer, int captionIndex) {
		StringBuilder line = new StringBuilder();
		int renderedCols = 1;
		int biggestMbs = 0;

		for (int i = 0; i < units.length; i++) {
			Renderable u = units[i];
			int individualMbs = minBoxSize(
					u.getTag(),
					biggestCaptionLabel,
					getBiggestString(u.getCaptions()));
			if (individualMbs > biggestMbs) biggestMbs = individualMbs;
		}


		for (int i = 0; i < units.length; i++) {
			Renderable u = units[i];

			if (captionIndex != -1) {
				String caption = u.getCaptions()[captionIndex];
				int[] padding = boxPadding(biggestMbs, biggestCaptionLabel + caption);

				line.append(
					border +
					" ".repeat(padding[0]) +
					biggestCaptionLabel + caption.toLowerCase() +
					" ".repeat(padding[1]) +
					border
				);

			} else {
				line.append(border + spacer.repeat(biggestMbs) + border);
			}

			line.append("  ");

			if (renderedCols == cols)
				break;
			renderedCols++;
		}

		return line.toString();
	}

	public static void drawMultipleCards(Renderable[] units, String[] captionsLabels, String[] sideDataLabels) {
		for (int i = 0; i < units.length; i++) {
			drawCard(units[i], captionsLabels, sideDataLabels);
		}
	}

	public static void drawCard(Renderable unit, String[] captionsLabels, String[] sideDataLabels) {
		if (captionsLabels == null) captionsLabels = new String[] {"bottoplceholdr"};
		drawTiledPattern_(new Renderable[] {unit}, captionsLabels, sideDataLabels, 1, 8, 2, false);
	}

	public static void drawTiledPattern_(Renderable[] units, String[] captionsLabels, String[] sideDataLabels, int cols, int offset, int verticalSpacers, boolean printCaptions) {
		if (captionsLabels == null)
			captionsLabels = new String[] { "" };

		String biggestCaptionLabel = getBiggestString(captionsLabels);
		int biggestMbs = 0;
		int dataIndex = -1;

		for (int i = 0; i < units.length; i++) {
			Renderable u = units[i];
			int individualMbs = minBoxSize(
					u.getTag(),
					biggestCaptionLabel,
					getBiggestString(u.getCaptions()));
			if (individualMbs > biggestMbs) biggestMbs = individualMbs;
		}

		String horizontalHeaders = generateHorizontalLine(units, biggestCaptionLabel, cols, "+", "-", -1);
		String horizontalSpacers = generateHorizontalLine(units, biggestCaptionLabel, cols, "|", " ", -1);
		String[] horizontalCaptions = new String[captionsLabels.length];
		String entireLeftPadding = " ".repeat(center(horizontalHeaders, 0, true, false, true).length() - offset);
		int linesCount = units[0].getTag().split().length;

		for (int i = 0; i < captionsLabels.length; i++) {
			horizontalCaptions[i] = generateHorizontalLine(units, captionsLabels[i], cols, "|", " ", i);
		}

		print(entireLeftPadding + horizontalHeaders);
		for (int i = 0; i < verticalSpacers; i++)
			print(entireLeftPadding + horizontalSpacers);

		// draw the ascii art
		for (int row = 0; row < linesCount; row++) {
			for (int col = 0; col < cols; col++) {
				if (col > 0) {
					print("  ", false);
				}
				Renderable u = units[col];
				Tags _tag = u.getTag();
				String[] lines = _tag.split();
				String line = lines[row];

				int[] padding = boxPadding(biggestMbs, line);
				String currLine = "|" + " ".repeat(padding[0]) + line + " ".repeat(padding[1]) + "|";

				if (col == 0)
					print(entireLeftPadding + currLine, false);
				else
					print(currLine, false);

				if (sideDataLabels != null) {
					if (dataIndex >= 0 && dataIndex < sideDataLabels.length)
						print("\t" + sideDataLabels[dataIndex] + u.getSideData()[dataIndex], false);
				}

				dataIndex++;

			}
			print();
		}

		
		for (int i = 0; i < verticalSpacers; i++)
			print(entireLeftPadding + horizontalSpacers);

		if (printCaptions) {
			for (int i = 0; i < horizontalCaptions.length; i++)
				print(entireLeftPadding + horizontalCaptions[i]);
		}

		print(entireLeftPadding + horizontalHeaders);
	}
}
