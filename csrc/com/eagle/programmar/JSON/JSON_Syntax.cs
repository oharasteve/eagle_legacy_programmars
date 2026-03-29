// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

namespace com.eagle.programmar.JSON
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using JSON_Comment = com.eagle.programmar.JSON.Terminals.JSON_Comment;

	public class JSON_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "JSON";
		}

		public JSON_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "";
			_commentInstance = new JSON_Comment();
		}
	}

}
