// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 14, 2025

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

	public class CSharp_StringFunction : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword STRING = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("string");
		public CSharp_Keyword STRING = new CSharp_Keyword("string");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_StringChoice choice;
		public  NOSPACE;

		public class CSharp_StringChoice : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_StringFormatFunc XXstringFormatFunction;
			public CSharp_StringFormatFunc XXstringFormatFunction;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(choice);
		}

		public static CSharp_Expression wrapStringFunction(AbstractToken choice, AbstractToken source)
		{
			CSharp_StringFunction func = new CSharp_StringFunction();
			func.dot = new PunctuationPeriod();
			func.choice = new CSharp_StringChoice();
			func.choice.setWhich(choice);
			func.setTransformationSource(source);
			return CSharp_Generator.wrapExpression(func);
		}
	}

}
