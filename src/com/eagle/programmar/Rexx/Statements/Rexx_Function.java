// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 18, 2025

package com.eagle.programmar.Rexx.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Rexx.Rexx_Element;
import com.eagle.programmar.Rexx.Rexx_Syntax;
import com.eagle.programmar.Rexx.Symbols.Rexx_Function_Definition;
import com.eagle.programmar.Rexx.Symbols.Rexx_Variable_Definition;
import com.eagle.programmar.Rexx.Terminals.Rexx_EndOfLine;
import com.eagle.programmar.Rexx.Terminals.Rexx_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Rexx_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) @DOC("reference-functions") Rexx_Function_Definition id;
	public @S(20) PunctuationColon colon;
	public @S(30) Rexx_EndOfLine eoln;
	public @S(40) @OPT Rexx_Parameters params;
	public @S(50) TokenList<Rexx_Element> stmts;
	
	public static class Rexx_Parameters extends TokenSequence
	{
		public @S(10) Rexx_Keyword PARSE = new Rexx_Keyword("PARSE");
		public @S(20) Rexx_Keyword ARG = new Rexx_Keyword("ARG");
		public @S(30) SeparatedList<Rexx_Variable_Definition, PunctuationComma> params;
		public @S(40) Rexx_EndOfLine eoln;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, Rexx_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

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
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);
		
		generator.addMethod(newReturnType, id.getValue(), this);
		generator.addMethodName(id.getValue());
		
		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);
		
		if (params != null && params.isPresent())
		{
			for (int i = 0; i < params.params.getPrimaryCount(); i++)
			{
				Rexx_Variable_Definition param = params.params.getPrimaryElement(i);
				AbstractType paramType = null;
				
				if (argTypes != null && i < argTypes.size())
				{
					String metricArgType = argTypes.get(i);
					TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
					paramType = generator.transformType(metricArg, null, param);
				}
				
				// System.err.println("****** paramType = " + paramType + " value = " + param.getValue());
				generator.addMethodParameter(paramType, param.getValue());
			}
		}
		
		addLocalVars(transformer, generator);
		
		for (Rexx_Element stmt : stmts._elements)
		{
			AbstractToken which = stmt.baseStatement.getWhich();

			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmt);
				}
			}
		}
		
		generator.doneMethod();
	}

	private boolean isFuncParam(String name)
	{
		if (params != null && params.isPresent())
		{
			int numParams = params.params.getPrimaryCount();
			for (int i = 0; i < numParams; i++)
			{
				Rexx_Variable_Definition var = params.params.getPrimaryElement(i);
				if (var.getValue().equalsIgnoreCase(name))
				{
					return true;
				}
			}
		}
		return false;
	}

	// Are there any local variables we need to declare?
	private void addLocalVars(EagleTransformer transformer, EagleGenerator generator)
	{
		String scopeStr = this._currentLine + "-" + this._endLine;
		ArrayList<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
		for (AssignMetrics met : asgMetrics)
		{
			TypeEnum typ = met.uniqueType();
			if (typ != TypeEnum.VOID)
			{
				if (! isFuncParam(met._symbolName))
				{
					// System.err.println("****** Found var " + met._symbolName);
					AbstractType absType = generator.transformType(typ, null, this);
					AbstractStatement dataStmt = generator.newDataDeclaration(met._symbolName, null, absType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}
		}
	}
}
