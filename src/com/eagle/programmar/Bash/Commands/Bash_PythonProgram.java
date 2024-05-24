// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 20, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_FilenameOrLiteral;
import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
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

public class Bash_PythonProgram extends TokenChooser
{
	public @CHOICE static class Bash_PythonScript extends TokenSequence
	{
		public @S(10) Bash_SheBang shebang;
		public @S(20) @OPT SeparatedList<PunctuationSlash, Bash_Identifier_Reference> dir;
		public @S(30) @OPT PunctuationSlash slash;
		public @S(40) Bash_KeywordChoice PYTHON = new Bash_KeywordChoice("python", "python3");
		public @S(50) @OPT TokenList<Bash_PythonOption> options;
		public @S(60) Bash_RealEndOfLine eoln;
		public @S(70) @SYNTAX(Python_Syntax.class) Python3_Program pyProg;
	}

	public @CHOICE static class Bash_PythonExec extends TokenSequence
	{
		public @S(10) @OPT SeparatedList<PunctuationSlash, Bash_Identifier_Reference> dir;
		public @S(20) @OPT PunctuationSlash slash;
		public @S(30) Bash_KeywordChoice PYTHON = new Bash_KeywordChoice("python", "python3");
		public @S(40) @OPT TokenList<Bash_PythonOption> options;
		public @S(50) Bash_FilenameOrLiteral name;
		public @S(60) @OPT TokenList<Bash_FilenameOrLiteral> args;
		public @S(70) Bash_EndOfLine eoln;
	}
}
