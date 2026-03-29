// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 31, 2010

namespace com.eagle.programmar.Java.Statements
{

	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Generator = com.eagle.programmar.Java.Java_Generator;
	using Java_Label = com.eagle.programmar.Java.Java_Label;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_StatementOrComment = com.eagle.programmar.Java.Java_StatementOrComment;
	using Java_Syntax = com.eagle.programmar.Java.Java_Syntax;
	using Java_LogicalNotExpression = com.eagle.programmar.Java.Expressions.Java_LogicalNotExpression;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;

	public class Java_DoWhileStatement : TokenSequence, AbstractStatement, EagleScope.EagleScopeInterface
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE Java_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @DOC("statements.html#14.13") com.eagle.programmar.Java.Terminals.Java_Keyword DO = new com.eagle.programmar.Java.Terminals.Java_Keyword("do");
		public @DOC("statements.html#14.13") Java_Keyword DO = new Java_Keyword("do");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_Comment comment;
		public @OPT Java_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Java.Java_Statement doStatement;
		public Java_Statement doStatement;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Java.Terminals.Java_Keyword WHILE = new com.eagle.programmar.Java.Terminals.Java_Keyword("while");
		public Java_Keyword WHILE = new Java_Keyword("while");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @NOSPACE Java_Expression condition;
		public @NOSPACE Java_Expression condition;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @NOSPACE PunctuationRightParen rightParen;
		public @NOSPACE PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @NOSPACE PunctuationSemicolon semicolon;
		public @NOSPACE PunctuationSemicolon semicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Java.Java_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

		public EagleScope Scope
		{
			return _scope;
		}

		public static Java_Statement generateDoUntilOne(Java_Expression cond, Java_Statement action, AbstractToken source)
		{
			Java_DoWhileStatement doStmt = new Java_DoWhileStatement();
			doStmt.leftParen = new PunctuationLeftParen();
			doStmt.rightParen = new PunctuationRightParen();
			doStmt.semicolon = new PunctuationSemicolon();

			Java_StatementBlock body = new Java_StatementBlock();
			body.statements = new TokenList<Java_StatementOrComment>();
			body.leftBrace = new PunctuationLeftBrace();
			body.rightBrace = new PunctuationRightBrace();

			doStmt.doStatement = action;

			doStmt.condition = Java_LogicalNotExpression.generateLogicalNot(cond, source);

			doStmt.setTransformationSource(source);
			return Java_Generator.wrapStatement(doStmt);
		}

		public static Java_Statement generateDoUntilMany(Java_Expression cond, List<Java_Statement> actions, AbstractToken source)
		{
			Java_Statement body = Java_StatementBlock.generateBlock(actions, source);
			return generateDoUntilOne(cond, body, source);
		}
	}

}
