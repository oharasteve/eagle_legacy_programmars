// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 8, 2025

namespace com.eagle.programmar.Basic
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Basic_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Basic";
		}

		public Basic_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"<=", ">="};

			addReservedWords(Basic_Reserved_Words.RESERVED_WORDS);
		}
	}

}
