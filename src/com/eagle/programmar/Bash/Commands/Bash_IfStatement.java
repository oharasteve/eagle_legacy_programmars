// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_Condition;
import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Statement;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;

public class Bash_IfStatement extends TokenSequence implements AbstractStatement
{
	public @S(10) @DOC("#Conditional-Constructs") Bash_Keyword IF = new Bash_Keyword("if");
	public @S(20) Bash_Condition condition;
	public @S(30) Bash_EndOfLine eoln1;

	public @S(40) Bash_Keyword THEN = new Bash_Keyword("then");
	public @S(50) @OPT Bash_EndOfLine eoln2;
	public @S(60) TokenList<Bash_Statement> statements;

	public @S(70) @OPT TokenList<Bash_If_Elif> elseIfBlock;

	public @S(80) @OPT Bash_If_Else elseBlock;

	public @S(90) Bash_Keyword FI = new Bash_Keyword("fi");

	public static class Bash_If_Elif extends TokenSequence
	{
		public @S(10) Bash_Keyword ELIF = new Bash_Keyword("elif");
		public @S(20) Bash_Condition condition;
		public @S(30) Bash_EndOfLine eoln;
		public @S(40) TokenList<Bash_Statement> statements;
	}

	public static class Bash_If_Else extends TokenSequence
	{
		public @S(10) Bash_Keyword ELSE = new Bash_Keyword("else");
		public @S(20) @OPT Bash_EndOfLine eoln;
		public @S(30) TokenList<Bash_Statement> statements;
	}
}
