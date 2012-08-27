package net.hellonico.pwidgets;

import com.explodingpixels.macwidgets.SourceListItem;

public class NSourceListItem extends SourceListItem {
	private String category;

	public NSourceListItem(String category, String text) {
		super(text);
		this.category = category;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
