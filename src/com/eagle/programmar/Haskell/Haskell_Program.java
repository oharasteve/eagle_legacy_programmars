// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 12, 2026

package com.eagle.programmar.Haskell;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Haskell.Haskell_ComplexStatement.Haskell_Statement;
import com.eagle.programmar.Haskell.Statements.Haskell_Function;
import com.eagle.programmar.Haskell.Statements.Haskell_MainFunction;
import com.eagle.programmar.Haskell.Statements.Haskell_StatementBlock.Haskell_SameLineStatement;
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

public class Haskell_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String HASKELL = "Haskell";

	public Haskell_Program()
	{
		super(HASKELL, new Haskell_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "TBD";
	}

	public @S(10) TokenList<Haskell_ComplexStatement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Haskell_ComplexStatement stmt : statements._elements)
		{
			AbstractToken which1 = stmt.statementOrComment.getWhich();
			if (which1 instanceof Haskell_SameLineStatement)
			{
				Haskell_SameLineStatement same = (Haskell_SameLineStatement) which1;
				AbstractToken which2 = same.statements.first().getWhich();
				if (which2 instanceof Haskell_Function)
				{
					Haskell_Function fn = (Haskell_Function) which2;
					interpreter.addFunction(fn.definition.def.getValue(), fn);
				}
			}
		}

		for (Haskell_ComplexStatement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		boolean needCallToMain = false;
		
		// First pass, transform all the function definitions, especially 'main'
		for (Haskell_ComplexStatement elt : statements._elements)
		{
			AbstractToken which = elt.statementOrComment.getWhich();
			if (which instanceof Haskell_SameLineStatement)
			{
				Haskell_SameLineStatement sameLine = (Haskell_SameLineStatement) which;
				for (int i = 0; i < sameLine.statements.getPrimaryCount(); i++)
				{
					Haskell_Statement stmt = sameLine.statements.getPrimaryElement(i);
					if (stmt.getWhich() instanceof EagleTransformableFunction)
					{
						EagleTransformableFunction transformable = (EagleTransformableFunction) stmt.getWhich();
						transformable.transformFunction(transformer, generator);
					}
					if (which instanceof Haskell_MainFunction)
					{
						needCallToMain = true;
					}
				}
			}
		}

		// Second pass, transform all the data and logic
		for (Haskell_ComplexStatement elt : statements._elements)
		{
			if (elt.statementOrComment.getWhich() instanceof Haskell_SameLineStatement)
			{
				Haskell_SameLineStatement sameLine = (Haskell_SameLineStatement) elt.statementOrComment.getWhich();
				for (int i = 0; i < sameLine.statements.getPrimaryCount(); i++)
				{
					Haskell_Statement stmt = sameLine.statements.getPrimaryElement(i);
					Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
			}
		}

		// Not needed for C# or Java, but Python needs this (unless there was an 'object' line)
		if (needCallToMain)
		{
			generator.addCallToMain();
		}

		return generator.getTransformedProgram();
	}
}