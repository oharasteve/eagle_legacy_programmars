// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 21, 2010

namespace com.eagle.programmar.Java.Statements
{
	using Java_Annotation = com.eagle.programmar.Java.Java_Annotation;
	using Java_Expression = com.eagle.programmar.Java.Java_Expression;
	using Java_Label = com.eagle.programmar.Java.Java_Label;
	using Java_Statement = com.eagle.programmar.Java.Java_Statement;
	using Java_Syntax = com.eagle.programmar.Java.Java_Syntax;
	using Java_Type = com.eagle.programmar.Java.Java_Type;
	using Java_Variable = com.eagle.programmar.Java.Java_Variable;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Java_ForEachStatement : TokenSequence, AbstractStatement, EagleScope.EagleScopeInterface
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE Java_Label label;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_Keyword FOR = new com.eagle.programmar.Java.Terminals.Java_Keyword("for");
		public Java_Keyword FOR = new Java_Keyword("for");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
		public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE Java_Annotation annotation;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Java_Keyword FINAL = new com.eagle.programmar.Java.Terminals.Java_Keyword("final");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.programmar.Java.Java_Type varType;
		public Java_Type varType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.Java.Java_Variable forVar;
		public Java_Variable forVar;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationColon colon;
		public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.Java.Java_Expression collection;
		public Java_Expression collection;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
		public PunctuationRightParen rightParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT Java_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) com.eagle.programmar.Java.Java_Statement action;
		public Java_Statement action;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, com.eagle.programmar.Java.Java_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, Java_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}
	}

}
