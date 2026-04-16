// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 6, 2011

package com.eagle.programmar.PLI;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class PLI_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
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

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, transform all the inner procedures
		for (PLI_Element element : elements._elements)
		{
			AbstractToken which3 = element.getWhich();
			if (which3 instanceof PLI_Procedure)
			{
				PLI_Procedure mainProc = (PLI_Procedure) which3;

				// Look for procs inside the outer proc
				for (PLI_StatementOrComment stmt2 : mainProc.statements._elements)
				{
					AbstractToken which4 = stmt2.getWhich();
					if (which4 instanceof PLI_Statement)
					{
						PLI_Statement stmt4 = (PLI_Statement) which4;
						AbstractToken which5 = stmt4.getWhich();
						if (which5 instanceof PLI_Procedure)
						{
							PLI_Procedure proc5 = (PLI_Procedure) which5;
							proc5.transformFunction(transformer, generator);
						}
					}
				}
			}
		}

		// Second pass, collect global variables
		for (PLI_Element element : elements._elements)
		{
			AbstractToken which1 = element.getWhich();
			if (which1 instanceof PLI_Procedure)
			{
				PLI_Procedure proc = (PLI_Procedure) which1;

				// Are there any global variables we need to declare?
				String scopeStr = proc.getScope().getScopeName();
				ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
				for (AssignMetrics met : asgMetrics)
				{
					TypeEnum typE = met.uniqueType();
					if (typE != TypeEnum.VOID)
					{
						AbstractType abstrType = generator.transformType(typE, null, this);

						// System.err.println("****** Found var " + met._symbolName);
						AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
								null, abstrType, null, this);
						generator.addStatement(dataStmt, this);
					}
				}
			}
		}

		// Third pass, transform the MAIN Function definition
		for (PLI_Element element : elements._elements)
		{
			AbstractToken which1 = element.getWhich();
			if (which1 instanceof PLI_Procedure)
			{
				PLI_Procedure proc = (PLI_Procedure) which1;
				// System.err.println("*** Found Main Procedure " + proc.id1.getValue());
				for (PLI_StatementOrComment stmtOrComment : proc.statements._elements)
				{
					AbstractToken which2 = stmtOrComment.getWhich();
					Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which2);
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmtOrComment);
						}
					}
				}
			}
		}

		return generator.getTransformedProgram();
	}
}
