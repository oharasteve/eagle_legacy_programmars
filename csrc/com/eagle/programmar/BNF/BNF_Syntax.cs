// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

namespace com.eagle.programmar.BNF
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class BNF_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "BNF";
		}

		public BNF_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_extraCharacters = "";
			_punctuationExceptions = new string[] {"::="};
		}
	}

}
