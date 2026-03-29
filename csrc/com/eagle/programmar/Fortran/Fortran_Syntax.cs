// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 23, 2022

namespace com.eagle.programmar.Fortran
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Fortran_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "Fortran";
		}

		public Fortran_Syntax()
		{
			_autoAdvance = false;
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "&";
			_extraCharacters = "";
			_fixedStartColumn = 6;
			_fixedEndColumn = 72;

			_continuationColumn = 5; // if 6th column is a * then continue previous line
			_continuationColumnChar = '*';

			// _commentInstance = new Fortran_Comment();
			_punctuationExceptions = new string[] {"/=", "::", "//", "<=", ">="};

			addReservedWords(RESERVED_WORDS);
		}

		private static readonly string[] RESERVED_WORDS = new string[] {"call", "common", "else", "end", "exit", "function", "if", "implicit", "print", "program", "subroutine", "then", "write"};
	}

}
