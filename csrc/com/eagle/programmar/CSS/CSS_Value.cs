// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 12, 2014

namespace com.eagle.programmar.CSS
{
	using CSS_Color = com.eagle.programmar.CSS.Terminals.CSS_Color;
	using CSS_HexNumber = com.eagle.programmar.CSS.Terminals.CSS_HexNumber;
	using CSS_Literal = com.eagle.programmar.CSS.Terminals.CSS_Literal;
	using CSS_Alpha_Value = com.eagle.programmar.CSS.Values.CSS_Alpha_Value;
	using CSS_Attr = com.eagle.programmar.CSS.Values.CSS_Attr;
	using CSS_Cubic_Bezier = com.eagle.programmar.CSS.Values.CSS_Cubic_Bezier;
	using CSS_Format = com.eagle.programmar.CSS.Values.CSS_Format;
	using CSS_Gradient = com.eagle.programmar.CSS.Values.CSS_Gradient;
	using CSS_IdentifierValue = com.eagle.programmar.CSS.Values.CSS_IdentifierValue;
	using CSS_Important = com.eagle.programmar.CSS.Values.CSS_Important;
	using CSS_Microsoft_Gradient = com.eagle.programmar.CSS.Values.CSS_Microsoft_Gradient;
	using CSS_NumericValue = com.eagle.programmar.CSS.Values.CSS_NumericValue;
	using CSS_Rect = com.eagle.programmar.CSS.Values.CSS_Rect;
	using CSS_RotateValue = com.eagle.programmar.CSS.Values.CSS_RotateValue;
	using CSS_Scale = com.eagle.programmar.CSS.Values.CSS_Scale;
	using CSS_Translate3d = com.eagle.programmar.CSS.Values.CSS_Translate3d;
	using CSS_URL_Value = com.eagle.programmar.CSS.Values.CSS_URL_Value;
	using CSS_Webkit_Image_Set = com.eagle.programmar.CSS.Values.CSS_Webkit_Image_Set;
	using TokenChooser = com.eagle.tokens.TokenChooser;

	public class CSS_Value : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Alpha_Value XXalphaValue;
		public CSS_Alpha_Value XXalphaValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Attr XXattr;
		public CSS_Attr XXattr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Color XXcolor;
		public CSS_Color XXcolor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Cubic_Bezier XXcubicBezier;
		public CSS_Cubic_Bezier XXcubicBezier;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Format XXformat;
		public CSS_Format XXformat;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Gradient XXgradient;
		public CSS_Gradient XXgradient;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_HexNumber XXhex;
		public CSS_HexNumber XXhex;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Important XXimportant;
		public CSS_Important XXimportant;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Literal XXlit;
		public CSS_Literal XXlit;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Microsoft_Gradient XXmsGradient;
		public CSS_Microsoft_Gradient XXmsGradient;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_NumericValue XXnumericValue;
		public CSS_NumericValue XXnumericValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Rect XXrect;
		public CSS_Rect XXrect;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_RotateValue XXrotateValue;
		public CSS_RotateValue XXrotateValue;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Scale XXscale;
		public CSS_Scale XXscale;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Translate3d XXtranslate3d;
		public CSS_Translate3d XXtranslate3d;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_URL_Value XXurl;
		public CSS_URL_Value XXurl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSS_Webkit_Image_Set XXwebkitImageSet;
		public CSS_Webkit_Image_Set XXwebkitImageSet;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST CSS_IdentifierValue XXidentifierValue;
		public CSS_IdentifierValue XXidentifierValue;
	}

}
