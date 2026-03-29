// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.Gupta
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using Gupta_Global_Declarations = com.eagle.programmar.Gupta.Declarations.Gupta_Global_Declarations;

	public class Gupta_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Gupta";
		}

		public Gupta_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "";

			findFirstWords(typeof(Gupta_Global_Declarations));
		}
	}

}
