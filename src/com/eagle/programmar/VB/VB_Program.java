// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import java.util.Collection;

import com.eagle.core.AbstractLanguage;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.VB.Statements.VB_FunctionDeclaration;
import com.eagle.programmar.VB.Statements.VB_SubDeclaration;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformableProgram;
import com.eagle.transform.EagleTransformer;

public class VB_Program extends AbstractLanguage implements EagleRunnable, EagleTransformableProgram
{
	public static final String VB = "VB";

	public VB_Program()
	{
		super(VB, new VB_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://msdn.microsoft.com/en-us/library/";
	}

	public @S(10) @OPT TokenList<VB_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// First pass, just collect all the Function and Sub definitions
		for (VB_Statement stmt : statements._elements)
		{
			AbstractToken which = stmt.baseStatement.getWhich();
			if (which instanceof VB_FunctionDeclaration)
			{
				VB_FunctionDeclaration func = (VB_FunctionDeclaration) which;
				interpreter.addFunction(func.name.getValue(), func);
			}
			if (which instanceof VB_SubDeclaration)
			{
				VB_SubDeclaration sub = (VB_SubDeclaration) which;
				interpreter.addFunction(sub.name.getValue(), sub);
			}
		}

		// Second pass, run any stuff in the outermost 'object'
		for (VB_Statement stmt : statements._elements)
		{
			interpreter.tryToInterpret(stmt.baseStatement);
		}
	}
	
	@Override
	public void transformFunctions(EagleTransformer transformer, EagleGenerator generator)
	{
		// First pass, transform all the Function and Sub definitions
		for (VB_Statement stmt : statements._elements)
		{
			AbstractToken which = stmt.baseStatement.getWhich();
			if (which instanceof EagleTransformableFunction)
			{
				EagleTransformableFunction transformable = (EagleTransformableFunction) which;
				AbstractFunction newFunc = transformable.transformFunction(transformer, generator);
				generator.addFunction(newFunc);
			}
		}
	}
		
	@Override
	public AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator generator)
	{
		// Second pass, transform all the data and logic
		for (VB_Statement stmt : statements._elements)
		{
			AbstractToken which = stmt.baseStatement.getWhich();
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt);
				}
			}
		}
		
		return generator.getTransfomedProgram();
	}
}
