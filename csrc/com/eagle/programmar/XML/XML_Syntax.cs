// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

namespace com.eagle.programmar.XML
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using HTML_Syntax = com.eagle.programmar.HTML.HTML_Syntax;

	public class XML_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "XML";
		}

		public XML_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "-";
			_punctuationExceptions = HTML_Syntax.PUNCT;
		}
	}

}
