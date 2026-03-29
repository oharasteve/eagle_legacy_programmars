// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 13, 2025

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
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;

	public class Java_StringFunction : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword STRING = new com.eagle.programmar.Java.Terminals.Java_Keyword("String");
		public Java_Keyword STRING = new Java_Keyword("String");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationPeriod dot;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE Java_StringChoice choice;
		public  NOSPACE;

		public class Java_StringChoice : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_StringFormatFunc XXstringFormatFunction;
			public Java_StringFormatFunc XXstringFormatFunction;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(choice);
		}

		public static Java_Expression wrapStringFunction(AbstractToken choice, AbstractToken source)
		{
			Java_StringFunction func = new Java_StringFunction();
			func.dot = new PunctuationPeriod();
			func.choice = new Java_StringChoice();
			func.choice.setWhich(choice);
			func.setTransformationSource(source);
			return Java_Generator.wrapExpression(func);
		}
	}

}
