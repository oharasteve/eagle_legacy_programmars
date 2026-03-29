// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Mar 30, 2025

namespace com.eagle.programmar.SQL.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using SQL_StatementOrComment = com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment;
	using SQL_Statement = com.eagle.programmar.SQL.SQL_Statement;
	using SQL_Syntax = com.eagle.programmar.SQL.SQL_Syntax;
	using SQL_Type = com.eagle.programmar.SQL.SQL_Type;
	using SQL_Parameter_Definition = com.eagle.programmar.SQL.Symbols.SQL_Parameter_Definition;
	using SQL_Procedure_Definition = com.eagle.programmar.SQL.Symbols.SQL_Procedure_Definition;
	using SQL_Keyword = com.eagle.programmar.SQL.Terminals.SQL_Keyword;
	using SQL_KeywordChoice = com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice;
	using SQL_PunctuationChoice = com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_CreateProcedureStatement : TokenSequence, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("sql_create_procedure.asp") com.eagle.programmar.SQL.Terminals.SQL_Keyword CREATE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("CREATE");
		public @DOC("sql_create_procedure.asp") SQL_Keyword CREATE = new SQL_Keyword("CREATE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT SQL_OrReplaceProcedure replace;
		public @OPT SQL_OrReplaceProcedure replace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.Terminals.SQL_Keyword PROCEDURE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("PROCEDURE");
		public SQL_Keyword PROCEDURE = new SQL_Keyword("PROCEDURE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.SQL.Symbols.SQL_Procedure_Definition procName;
		public SQL_Procedure_Definition procName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT SeparatedList<SQL_ProcedureParameter, com.eagle.tokens.punctuation.PunctuationComma> params;
		public @OPT SeparatedList<SQL_ProcedureParameter, PunctuationComma> @params;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.SQL.Terminals.SQL_Keyword BEGIN = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("BEGIN");
		public SQL_Keyword BEGIN = new SQL_Keyword("BEGIN");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.tokens.TokenList<com.eagle.programmar.SQL.SQL_Program.SQL_StatementOrComment> statements;
		public TokenList<SQL_StatementOrComment> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.programmar.SQL.Terminals.SQL_Keyword END = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("END");
		public SQL_Keyword END = new SQL_Keyword("END");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice semicolon = new com.eagle.programmar.SQL.Terminals.SQL_PunctuationChoice(";", "//");
		public SQL_PunctuationChoice semicolon = new SQL_PunctuationChoice(";", "//");

		public static class SQL_OrReplaceProcedure extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.SQL.Terminals.SQL_Keyword OR = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("OR");
			public SQL_Keyword OR = new SQL_Keyword("OR");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Terminals.SQL_Keyword REPLACE = new com.eagle.programmar.SQL.Terminals.SQL_Keyword("REPLACE");
			public SQL_Keyword REPLACE = new SQL_Keyword("REPLACE");
		}

		public static class SQL_ProcedureParameter extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT SQL_KeywordChoice OUT = new com.eagle.programmar.SQL.Terminals.SQL_KeywordChoice("IN", "OUT");
			public @OPT SQL_KeywordChoice OUT = new SQL_KeywordChoice("IN", "OUT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.SQL.Symbols.SQL_Parameter_Definition param;
			public SQL_Parameter_Definition param;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.SQL.SQL_Type type;
			public SQL_Type type;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.SQL.SQL_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, SQL_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

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

		public void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			string newName = procName.getValue();
			generator.addMethod(null, newName, this);
			generator.setMethodName(newName);
			if (VERBOSE)
			{
				Console.WriteLine("** Found SQL procedure " + newName);
			}

			if (@params != null && @params.isPresent())
			{
				int nParams = @params.getPrimaryCount();
				for (int i = 0; i < nParams; i++)
				{
					SQL_ProcedureParameter param = @params.getPrimaryElement(i);
					AbstractType paramType = SQL_Type.findAbstractType(generator, param.type);
					generator.addMethodParameter(paramType, param.param.getValue());
				}
			}

			findGlobalVariables(transformer, generator);

			foreach (SQL_StatementOrComment stmtComm in statements._elements)
			{
				AbstractToken which = stmtComm.getWhich();
				if (which is SQL_Statement)
				{
					SQL_Statement stmt = (SQL_Statement) which;
					List<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt);
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
			}

			generator.doneMethod();
		}

		private void findGlobalVariables(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// Are there any 'global' variables we need to declare?
			ICollection<string> externals = this.Scope.allExternalReferences();
			if (externals != null && externals.Count > 0)
			{
				foreach (string varName in externals)
				{
					if (!generator.isKnownMethod(varName))
					{
						AbstractStatement newStmt = generator.newGlobalVariable(varName, null);
						generator.addStatement(newStmt, null);
					}
				}
			}
		}
	}
}
