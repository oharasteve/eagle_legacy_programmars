// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

namespace com.eagle.programmar.Fortran.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleString = com.eagle.math.EagleString;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using Oper1Types = com.eagle.metrics.Operator1Metrics.Oper1Types;
	using Fortran_Format = com.eagle.programmar.Fortran.Fortran_Format;
	using Fortran_Variable = com.eagle.programmar.Fortran.Fortran_Variable;
	using Fortran_Variable_Reference = com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference;
	using Fortran_Comment = com.eagle.programmar.Fortran.Terminals.Fortran_Comment;
	using Fortran_EOLN = com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
	using Fortran_Keyword = com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
	using Fortran_Literal = com.eagle.programmar.Fortran.Terminals.Fortran_Literal;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Fortran_WriteStatement : TokenSequence, AbstractStatement, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("6j4m0vnbs/index.html") com.eagle.programmar.Fortran.Terminals.Fortran_Keyword WRITE = new com.eagle.programmar.Fortran.Terminals.Fortran_Keyword("WRITE");
		public @DOC("6j4m0vnbs/index.html") Fortran_Keyword WRITE = new Fortran_Keyword("WRITE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Fortran.Fortran_Variable var;
		public Fortran_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationComma comma;
		public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Fortran.Terminals.Fortran_Literal format;
		public Fortran_Literal format;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.SeparatedList<com.eagle.programmar.Fortran.Symbols.Fortran_Variable_Reference, com.eagle.tokens.punctuation.PunctuationComma> parameters;
		public SeparatedList<Fortran_Variable_Reference, PunctuationComma> parameters;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT Fortran_Comment comment;
		public @OPT Fortran_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Fortran.Terminals.Fortran_EOLN eoln;
		public Fortran_EOLN eoln;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP ArgumentsMetrics _metrics = null;
		private ArgumentsMetrics _metrics = null;

		public void interpret(EagleInterpreter interpreter)
		{
			if (_metrics == null)
			{
				_metrics = new ArgumentsMetrics(interpreter._metrics, WRITE.getValue(), WRITE);
			}
			List<EagleGenerator.TypeEnum> argTypes = new List<EagleGenerator.TypeEnum>();

			// Example: WRITE(numStr, '(I5)') numb
			// puts the number 'numb' into the string 'numStr' with format I5
			string formatted = Fortran_Format.format(interpreter, format.getValue(), parameters, argTypes);
			EagleString val = new EagleString(formatted);

			_metrics.calledWith(argTypes);
			interpreter.setSymbol(this, var.var.getValue(), val);
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Breaks in java. Creates Integer.toString(Integer.toString(ok))
			// ArrayList<String> metrics = transformer.findArgumentsMetric(WRITE);
			List<EagleGenerator.TypeEnum> metrics = null;

			AbstractExpression line = Fortran_Format.transform(transformer, generator, format.getValue(), parameters, metrics);
			Oper1Types types = new Oper1Types();
			types._type1 = EagleGenerator.TypeEnum.INTEGER;
			AbstractExpression expr = generator.newStringFunction(types, line, WRITE);
			AbstractExpression newValue = generator.newAssignmentExpression(var.var.getValue(), EagleGenerator.SubscriptEnum.FIRST_IS_ONE, null, EagleGenerator.AssignmentEnum.EQUALS, expr, WRITE);
			return generator.newExpressionStatement(newValue, WRITE);
		}
	}

}
