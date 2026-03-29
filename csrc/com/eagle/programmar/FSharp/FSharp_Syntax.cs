// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class FSharp_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "FSharp";
		}

		public FSharp_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_extraCharacters = "";
			_autoAdvance = false;
			_punctuationExceptions = new string[] {"<-", "..", "[|", "|]", "||", "<>", "<=", ">="};

			addReservedWords(FSharp_Reserved_Words.RESERVED_WORDS);
		}

		public class FSharp_Multiline_Syntax : FSharp_Syntax
		{
			public override string syntaxId()
			{
				return "FSharp Multi";
			}

			public FSharp_Multiline_Syntax()
			{
				_autoAdvance = true;
			}
		}
	}

}
