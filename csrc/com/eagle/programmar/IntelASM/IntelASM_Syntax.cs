// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.IntelASM
{
	using EagleSyntax = com.eagle.core.EagleSyntax;

	public class IntelASM_Syntax : EagleSyntax
	{
		public const bool IS_CASE_SENSITIVE = false;

		public override string syntaxId()
		{
			return "IntelASM";
		}

		public IntelASM_Syntax()
		{
			_isCaseSensitive = IS_CASE_SENSITIVE;
			_continuationChar = "_";
			_extraCharacters = "";
			_autoAdvance = false;

			addReservedWords(RESERVED_WORDS);
			addReservedWords(IntelASM_Register._REGISTERS);
		}

		private static readonly string[] RESERVED_WORDS = new string[] {"BYTE", "COUNT", "DWORD", "PTR"};
	}

}
