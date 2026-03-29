// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 27, 2015

namespace com.eagle.programmar.CMacro
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleSymbolTable = com.eagle.math.EagleSymbolTable;
	using ParserManager = com.eagle.parsers.ParserManager;

	public class CMacro_Interpreter : EagleInterpreter
	{
		public CMacro_Interpreter(ParserManager parser, EagleSymbolTable symbolTable) : base(parser, null, symbolTable)
		{
		}
	}

}
