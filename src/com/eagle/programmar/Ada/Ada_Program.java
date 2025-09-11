// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ada;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ada.Statements.Ada_Function;
import com.eagle.programmar.Ada.Statements.Ada_Procedure;
import com.eagle.programmar.Ada.Terminals.Ada_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Ada_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String ADA = "Ada";

	public Ada_Program()
	{
		super(ADA, new Ada_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.adaic.org/resources/add_content/standards/05rm/RM-Final.pdf";
	}

	public @S(10) TokenList<Ada_Element> elements;

	public static class Ada_Element extends TokenChooser
	{
		public @CHOICE Ada_Comment XXcomment;
		public @CHOICE Ada_Statement XXstmt;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Ada_Element element : elements._elements)
		{
			AbstractToken whichElt = element.getWhich();
			if (whichElt instanceof Ada_Statement)
			{
				Ada_Statement statement = (Ada_Statement) whichElt;
				AbstractToken whichStmt = statement.getWhich();
				if (whichStmt instanceof Ada_Procedure)
				{
					Ada_Procedure proc = (Ada_Procedure) whichStmt;
					for (Ada_Statement stmt : proc.statements1._elements)
					{
						AbstractToken which = stmt.getWhich();
						if (which instanceof Ada_Function)
						{
							Ada_Function fn = (Ada_Function) which;
							interpreter.addFunction(fn.id.getValue(), fn);
						}
						else if (which instanceof Ada_Procedure)
						{
							Ada_Procedure pr = (Ada_Procedure) which;
							interpreter.addFunction(pr.id.getValue(), pr);
						}
					}
				}
			}
		}

		// Second pass, execute the program
		for (Ada_Element element : elements._elements)
		{
			AbstractToken which = element.getWhich();
			if (which instanceof Ada_Statement)
			{
				Ada_Statement stmt = (Ada_Statement) which;
				interpreter.tryToInterpret(stmt);
			}
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// Transform all the Function definitions and global data
		for (Ada_Element elt : elements._elements)
		{
			AbstractToken whichElt = elt.getWhich();
			if (whichElt instanceof Ada_Comment)
			{
				// Ignore comments for now
			}
			else if (whichElt instanceof Ada_Statement)
			{
				Ada_Statement stmt = (Ada_Statement) whichElt;
				AbstractToken whichStmt = stmt.getWhich();
				if (whichStmt instanceof Ada_Function)
				{
					Ada_Function func = (Ada_Function) whichStmt;
					func.transformFunction(transformer, generator);
				}
				else if (stmt.getWhich() instanceof Ada_Procedure)
				{
					Ada_Procedure proc = (Ada_Procedure) whichStmt;
					proc.transformFunction(transformer, generator);
				}
				else	// Other statements
				{
					Collection<AbstractStatement> newStmts = transformer.transformStatement(
							generator, whichStmt);
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, elt);
						}
					}
				}
			}
			else
			{
				throw new RuntimeException("Unable to handle " + whichElt);
			}
		}
		
		// Not needed for C# or Java, but Python needs this
		generator.addCallToMain();

		return generator.getTransfomedProgram();
	}
}
