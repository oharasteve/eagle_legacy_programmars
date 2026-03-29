// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 6, 2022

namespace com.eagle.programmar.MSSolution
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class MSSolution_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "MSSolution";
		}

		public MSSolution_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_autoAdvance = false;
		}
	}

}
