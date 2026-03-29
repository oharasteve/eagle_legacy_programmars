// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 21, 2012

namespace com.eagle.programmar.COBOL
{
	using COBOL_Free_Format_Syntax = com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Free_Format_Syntax;

	public class COBOL_Program_Free_Format : COBOL_Program_Complete
	{
		public const string COBOLFree = "COBOL_Free_Format";

		public COBOL_Program_Free_Format() : base(COBOLFree, new COBOL_Free_Format_Syntax())
		{
		}
	}

}
