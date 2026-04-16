// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 23, 2022

package com.eagle.programmar.Fortran;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Statements.Fortran_Function;
import com.eagle.programmar.Fortran.Statements.Fortran_Subroutine;
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

public class Fortran_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String FORTRAN = "Fortran";

	public Fortran_Program()
	{
		super(FORTRAN, new Fortran_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.oracle.com/cd/E19957-01/805-4939/";
	}

	public @S(10) TokenList<Fortran_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Fortran_Statement stmt : statements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof Fortran_Function)
			{
				Fortran_Function fn = (Fortran_Function) which;
				interpreter.addFunction(fn.id.getValue(), fn);
			}
			else if (which instanceof Fortran_Subroutine)
			{
				Fortran_Subroutine sub = (Fortran_Subroutine) which;
				interpreter.addFunction(sub.id.getValue(), sub);
			}
		}

		// Second pass, execute the program
		for (Fortran_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, just collect all the FUNCTION, SUBROUTINE and PROGRAM definitions
		for (Fortran_Statement stmt : statements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof EagleTransformableFunction)
			{
				EagleTransformableFunction transformable = (EagleTransformableFunction) which;
				transformable.transformFunction(transformer, generator);
			}
		}

		// Only needed by Python
		generator.addCallToMain();

		return generator.getTransformedProgram();
	}
}
