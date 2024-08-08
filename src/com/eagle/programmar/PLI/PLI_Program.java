// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 6, 2011

package com.eagle.programmar.PLI;

import com.eagle.core.EagleInterpreter;
import com.eagle.core.EagleLanguage;
import com.eagle.core.EagleRunnable;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.tokens.AbstractToken;
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
		public @CHOICE PLI_Comment XXcomment;
		public @CHOICE PLI_DeclareGeneric XXdeclareGeneric;
		public @CHOICE PLI_Procedure XXprocedure;
		public @CHOICE PLI_Declaration XXdeclaration;
		public @CHOICE PLI_PercentStatement XXpercentStmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (PLI_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof PLI_Procedure)
			{
				PLI_Procedure proc = (PLI_Procedure) which;
				interpreter.addFunction(proc.id1.getValue(), proc);
				
				// Look for procs inside the outer proc
				for (PLI_StatementOrComment stmt1 : proc.statements._elements)
				{
					AbstractToken which2 = stmt1.getWhich();
					if (which2 instanceof PLI_Statement)
					{
						PLI_Statement stmt2 = (PLI_Statement) which2;
						AbstractToken which3 = stmt2.getWhich();
						if (which3 instanceof PLI_Procedure)
						{
							PLI_Procedure proc3 = (PLI_Procedure) which3;
							interpreter.addFunction(proc3.id1.getValue(), proc3);
						}
					}
				}
			}
		}

		// Second pass, execute the program
		for (PLI_Element element : elements._elements)
		{
			interpreter.tryToInterpret(element);
		}
	}
}
