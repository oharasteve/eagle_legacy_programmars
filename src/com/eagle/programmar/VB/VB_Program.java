// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.VB_Element.VB_Statement;
import com.eagle.programmar.VB.Statements.VB_Function;
import com.eagle.programmar.VB.Statements.VB_Subroutine;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class VB_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String VB = "VB";

	public VB_Program()
	{
		super(VB, new VB_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://learn.microsoft.com/en-us/dotnet/visual-basic/language-reference/";
	}

	public @S(10) @OPT TokenList<VB_Element> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the Function and Sub definitions
		for (VB_Element stmt : statements._elements)
		{
			for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
			{
				VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
				AbstractToken which = baseStatement.getWhich();
				if (which instanceof VB_Function)
				{
					VB_Function func = (VB_Function) which;
					interpreter.addFunction(func.id.getValue(), func);
				}
				if (which instanceof VB_Subroutine)
				{
					VB_Subroutine sub = (VB_Subroutine) which;
					interpreter.addFunction(sub.id.getValue(), sub);
				}
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (VB_Element stmt : statements._elements)
		{
			for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
			{
				VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
				interpreter.tryToInterpret(baseStatement);
			}
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, transform all the Function and Sub definitions
		for (VB_Element stmt : statements._elements)
		{
			for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
			{
				VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
				AbstractToken which = baseStatement.getWhich();
				if (which instanceof EagleTransformableFunction)
				{
					EagleTransformableFunction transformable = (EagleTransformableFunction) which;
					transformable.transformFunction(transformer, generator);
				}
			}
		}

		// Second pass, transform all the data and logic
		for (VB_Element stmt : statements._elements)
		{
			for (int i = 0; i < stmt.baseStatements.getPrimaryCount(); i++)
			{
				VB_Statement baseStatement = stmt.baseStatements.getPrimaryElement(i);
				AbstractToken which = baseStatement.getWhich();
				Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}
		}

		return generator.getTransformedProgram();
	}
}
