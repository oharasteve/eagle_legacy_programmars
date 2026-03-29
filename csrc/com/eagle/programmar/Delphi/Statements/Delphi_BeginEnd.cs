// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

namespace com.eagle.programmar.Delphi.Statements
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnableWithResult = com.eagle.interpret.EagleRunnableWithResult;
	using Delphi_Statement_List = com.eagle.programmar.Delphi.Delphi_Statement_List;
	using Delphi_NextStatement = com.eagle.programmar.Delphi.Delphi_Statement_List.Delphi_NextStatement;
	using Delphi_Comment = com.eagle.programmar.Delphi.Terminals.Delphi_Comment;
	using Delphi_Keyword = com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Delphi_BeginEnd : TokenSequence, EagleRunnableWithResult, AbstractStatement, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @DOC("Programs_and_Units_(Delphi)#The_Block") com.eagle.programmar.Delphi.Terminals.Delphi_Keyword BEGIN = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("Begin");
		public @DOC("Programs_and_Units_(Delphi)#The_Block") Delphi_Keyword BEGIN = new Delphi_Keyword("Begin");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Delphi.Terminals.Delphi_Comment> comments;
		public @OPT TokenList<Delphi_Comment> comments;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Delphi_Statement_List statements;
		public @OPT Delphi_Statement_List statements;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Delphi.Terminals.Delphi_Keyword END = new com.eagle.programmar.Delphi.Terminals.Delphi_Keyword("End");
		public Delphi_Keyword END = new Delphi_Keyword("End");

		public Eagle_Statement_Result interpretStatement(EagleInterpreter interpreter)
		{
			Eagle_Statement_Result result = interpreter.tryToInterpret(statements.stmt);
			if (result != Eagle_Statement_Result.NORMAL)
			{
				return result;
			}
			if (statements.stmts != null)
			{
				foreach (Delphi_Statement_List.Delphi_NextStatement more in statements.stmts._elements)
				{
					result = interpreter.tryToInterpret(more.stmt);
					if (result != Eagle_Statement_Result.NORMAL)
					{
						return result;
					}
				}
			}
			return result;
		}

		public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> stmts = new List<AbstractStatement>();
			AbstractStatement newStmt = transformer.transformStatement1(generator, this.statements.stmt.getWhich());
			if (newStmt != null)
			{
				stmts.Add(newStmt);
			}

			if (this.statements.stmts != null)
			{
				foreach (Delphi_Statement_List.Delphi_NextStatement more in this.statements.stmts._elements)
				{
					newStmt = transformer.transformStatement1(generator, more.stmt.getWhich());
					if (newStmt != null)
					{
						stmts.Add(newStmt);
					}
				}
			}

			return generator.newBlockStatement(stmts, this);
		}
	}

}
