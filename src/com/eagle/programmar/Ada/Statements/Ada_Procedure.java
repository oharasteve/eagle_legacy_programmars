// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.programmar.Ada.Ada_Statement;
import com.eagle.programmar.Ada.Ada_Syntax;
import com.eagle.programmar.Ada.Statements.Ada_Function.Ada_FunctionParams;
import com.eagle.programmar.Ada.Statements.Ada_Function.Ada_Parameter;
import com.eagle.programmar.Ada.Symbols.Ada_Function_Definition;
import com.eagle.programmar.Ada.Symbols.Ada_Identifier_Reference;
import com.eagle.programmar.Ada.Terminals.Ada_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Ada_Procedure extends TokenSequence
		implements EagleRunnable, AbstractFunction, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) Ada_Keyword PROCEDURE = new Ada_Keyword("procedure");
	public @S(20) Ada_Function_Definition id;
	public @S(30) @OPT Ada_FunctionParams procParamDefs;
	public @S(40) Ada_Keyword IS = new Ada_Keyword("is");
	public @S(50) @OPT Ada_Package pkg;
	public @S(60) TokenList<Ada_Statement> statements1;
	public @S(70) Ada_Keyword BEGIN = new Ada_Keyword("begin");
	public @S(80) TokenList<Ada_Statement> statements2;
	public @S(90) Ada_Keyword END = new Ada_Keyword("end");
	public @S(100) @OPT Ada_Identifier_Reference name;
	public @S(110) PunctuationSemicolon semicolon;

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Ada_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
		}

		// Only deal with main procedure
		// ideone.com wants it named "test" for some reason
		if (id.getValue().equals("main") || id.getValue().equals("test"))
		{
			for (Ada_Statement stmt : statements1._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
			for (Ada_Statement stmt : statements2._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}
	}
	
	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		String fnName = id.getValue();
		boolean isMain = false;
		if (fnName.equals("main"))
		{
			fnName = generator.mainName();	// Change from 'main' to 'Main' for C#
			isMain = true;
		}

		generator.addMethod(null, fnName, this);
		generator.addMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Ada function " + fnName);
		}
		
		if (isMain)
		{
			// Have to wait until addMethod is called
			generator.addMainArgs();		// For java and C# but not for Python
		}
		
		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);
		
		if (procParamDefs != null && procParamDefs.isPresent())
		{
			if (procParamDefs.parameters != null && procParamDefs.parameters.isPresent())
			{
				for (int i = 0; i < procParamDefs.parameters.getPrimaryCount(); i++)
				{
					Ada_Parameter param = procParamDefs.parameters.getPrimaryElement(i);
					AbstractType paramType = null;
					
					if (argTypes != null && i < argTypes.size())
					{
						String metricArgType = argTypes.get(i);
						TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
						paramType = generator.transformType(metricArg, null, param);
					}
					
					generator.addMethodParameter(paramType, param.param.getValue());
				}
			}
		}

		for (Ada_Statement stmt : statements1._elements)
		{
			AbstractToken which = stmt.getWhich();
			if (which instanceof Ada_Data)
			{
				Ada_Data data = (Ada_Data) which;
				Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, data);
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, data);
					}
				}
			}
		}
		
		for (Ada_Statement stmt : statements2._elements)
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
