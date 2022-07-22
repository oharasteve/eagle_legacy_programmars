// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 18, 2022

package com.eagle.programmar.Bash.Commands;

import com.eagle.programmar.Bash.Bash_EndOfLine;
import com.eagle.programmar.Bash.Bash_Expression;
import com.eagle.programmar.Bash.Bash_Statement;
import com.eagle.programmar.Bash.Symbols.Bash_Identifier_Reference;
import com.eagle.programmar.Bash.Terminals.Bash_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Bash_ForStatement extends TokenSequence
{
	public @S(10) @DOC("#Looping-Constructs") Bash_Keyword FOR = new Bash_Keyword("for");
	public @S(20) Bash_Identifier_Reference id;
	public @S(30) Bash_Keyword IN = new Bash_Keyword("in");
	public @S(40) Bash_Expression values;
	public @S(50) Bash_EndOfLine eoln1;
	
	public @S(60) Bash_Keyword DO = new Bash_Keyword("do");
	public @S(70) @OPT Bash_EndOfLine eoln2;
	public @S(80) TokenList<Bash_Statement> statements;
	
	public @S(90) Bash_Keyword DONE = new Bash_Keyword("done");
}
