// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

namespace com.eagle.programmar.TCL
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class TCL_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "TCL";
		}

		public TCL_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			// _continuationChar = "+"; // THIS DOESN'T WORK WELL AT ALL.
			_extraCharacters = "_";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"--", "&&", "||", "<=", ">=", "<>", "!=", "=="};

			addReservedWords(RESERVED_WORDS);
		}

		private static readonly string[] RESERVED_WORDS = new string[] {"and", "break", "not", "or", "puts", "set"};
	}
}
