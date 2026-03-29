// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

namespace com.eagle.programmar.Powershell
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Powershell_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Powershell";
		}

		public Powershell_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "`";
			_extraCharacters = "_-";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"<=", ">=", "==", "!=", "::", "++", "--", "*>", ">>", "$?", "$_", "<#", "#>", ".."};

			// Breaks everything - problem is the EOLN, not the comment itself.
			// _commentInstance = new Powershell_Comment();

			addReservedWords(Powershell_Reserved_Words.RESERVED_WORDS);
			addHyphenWords(Powershell_Reserved_Words.HYPHEN_WORDS);
		}
	}
}
