// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 31, 2024

namespace com.eagle.programmar.Bash.Expressions
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_Variable = com.eagle.programmar.Bash.Bash_Variable;
	using Bash_Punctuation = com.eagle.programmar.Bash.Terminals.Bash_Punctuation;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;

	public class Bash_DollarSubstring : PrimaryOperator, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_Punctuation dollar = new com.eagle.programmar.Bash.Terminals.Bash_Punctuation("$");
		public Bash_Punctuation dollar = new Bash_Punctuation("$");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftBrace leftBrace;
		public PunctuationLeftBrace leftBrace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_Variable variable;
		public Bash_Variable variable;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationColon colon1;
		public PunctuationColon colon1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Bash.Bash_Expression start;
		public Bash_Expression start;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationColon colon2;
		public PunctuationColon colon2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Bash.Bash_Expression len;
		public Bash_Expression len;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightBrace rightBrace;
		public PunctuationRightBrace rightBrace;

		public override void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.findSymbol(variable.id.getValue());
			string str = val.forceStringValue();
			int nc = str.Length;
			int sc = interpreter.getIntValue(start);
			int ec = sc + interpreter.getIntValue(len);
			if (ec > nc)
			{
				ec = nc;
			}
			string piece = str.Substring(sc, ec - sc);
			interpreter.pushStr(piece);
		}
	}
}
