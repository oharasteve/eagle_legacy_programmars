// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

namespace com.eagle.programmar.TCL.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleInteger = com.eagle.math.EagleInteger;
	using TCL_Expression = com.eagle.programmar.TCL.TCL_Expression;
	using TCL_Variable = com.eagle.programmar.TCL.TCL_Variable;
	using TCL_Keyword = com.eagle.programmar.TCL.Terminals.TCL_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class TCL_IncrStatement : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("TclCmd/incr.html") com.eagle.programmar.TCL.Terminals.TCL_Keyword INCR = new com.eagle.programmar.TCL.Terminals.TCL_Keyword("incr");
		public @DOC("TclCmd/incr.html") TCL_Keyword INCR = new TCL_Keyword("incr");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.TCL.TCL_Variable var;
		public TCL_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TCL_Expression amount;
		public @OPT TCL_Expression amount;

		public void interpret(EagleInterpreter interpreter)
		{
			int x = 1;
			if (amount != null && amount.isPresent())
			{
				x = interpreter.getIntValue(amount);
			}

			int prev = interpreter.getIntValue(var);
			int newV = prev + x;
			EagleInteger val = new EagleInteger(newV);
			interpreter.setSymbol(var, var.id.getValue(), val);
		}

		public AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscrExpr = null;
			AbstractExpression value;
			if (amount != null && amount.isPresent())
			{
				value = transformer.transformExpression(generator, amount);
			}
			else
			{
				value = generator.newNumberExpression("1", INCR);
			}
			return generator.newAssignmentExpression(var.id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, EagleGenerator.AssignmentEnum.PLUS_EQUALS, value, this);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression asgExpr = this.transformExpression(transformer, generator);
			AbstractStatement exprStmt = generator.newExpressionStatement(asgExpr, this);
			return exprStmt;
		}
	}

}
