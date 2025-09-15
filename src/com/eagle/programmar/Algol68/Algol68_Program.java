// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Algol68;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Algol68.Statements.Algol68_Data;
import com.eagle.programmar.Algol68.Statements.Algol68_Procedure;
import com.eagle.programmar.Algol68.Terminals.Algol68_Keyword;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Algol68_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
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

	public @S(10) TokenList<Algol68_TopElement> elements;

	public static class Algol68_Main extends TokenSequence
	{
		public @S(10) Algol68_Keyword MAIN = new Algol68_Keyword("MAIN");
		public @S(20) PunctuationColon colon;
		public @S(30) PunctuationLeftParen leftParen;
		public @S(40) TokenList<Algol68_Statement> statements;
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class Algol68_TopElement extends TokenChooser
	{
		public @CHOICE Algol68_Statement XXstatement;
		public @CHOICE Algol68_Main XXmain;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the Procedure definitions
		for (Algol68_TopElement element : elements._elements)
		{
			AbstractToken which1 = element.getWhich();
			if (which1 instanceof Algol68_Statement)
			{
				Algol68_Statement stmt = (Algol68_Statement) which1;
				AbstractToken which2 = stmt.getWhich();
				if (which2 instanceof Algol68_Procedure)
				{
					Algol68_Procedure fn = (Algol68_Procedure) which2;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
				else if (which2 instanceof Algol68_Statement)
				{
					Algol68_Data stmt2 = (Algol68_Data) which2;
					interpreter.tryToInterpret(stmt2);
				}
			}
		}

		// Second pass, execute the program
		for (Algol68_TopElement element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Algol68_Main)
			{
				Algol68_Main main = (Algol68_Main) which;
				for (Algol68_Statement stmt1 : main.statements._elements)
				{
					interpreter.tryToInterpret(stmt1);
				}
			}
			else if (which instanceof Algol68_Statement)
			{
				Algol68_Statement stmt2 = (Algol68_Statement) which;
				interpreter.tryToInterpret(stmt2);
			}
		}
	}
	
	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// First pass, just collect all the Procedure definitions
		for (Algol68_TopElement element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Algol68_Statement)
			{
				Algol68_Statement stmt = (Algol68_Statement) which;
				if (stmt.getWhich() instanceof EagleTransformableFunction)
				{
					EagleTransformableFunction transformable = (EagleTransformableFunction) stmt.getWhich();
					transformable.transformFunction(transformer, generator);
				}
			}
		}

		// Second pass, collect the main procedure and other stuff
		for (Algol68_TopElement elt : elements._elements)
		{
			AbstractToken whichElt = elt.getWhich();
			if (whichElt instanceof Algol68_Main)
			{
				Algol68_Main main = (Algol68_Main) whichElt;
				for (Algol68_Statement stmt : main.statements._elements)
				{
					Collection<AbstractStatement> newStmts = transformer.transformStatement(generator,
							stmt.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
			}
			else if (whichElt instanceof Algol68_Statement)
			{
				Algol68_Statement stmt = (Algol68_Statement) whichElt;
				Collection<AbstractStatement> newStmts = transformer.transformStatement(generator,
						stmt.getWhich());
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}
			else
			{
				throw new RuntimeException("Unable to handle: " + whichElt);
			}
		}
		
		return generator.getTransfomedProgram();
	}
}
