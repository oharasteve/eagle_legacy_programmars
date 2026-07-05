// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 28, 2022

package com.eagle.programmar.Powershell.Statements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Powershell.Powershell_Element;
import com.eagle.programmar.Powershell.Powershell_EndOfLine;
import com.eagle.programmar.Powershell.Powershell_Syntax;
import com.eagle.programmar.Powershell.Powershell_Type;
import com.eagle.programmar.Powershell.Powershell_Variable;
import com.eagle.programmar.Powershell.Symbols.Powershell_Function_Definition;
import com.eagle.programmar.Powershell.Terminals.Powershell_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.PrimaryOperator;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Powershell_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @DOC("chapter-08?view=powershell-5.1#810-function-definitions") Powershell_Keyword FUNCTION = new Powershell_Keyword(
			"Function");
	public @S(20) Powershell_Function_Definition id;
	public @S(30) @OPT Powershell_FunctionParams params;
	public @S(40) @OPT Powershell_EndOfLine eoln1;
	public @S(50) PunctuationLeftBrace leftBrace;
	public @S(60) @OPT Powershell_EndOfLine eoln2;
	public @S(70) @OPT TokenList<Powershell_Element> stmts;
	public @S(80) PunctuationRightBrace rightBrace;

	public static class Powershell_FunctionParams extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Powershell_FunctionParam, PunctuationComma> params;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Powershell_FunctionParam extends TokenSequence
	{
		public @S(10) @OPT Powershell_CastParameter cast;
		public @S(20) Powershell_Variable var;

		public static class Powershell_CastParameter extends PrimaryOperator
		{
			public @S(10) PunctuationLeftBracket leftBracket;
			public @S(20) Powershell_Type type;
			public @S(30) PunctuationRightBracket rightBracket;
		}
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, Powershell_Syntax.IS_CASE_SENSITIVE);

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
		// We searched for all the function in a preliminary pass
		// And we only evaluate when it is called
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);

		String fnName = id.getValue();
		generator.addMethod(newReturnType, fnName, this);
		generator.addMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found Powershell function " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (params != null && params.isPresent())
		{
			for (int i = 0; i < params.params.getPrimaryCount(); i++)
			{
				Powershell_FunctionParam paramVar = params.params.getPrimaryElement(i);
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					TypeEnum metricArg = argTypes.get(i);
					paramType = generator.transformType(metricArg, null, paramVar);
				}

				// System.err.println("****** paramType = " + paramType + " value = " +
				// param.getValue());
				generator.addMethodParameter(paramType, paramVar.var.id.getValue());
			}
		}

		findGlobalVars(transformer, generator);
		
		addLocalVars(transformer, generator);

		for (Powershell_Element stmt : stmts._elements)
		{
			AbstractToken which = stmt.element.getWhich();

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
				Powershell_FunctionParam var = params.params.getPrimaryElement(i);
				if (var.var.id.getValue().equalsIgnoreCase(name))
				{
					return true;
				}
			}
		}
		return false;
	}

	// Anything to declare as 'global' in python?
	// E.g., $script:emsg
	private void findGlobalVars(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		HashSet<String> added = new HashSet<String>();
		for (AssignMetrics var : transformer._metrics.findAllAssignments())
		{
			if (var._startingLine >= this._currentLine && var._startingLine <= this._endLine)
			{
				if (var._scopeStart == 0)
				{
					// Scope of this variable is outside the current function.
					// I.e., it is a $script:emsg or similar 
					String varName = var._symbolName;
					if (! added.contains(varName))
					{
						AbstractStatement newStmt = generator.newGlobalVariable(varName, null);
						generator.addStatement(newStmt, null);
						added.add(varName);
					}
				}
			}
		}
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
					// System.err.println("****** Found var " + met._symbolName);
					AbstractType absType = generator.transformType(typ, null, this);
					AbstractStatement dataStmt = generator.newDataDeclaration(StaticEnum.NONE,
							met._symbolName, null, absType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}
		}
	}
}
