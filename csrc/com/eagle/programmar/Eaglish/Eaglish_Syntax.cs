// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2024

namespace com.eagle.programmar.Eaglish
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Eaglish_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Eaglish";
		}

		public Eaglish_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_autoAdvance = false;
			_continuationChar = null;
			_extraCharacters = "";
			_punctuationExceptions = new string[] {"<=", ">="};
			addReservedWords(RESERVED_WORDS);
		}

		private static readonly string[] RESERVED_WORDS = new string[] {"NOT"};
	}

}
