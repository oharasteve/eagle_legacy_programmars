// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.SQL
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using SQL_Comment = com.eagle.programmar.SQL.Terminals.SQL_Comment;

	public class SQL_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "SQL";
		}

		public SQL_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			_commentInstance = new SQL_Comment();
			_punctuationExceptions = new string[] {"!=", "<=", ">=", "=>", "||", "&&", "/*", "//"};

			addReservedWords(SQL_Reserved_Words.RESERVED_WORDS);
		}
	}

}
