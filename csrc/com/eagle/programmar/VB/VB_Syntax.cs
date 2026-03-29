// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.VB
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class VB_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "VB";
		}

		public VB_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "_";
			_autoAdvance = false;
			_extraCharacters = "_";
			_punctuationExceptions = new string[] {"!=", "<=", "==", ">=", "/*"};

			addReservedWords(VB_Reserved_Words.RESERVED_WORDS);
		}
	}

}
