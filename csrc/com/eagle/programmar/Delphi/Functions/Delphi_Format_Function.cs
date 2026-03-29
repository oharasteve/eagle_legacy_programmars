// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Delphi.Functions
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Delphi_Argument_List = com.eagle.programmar.Delphi.Delphi_Argument_List;
	using Delphi_Expression = com.eagle.programmar.Delphi.Delphi_Expression;
	using Delphi_Format = com.eagle.programmar.Delphi.Delphi_Format;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;

	public class Delphi_Format_Function : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword FORMAT = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Format");
		public Delphi_Keyword FORMAT = new Delphi_Keyword("Format");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Delphi.Delphi_Argument_List arguments;
		public Delphi_Argument_List arguments;

		public override void interpret(EagleInterpreter interpreter)
		{
			Delphi_Expression fmtExpr = arguments.exprs.getPrimaryElement(0);
			string fmt = interpreter.getStrValue(fmtExpr);
			Delphi_Expression arrayExpr = arguments.exprs.getPrimaryElement(1);
			List<EagleValue> values = interpreter.getArrayValue(arrayExpr);
			string formatted = Delphi_Format.format(fmt, values);
			interpreter.pushStr(formatted);
		}
	}

}
