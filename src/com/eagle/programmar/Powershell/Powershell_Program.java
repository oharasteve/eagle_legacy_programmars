// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Powershell.Statements.Powershell_Function;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class Powershell_Program extends AbstractLanguage
		implements EagleRunnable, EagleTransformableProgram
{
	public static final String POWERHSELL = "Powershell";

	public Powershell_Program()
	{
		super(POWERHSELL, new Powershell_Syntax());
	}

	@Override
	public String booleanName(boolean flag)
	{
		if (flag)
		{
			return "$True";
		}
		return "$False";
	}

	@Override
	public String getDocRoot()
	{
		return "https://docs.microsoft.com/en-us/powershell/scripting/lang-spec/";
	}

	public @S(10) @OPT TokenList<Powershell_CommentEoln> comments1;
	public @S(20) @OPT TokenList<Powershell_Directive> directives;
	public @S(30) @OPT Powershell_CmdletBinding cmdletBinding;
	public @S(40) @OPT Powershell_Parameters parameters;
	public @S(50) @OPT TokenList<Powershell_CommentEoln> comments2;
	public @S(60) @OPT TokenList<Powershell_Element> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Powershell_Element stmt : statements._elements)
		{
			if (stmt.element.getWhich() instanceof Powershell_Function)
			{
				Powershell_Function fn = (Powershell_Function) stmt.element.getWhich();
				interpreter.addFunction(fn.id.getValue(), fn);
			}
		}

		// Second pass, execute the program
		for (Powershell_Element stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt.element);
		}
	}

	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Transform all the Function definitions first
		for (Powershell_Element stmt : statements._elements)
		{
			AbstractToken whichStmt = stmt.element.getWhich();
			if (whichStmt instanceof Powershell_Function)
			{
				Powershell_Function func = (Powershell_Function) whichStmt;
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
				AbstractExpression initExpr = null;
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName,
						null, abstrType, initExpr, this);
				generator.addStatement(dataStmt, this);
			}
		}

		// Transform all the global data and logic, etc.
		for (Powershell_Element stmt : statements._elements)
		{
			AbstractToken whichStmt = stmt.element.getWhich();
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, whichStmt);
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, whichStmt);
				}
			}
		}

		return generator.getTransformedProgram();
	}
}