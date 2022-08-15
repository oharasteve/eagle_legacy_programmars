// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.programmar.Bash.Terminals.Bash_KeywordChoice;
import com.eagle.programmar.Bash.Terminals.Bash_RealEndOfLine;
import com.eagle.programmar.Bash.Terminals.Bash_SheBang;
import com.eagle.programmar.Python.Python3_Program;
import com.eagle.programmar.Python.Python_Syntax;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class Bash_PythonProgram extends TokenSequence
{
	public @S(10) @OPT Bash_SheBang shebang;
	public @S(20) @OPT SeparatedList<PunctuationSlash,Bash_Identifier_Reference> dir;
	public @S(30) @OPT PunctuationSlash slash;
	public @S(40) Bash_KeywordChoice PYTHON = new Bash_KeywordChoice("python", "python3");
	public @S(50) @OPT TokenList<Bash_PythonOption> options;
	public @S(60) Bash_PythonWhichWay which;
	
	public static class Bash_PythonOption extends TokenChooser
	{
		public @CHOICE static class Bash_PythonOptionM extends TokenSequence
		{
			public @S(10) Bash_Keyword M = new Bash_Keyword("-m");
			public @S(20) Bash_Identifier_Reference moduleName;
		}
	}
	
	public static class Bash_PythonWhichWay extends TokenChooser
	{
		public @CHOICE static class Bash_PythonRunFile extends TokenSequence
		{
			public @S(10) Bash_FilenameOrLiteral name;
			public @S(20) @OPT TokenList<Bash_FilenameOrLiteral> args;
			public @S(30) Bash_RealEndOfLine eoln;
		}
		
		public @CHOICE static class Bash_PythonInline extends TokenSequence
		{
			public @S(10) Bash_RealEndOfLine eoln;
			public @S(20) @SYNTAX(Python_Syntax.class) Python3_Program pyProg;
		}
	}
}
