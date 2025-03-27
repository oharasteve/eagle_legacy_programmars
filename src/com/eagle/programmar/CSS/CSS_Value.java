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
	public @CHOICE CSS_Alpha_Value XXalphaValue;
	public @CHOICE CSS_Attr XXattr;
	public @CHOICE CSS_Color XXcolor;
	public @CHOICE CSS_Cubic_Bezier XXcubicBezier;
	public @CHOICE CSS_Format XXformat;
	public @CHOICE CSS_Gradient XXgradient;
	public @CHOICE CSS_HexNumber XXhex;
	public @CHOICE CSS_Important XXimportant;
	public @CHOICE CSS_Literal XXlit;
	public @CHOICE CSS_Microsoft_Gradient XXmsGradient;
	public @CHOICE CSS_NumericValue XXnumericValue;
	public @CHOICE CSS_Rect XXrect;
	public @CHOICE CSS_RotateValue XXrotateValue;
	public @CHOICE CSS_Scale XXscale;
	public @CHOICE CSS_Translate3d XXtranslate3d;
	public @CHOICE CSS_URL_Value XXurl;
	public @CHOICE CSS_Webkit_Image_Set XXwebkitImageSet;

	public @LAST CSS_IdentifierValue XXidentifierValue;
}
