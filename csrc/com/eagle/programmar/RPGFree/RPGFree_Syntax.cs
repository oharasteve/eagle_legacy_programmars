// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 7, 2025

namespace com.eagle.programmar.RPGFree
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class RPGFree_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "RPGFree";
		}

		public RPGFree_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "?";
			_extraCharacters = "";
			_autoAdvance = true;
		}
	}

}
