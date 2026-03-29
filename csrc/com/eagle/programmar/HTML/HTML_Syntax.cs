// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.HTML
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class HTML_Syntax : EagleSyntax
	{
		public static readonly string[] PUNCT = new string[] {"<!", "<!--", "</", "/>", "{%", "%}", "<?", "?>", "<%@", "%>"};

		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "HTML";
		}

		public HTML_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "";
			_punctuationExceptions = PUNCT;

			addReservedWords(RESERVED_WORDS);
		}

		private static readonly string[] RESERVED_WORDS = new string[] {"a", "caption", "pre", "script", "span", "style", "table", "td", "tr"};
	}

}
