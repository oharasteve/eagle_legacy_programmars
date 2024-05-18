// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Scala;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.Scala.Statements.Scala_Import;
import com.eagle.programmar.Scala.Statements.Scala_Object;
import com.eagle.programmar.Scala.Statements.Scala_Package;
import com.eagle.programmar.Scala.Terminals.Scala_Comment;
import com.eagle.programmar.Scala.Terminals.Scala_EOLN;
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
		for (Scala_Element elt : elements._elements)
		{
			interpreter.tryToInterpret(elt.getWhich());
		}
	}
}
