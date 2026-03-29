// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 29, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Bash_Expression = com.eagle.programmar.Bash.Bash_Expression;
	using Bash_Variable = com.eagle.programmar.Bash.Bash_Variable;
	using Bash_Keyword = com.eagle.programmar.Bash.Terminals.Bash_Keyword;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;

	public class Bash_ExportCommand : TokenSequence, AbstractStatement, EagleRunnable
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("#index-export") com.eagle.programmar.Bash.Terminals.Bash_Keyword EXPORT = new com.eagle.programmar.Bash.Terminals.Bash_Keyword("export");
		public @DOC("#index-export") Bash_Keyword EXPORT = new Bash_Keyword("export");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Bash_ExportOption> options;
		public @OPT TokenList<Bash_ExportOption> options;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Bash_Variable var;
		public Bash_Variable var;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationEquals equals;
		public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Bash.Bash_Expression expr;
		public Bash_Expression expr;

		public static class Bash_ExportOption extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Bash_KeywordChoice XXopt = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("-n");
			public Bash_KeywordChoice XXopt = new Bash_KeywordChoice("-n");
		}

		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue val = interpreter.getEagleValue(expr);
			interpreter.setSymbol(var, var.id.getValue(), val);
		}
	}

}
