// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 14, 2022

package com.eagle.programmar.Fortran.Statements;

import java.util.Collection;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Fortran.Fortran_Statement;
import com.eagle.programmar.Fortran.Fortran_Syntax;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Definition;
import com.eagle.programmar.Fortran.Symbols.Fortran_Function_Reference;
import com.eagle.programmar.Fortran.Terminals.Fortran_EOLN;
import com.eagle.programmar.Fortran.Terminals.Fortran_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Fortran_ProgramBlock extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) @DOC("6j4m0vnar/index.html") Fortran_Keyword PROGRAM1 = new Fortran_Keyword("PROGRAM");
	public @S(20) Fortran_Function_Definition id;
	public @S(30) Fortran_EOLN eoln1;

	public @S(40) TokenList<Fortran_Statement> statements;

	public @S(50) Fortran_Keyword END = new Fortran_Keyword("END");
	public @S(60) Fortran_Keyword PROGRAM2 = new Fortran_Keyword("PROGRAM");
	public @S(70) Fortran_Function_Reference fnName2;
	public @S(80) Fortran_EOLN eoln2;

	private @SKIP EagleScope _scope = new EagleScope(this, Fortran_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (Fortran_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String fnName = id.getValue();
		if (VERBOSE)
		{
			System.out.println("** Found Fortran program " + fnName);
		}

		// Set up the main program
		generator.addMethod(null, generator.mainName(), this);
		generator.addMainArgs();

		for (Fortran_Statement stmt : statements._elements)
		{
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmt.getWhich());
				}
			}
		}

		generator.doneMethod();
	}
}
