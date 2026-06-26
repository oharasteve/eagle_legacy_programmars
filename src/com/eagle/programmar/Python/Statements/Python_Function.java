// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2013

package com.eagle.programmar.Python.Statements;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Python.Python_ComplexStatement;
import com.eagle.programmar.Python.Python_Decorators;
import com.eagle.programmar.Python.Python_Parameter_List;
import com.eagle.programmar.Python.Python_Params;
import com.eagle.programmar.Python.Python_Params.Python_MoreParams;
import com.eagle.programmar.Python.Python_Params.Python_Parameter;
import com.eagle.programmar.Python.Python_Syntax;
import com.eagle.programmar.Python.Python_Type;
import com.eagle.programmar.Python.Statements.Python_StatementBlock.Python_MultilineStatement;
import com.eagle.programmar.Python.Symbols.Python_Function_Definition;
import com.eagle.programmar.Python.Symbols.Python_Variable_Definition;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractMethod;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

// Why does this implement AbstractMethod ?? Transformation needs / uses it, but why ??
public class Python_Function extends TokenSequence
		implements AbstractMethod, AbstractFunction, EagleRunnable,
		EagleTransformableFunction
{
	public @S(10) @OPT @BLANKLINE Python_Decorators decorators;
	public @S(20) @OPT Python_Keyword ASYNC = new Python_Keyword("async");
	public @S(30) @DOC("compound_stmts.html#function-definitions") Python_Keyword DEF = new Python_Keyword("def");
	public @S(40) Python_FunctionName fnName;
	public @S(50) Python_FunctionHeader header;

	public static class Python_FunctionHeader extends TokenSequence implements EagleScopeInterface
	{
		public @S(10) Python_Parameter_List params;
		public @S(20) @OPT Python_ReturnType returnType;
		public @S(30) @NOSPACE PunctuationColon colon;
		public @S(40) @OPT TokenList<Python_Comment> comment;
		public @S(50) @PYDENT Python_StatementBlock defBody;

		private @SKIP EagleScope _scope = new EagleScope(this, Python_Syntax.IS_CASE_SENSITIVE);

		@Override
		public EagleScope getScope()
		{
			return _scope;
		}
	}

	public static class Python_FunctionName extends TokenChooser
	{
		public @CHOICE Python_Function_Definition XXname;
		public @CHOICE Python_Keyword XXINIT = new Python_Keyword("__init__");
	}

	public static class Python_ReturnType extends TokenSequence
	{
		public @S(10) Python_Punctuation arrow = new Python_Punctuation("->");
		public @S(20) Python_Type type;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (fnName.getWhich() instanceof Python_Function_Definition)
		{
			Python_Function_Definition id = (Python_Function_Definition) fnName.getWhich();
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
		}

		// Don't do anything here.
		// We searched for all the functions in a preliminary pass
		// And we only evaluate when it is called
	}

	public static Python_Function newPythonFunction(String name)
	{
		Python_Function_Definition funcDef = new Python_Function_Definition();
		funcDef.setValue(name);
		Python_Function func = new Python_Function();
		func.fnName = new Python_FunctionName();
		func.fnName.setWhich(funcDef);

		func.header = new Python_FunctionHeader();
		func.header.colon = new PunctuationColon();

		func.header.params = new Python_Parameter_List();
		func.header.params.leftParen = new PunctuationLeftParen();
		func.header.params.params = new Python_Params();
		func.header.params.params.setPresent(true);
		func.header.params.rightParen = new PunctuationRightParen();

		func.header.defBody = new Python_StatementBlock();
		Python_MultilineStatement multi = new Python_MultilineStatement();
		multi.statements = new TokenList<Python_ComplexStatement>();
		func.header.defBody.setWhich(multi);

		return func;
	}

	public void addFunctionParameter(AbstractType type, String name)
	{
		Python_Variable_Definition var = new Python_Variable_Definition();
		var.setValue(name);
		Python_Parameter newParam = new Python_Parameter();
		newParam.setWhich(var);

		if (header.params.params.param == null)
		{
			header.params.params.param = newParam;
		}
		else
		{
			if (header.params.params.moreParams == null)
			{
				header.params.params.moreParams = new TokenList<Python_MoreParams>();
			}
			Python_MoreParams more = new Python_MoreParams();
			more.comma = new PunctuationComma();
			more.param = newParam;
			header.params.params.moreParams.addToken(more);
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		if (!(fnName.getWhich() instanceof Python_Function_Definition))
		{
			throw new RuntimeException("Can only handle regular function definitions");
		}
		Python_Function_Definition id = (Python_Function_Definition) fnName.getWhich();
		TypeEnum metricRetType = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id);

		String name = id.getValue();
		generator.addMethod(newReturnType, name, this);
		generator.setMethodName(name);
		if (VERBOSE)
		{
			System.out.println("** Found Python function " + name);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id);

		if (header.params != null && header.params.isPresent())
		{
			Python_Parameter paramVar1 = header.params.params.param;
			AbstractType paramType1 = null;
			if (argTypes != null && 0 < argTypes.size())
			{
				TypeEnum metricArg1 = argTypes.get(0);
				paramType1 = generator.transformType(metricArg1, null, paramVar1);
			}
			AbstractToken which = paramVar1.getWhich();
			if (which != null)
			{
				if (!(which instanceof Python_Variable_Definition))
				{
					throw new RuntimeException("Unable to handle " + which);
				}
				Python_Variable_Definition varDef1 = (Python_Variable_Definition) which;
				// System.err.println("****** paramType = " + paramType1 + " value = " + varDef1.getValue());
				generator.addMethodParameter(paramType1, varDef1.getValue());
			}

			if (header.params.params.moreParams != null)
			{
				int i = 1;
				for (Python_MoreParams more : header.params.params.moreParams._elements)
				{
					Python_Parameter paramVar2 = more.param;
					AbstractType paramType2 = null;
					if (argTypes != null && 0 < argTypes.size())
					{
						TypeEnum metricArg2 = argTypes.get(i);
						paramType2 = generator.transformType(metricArg2, null, paramVar2);
					}
					if (!(paramVar2.getWhich() instanceof Python_Variable_Definition))
					{
						throw new RuntimeException("Unable to handle " + paramVar2.getWhich());
					}
					Python_Variable_Definition varDef2 = (Python_Variable_Definition) paramVar2.getWhich();
					// System.err.println("****** paramType = " + paramType2 + " value = " + varDef2.getValue());
					generator.addMethodParameter(paramType2, varDef2.getValue());
					i++;
				}
			}
		}

		addLocalVars(transformer, generator);

		for (AbstractStatement stmt1 : header.defBody.transformStatement(transformer, generator))
		{
			generator.addStatement(stmt1, header.defBody);
		}

		generator.doneMethod();
	}

	private boolean isFuncParam(String name)
	{
		if (header.params != null && header.params.isPresent())
		{
			Python_Parameter paramVar1 = header.params.params.param;
			if (paramVar1.getWhich() instanceof Python_Variable_Definition)
			{
				Python_Variable_Definition varDef1 = (Python_Variable_Definition) paramVar1.getWhich();
				if (varDef1.getValue().equals(name))
				{
					return true;
				}
			}

			if (header.params.params.moreParams != null)
			{
				for (Python_MoreParams more : header.params.params.moreParams._elements)
				{
					Python_Parameter paramVar2 = more.param;
					if (paramVar2.getWhich() instanceof Python_Variable_Definition)
					{
						Python_Variable_Definition varDef2 = (Python_Variable_Definition) paramVar2.getWhich();
						if (varDef2.getValue().equals(name))
						{
							return true;
						}
					}
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
