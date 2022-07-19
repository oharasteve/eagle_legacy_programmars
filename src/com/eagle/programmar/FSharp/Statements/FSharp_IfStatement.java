// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import com.eagle.programmar.FSharp.FSharp_Expression;
import com.eagle.programmar.FSharp.FSharp_Statement.FSharp_SingleOrMultiLineStatement;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
import com.eagle.programmar.FSharp.Terminals.FSharp_StartOfLine;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class FSharp_IfStatement extends TokenSequence
{
	public @S(10) @DOC("conditional-expressions-if-then-else") FSharp_Keyword IF = new FSharp_Keyword("if");
	public @S(20) FSharp_Expression condition;
	public @S(30) FSharp_Keyword THEN = new FSharp_Keyword("then");
	public @S(40) FSharp_SingleOrMultiLineStatement ifThen;
	public @S(50) @OPT TokenList<FSharp_IfElif> ifElif;
	public @S(60) @OPT FSharp_IfElse ifElse;
	
	public static class FSharp_IfElif extends TokenSequence
	{
		public @S(10) FSharp_StartOfLine soln = new FSharp_StartOfLine();
		public @S(20) FSharp_Keyword ELIF = new FSharp_Keyword("elif");
		public @S(30) FSharp_Expression condition;
		public @S(50) FSharp_SingleOrMultiLineStatement elifStatement;
	}
		
	public static class FSharp_IfElse extends TokenSequence
	{
		public @S(10) FSharp_StartOfLine soln = new FSharp_StartOfLine();
		public @S(20) FSharp_Keyword ELSE = new FSharp_Keyword("else");
		public @S(40) FSharp_SingleOrMultiLineStatement ifElseStatement;
	}
}
