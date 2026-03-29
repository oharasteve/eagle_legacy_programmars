// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.CSharp
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;

	public class CSharp_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "CSharp";
		}

		public CSharp_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			_punctuationExceptions = new string[] {"!=", "<=", "==", ">=", "=>", "//", "...", "::", "||", "&&"};
			_commentInstance = new CSharp_Comment();

			addReservedWords(CSharp_Reserved_Words.RESERVED_WORDS);
		}
	}

}
