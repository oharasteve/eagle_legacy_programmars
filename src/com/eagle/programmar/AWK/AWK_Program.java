// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.AWK;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class AWK_Program extends EagleLanguage implements EagleRunnable
{
	public static final String AWK = "AWK";
	
	public AWK_Program()
	{
		super(AWK, new AWK_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "https://www.gnu.org/software/gawk/manual/gawk.html";
	}

	public @S(10) TokenList<AWK_Element> elements;
	
	public static class AWK_Element extends TokenChooser
	{
		public @CHOICE AWK_Command command;
		public @FIRST AWK_CommentLine comment;
		public @CHOICE AWK_Function function;
	}
	
	public static class AWK_CommentLine extends TokenSequence
	{
		public @S(10) AWK_Comment comment;
		public @S(20) AWK_EndOfLine eoln;
	}
	
	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (AWK_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof AWK_Function)
			{
				AWK_Function fn = (AWK_Function) which;
				interpreter._functionList.add(fn);
			}
		}
		
		// Second pass, execute the program
		for (AWK_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof AWK_Command)
			{
				AWK_Command cmd = (AWK_Command) which;
				interpreter.tryToInterpret(cmd.action);
			}
		}
	}
}
