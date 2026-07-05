// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

package com.eagle.programmar.SQL.Statements;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.generate.EagleGenerator;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.metrics.ArgumentsMetrics;
import com.eagle.metrics.CallMetrics;
import com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
import com.eagle.programmar.SQL.SQL_Statement;
import com.eagle.programmar.SQL.SQL_Syntax;
import com.eagle.programmar.SQL.SQL_Type;
import com.eagle.programmar.SQL.Symbols.SQL_Parameter_Definition;
import com.eagle.programmar.SQL.Symbols.SQL_Procedure_Definition;
import com.eagle.programmar.SQL.Terminals.SQL_Keyword;
import com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
import com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
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
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleTransformableFunction;
import com.eagle.transform.EagleTransformer;

public class SQL_CreateProcedureStatement extends TokenSequence
		implements AbstractFunction, EagleRunnable, EagleScopeInterface,
		EagleTransformableFunction
{
	public @S(10) @DOC("sql_create_procedure.asp") SQL_Keyword CREATE = new SQL_Keyword("CREATE");
	public @S(20) @OPT SQL_OrReplaceProcedure replace;
	public @S(30) SQL_Keyword PROCEDURE = new SQL_Keyword("PROCEDURE");
	public @S(40) SQL_Procedure_Definition procName;
	public @S(50) PunctuationLeftParen leftParen;
	public @S(60) @OPT SeparatedList<SQL_ProcedureParameter, PunctuationComma> params;
	public @S(70) PunctuationRightParen rightParen;
	public @S(80) SQL_Keyword BEGIN = new SQL_Keyword("BEGIN");
	public @S(90) TokenList<SQL_StatementOrComment> statements;
	public @S(100) SQL_Keyword END = new SQL_Keyword("END");
	public @S(110) SQL_PunctuationChoice semicolon = new SQL_PunctuationChoice(";", "//");

	public static class SQL_OrReplaceProcedure extends TokenSequence
	{
		public @S(10) SQL_Keyword OR = new SQL_Keyword("OR");
		public @S(20) SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
	}

	public static class SQL_ProcedureParameter extends TokenSequence
	{
		public @S(10) @OPT SQL_KeywordChoice OUT = new SQL_KeywordChoice("IN", "OUT");
		public @S(20) SQL_Parameter_Definition param;
		public @S(30) SQL_Type type;
	}

	public @SKIP CallMetrics _callMetrics = null;
	public @SKIP ArgumentsMetrics _argumentsMetrics = null;

	private @SKIP EagleScope _scope = new EagleScope(this, SQL_Syntax.IS_CASE_SENSITIVE);

	@Override
	public EagleScope getScope()
	{
		return _scope;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// This is unusual. SQL_Program collects these when it starts to run
		// So there isn't really much to do here.
		// It will get called at some point, that is when the work happens.
		if (_callMetrics == null)
		{
			_callMetrics = new CallMetrics(interpreter._metrics, procName.getValue(), procName);
		}
		if (_argumentsMetrics == null)
		{
			_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, procName.getValue(), procName);
		}
	}

	@Override
	public void transformFunction(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		String newName = procName.getValue();
		generator.addMethod(null, newName, this);
		generator.addMethodName(newName);
		if (VERBOSE)
		{
			System.out.println("** Found SQL procedure " + newName);
		}

		if (params != null && params.isPresent())
		{
			int nParams = params.getPrimaryCount();
			for (int i = 0; i < nParams; i++)
			{
				SQL_ProcedureParameter param = params.getPrimaryElement(i);
				AbstractType paramType = SQL_Type.findAbstractType(generator, param.type);
				generator.addMethodParameter(paramType, param.param.getValue());
			}
		}

		findGlobalVariables(transformer, generator);

		for (SQL_StatementOrComment stmtComm : statements._elements)
		{
			AbstractToken which = stmtComm.getWhich();
			if (which instanceof SQL_Statement)
			{
				SQL_Statement stmt = (SQL_Statement) which;
				ArrayList<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt);
				if (newStmts != null)
				{
					for (AbstractStatement newStmt : newStmts)
					{
						generator.addStatement(newStmt, stmt);
					}
				}
			}
		}

		generator.doneMethod();
	}

	private void findGlobalVariables(EagleTransformer transformer,
			EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
	{
		// Are there any 'global' variables we need to declare?
		Collection<String> externals = this.getScope().allExternalReferences();
		if (externals != null && externals.size() > 0)
		{
			for (String varName : externals)
			{
				if (! generator.isKnownMethod(varName))
				{
					AbstractStatement newStmt = generator.newGlobalVariable(varName, null);
					generator.addStatement(newStmt, null);
				}
			}
		}
	}
}