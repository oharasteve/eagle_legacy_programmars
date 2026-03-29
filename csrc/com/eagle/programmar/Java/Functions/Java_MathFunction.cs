// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

namespace com.eagle.programmar.Java.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableExpression = com.eagle.transform.EagleTransformableExpression;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_MathFunction : PrimaryOperator, EagleRunnable, EagleTransformableExpression
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword MATH = new com.eagle.programmar.Java.Terminals.Java_Keyword("Math");
		public Java_Keyword MATH = new Java_Keyword("Math");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_MathChoice choice;
		public  NOSPACE;

		public class Java_MathChoice : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MathPowFunc XXmathPowFunction;
			public Java_MathPowFunc XXmathPowFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MathLogFunc XXmathLogFunction;
			public Java_MathLogFunc XXmathLogFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MathRoundFunc XXmathRoundFunction;
			public Java_MathRoundFunc XXmathRoundFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MathAbsFunc XXmathAbsFunction;
			public Java_MathAbsFunc XXmathAbsFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_MathMinMaxFunc XXmathMinMaxFunction;
			public Java_MathMinMaxFunc XXmathMinMaxFunction;
		}

		public static Java_Expression wrapMathFunction(AbstractToken choice, AbstractToken source)
		{
			Java_MathFunction func = new Java_MathFunction();
			func.dot = new PunctuationPeriod();
			func.choice = new Java_MathChoice();
			func.choice.setWhich(choice);
			func.setTransformationSource(source);
			return Java_Generator.wrapExpression(func);
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(choice);
		}

		public override AbstractExpression transformExpression(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = choice.getWhich();
			if (which is EagleTransformableExpression)
			{
				EagleTransformableExpression transf = (EagleTransformableExpression) which;
				return transf.transformExpression(transformer, generator);
			}
			throw new Exception("Please make " + which + " transformable");
		}
	}

}
