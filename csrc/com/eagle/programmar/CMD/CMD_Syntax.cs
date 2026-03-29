// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.CMD
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class CMD_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "CMD";
		}

		public CMD_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "^"; // Perhaps a backslash (\) on some systems?
			_extraCharacters = "";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"::", "==", "&&", ">>", "||"};

			addReservedWords(new string[] {"defined", "else", "eof", "for", "not"});
		}
	}

}
