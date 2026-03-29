// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2013

namespace com.eagle.programmar.Python.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Python_ComplexStatement = com.eagle.programmar.Python.Python_ComplexStatement;
	using Python_Statement = com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement;
	using Python_StatementOrComment = com.eagle.programmar.Python.Python_ComplexStatement.Python_StatementOrComment;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using Python_Punctuation = com.eagle.programmar.Python.Terminals.Python_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Python_StatementBlock : TokenChooser, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Punctuation XXdots = new com.eagle.programmar.Python.Terminals.Python_Punctuation("...");
		public Python_Punctuation XXdots = new Python_Punctuation("...");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_SameLineStatement extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnableWithResult, com.eagle.transform.EagleTransformableStatementList
		public class Python_SameLineStatement : TokenSequence, EagleRunnableWithResult, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<com.eagle.programmar.Python.Python_ComplexStatement.Python_Statement, com.eagle.tokens.punctuation.PunctuationSemicolon> statements;
			public SeparatedList<Python_ComplexStatement.Python_Statement, PunctuationSemicolon> statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_Comment comment;
			public  OPT;

			public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
			{
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				for (int i = 0; i < statements.getPrimaryCount(); i++)
				{
					Python_ComplexStatement.Python_Statement stmt = statements.getPrimaryElement(i);
					result = interpreter.tryToInterpret(stmt.getWhich());
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
				return result;
			}

			public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				List<AbstractStatement> result = new List<AbstractStatement>();
				int numStmts2 = statements.getPrimaryCount();
				for (int i = 0; i < numStmts2; i++)
				{
					Python_ComplexStatement.Python_Statement stmt3 = statements.getPrimaryElement(i);
					List<AbstractStatement> newStmts3 = transformer.transformStatement(generator, stmt3);
					if (newStmts3 != null)
					{
						foreach (AbstractStatement newStmt3 in newStmts3)
						{
							result.Add(newStmt3);
						}
					}
				}
				return result;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_MultilineStatement extends com.eagle.tokens.TokenSequence implements com.eagle.interpret.EagleRunnableWithResult, com.eagle.transform.EagleTransformableStatementList
		public class Python_MultilineStatement : TokenSequence, EagleRunnableWithResult, EagleTransformableStatementList
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_Comment comment;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_EndOfLine eoln;
			public Python_EndOfLine eoln;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.TokenList<com.eagle.programmar.Python.Python_ComplexStatement> statements;
			public TokenList<Python_ComplexStatement> statements;

			public override Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
			{
				Eagle_Statement_Result result = Eagle_Statement_Result.NORMAL;
				foreach (Python_ComplexStatement stmt in statements._elements)
				{
					result = interpreter.tryToInterpret(stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						break;
					}
				}
				return result;
			}

			public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
			{
				List<AbstractStatement> result = new List<AbstractStatement>();
				foreach (Python_ComplexStatement stmt in statements._elements)
				{
					AbstractToken which = stmt.statementOrComment.getWhich();
					if (which is Python_SameLineStatement)
					{
						Python_SameLineStatement same = (Python_SameLineStatement) which;
						List<AbstractStatement> stmts = same.transformStatement(transformer, generator);
						foreach (AbstractStatement newStmt in stmts)
						{
							result.Add(newStmt);
						}
					}
				}
				return result;
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractToken which = getWhich();
			if (which is Python_SameLineStatement)
			{
				Python_SameLineStatement same = (Python_SameLineStatement) which;
				return same.transformStatement(transformer, generator);
			}
			if (which is Python_MultilineStatement)
			{
				Python_MultilineStatement multi = (Python_MultilineStatement) which;
				return multi.transformStatement(transformer, generator);
			}

			throw new Exception("Unable to handle " + which);
		}

		public virtual Python_ComplexStatement addStatements<T1>(List<T1> statements) where T1 : com.eagle.tokens.interfaces.AbstractStatement
		{
			Python_MultilineStatement multi = new Python_MultilineStatement();
			multi.statements = new TokenList<Python_ComplexStatement>();
			foreach (AbstractStatement stmt in statements)
			{
				multi.statements.addToken((Python_ComplexStatement) stmt);
			}
			this.setWhich(multi);
			Python_ComplexStatement wrapper = new Python_ComplexStatement();
			wrapper.statementOrComment = new Python_ComplexStatement.Python_StatementOrComment();
			wrapper.statementOrComment.setWhich(multi);
			return wrapper;
		}
	}

}
