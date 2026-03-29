// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2013

namespace com.eagle.programmar.Lisp
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using Lisp_Comment = com.eagle.programmar.Lisp.Terminals.Lisp_Comment;

	public class Lisp_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Lisp";
		}

		public Lisp_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			_commentInstance = new Lisp_Comment();
			_punctuationExceptions = new string[] {"<=", ">=", "/=", "++", "+++", "**", "***"};

			// addReservedWords(RESERVED_WORDS); // None needed!
		}
	}

}
