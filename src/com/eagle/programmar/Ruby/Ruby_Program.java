// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ruby;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Ruby.Statements.Ruby_Function;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Ruby_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String RUBY = "Ruby";

	public Ruby_Program()
	{
		super(RUBY, new Ruby_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.ruby-lang.org/en/2.4.0/syntax/";
	}

	public @S(10) TokenList<Ruby_Statement> elements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Ruby_Statement stmt : elements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof Ruby_Function)
			{
				Ruby_Function fn = (Ruby_Function) which;
				interpreter.addFunction(fn.id.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (Ruby_Statement stmt : elements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// First pass, transform all the Function definitions
		for (Ruby_Statement stmt : elements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof Ruby_Function)
			{
				Ruby_Function func = (Ruby_Function) which;
				func.transformFunction(transformer, generator);
			}
		}
		
		// Are there any global variables we need to declare?
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typE = met.uniqueType();
			if (typE != TypeEnum.VOID)
			{
				AbstractType abstrType = generator.transformType(typE, null, this);

				// System.err.println("****** Found var " + met._symbolName);
				AbstractStatement dataStmt = generator.newDataDeclaration(met._symbolName,
						null, abstrType, null, this);
				generator.addStatement(dataStmt, this);
			}
		}

		// Second pass, transform all the data and logic
		for (Ruby_Statement stmt : elements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (! (which instanceof Ruby_Function))
			{
				Collection<AbstractStatement> newStmts = transformer.transformStatement(
						generator, which);
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}
		}
		
		return generator.getTransfomedProgram();
	}
}