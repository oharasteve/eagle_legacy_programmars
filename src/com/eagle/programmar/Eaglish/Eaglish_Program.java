// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 13, 2024

package com.eagle.programmar.Eaglish;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Eaglish.Statements.Eaglish_Function_Block;
import com.eagle.programmar.Eaglish.Symbols.Eaglish_Program_Identifier;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_CommentEoln;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_EndOfLine;
import com.eagle.programmar.Eaglish.Terminals.Eaglish_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Eaglish_Program extends AbstractLanguage implements EagleRunnable
{
	public static final String EAGLISH = "Eaglish";

	public Eaglish_Program()
	{
		super(EAGLISH, new Eaglish_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "Unknown";
	}

	// Components of an Eaglish Program
	public @S(10) @OPT TokenList<Eaglish_CommentEoln> comments1;
	public @S(20) Eaglish_Prog prog;
	public @S(30) @OPT TokenList<Eaglish_CommentEoln> comments2;

	public static class Eaglish_Prog extends TokenSequence
	{
		public @S(10) Eaglish_Keyword PROGRAM = new Eaglish_Keyword("PROGRAM");
		public @S(20) Eaglish_Program_Identifier id;
		public @S(30) Eaglish_EndOfLine eoln1;

		public @S(40) @OPT TokenList<Eaglish_Statement> statements;

		public @S(50) Eaglish_Keyword END_PROGRAM = new Eaglish_Keyword("END_PROGRAM");
		public @S(60) Eaglish_EndOfLine eoln2;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Eaglish_Statement stmt : prog.statements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof Eaglish_Function_Block)
			{
				Eaglish_Function_Block fn = (Eaglish_Function_Block) which;
				interpreter.addFunction(fn.id.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (Eaglish_Statement stmt : prog.statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}
}
