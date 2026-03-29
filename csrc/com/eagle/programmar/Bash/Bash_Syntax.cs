// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2014

namespace com.eagle.programmar.Bash
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Bash_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "Bash";
		}

		public Bash_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "\\";
			_extraCharacters = "";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"==", "!=", "[[", "]]", "((", "))", "..", "#!", ">>", "&>", "$#", "$?", "$@", "$*", "&&", "||", "&>>", "+=", "-=", ">=", "<=", "++"};

			addReservedWords(Bash_Reserved_Words.RESERVED_WORDS);
			addHyphenWords(Bash_Reserved_Words.HYPHEN_WORDS);
		}
	}

}
