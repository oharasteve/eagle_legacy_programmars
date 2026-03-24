// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 15, 2014

package com.eagle.programmar.TCL;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class TCL_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String TCL = "TCL";

	public TCL_Program()
	{
		super(TCL, new TCL_Syntax());
	}

	@Override
	public String booleanName(boolean flag)
	{
		if (flag) return "$true";
		return "$false";
	}

	@Override
	public String getDocRoot()
	{
		return "https://www.tcl.tk/man/tcl8.7/";
	}

	public @S(10) TokenList<TCL_Element> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the method definitions
		for (TCL_Element stmt : statements._elements)
		{
			for (int i = 0; i < stmt.statements.getPrimaryCount(); i++)
			{
				TCL_Statement base = stmt.statements.getPrimaryElement(i);
				if (base.getWhich() instanceof TCL_Procedure)
				{
					TCL_Procedure proc = (TCL_Procedure) base.getWhich();
					interpreter.addFunction(proc.id.getValue(), proc);
				}
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (TCL_Element stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// First pass, transform all the Procedure definitions
		for (TCL_Element element : statements._elements)
		{
			int nstmts = element.statements.getPrimaryCount();
			for (int i = 0; i < nstmts; i++)
			{
				TCL_Statement base = element.statements.getPrimaryElement(i);
				if (base.getWhich() instanceof EagleTransformableFunction)
				{
					EagleTransformableFunction transformable = (EagleTransformableFunction) base.getWhich();
					transformable.transformFunction(transformer, generator);
				}
			}
		}

		// Are there any global variables we need to declare?
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typ = met.uniqueType();
			if (typ != TypeEnum.VOID)
			{
				// System.err.println("****** Found var " + met._symbolName);
				AbstractType absType = generator.transformType(typ, null, this);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, absType, null,
						this);
				generator.addStatement(dataStmt, this);
			}
		}

		// Second pass, transform all the data and logic
		for (TCL_Element element : statements._elements)
		{
			int nstmts = element.statements.getPrimaryCount();
			for (int i = 0; i < nstmts; i++)
			{
				TCL_Statement stmt = element.statements.getPrimaryElement(i);
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

		return generator.getTransfomedProgram();
	}
}
