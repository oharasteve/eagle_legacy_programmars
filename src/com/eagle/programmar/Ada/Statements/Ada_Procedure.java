// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2022

package com.eagle.programmar.Ada.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
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
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

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
			for (Ada_Statement stmt1 : statements1._elements)
			{
				interpreter.tryToInterpret(stmt1);
			}
			for (Ada_Statement stmt2 : statements2._elements)
			{
				interpreter.tryToInterpret(stmt2);
			}
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String fnName = id.getValue();
		boolean isMain = false;
		if (fnName.equals("main"))
		{
			fnName = generator.mainName(); // Change from 'main' to 'Main' for C#
			isMain = true;
		}

		generator.addMethod(null, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Ada function " + fnName);
		}

		if (isMain)
		{
			// Have to wait until addMethod is called
			generator.addMainArgs(); // For java and C# but not for Python
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

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
						TypeEnum metricArg = argTypes.get(i);
						paramType = generator.transformType(metricArg, null, param);
					}

					generator.addMethodParameter(paramType, param.param.getValue());
				}
			}
		}

		transformBody(transformer, generator);

		generator.doneMethod();
	}

	public void transformBody(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		for (Ada_Statement stmt1 : statements1._elements)
		{
			AbstractToken which1 = stmt1.getWhich();
			if (which1 instanceof Ada_Function)
			{
				Ada_Function func = (Ada_Function) which1;
				func.transformFunction(transformer, generator);
			}
			else if (which1 instanceof Ada_Procedure)
			{
				Ada_Procedure proc = (Ada_Procedure) which1;
				proc.transformFunction(transformer, generator);
			}
			else // Other statements
			{
				Collection<AbstractStatement> newStmts1 = transformer.transformStatement(generator, which1);
				if (newStmts1 != null)
				{
					for (AbstractStatement newStmt1 : newStmts1)
					{
						generator.addStatement(newStmt1, which1);
					}
				}
			}
		}

		for (Ada_Statement stmt2 : statements2._elements)
		{
			AbstractToken which2 = stmt2.getWhich();
			Collection<AbstractStatement> newStmts2 = transformer.transformStatement(generator, which2);
			if (newStmts2 != null)
			{
				for (AbstractStatement newStmt2 : newStmts2)
				{
					generator.addStatement(newStmt2, which2);
				}
			}
		}
	}
}
