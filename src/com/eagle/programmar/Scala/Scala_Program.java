// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Scala;

import java.util.ArrayList;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Statements.Scala_Function;
import com.eagle.programmar.Scala.Statements.Scala_Import;
import com.eagle.programmar.Scala.Statements.Scala_Object;
import com.eagle.programmar.Scala.Statements.Scala_Package;
import com.eagle.programmar.Scala.Terminals.Scala_Comment;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Scala_Program extends EagleLanguage implements EagleRunnable
{
	public static final String SCALA = "Scala";

	public Scala_Program()
	{
		super(SCALA, new Scala_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.scala-lang.org/scala3/book/";
	}

	public @S(10) TokenList<Scala_Element> elements;

	public static class Scala_Element extends TokenChooser
	{
		public @CHOICE Scala_CommentEoln comment;
		public @CHOICE Scala_Import imprt;
		public @CHOICE Scala_Object object;
		public @CHOICE Scala_Package pkg;
		public @CHOICE Scala_Statement stmt;
	}

	public static class Scala_CommentEoln extends TokenSequence
	{
		public @S(10) Scala_Comment comment;
		public @S(20) Scala_EOLN eoln;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		interpreter._functionList = new ArrayList<AbstractFunction>();
		for (Scala_Element elt : elements._elements)
		{
			if (elt.getWhich() instanceof Scala_Object)
			{
				Scala_Object obj = (Scala_Object) elt.getWhich();
				for (Scala_Statement stmt : obj.statement.statements._elements)
				{
					if (stmt.getWhich() instanceof Scala_Function)
					{
						Scala_Function func = (Scala_Function) stmt.getWhich();
						interpreter._functionList.add(func);
						if (interpreter._TRACE)
						{
							System.err.println("*** Found Scala function " + func.id.getValue());
						}
					}
				}
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (Scala_Element elt : elements._elements)
		{
			if (elt.getWhich() instanceof Scala_Object)
			{
				Scala_Object obj = (Scala_Object) elt.getWhich();
				for (Scala_Statement stmt : obj.statement.statements._elements)
				{
					interpreter.tryToInterpret(stmt);
				}
			}
		}
	}
}
