// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.Go
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Go_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "Go";
		}

		public Go_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_extraCharacters = "";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {":=", "==", "!=", "<=", ">=", "++", "--", "+=", "-=", "*=", "/-", "!~"};

			addReservedWords(Go_Reserved_Words.RESERVED_WORDS);
		}

		public class Go_Multiline_Syntax : Go_Syntax
		{
			public override string syntaxId()
			{
				return "Go Multi";
			}

			public Go_Multiline_Syntax()
			{
				_autoAdvance = true;
			}
		}
	}

}
