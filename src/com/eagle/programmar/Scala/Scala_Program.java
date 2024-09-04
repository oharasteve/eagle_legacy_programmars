// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Scala;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Scala.Statements.Scala_Function;
import com.eagle.programmar.Scala.Statements.Scala_Import;
import com.eagle.programmar.Scala.Statements.Scala_Object;
import com.eagle.programmar.Scala.Statements.Scala_Package;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Scala_Program extends AbstractLanguage implements EagleRunnable
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
		public @CHOICE Scala_CommentEoln XXcomment;
		public @CHOICE Scala_Import XXimport;
		public @CHOICE Scala_Object XXobject;
		public @CHOICE Scala_Package XXpkg;
		public @CHOICE Scala_Statement XXstmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
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
						interpreter.addFunction(func.id.getValue(), func);
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
