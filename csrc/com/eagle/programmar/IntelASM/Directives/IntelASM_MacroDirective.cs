// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 11, 2014

namespace com.eagle.programmar.IntelASM.Directives
{
	using IntelASM_Line = com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line;
	using IntelASM_Macro_Definition = com.eagle.programmar.IntelASM.Symbols.IntelASM_Macro_Definition;
	using IntelASM_EndOfLine = com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
	using IntelASM_Keyword = com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword;
	using IntelASM_Number = com.eagle.programmar.IntelASM.Terminals.IntelASM_Number;
	using IntelASM_Punctuation = com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;

	public class IntelASM_MacroDirective : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation percent = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation('%');
		public IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword MACRO = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("macro");
		public IntelASM_Keyword MACRO = new IntelASM_Keyword("macro");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.IntelASM.Symbols.IntelASM_Macro_Definition name;
		public IntelASM_Macro_Definition name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.IntelASM.Terminals.IntelASM_Number parameterCount;
		public IntelASM_Number parameterCount;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine eoln;
		public IntelASM_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.TokenList<com.eagle.programmar.IntelASM.IntelASM_Program.IntelASM_Line> lines;
		public TokenList<IntelASM_Line> lines;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) IntelASM_EndMacro endMacro;
		public IntelASM_EndMacro endMacro;

		public class IntelASM_EndMacro : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation percent = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Punctuation('%');
			public IntelASM_Punctuation percent = new IntelASM_Punctuation('%');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword ENDMACRO = new com.eagle.programmar.IntelASM.Terminals.IntelASM_Keyword("endmacro");
			public IntelASM_Keyword ENDMACRO = new IntelASM_Keyword("endmacro");
		}
	}

}
