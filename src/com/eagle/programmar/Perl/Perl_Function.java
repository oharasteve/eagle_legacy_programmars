// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 26, 2014

package com.eagle.programmar.Perl;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.EagleMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.Perl.Statements.Perl_StatementBlock;
import com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class Perl_Function extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
				EagleTransformableFunction
{
	public @S(10) @OPT TokenList<Perl_FunctionPrefix> modifiers;
	public @S(20) Perl_Keyword FUNCTION = new Perl_Keyword("function");
	public @S(30) Perl_Function_Definition id;
	public @S(40) Perl_Function_Parameters params;
	public @S(50) @OPT Perl_FunctionReturn returns;
	public @S(60) Perl_FunctionBlock block;

	public static class Perl_FunctionPrefix extends TokenSequence
	{
		public @S(10) Perl_KeywordChoice modifier = new Perl_KeywordChoice(Perl_Program.MODIFIERS);
	}

	public static class Perl_FunctionBlock extends TokenChooser
	{
		public @CHOICE PunctuationSemicolon XXsemicolon;
		public @CHOICE Perl_StatementBlock XXblock;
	}

	public static class Perl_Function_Parameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT SeparatedList<Perl_FunctionVariableOrTypeVariable, PunctuationComma> parameters;
		public @S(30) PunctuationRightParen rightParen;
	}

	public static class Perl_FunctionVariableOrTypeVariable extends TokenChooser
	{
		public @LAST Perl_FunctionVariable XXvar;
		public @CHOICE Perl_FunctionTypeAndVariable XXtypeAndVar;
	}

	public static class Perl_FunctionTypeAndVariable extends TokenSequence
	{
		public @S(10) Perl_Type type;
		public @S(20) Perl_FunctionVariable var;
	}
	
	public static class Perl_FunctionVariable extends TokenSequence
	{
		public @S(10) @OPT Perl_Punctuation amp = new Perl_Punctuation('&');
		public @S(20) Perl_Variable_Definition param;
		public @S(30) @OPT Perl_Variable_Initializer init;

		public static class Perl_Variable_Initializer extends TokenSequence
		{
			public @S(10) PunctuationEquals equals;
			public @S(20) Perl_Expression initVal;
		}
	}

	public static class Perl_FunctionReturn extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) Perl_Type returnType;
	}
	
	private @SKIP EagleScope _scope = new EagleScope(this, Perl_Syntax.IS_CASE_SENSITIVE);

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
		TypeEnum typRet = transformer.findReturnMetric(id);
		AbstractType newReturnType = generator.transformType(typRet, null, id);
		
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
			for (int i = 0; i < params.parameters.getPrimaryCount(); i++)
			{
				Perl_FunctionVariableOrTypeVariable param = params.parameters.getPrimaryElement(i);
				Perl_FunctionVariable paramVar;
				TypeEnum type = null;
				if (param.getWhich() instanceof Perl_FunctionVariable)
				{
					paramVar = (Perl_FunctionVariable) param.getWhich();
					if (argTypes != null && i < argTypes.size())
					{
						String metricArgType = argTypes.get(i);
						type = EagleMetrics.convertType(metricArgType);
					}
				}
				else
				{
					Perl_FunctionTypeAndVariable typedParam = (Perl_FunctionTypeAndVariable) param.getWhich();
					paramVar = typedParam.var;
					type  = Perl_Type.findType(typedParam.type);
				}
				
				AbstractType newParamType = generator.transformType(type, null, param);
				String paramName = Perl_Variable.repairName(paramVar.param.getValue());
				generator.addMethodParameter(newParamType, paramName);
			}
		}

		addLocalVars(transformer, generator);

		if (block.getWhich() instanceof Perl_StatementBlock)
		{
			Perl_StatementBlock stmts = (Perl_StatementBlock) block.getWhich();
			if (stmts.statements != null && stmts.statements.size() > 0)
			{
				for (Perl_StatementOrComment stmtOrComment : stmts.statements._elements)
				{
					if (stmtOrComment.getWhich() instanceof Perl_Statement)
					{
						Perl_Statement stmt = (Perl_Statement) stmtOrComment.getWhich();
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
		}
		
		generator.doneMethod();
	}

	private boolean isFuncParam(String name)
	{
		if (params != null && params.isPresent())
		{
			int numParams = params.parameters.getPrimaryCount();
			for (int i = 0; i < numParams; i++)
			{
				Perl_FunctionVariableOrTypeVariable param = params.parameters.getPrimaryElement(i);
				AbstractToken which = param.getWhich();
				Perl_FunctionVariable var;
				if (which instanceof Perl_FunctionTypeAndVariable)
				{
					Perl_FunctionTypeAndVariable varType = (Perl_FunctionTypeAndVariable) which;
					var = varType.var;
				}
				else
				{
					var = (Perl_FunctionVariable) which;
				}
				if (var.param.getValue().equalsIgnoreCase(name))
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
					AbstractStatement dataStmt = generator.newDataDeclaration(false,
							met._symbolName, null, absType, null, this);
					generator.addStatement(dataStmt, this);
				}
			}
		}
	}
}
