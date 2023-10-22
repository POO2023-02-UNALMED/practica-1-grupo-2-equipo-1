package com.ecart.uiMain;

import com.ecart.gestorAplicacion.merchandise.Tags;

public class Renderable {
	private Tags tag;
	private String[] captions;
	private String[] sideData;

	public Renderable(Tags tag, String[] captions, String[] sideData) {
		this.tag = tag;
		if (captions == null)
			this.captions = new String[] { "" };
		else
			this.captions = captions;
		this.sideData = sideData;
	}

	public Tags getTag() {
		return tag;
	}

	public void setTag(Tags tag) {
		this.tag = tag;
	}

	public String[] getCaptions() {
		return captions;
	}

	public void setCaptions(String[] captions) {
		this.captions = captions;
	}

	public String[] getSideData() {
		return sideData;
	}

	public void setSideData(String[] sideData) {
		this.sideData = sideData;
	}

}
