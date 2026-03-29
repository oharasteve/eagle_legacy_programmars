// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Template
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Template_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Template";
		}

		public Template_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			_punctuationExceptions = new string[] {"<=", ">=", "==", "!="};

			addReservedWords(RESERVED_WORDS);
		}

		private static string[] RESERVED_WORDS = new string[] {"and", "data", "not", "or", "print"};
	}
}
