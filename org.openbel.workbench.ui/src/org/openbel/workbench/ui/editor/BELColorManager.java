package org.openbel.workbench.ui.editor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * @author julianray
 *
 */
public class BELColorManager {

	protected Map<RGB, Color> colorTable = new HashMap<RGB, Color>(10);
	/**
	 * 
	 */
	public void dispose() {
		Iterator<Color> e = colorTable.values().iterator();
		while (e.hasNext())
			 e.next().dispose();
	}
	/**
	 * 
	 * @param rgb
	 * @return
	 */
	public Color getColor(RGB rgb) {
		Color color = colorTable.get(rgb);
		if (color == null) {
			color = new Color(Display.getCurrent(), rgb);
			colorTable.put(rgb, color);
		}
		return color;
	}
}
