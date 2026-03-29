// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.Perl
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using Perl_Comment = com.eagle.programmar.Perl.Terminals.Perl_Comment;

	public class Perl_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "Perl";
		}

		public Perl_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			_commentInstance = new Perl_Comment();
			_punctuationExceptions = new string[] {"..", "===", "!==", "::", "==", "->", "<=", ">=", "<<", ">>", "!=", "=>", "=~", "?>", "//", "/*", "**"};

			addReservedWords(Perl_Reserved_Words.RESERVED_WORDS);
		}
	}

}
