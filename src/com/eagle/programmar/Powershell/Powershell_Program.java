// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Powershell;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.eagle.core.AbstractLanguage;
import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.AssignMetrics;
import com.eagle.programmar.Powershell.Commands.Powershell_SetVariable;
import com.eagle.programmar.Powershell.Statements.Powershell_Function;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
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
	public @S(60) TokenList<Powershell_Element> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the FUNCTION definitions
		for (Powershell_Element stmt : statements._elements)
		{
			AbstractToken which = stmt.element.getWhich();
			if (which instanceof Powershell_Function)
			{
				Powershell_Function fn = (Powershell_Function) stmt.element.getWhich();
				interpreter.addFunction(fn.id.getValue(), fn);
			}
			else if (which instanceof Powershell_SetVariable)
			{
				interpreter.tryToInterpret(stmt.element);
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

		// Transform all the data and logic, etc.
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

		// Are there any global "script:emsg" variables we need to declare?
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findAllAssignments();
		HashSet<String> didAlready = new HashSet<String>();
		for (AssignMetrics met : asgMetrics)
		{
			if (met._scopeStart == 0)
			{
				String name = met._symbolName;
				if (!generator.isKnownConstant(name))
				{
					if (!didAlready.contains(name))
					{
						TypeEnum typE = met.uniqueType();
						if (typE != TypeEnum.VOID)
						{
							AbstractType abstrType = generator.transformType(typE, null, this);
							// System.err.println("****** Found global var " + name);
							AbstractExpression initExpr = null;
							AbstractStatement dataStmt = generator.newDataDeclaration(StaticEnum.STATIC, name,
									null, abstrType, initExpr, this);
							generator.addStatement(dataStmt, this);
						}
						didAlready.add(name);
					}
				}
			}
		}
		
		return generator.getTransformedProgram();
	}
}