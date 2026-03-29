// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

namespace com.eagle.programmar.Django
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using HTML_Syntax = com.eagle.programmar.HTML.HTML_Syntax;

	public class Django_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Django";
		}

		public Django_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "";
			_punctuationExceptions = HTML_Syntax.PUNCT;

			addReservedWord("default");
			addReservedWord("super");
		}
	}

}
