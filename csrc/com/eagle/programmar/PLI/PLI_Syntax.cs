// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.PLI
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using PLI_Comment = com.eagle.programmar.PLI.Terminals.PLI_Comment;

	public class PLI_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "PLI";
		}

		public PLI_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			this._commentInstance = new PLI_Comment();
		}
	}

}
