// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Algol68;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Algol68.Algol68_Program.Algol68_Element.Algol68_Main;
import com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Algol68_Program extends EagleLanguage implements EagleRunnable
{
	public static final String ALGOL68 = "Algol68";
	
	public Algol68_Program()
	{
		super(ALGOL68, new Algol68_Syntax());
	}
	
	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "TRUE";
		return "FALSE";
	}

	@Override
	public String getDocRoot()
	{
		return "https://jmvdveer.home.xs4all.nl/learning-algol-68-genie.pdf";
	}

	public @S(10) TokenList<Algol68_Element> elements;
	
	public static class Algol68_Element extends TokenChooser
	{
		public @CHOICE Algol68_Statement statement;
		
		public @CHOICE static class Algol68_Main extends TokenSequence
		{
			public @S(10) Algol68_Keyword MAIN = new Algol68_Keyword("MAIN");
			public @S(20) PunctuationColon colon;
			public @S(30) PunctuationLeftParen leftParen;
			public @S(40) TokenList<Algol68_Element> elements;
			public @S(50) PunctuationRightParen rightParen;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Algol68_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Algol68_Statement)
			{
				Algol68_Statement stmt = (Algol68_Statement) which;
				if (stmt.getWhich() instanceof Algol68_Procedure)
				{
					Algol68_Procedure fn = (Algol68_Procedure) stmt.getWhich();
					interpreter._functionList.add(fn);
				}
			}
		}
		
		// Second pass, execute the program
		for (Algol68_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Algol68_Main)
			{
				Algol68_Main main = (Algol68_Main) which;
				for (Algol68_Element elt : main.elements._elements)
				{
					interpreter.tryToInterpret(elt.getWhich());
				}
			}
			else if (which instanceof Algol68_Statement)
			{
				Algol68_Statement stmt = (Algol68_Statement) which;
				interpreter.tryToInterpret(stmt.getWhich());
			}
		}
	}
}
