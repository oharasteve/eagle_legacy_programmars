// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

namespace com.eagle.programmar.Python
{
	using EagleSyntax = com.eagle.core.EagleSyntax;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;

	public class Python_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "Python";
		}

		public Python_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "\\";
			_extraCharacters = "_";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {">>", "|=", "+=", "-=", "*=", "/=", "__", "_$", "**", ":=", "->", "..", "..."};

			_commentInstance = new Python_Comment();

			addReservedWords(Python_Reserved_Words.RESERVED_WORDS);
		}

		public class Python_Multiline_Syntax : Python_Syntax
		{
			public override string syntaxId()
			{
				return "Python Multi";
			}

			public Python_Multiline_Syntax()
			{
				_autoAdvance = true;
			}
		}
	}

}
