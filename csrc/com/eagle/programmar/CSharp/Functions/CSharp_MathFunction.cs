// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 4, 2024

namespace com.eagle.programmar.CSharp.Functions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class CSharp_MathFunction : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CSharp_Keyword SYSTEM = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("System");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE PunctuationPeriod dot1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Keyword MATH = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("Math");
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationPeriod dot2;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE CSharp_MathChoice choice;
		public  NOSPACE;

		public class CSharp_MathChoice : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_MathAbsFunc XXmathAbsFunction;
			public CSharp_MathAbsFunc XXmathAbsFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_MathPowFunc XXmathPowFunction;
			public CSharp_MathPowFunc XXmathPowFunction;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_MathMinMaxFunc XXmathMinMaxFunction;
			public CSharp_MathMinMaxFunc XXmathMinMaxFunction;
		}

		public static CSharp_Expression wrapMathFunction(AbstractToken choice, AbstractToken source)
		{
			CSharp_MathFunction func = new CSharp_MathFunction();
			func.SYSTEM.setPresent(true);
			func.dot1 = new PunctuationPeriod();
			func.dot1.setPresent(true);
			func.dot2 = new PunctuationPeriod();
			func.choice = new CSharp_MathChoice();
			func.choice.setWhich(choice);
			func.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(func);
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(choice);
		}
	}

}
