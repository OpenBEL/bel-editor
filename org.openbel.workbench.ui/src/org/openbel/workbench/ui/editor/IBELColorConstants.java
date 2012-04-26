/**
 * Copyright (c) 2012 Selventa.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html

 * Contributors:
 *     Selventa - initial API and implementation
 */

package org.openbel.workbench.ui.editor;

import org.eclipse.swt.graphics.RGB;

public interface IBELColorConstants {
	
	RGB CONTROL_WORD = new RGB(200, 0, 200);	// Dark Magenta
	RGB OBJECT_WORD = new RGB(56, 142, 142);	// Dark Teal		
	RGB BEL_WORD = new RGB(120, 0, 0);			// Maroon
	RGB BEL_RELATIONSHIP = new RGB(128, 0, 0);	// Maroon

	RGB BEL_COMMENT = new RGB(0, 128, 0);		// Dark Green Italic
	
	RGB PROC_INSTR = new RGB(128, 128, 128);	// Grey
	
	RGB STRING = new RGB(0, 0, 200);			// Blue
	RGB DEFAULT = new RGB(0, 0, 128);			// Dark Blue
}
