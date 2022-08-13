// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Terminals.CSS_Color;
import com.eagle.programmar.CSS.Terminals.CSS_HexNumber;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Values.CSS_Alpha_Value;
import com.eagle.programmar.CSS.Values.CSS_Attr;
import com.eagle.programmar.CSS.Values.CSS_Cubic_Bezier;
import com.eagle.programmar.CSS.Values.CSS_Format;
import com.eagle.programmar.CSS.Values.CSS_Gradient;
import com.eagle.programmar.CSS.Values.CSS_IdentifierValue;
import com.eagle.programmar.CSS.Values.CSS_Important;
import com.eagle.programmar.CSS.Values.CSS_Microsoft_Gradient;
import com.eagle.programmar.CSS.Values.CSS_NumericValue;
import com.eagle.programmar.CSS.Values.CSS_Rect;
import com.eagle.programmar.CSS.Values.CSS_RotateValue;
import com.eagle.programmar.CSS.Values.CSS_Scale;
import com.eagle.programmar.CSS.Values.CSS_Translate3d;
import com.eagle.programmar.CSS.Values.CSS_URL_Value;
import com.eagle.programmar.CSS.Values.CSS_Webkit_Image_Set;
import com.eagle.tokens.TokenChooser;

public class CSS_Value extends TokenChooser
{
	public @CHOICE CSS_Alpha_Value alphaValue; 
	public @CHOICE CSS_Attr attr;
	public @CHOICE CSS_Color color;
	public @CHOICE CSS_Cubic_Bezier cubicBezier;
	public @CHOICE CSS_Format format;
	public @CHOICE CSS_Gradient gradient;
	public @CHOICE CSS_HexNumber hex;
	public @CHOICE CSS_Important important;
	public @CHOICE CSS_Literal lit;
	public @CHOICE CSS_Microsoft_Gradient msGradient; 
	public @CHOICE CSS_NumericValue numericValue;
	public @CHOICE CSS_Rect rect;
	public @CHOICE CSS_RotateValue rotateValue;
	public @CHOICE CSS_Scale scale;
	public @CHOICE CSS_Translate3d translate3d;
	public @CHOICE CSS_URL_Value url;
	public @CHOICE CSS_Webkit_Image_Set webkitImageSet;

	public @LAST CSS_IdentifierValue identifierValue;
}
