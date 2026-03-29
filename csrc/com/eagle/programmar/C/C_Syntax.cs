// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.C
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using CMacro_Comment = com.eagle.programmar.CMacro.Terminals.CMacro_Comment;

	public class C_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "C";
		}

		public C_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "\\";
			_extraCharacters = "_";
			_commentInstance = new CMacro_Comment(); // Doesn't work at all
			_punctuationExceptions = new string[] {"!=", "<=", "==", ">=", "/*", "&&", "||", "..", "->", "++", "--", "::", "+=", "-=", "...", ">>", "<<"};

			addReservedWords(C_Reserved_Words.RESERVED_WORDS);
		}
	}

}
