// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

namespace com.eagle.programmar.COBOL
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public abstract class COBOL_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "COBOL";
		}

		public COBOL_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "-";
			_punctuationExceptions = new string[] {"**", "<=", ">=", "<>"};

			addReservedWords(COBOL_Reserved_Words.RESERVED_WORDS);
			// findFirstWords(COBOL_Statement.class);
		}

		public class COBOL_Fixed_Format_Syntax : COBOL_Syntax
		{
			public override string syntaxId()
			{
				return "COBOL Fixed";
			}

			public COBOL_Fixed_Format_Syntax()
			{
				_commentColumn = 6; // 0 = first for all these
				_earliestComment = 11; // Comments to end-of-line must start in column 12 or later
				_fixedStartColumn = 6;
				_fixedEndColumn = 72;
			}
		}

		public class COBOL_Free_Format_Syntax : COBOL_Syntax
		{
			public override string syntaxId()
			{
				return "COBOL Free";
			}

			public COBOL_Free_Format_Syntax()
			{
				_commentColumn = 0; // Default is 0 anyways
				_earliestComment = 1; // Comments to end-of-line must start in column 2 or later
			}
		}
	}

}
