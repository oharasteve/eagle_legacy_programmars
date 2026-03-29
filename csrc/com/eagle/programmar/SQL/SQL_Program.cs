// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

namespace com.eagle.programmar.SQL
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using SQL_CreateFunctionStatement = com.eagle.programmar.SQL.Statements.SQL_CreateFunctionStatement;
	using SQL_CreateProcedureStatement = com.eagle.programmar.SQL.Statements.SQL_CreateProcedureStatement;
	using SQL_Comment = com.eagle.programmar.SQL.Terminals.SQL_Comment;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class SQL_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string SQL = "SQL";

		public SQL_Program() : base(SQL, new SQL_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://www.w3schools.com/sql/";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<SQL_StatementOrComment> statements;
		public TokenList<SQL_StatementOrComment> statements;

		public class SQL_StatementOrComment : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_Statement XXstatement;
			public SQL_Statement XXstatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE SQL_Comment XXcomment;
			public SQL_Comment XXcomment;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the CREATE PROCEDURE calls
			foreach (SQL_StatementOrComment elt in statements._elements)
			{
				if (elt.getWhich() is SQL_Statement)
				{
					SQL_Statement stmt = (SQL_Statement) elt.getWhich();
					if (stmt.getWhich() is SQL_CreateProcedureStatement)
					{
						SQL_CreateProcedureStatement proc = (SQL_CreateProcedureStatement) stmt.getWhich();
						interpreter.addFunction(proc.procName.getValue(), proc);
					}
					else if (stmt.getWhich() is SQL_CreateFunctionStatement)
					{
						SQL_CreateFunctionStatement func = (SQL_CreateFunctionStatement) stmt.getWhich();
						interpreter.addFunction(func.funcName.getValue(), func);
					}
				}
			}

			// Second pass, run any stuff in the top-level program
			foreach (SQL_StatementOrComment elt in statements._elements)
			{
				if (elt.getWhich() is SQL_Statement)
				{
					SQL_Statement stmt = (SQL_Statement) elt.getWhich();
					interpreter.tryToInterpret(stmt);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the procedure definitions
			foreach (SQL_StatementOrComment elt in statements._elements)
			{
				if (elt.getWhich() is SQL_Statement)
				{
					SQL_Statement stmt = (SQL_Statement) elt.getWhich();
					if (stmt.getWhich() is EagleTransformableFunction)
					{
						EagleTransformableFunction transformable = (EagleTransformableFunction) stmt.getWhich();
						transformable.transformFunction(transformer, generator);
					}
					else if (elt.getWhich() is SQL_Comment)
					{
						SQL_Comment comm = (SQL_Comment) elt.getWhich();
						generator.addComment(comm.getValue(), comm);
					}
				}
			}

			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				// System.err.println("****** Found var " + met._symbolName);
				SQL_Type type = SQL_Type.newPrimitiveType("VARCHAR");
				AbstractType abstrType = SQL_Type.findAbstractType(generator, type);
				AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, null, this);
				generator.addStatement(dataStmt, this);
			}

			// Second pass, transform all the data and logic
			foreach (SQL_StatementOrComment elt in statements._elements)
			{
				if (elt.getWhich() is SQL_Statement)
				{
					SQL_Statement stmt = (SQL_Statement) elt.getWhich();
					ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, stmt.getWhich());
					if (newStmts != null)
					{
						foreach (AbstractStatement newStmt in newStmts)
						{
							generator.addStatement(newStmt, stmt);
						}
					}
				}
			}

	//		// Not needed for C# or Java, but Python needs this
	//		generator.addCallToMain();

			return generator.getTransfomedProgram();
		}
	}

}
