// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.CSS
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using CSS_Comment = com.eagle.programmar.CSS.Terminals.CSS_Comment;

	public class CSS_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "CSS";
		}

		public CSS_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "";
			_punctuationExceptions = new string[] {"::", "--"};
			_commentInstance = new CSS_Comment();

			addReservedWords(RESERVED_WORDS);
		}

		private static readonly string[] RESERVED_WORDS = new string[] {"media", "-moz-document", "namespace", "not", "rgb", "rgba", "rotate", "url-prefix"};
	}

}
