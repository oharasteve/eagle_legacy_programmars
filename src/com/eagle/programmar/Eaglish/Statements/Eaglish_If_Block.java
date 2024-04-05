// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2024

package com.eagle.programmar.Eaglish.Statements;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Eaglish.Eaglish_Expression;
import com.eagle.programmar.Eaglish.Eaglish_Statement;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Eaglish_If_Block extends TokenSequence implements EagleRunnable
{
	public @S(10) Eaglish_Keyword IF = new Eaglish_Keyword("IF");
	public @S(20) Eaglish_Expression condition;
	public @S(30) Eaglish_EndOfLine eoln1;

	public @S(40) @OPT TokenList<Eaglish_Statement> statements;

	public @S(50) @OPT TokenList<Eaglish_If_ElseIf_Block> elseifBlocks;

	public @S(60) @OPT Eaglish_If_Else_Block elseBlock;

	public @S(70) Eaglish_Keyword END_IF = new Eaglish_Keyword("END_IF");
	public @S(80) Eaglish_EndOfLine eoln2;
	
	public static class Eaglish_If_ElseIf_Block extends TokenSequence
	{
		public @S(10) Eaglish_Keyword ELSE_IF = new Eaglish_Keyword("ELSE_IF");
		public @S(20) Eaglish_Expression condition;
		public @S(30) Eaglish_EndOfLine eoln1;
		public @S(40) @OPT TokenList<Eaglish_Statement> statements;
	}
	
	public static class Eaglish_If_Else_Block extends TokenSequence
	{
		public @S(10) Eaglish_Keyword ELSE = new Eaglish_Keyword("ELSE");
		public @S(20) Eaglish_EndOfLine eoln1;
		public @S(30) @OPT TokenList<Eaglish_Statement> statements;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		TokenList<Eaglish_Statement> todo = null;
		
		boolean cond1 = interpreter.getBoolValue(condition);
		if (cond1)
		{
			todo = statements;
		}
		else
		{
			// Check for each 'else if'
			for (Eaglish_If_ElseIf_Block elif : elseifBlocks._elements)
			{
				boolean cond2 = interpreter.getBoolValue(elif.condition);
				if (cond2)
				{
					todo = elif.statements;
					break;
				}
			}
			
			// Check for 'else'
			if (todo == null)
			{
				if (elseBlock.isPresent())
				{
					todo = elseBlock.statements;
				}
			}
		}
		
		if (todo != null)
		{
			for (Eaglish_Statement stmt : todo._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}
