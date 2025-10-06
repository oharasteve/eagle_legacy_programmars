// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2022

package com.eagle.programmar.FSharp.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.FSharp.FSharp_Element;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement;
import com.eagle.programmar.FSharp.FSharp_Element.FSharp_Statement_List;
import com.eagle.programmar.FSharp.FSharp_Syntax;
import com.eagle.programmar.FSharp.FSharp_Type;
import com.eagle.programmar.FSharp.Symbols.FSharp_Function_Definition;
import com.eagle.programmar.FSharp.Symbols.FSharp_Variable_Definition;
import com.eagle.programmar.FSharp.Terminals.FSharp_EndOfLine;
import com.eagle.programmar.FSharp.Terminals.FSharp_Keyword;
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
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class FSharp_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) @DOC("functions/") FSharp_Keyword LET = new FSharp_Keyword("let");
	public @S(20) FSharp_Function_Definition id;
	public @S(30) PunctuationLeftParen leftParen;
	public @S(40) @OPT SeparatedList<FSharp_FunctionParam, PunctuationComma> params;
	public @S(50) PunctuationRightParen rightParen;
	public @S(60) PunctuationEquals equals;
	public @S(70) FSharp_EndOfLine eoln;
	public @S(80) TokenList<FSharp_Element> statements;

	public static class FSharp_FunctionParam extends TokenSequence
	{
		public @S(10) FSharp_Variable_Definition var;
		public @S(20) PunctuationColon colon;
		public @S(30) FSharp_Type type;
	}
	
	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, FSharp_Syntax.IS_CASE_SENSITIVE);

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
	public void transformFunction(EagleTransformer transformer, EagleGenerator generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);
		
		String fnName = id.getValue();

		generator.addMethod(newReturnType, fnName, this);
		generator.addMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found F# function " + fnName);
		}
		
		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);
		
		if (params != null && params.isPresent())
		{
			for (int i = 0; i < params.getPrimaryCount(); i++)
			{
				FSharp_FunctionParam param = params.getPrimaryElement(i);
				AbstractType paramType = null;
				
				if (argTypes != null && i < argTypes.size())
				{
					String metricArgType = argTypes.get(i);
					TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
					paramType = generator.transformType(metricArg, null, param);
				}
				
				generator.addMethodParameter(paramType, param.var.getValue());
			}
		}

		for (FSharp_Element elt : statements._elements)
		{
			AbstractToken which = elt.statementOrComment.getWhich();
			if (which instanceof FSharp_Statement_List)
			{
				FSharp_Statement_List stmtList = (FSharp_Statement_List) which;
				for (int i = 0; i < stmtList.statements.getPrimaryCount(); i++)
				{
					FSharp_Statement stmt = stmtList.statements.getPrimaryElement(i);
					Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmt.getWhich());
						}
					}
				}
			}
		}
		
		generator.doneMethod();
	}
}
