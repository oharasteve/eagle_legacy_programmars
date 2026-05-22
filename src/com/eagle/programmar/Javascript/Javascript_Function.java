// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Javascript.Javascript_Element.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_FunctionParameter;
import com.eagle.programmar.Javascript.Javascript_FunctionParameters.Javascript_MoreParameters;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Javascript_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @OPT Javascript_KeywordChoice STATIC = new Javascript_KeywordChoice("static", "async");
	public @S(20) @OPT Javascript_Keyword EXPORT = new Javascript_Keyword("export");
	public @S(30) @OPT Javascript_Keyword DEFAULT = new Javascript_Keyword("default");
	public @S(40) @OPT Javascript_Keyword FUNCTION = new Javascript_Keyword("function");
	public @S(50) Javascript_FunctionImplementation implementation;

	public static class Javascript_FunctionImplementation extends TokenSequence
	{
		public @S(10) @OPT Javascript_Function_Definition id;
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT Javascript_FunctionParameters params;
		public @S(40) @OPT TokenList<Javascript_Comment> comments1;
		public @S(50) PunctuationRightParen rightParen;
		public @S(60) @OPT TokenList<Javascript_Comment> comments2;
		public @S(70) Javascript_FunctionBody body;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Javascript_Syntax.IS_CASE_SENSITIVE);

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
			_callMetrics = new CallMetrics(interpreter._metrics, implementation.id.getValue(), implementation.id);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, implementation.id.getValue(),
					implementation.id);
		}
		if (_returnMetrics == null)
		{
			_returnMetrics = new ReturnMetrics(interpreter._metrics, implementation.id.getValue(), implementation.id);
		}

		// Nothing to do here. Only run functions when they are called / invoked.
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		AbstractType newReturnType = null;
		TypeEnum metricRetType = transformer.findReturnMetric(implementation.id);
		if (metricRetType != null)
		{
			newReturnType = generator.transformType(metricRetType, null, implementation);
		}

		String newName = implementation.id.getValue();
		if (VERBOSE)
		{
			System.out.println("** Found Javascript method " + newName);
		}
		if (newName.equals("Main"))
		{
			newName = generator.mainName();
		}

		generator.addMethod(newReturnType, newName, this);
		generator.setMethodName(newName);

		// Pick up metrics, if known
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(implementation.id);

		Javascript_FunctionParameter param1 = implementation.params.param;
		AbstractToken which1 = param1.paramName.getWhich();
		AbstractType type = null;
		if (which1 instanceof Javascript_Variable_Definition)
		{
			if (argTypes != null)
			{
				TypeEnum argType = argTypes.get(0);
				type = generator.transformType(argType, null, which1);
			}
			Javascript_Variable_Definition varDef1 = (Javascript_Variable_Definition) which1;
			generator.addMethodParameter(type, varDef1.getValue());
		}

		int argNumber = 1;
		for (Javascript_MoreParameters next : implementation.params.moreParams._elements)
		{
			Javascript_FunctionParameter param2 = next.param;
			AbstractToken which2 = param2.paramName.getWhich();
			if (which2 instanceof Javascript_Variable_Definition)
			{
				if (argTypes != null)
				{
					TypeEnum argType = argTypes.get(argNumber);
					type = generator.transformType(argType, null, which1);
				}
				Javascript_Variable_Definition varDef2 = (Javascript_Variable_Definition) which2;
				generator.addMethodParameter(type, varDef2.getValue());
			}
			argNumber++;
		}

		Javascript_FunctionBody impl = implementation.body;
		ArrayList<AbstractStatement> newStmts = new ArrayList<AbstractStatement>();
		for (Javascript_StatementOrComment javaStmt : impl.statements._elements)
		{
			if (javaStmt.getWhich() instanceof Javascript_Statement)
			{
				Javascript_Statement stmt1 = (Javascript_Statement) javaStmt.getWhich();
				ArrayList<AbstractStatement> stmts2 = transformer.transformStatement(generator, stmt1.getWhich());
				if (stmts2 != null)
				{
					for (AbstractStatement stmt2 : stmts2)
					{
						newStmts.add(stmt2);
					}
				}
			}
		}

		AbstractStatement newBlock = generator.newBlockStatement(newStmts, impl);
		generator.addStatement(newBlock, impl);
		generator.doneMethod();
	}
}
