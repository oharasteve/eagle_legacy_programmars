// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 9, 2022

package com.eagle.programmar.Ruby.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Ruby.Ruby_Statement;
import com.eagle.programmar.Ruby.Ruby_Syntax;
import com.eagle.programmar.Ruby.Ruby_Variable;
import com.eagle.programmar.Ruby.Symbols.Ruby_Function_Definition;
import com.eagle.programmar.Ruby.Terminals.Ruby_EOLN;
import com.eagle.programmar.Ruby.Terminals.Ruby_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Ruby_Function extends TokenSequence
		implements EagleRunnable, AbstractFunction, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) Ruby_Keyword DEF = new Ruby_Keyword("def");
	public @S(20) Ruby_Function_Definition id;
	public @S(30) @OPT Ruby_FunctionParams funcParamDefs;
	public @S(40) Ruby_EOLN eoln1;
	public @S(50) TokenList<Ruby_Statement> statements;
	public @S(60) Ruby_Keyword END = new Ruby_Keyword("end");
	public @S(70) Ruby_EOLN eoln2;

	public static class Ruby_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Ruby_Variable, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Ruby_Syntax.IS_CASE_SENSITIVE);

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
		if (_returnMetrics == null)
		{
			_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);

		String fnName = id.getValue();
		boolean isMain = false;
		if (fnName.equals("main"))
		{
			fnName = generator.mainName(); // Change from 'main' to 'Main' for C#
			isMain = true;
		}

		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Ruby function " + fnName);
		}

		if (isMain)
		{
			// Have to wait until addMethod is called
			generator.addMainArgs(); // For java and C# but not for Python
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (funcParamDefs != null && funcParamDefs.isPresent())
		{
			for (int i = 0; i < funcParamDefs.parameters.getPrimaryCount(); i++)
			{
				Ruby_Variable param = funcParamDefs.parameters.getPrimaryElement(i);
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					TypeEnum metricArg = argTypes.get(i);
					paramType = generator.transformType(metricArg, null, param);
				}

				generator.addMethodParameter(paramType, param.vars.first().getValue());
			}
		}

		addLocalVars(transformer, generator);

		for (Ruby_Statement stmt : statements._elements)
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

	private boolean isFuncParam(String name)
	{
		int numParams = funcParamDefs.parameters.getPrimaryCount();
		for (int i = 0; i < numParams; i++)
		{
			Ruby_Variable var = funcParamDefs.parameters.getPrimaryElement(i);
			if (var.vars.first().getValue().equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}

	// Are there any local variables we need to declare?
	private void addLocalVars(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typ = met.uniqueType();
			if (typ != TypeEnum.VOID)
			{
				if (!isFuncParam(met._symbolName))
				{
					// System.err.println("****** Found local var " + met._symbolName);
					AbstractType absType = generator.transformType(typ, null, this);
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, absType,
							null, this);
					generator.addStatement(dataStmt, this);
				}
			}
		}
	}
}
