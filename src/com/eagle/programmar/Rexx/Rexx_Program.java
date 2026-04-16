// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 20, 2019

package com.eagle.programmar.Rexx;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Rexx.Statements.Rexx_Function;
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

public class Rexx_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String REXX = "Rexx";

	public Rexx_Program()
	{
		super(REXX, new Rexx_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.ibm.com/docs/en/cics-ts/6.x?topic=";
	}

	public @S(10) TokenList<Rexx_TopElement> elements;

	public static class Rexx_TopElement extends TokenChooser
	{
		public @CHOICE Rexx_Element XXelement;
		public @CHOICE Rexx_Function XXfunction;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the Function and Sub definitions
		for (Rexx_TopElement elt : elements._elements)
		{
			AbstractToken which = elt.getWhich();
			if (which instanceof Rexx_Function)
			{
				Rexx_Function func = (Rexx_Function) which;
				interpreter.addFunction(func.id.getValue(), func);
				interpreter.tryToInterpret(func); // Initialize metrics because functions *follow* main()
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (Rexx_TopElement elt : elements._elements)
		{
			interpreter.tryToInterpret(elt);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, transform all the Function definitions
		for (Rexx_TopElement stmt : elements._elements)
		{
			AbstractToken which1 = stmt.getWhich();
			if (which1 instanceof Rexx_Function)
			{
				Rexx_Function func = (Rexx_Function) which1;
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

				AbstractExpression initExpr = null;
				if (typE == TypeEnum.HASH)
				{
					// Need to create an empty hashmap
					initExpr = generator.newClassCreation(abstrType, null, this);
				}

				// System.err.println("****** Found var " + met._symbolName);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
						null, abstrType, initExpr, this);
				generator.addStatement(dataStmt, this);
			}
		}

		// Second pass, transform all the data and logic
		for (Rexx_TopElement stmt : elements._elements)
		{
			AbstractToken which2 = stmt.getWhich();
			if (which2 instanceof Rexx_Element)
			{
				Rexx_Element elt = (Rexx_Element) which2;
				Collection<AbstractStatement> newStmts = transformer.transformStatement(
						generator, elt.baseStatement.getWhich());
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, elt);
					}
				}
			}
		}

		return generator.getTransformedProgram();
	}
}