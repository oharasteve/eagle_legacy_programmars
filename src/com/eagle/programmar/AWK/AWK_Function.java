// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Mar 16, 2022

package com.eagle.programmar.AWK;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.AWK.AWK_Action.AWK_StatementOrComment;
import com.eagle.programmar.AWK.AWK_Statements.AWK_Statement;
import com.eagle.programmar.AWK.Symbols.AWK_Function_Definition;
import com.eagle.programmar.AWK.Symbols.AWK_Parameter_Definition;
import com.eagle.programmar.AWK.Terminals.AWK_Comment;
import com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
import com.eagle.programmar.AWK.Terminals.AWK_Keyword;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class AWK_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) AWK_Keyword FUNCTION = new AWK_Keyword("function");
	public @S(20) AWK_Function_Definition id;
	public @S(30) AWK_Function_ParameterDefs parameters;
	public @S(40) @OPT TokenList<AWK_Comment> comments;
	public @S(50) AWK_FunctionBody body;

	public static class AWK_Function_ParameterDefs extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT AWK_Comment comment1;
		public @S(30) @OPT SeparatedList<AWK_Parameter_Definition, PunctuationComma> params;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class AWK_FunctionBody extends TokenSequence
	{
		public @S(10) PunctuationLeftBrace leftBrace;
		public @S(20) @OPT AWK_EndOfLine eoln1;
		public @S(30) @OPT TokenList<AWK_StatementOrComment> elements;
		public @S(40) PunctuationRightBrace rightBrace;
		public @S(50) @OPT AWK_EndOfLine eoln2;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, AWK_Syntax.IS_CASE_SENSITIVE);

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
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("** Found AWK function " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (parameters.params != null && parameters.params.isPresent())
		{
			for (int i = 0; i < parameters.params.getPrimaryCount(); i++)
			{
				AWK_Parameter_Definition paramVar = parameters.params.getPrimaryElement(i);
				AbstractType paramType = null;

				if (argTypes != null && i < argTypes.size())
				{
					TypeEnum metricArg = argTypes.get(i);
					paramType = generator.transformType(metricArg, null, paramVar);
				}

				// System.err.println("****** paramType = " + paramType + " value = " +
				// param.getValue());
				generator.addMethodParameter(paramType, paramVar.getValue());
			}
		}

		addLocalVars(transformer, generator);

		for (AWK_StatementOrComment stmtOrComment : body.elements._elements)
		{
			AbstractToken which = stmtOrComment.getWhich();
			if (which instanceof AWK_Action)
			{
				AWK_Action action = (AWK_Action) which;
				for (AWK_StatementOrComment stmt : action.statements._elements)
				{
					ArrayList<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmtOrComment);
						}
					}
				}
			}
			else if (which instanceof AWK_Statements)
			{
				AWK_Statements stmts = (AWK_Statements) which;
				int numStmts = stmts.statements.getPrimaryCount();
				for (int i = 0; i < numStmts; i++)
				{
					AWK_Statement stmt = stmts.statements.getPrimaryElement(i);
					ArrayList<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						for (AbstractStatement newStmt : newStmts)
						{
							generator.addStatement(newStmt, stmtOrComment);
						}
					}
				}
			}
		}

		generator.doneMethod();
	}

	private boolean isFuncParam(String name)
	{
		if (parameters != null && parameters.isPresent())
		{
			int numParams = parameters.params.getPrimaryCount();
			for (int i = 0; i < numParams; i++)
			{
				AWK_Parameter_Definition var = parameters.params.getPrimaryElement(i);
				if (var.getValue().equalsIgnoreCase(name))
				{
					return true;
				}
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
