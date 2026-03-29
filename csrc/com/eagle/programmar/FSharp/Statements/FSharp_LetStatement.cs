// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

namespace com.eagle.programmar.FSharp.Statements
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using FSharp_Expression = com.eagle.programmar.FSharp.FSharp_Expression;
	using FSharp_Type = com.eagle.programmar.FSharp.FSharp_Type;
	using FSharp_Variable = com.eagle.programmar.FSharp.FSharp_Variable;
	using FSharp_EndOfLine = com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
	using FSharp_Keyword = com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class FSharp_LetStatement : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("functions/let-bindings") com.eagle.programmar.FSharp.Terminals.FSharp_Keyword LET = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("let");
		public @DOC("functions/let-bindings") FSharp_Keyword LET = new FSharp_Keyword("let");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT FSharp_Keyword MUTABLE = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("mutable");
		public @OPT FSharp_Keyword MUTABLE = new FSharp_Keyword("mutable");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT FSharp_Keyword MAIN = new com.eagle.programmar.FSharp.Terminals.FSharp_Keyword("main");
		public @OPT FSharp_Keyword MAIN = new FSharp_Keyword("main");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.FSharp.FSharp_Variable variable;
		public FSharp_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT FSharp_VariableType varType;
		public @OPT FSharp_VariableType varType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT FSharp_EndOfLine eoln1;
		public @OPT FSharp_EndOfLine eoln1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.FSharp.FSharp_Expression expression;
		public FSharp_Expression expression;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine eoln2;
		public FSharp_EndOfLine eoln2;

		public static class FSharp_VariableType extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.FSharp.FSharp_Type type;
			public FSharp_Type type;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expression);
			interpreter.setSymbol(variable, variable.id.getValue(), value);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractExpression subscrExpr = null;
			AbstractExpression value = transformer.transformExpression(generator, expression);
			AbstractExpression asgExpr = generator.newAssignmentExpression(variable.id.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, subscrExpr, EagleGenerator.AssignmentEnum.EQUALS, value, this);
			return generator.newExpressionStatement(asgExpr, this);
		}
	}

}
