// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 6, 2011

package com.eagle.programmar.PLI;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class PLI_Program extends EagleLanguage implements EagleRunnable
{
	public static final String PLI = "PL/I";

	public PLI_Program()
	{
		super(PLI, new PLI_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://publibfp.boulder.ibm.com/cgi-bin/bookmgr/BOOKS/IBM3L101/";
	}

	// Components of a PL/I Program
	public @S(10) @OPT TokenList<PLI_Element> elements;

	public static class PLI_Element extends TokenChooser
	{
		public @CHOICE PLI_Comment comment;
		public @CHOICE PLI_DeclareGeneric declareGeneric;
		public @CHOICE PLI_Procedure procedure;
		public @CHOICE PLI_Declaration declaration;
		public @CHOICE PLI_PercentStatement percentStmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (PLI_Element elt : elements._elements)
		{
			interpreter.tryToInterpret(elt.getWhich());
		}
	}
}
