// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

namespace com.eagle.programmar.Bash.Commands
{
	using Bash_EndOfLine = com.eagle.programmar.Bash.Bash_EndOfLine;
	using Bash_FilenameOrLiteral = com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
	using Bash_Identifier_Reference = com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
	using Bash_KeywordChoice = com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
	using Bash_RealEndOfLine = com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine;
	using Bash_SheBang = com.eagle.programmar.Bash.Terminals.Bash_SheBang;
	using Python3_Program = com.eagle.programmar.Python.Python3_Program;
	using Python_Syntax = com.eagle.programmar.Python.Python_Syntax;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationSlash = com.eagle.tokens.punctuation.PunctuationSlash;

	public class Bash_PythonProgram : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_PythonScript extends com.eagle.tokens.TokenSequence implements com.eagle.tokens.interfaces.AbstractStatement
		public class Bash_PythonScript : TokenSequence, AbstractStatement
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Bash.Terminals.Bash_SheBang shebang;
			public Bash_SheBang shebang;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SeparatedList<com.eagle.tokens.punctuation.PunctuationSlash, com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference> dir;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationSlash slash;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice PYTHON = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("python", "python3");
			public Bash_KeywordChoice PYTHON = new Bash_KeywordChoice("python", "python3");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT TokenList<Bash_PythonOption> options;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine eoln;
			public Bash_RealEndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @SYNTAX(com.eagle.programmar.Python.Python_Syntax.class) com.eagle.programmar.Python.Python3_Program pyProg;
			public @SYNTAX(typeof(Python_Syntax)) Python3_Program pyProg;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Bash_PythonExec extends com.eagle.tokens.TokenSequence
		public static class Bash_PythonExec extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT SeparatedList<com.eagle.tokens.punctuation.PunctuationSlash, com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference> dir;
			public @OPT SeparatedList<PunctuationSlash, Bash_Identifier_Reference> dir;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PunctuationSlash slash;
			public @OPT PunctuationSlash slash;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice PYTHON = new com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice("python", "python3");
			public Bash_KeywordChoice PYTHON = new Bash_KeywordChoice("python", "python3");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Bash_PythonOption> options;
			public @OPT TokenList<Bash_PythonOption> options;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Bash.Bash_FilenameOrLiteral name;
			public Bash_FilenameOrLiteral name;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<com.eagle.programmar.Bash.Bash_FilenameOrLiteral> args;
			public @OPT TokenList<Bash_FilenameOrLiteral> args;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Bash.Bash_EndOfLine eoln;
			public Bash_EndOfLine eoln;
		}
	}

}
