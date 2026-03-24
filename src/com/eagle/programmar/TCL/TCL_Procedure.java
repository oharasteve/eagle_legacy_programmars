// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 30, 2022

package com.eagle.programmar.TCL;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.TCL.TCL_Element.TCL_Statement;
import com.eagle.programmar.TCL.Statements.TCL_BlockStatement;
import com.eagle.programmar.TCL.Symbols.TCL_Function_Definition;
import com.eagle.programmar.TCL.Symbols.TCL_Variable_Definition;
import com.eagle.programmar.TCL.Terminals.TCL_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class TCL_Procedure extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @DOC("TclCmd/proc.html") TCL_Keyword PROC = new TCL_Keyword("proc");
	public @S(20) TCL_Function_Definition id;
	public @S(30) PunctuationLeftBrace leftBrace;
	public @S(40) @OPT TokenList<TCL_Variable_Definition> vars;
	public @S(50) PunctuationRightBrace rightBrace;
	public @S(60) TCL_BlockStatement body;

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
	public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);

		String fnName = id.getValue();
		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found TCL procedure " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<String> argTypes = transformer.findArgumentsMetric(id);

		if (vars != null && vars.isPresent())
		{
			int i = 0;
			for (TCL_Variable_Definition param : vars._elements)
			{
				AbstractType paramType = null;
				if (argTypes != null && i < argTypes.size())
				{
					String metricArgType = argTypes.get(i);
					TypeEnum metricArg = EagleMetrics.convertType(metricArgType);
					paramType = generator.transformType(metricArg, null, param);
				}

				generator.addMethodParameter(paramType, param.getValue());
				i++;
			}
		}

		addLocalVars(transformer, generator);

		for (TCL_Element element : body.statements._elements)
		{
			int nStmts = element.statements.getPrimaryCount();
			for (int i = 0; i < nStmts; i++)
			{
				TCL_Statement stmt = element.statements.getPrimaryElement(i);
				ArrayList<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, body);
					}
				}
			}
		}

		generator.doneMethod();
	}

	private boolean isFuncParam(String name)
	{
		for (TCL_Variable_Definition var : vars._elements)
		{
			if (var.getValue().equalsIgnoreCase(name))
			{
				return true;
			}
		}
		return false;
	}

	// Are there any local variables we need to declare?
	private void addLocalVars(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
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
					// System.err.println("****** Found var " + met._symbolName);
					AbstractType absType = generator.transformType(typ, null, this);
					AbstractStatement dataStmt = generator.newDataDeclaration(false,
							met._symbolName, null, absType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}
		}
	}
}
