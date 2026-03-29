// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2026

namespace com.eagle.programmar.Fortran.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Oper2Types = com.eagle.metrics.Operator2Metrics.Oper2Types;
	using Fortran_Expression = com.eagle.programmar.Fortran.Fortran_Expression;
	using Fortran_Variable = com.eagle.programmar.Fortran.Fortran_Variable;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AdditiveEnum = com.eagle.transform.EagleGenerator.AdditiveEnum;
	using SubstringSCEnum = com.eagle.transform.EagleGenerator.SubstringSCEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_IndexFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Fortran.Terminals.Fortran_Keyword INDEX = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("INDEX");
		public Fortran_Keyword INDEX = new Fortran_Keyword("INDEX");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Fortran_Variable variable;
		public Fortran_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Fortran.Fortran_Expression pattern;
		public Fortran_Expression pattern;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;

		public override void interpret(EagleInterpreter interpreter)
		{
			string str = interpreter.getStrValue(variable);
			string patt = interpreter.getStrValue(pattern);
			int pos = str.IndexOf(patt, StringComparison.Ordinal);
			if (pos < 0)
			{
				interpreter.pushInt(0);
			}
			else
			{
				interpreter.pushInt(pos + 1);
			}
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractVariable var = generator.newVariable(variable.var.getValue());
			AbstractExpression pattExpr = transformer.transformExpression(generator, pattern);
			AbstractExpression indexFunc = generator.newIndexOfFunction(var, pattExpr, null, EagleGenerator.SubstringSCEnum.FIRST_CHAR_IS_ONE, this);
			AbstractExpression oneExpr = generator.newNumberExpression("1", null);
			Oper2Types types = new Oper2Types(EagleGenerator.TypeEnum.INTEGER, EagleGenerator.TypeEnum.INTEGER);
			return generator.newAdditiveExpression(types, indexFunc, EagleGenerator.AdditiveEnum.PLUS, oneExpr, this);
		}
	}
}
