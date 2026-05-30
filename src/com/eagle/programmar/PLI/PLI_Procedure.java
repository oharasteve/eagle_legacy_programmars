// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 26, 2011

package com.eagle.programmar.PLI;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.AssignMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.metrics.ReturnMetrics;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Literal;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.scope.EagleScope;
import com.eagle.scope.EagleScope.EagleScopeInterface;
import com.eagle.tokens.AbstractFunction;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.StaticEnum;
import com.eagle.transform.EagleGenerator.TypeEnum;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class PLI_Procedure extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @OPT PLI_Signals signals;
	public @S(20) @OPT PLI_Punctuation percent1 = new PLI_Punctuation('%');
	public @S(30) PLI_Procedure_Definition id1;
	public @S(40) PunctuationColon colon;

	public @S(50) PLI_KeywordChoice PROCEDURE = new PLI_KeywordChoice("PROCEDURE", "PROC");
	public @S(60) @OPT PLI_Procedure_Parameters params;
	public @S(70) @OPT TokenList<PLI_ProcedureOption> options;
	public @S(80) PunctuationSemicolon semicolon1;

	public @S(90) TokenList<PLI_StatementOrComment> statements;

	public @S(100) @OPT PLI_Punctuation percent2 = new PLI_Punctuation('%');
	public @S(110) PLI_Keyword END = new PLI_Keyword("END");
	public @S(120) PLI_Identifier_Reference id2;
	public @S(130) PunctuationSemicolon semicolon2;

	public static class PLI_ProcedureOption extends TokenChooser
	{
		public @CHOICE PLI_ProcedureOptionsMain XXoptionsMain;
		public @CHOICE PLI_ProcedureOptionsLinkage XXoptionsLinkage;
		public @CHOICE PLI_Keyword XXRECURSIVE = new PLI_Keyword("RECURSIVE");
		public @CHOICE PLI_ProcedureReturns XXreturns;
		public @CHOICE PLI_ProcedureExternal XXexternal;
	}

	public static class PLI_Procedure_Parameters extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT PunctuationStar star;
		public @S(30) @OPT SeparatedList<PLI_Identifier_Reference, PunctuationComma> params;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PLI_ProcedureOptionsMain extends TokenSequence
	{
		public @S(10) PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) @OPT PLI_Keyword MAIN = new PLI_Keyword("MAIN");
		public @S(40) @OPT PunctuationComma comma;
		public @S(50) @OPT PLI_KeywordChoice order = new PLI_KeywordChoice("ORDER", "REENTRANT", "REORDER");
		public @S(60) PunctuationRightParen rightParen;
	}

	public static class PLI_ProcedureOptionsLinkage extends TokenSequence
	{
		public @S(10) PLI_Keyword OPTIONS = new PLI_Keyword("OPTIONS");
		public @S(20) PunctuationLeftParen leftParen1;
		public @S(30) PLI_Keyword LINKAGE = new PLI_Keyword("LINKAGE");
		public @S(40) PunctuationLeftParen leftParen2;
		public @S(50) PLI_Keyword SYSTEM = new PLI_Keyword("SYSTEM");
		public @S(60) PunctuationRightParen rightParen2;
		public @S(70) PunctuationRightParen rightParen1;
	}

	public static class PLI_ProcedureReturns extends TokenSequence
	{
		public @S(10) PLI_Keyword RETURNS = new PLI_Keyword("RETURNS");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) PLI_Type type;
		public @S(40) @OPT PLI_Keyword BYADDR = new PLI_Keyword("BYADDR");
		public @S(50) PunctuationRightParen rightParen;
	}

	public static class PLI_ProcedureExternal extends TokenSequence
	{
		public @S(10) PLI_KeywordChoice EXTERNAL = new PLI_KeywordChoice("EXT", "EXTERNAL");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) PLI_Literal literal;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PLI_StatementOrComment extends TokenChooser
	{
		public @FIRST PLI_Entry XXentry;
		public @CHOICE PLI_Comment XXcomment;
		public @CHOICE PLI_Statement XXstatement;
		public @CHOICE PLI_Declaration XXdeclaration;
		public @CHOICE PLI_Signals XXsignals;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;
	public @SKIP ReturnMetrics _returnMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, PLI_Syntax.IS_CASE_SENSITIVE);

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
			_callMetrics = new CallMetrics(interpreter._metrics, id1.getValue(), id1);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id1.getValue(), id1);
		}
		if (_returnMetrics == null)
		{
			_returnMetrics = new ReturnMetrics(interpreter._metrics, id1.getValue(), id1);
		}

		// Only auto-run the Procedure if it has OPTIONS(MAIN)
		// Otherwise, let PLI_CallStatement or PLI_VariableOrFunctionCall run it
		if (options != null && options.isPresent())
		{
			for (PLI_ProcedureOption opt : options._elements)
			{
				if (opt.getWhich() instanceof PLI_ProcedureOptionsMain)
				{
					PLI_ProcedureOptionsMain main = (PLI_ProcedureOptionsMain) opt.getWhich();
					if (main.MAIN != null && main.MAIN.isPresent())
					{
						interpreter.callingFunction("main", this);
						for (PLI_StatementOrComment elt : statements._elements)
						{
							interpreter.tryToInterpret(elt);
						}
						interpreter.completedFunction("main", this);
					}
				}
			}
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		TypeEnum metricRetType = transformer.findReturnMetric(id1);
		AbstractType newReturnType = generator.transformType(metricRetType, null, id1);

		String fnName = id1.getValue();
		generator.addMethod(newReturnType, fnName, this);
		generator.setMethodName(fnName);
		if (VERBOSE)
		{
			System.out.println("*** Found PLI procedure " + fnName);
		}

		// Search metrics for arg types -- might not be any
		ArrayList<TypeEnum> argTypes = transformer.findArgumentsMetric(id1);

		if (params != null && params.isPresent())
		{
			for (int i = 0; i < params.params.getPrimaryCount(); i++)
			{
				PLI_Identifier_Reference paramVar = params.params.getPrimaryElement(i);
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

		for (PLI_StatementOrComment stmtOrComment : statements._elements)
		{
			AbstractToken which = stmtOrComment.getWhich();
			Collection<AbstractStatement> newStmts = transformer.transformStatement(generator, which);
			if (newStmts != null)
			{
				for (AbstractStatement newStmt : newStmts)
				{
					generator.addStatement(newStmt, stmtOrComment);
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
				PLI_Identifier_Reference param = params.params.getPrimaryElement(i);
				if (param.getValue().equalsIgnoreCase(name))
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
