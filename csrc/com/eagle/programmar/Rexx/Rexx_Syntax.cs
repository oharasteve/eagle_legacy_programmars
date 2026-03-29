// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 26, 2024

namespace com.eagle.programmar.Rexx
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Rexx_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Rexx";
		}

		public Rexx_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_autoAdvance = false;
			_extraCharacters = "_";
			_punctuationExceptions = new string[] {"\\=", "<=", ">=", "//"};

			addReservedWords(Rexx_Reserved_Words.RESERVED_WORDS);
		}
	}
}
