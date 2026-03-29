// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.Ruby
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Ruby_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Ruby";
		}

		public Ruby_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_extraCharacters = "";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"==", "!=", "<=", ">=", "++", "--", "+=", ".."};

			addReservedWords(Ruby_Reserved_Words.RESERVED_WORDS);
		}
	}

}
