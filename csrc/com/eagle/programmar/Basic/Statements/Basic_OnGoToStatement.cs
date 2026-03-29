// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 20, 2025

namespace com.eagle.programmar.Basic.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Basic_Expression = com.eagle.programmar.Basic.Basic_Expression;
	using Basic_StateMachine = com.eagle.programmar.Basic.Basic_StateMachine;
	using Basic_Keyword = com.eagle.programmar.Basic.Terminals.Basic_Keyword;
	using Basic_Number = com.eagle.programmar.Basic.Terminals.Basic_Number;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Basic_OnGoToStatement : TokenSequence, AbstractStatement, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Basic.Terminals.Basic_Keyword ON = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("ON");
		public Basic_Keyword ON = new Basic_Keyword("ON");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Basic.Basic_Expression expr;
		public Basic_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Basic.Terminals.Basic_Keyword GOTO = new com.eagle.programmar.Basic.Terminals.Basic_Keyword("GOTO");
		public Basic_Keyword GOTO = new Basic_Keyword("GOTO");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.SeparatedList<com.eagle.programmar.Basic.Terminals.Basic_Number, com.eagle.tokens.punctuation.PunctuationComma> labels;
		public SeparatedList<Basic_Number, PunctuationComma> labels;

		public override void interpret(EagleInterpreter interpreter)
		{
			Basic_StateMachine state = (Basic_StateMachine) interpreter._state;
			int index = interpreter.getIntValue(expr);
			if (index < 1 || index > labels.getPrimaryCount())
			{
				throw new Exception("ON / GOTO invalid index: " + index);
			}
			Basic_Number lbl = labels.getPrimaryElement(index - 1);
			int label = int.Parse(lbl.getValue());
			state.gotoStatement(label); // Goto this label
		}
	}

}
