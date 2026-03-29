// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

namespace com.eagle.programmar.ObjectiveC
{
	using C_Reserved_Words = com.eagle.programmar.C.C_Reserved_Words;
	using C_Syntax = com.eagle.programmar.C.C_Syntax;

	public class ObjectiveC_Syntax : C_Syntax
	{
		public override string syntaxId()
		{
			return "ObjC";
		}

		public ObjectiveC_Syntax()
		{
			addReservedWords(C_Reserved_Words.RESERVED_WORDS);
			addReservedWords(reservedWords);
		}

		private static string[] reservedWords = new string[] {"BOOL", "NO", "YES"};
	}

}
