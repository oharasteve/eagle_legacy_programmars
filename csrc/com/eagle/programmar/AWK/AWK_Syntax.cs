// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class AWK_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "AWK";
		}

		public AWK_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_extraCharacters = "";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"==", "!=", "<=", ">=", "++", "--", "+=", "!~", "**"};

			addReservedWords(AWK_Reserved_Words.RESERVED_WORDS);
		}
	}

}
