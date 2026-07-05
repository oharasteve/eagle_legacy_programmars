// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

package com.eagle.programmar.Ruby;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.Ruby.Statements.Ruby_Function;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
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
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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

		// Second pass, transform all the data and logic
		for (Ruby_Statement stmt : elements._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (!(which instanceof Ruby_Function))
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

		return generator.getTransformedProgram();
	}
}