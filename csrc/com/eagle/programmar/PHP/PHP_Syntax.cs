// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.PHP
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using HTML_Syntax = com.eagle.programmar.HTML.HTML_Syntax;

	public class PHP_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "PHP";
		}

		public PHP_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			_punctuationExceptions = HTML_Syntax.PUNCT;
		}
	}

}
