// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

namespace com.eagle.programmar.TCL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class TCL_PutsStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("TclCmd/puts.html") com.eagle.programmar.TCL.Terminals.TCL_Keyword PUTS = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("puts");
		public @DOC("TclCmd/puts.html") TCL_Keyword PUTS = new TCL_Keyword("puts");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.TCL_Expression expr;
		public TCL_Expression expr;

		public void interpret(EagleInterpreter interpreter)
		{
			string val = interpreter.getStrValue(expr);
			Console.WriteLine(val);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression line = transformer.transformExpression(generator, expr);
			return generator.newPrintStatement(line, EagleGenerator.TypeEnum.STRING, true, false, this);
		}
	}

}
