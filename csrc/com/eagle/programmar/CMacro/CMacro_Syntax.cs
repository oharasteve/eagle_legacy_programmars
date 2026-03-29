// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.CMacro
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class CMacro_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "CMacro";
		}

		public CMacro_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "\\";
			_extraCharacters = "_";
			_autoAdvance = false;
			// _commentInstance = new C_Comment();
			_punctuationExceptions = new string[] {"!=", "<=", "==", ">=", "/*", "&&", "||", "##", "..."};
		}
	}

}
