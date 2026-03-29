// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

namespace com.eagle.programmar.CSharp.Statements
{

	using CSharp_Expression = com.eagle.programmar.CSharp.CSharp_Expression;
	using CSharp_Generator = com.eagle.programmar.CSharp.CSharp_Generator;
	using CSharp_Statement = com.eagle.programmar.CSharp.CSharp_Statement;
	using CSharp_LogicalNotExpression = com.eagle.programmar.CSharp.Expressions.CSharp_LogicalNotExpression;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class CSharp_DoWhileStatement : TokenSequence, AbstractStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE @DOC("statements/iteration-statements") com.eagle.programmar.CSharp.Terminals.CSharp_Keyword DO = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("do");
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.CSharp_Statement doStatement;
		public CSharp_Statement doStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword WHILE = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("while");
		public CSharp_Keyword WHILE = new CSharp_Keyword("while");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSharp.CSharp_Expression condition;
		public CSharp_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE PunctuationSemicolon semicolon;
		public  NOSPACE;

		public static CSharp_Statement generateDoUntilOne(CSharp_Expression cond, CSharp_Statement action, AbstractToken source)
		{
			CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
			doStmt.leftParen = new PunctuationLeftParen();
			doStmt.rightParen = new PunctuationRightParen();
			doStmt.semicolon = new PunctuationSemicolon();

			doStmt.doStatement = action;
			doStmt.condition = CSharp_LogicalNotExpression.generateLogicalNot(cond, source);

			doStmt.setTransformationSource(source);
			return CSharp_Generator.wrapStatement(doStmt);
		}

		public static CSharp_Statement generateDoUntilMany(CSharp_Expression cond, List<CSharp_Statement> actions, AbstractToken source)
		{
			CSharp_Statement body = CSharp_StatementBlock.generateBlock(actions, source);
			return generateDoUntilOne(cond, body, source);
		}
	}

}
