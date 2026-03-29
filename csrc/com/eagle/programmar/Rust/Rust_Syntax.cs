// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

namespace com.eagle.programmar.Rust
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class Rust_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = true;

		public override string syntaxId()
		{
			return "Rust";
		}

		public Rust_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = null;
			_extraCharacters = "_";
			_punctuationExceptions = new string[] {"!=", "%=", "&=", "&&", "*=", "+=", "-=", "->", "::", "..", "..=", "...", "/=", "<<", "<<=", "<=", "==", "=>", ">>", ">>=", ">=", "^=", "|=", "||"};

			addReservedWords(Rust_Reserved_Words.RESERVED_WORDS);
		}
	}
}
