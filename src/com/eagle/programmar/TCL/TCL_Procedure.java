// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.EagleGenerator.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.TCL.Statements.TCL_BlockStatement;
import com.eagle.programmar.TCL.Symbols.TCL_Function_Definition;
import com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class TCL_Procedure extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) @DOC("TclCmd/proc.html") TCL_Keyword PROC = new TCL_Keyword("proc");
	public @S(20) TCL_Function_Definition name;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT TokenList<TCL_Variable_Definition> vars;
	public @S(50) PunctuationRightBrace rightBrace;
	public @S(60) TCL_BlockStatement block;
	
	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, TCL_Syntax.IS_CASE_SENSITIVE);

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
			_callMetrics = new CallMetrics(interpreter._metrics, name.getValue(), name);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, name.getValue(), name);
		}
		if (_returnMetrics == null)
		{
			_returnMetrics = new ReturnMetrics(interpreter._metrics, name.getValue(), name);
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}

	@Override
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		generator.addMethod(null, name.getValue(), this);
		generator.addMethodName(name.getValue());
		
		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(name);

		if (vars != null && vars.isPresent())
		{
			int i = 0;
			for (TCL_Variable_Definition param : vars._elements)
			{
				i++;
				AbstractType paramType = null;
				if (argTypes != null && i < argTypes.size())
				{
					String metricArgType = argTypes.get(i);
					TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
					paramType = generator.transformType(false, metricArg, null, param);
				}
				
				generator.addMethodParameter(paramType, param.getValue());
			}
		}
		
		Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, block);
		if (newStmts != null)
		{
			for (AbstractStatement newStmt : newStmts)
			{
				generator.addStatement(newStmt, block);
			}
		}
		
		generator.doneMethod();
	}
}
