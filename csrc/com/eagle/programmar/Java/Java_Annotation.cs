// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

namespace com.eagle.programmar.Java
{
	using Java_VariableIdentifier = com.eagle.programmar.Java.Java_Variable.Java_VariableIdentifier;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Identifier = com.eagle.programmar.Java.Terminals.Java_Identifier;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using Java_Punctuation = com.eagle.programmar.Java.Terminals.Java_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;

	public class Java_Annotation : TokenChooser
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Java_AnnotationCallParens extends com.eagle.tokens.TokenSequence
		public class Java_AnnotationCallParens : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation atSign = new com.eagle.programmar.Java.Terminals.Java_Punctuation('@');
			public Java_Punctuation atSign = new Java_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Java_Variable var;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Java_DotVar> moreIds;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @NOSPACE PunctuationLeftParen leftParen;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT @NOSPACE SeparatedList<Java_Expression, com.eagle.tokens.punctuation.PunctuationComma> expressions;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightParen rightParen;
			public  NOSPACE;

			public class Java_DotVar : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationPeriod dot;
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Java_VariableIdentifier nextId;
				public  NOSPACE;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Java_AnnotationCallList extends com.eagle.tokens.TokenSequence
		public class Java_AnnotationCallList : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation atSign = new com.eagle.programmar.Java.Terminals.Java_Punctuation('@');
			public Java_Punctuation atSign = new Java_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE SeparatedList<com.eagle.programmar.Java.Terminals.Java_Identifier, com.eagle.tokens.punctuation.PunctuationPeriod> idList;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments;
			public  OPT;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Java_AnnotationList extends com.eagle.tokens.TokenSequence
		public class Java_AnnotationList : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation atSign = new com.eagle.programmar.Java.Terminals.Java_Punctuation('@');
			public Java_Punctuation atSign = new Java_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Terminals.Java_KeywordChoice ATTR = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("AccessoryAction", "Nullable", "PageTransition");
			public Java_KeywordChoice ATTR = new Java_KeywordChoice("AccessoryAction", "Nullable", "PageTransition");
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST static class Java_AnnotationSimple extends com.eagle.tokens.TokenSequence
		public class Java_AnnotationSimple : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation atSign = new com.eagle.programmar.Java.Terminals.Java_Punctuation('@');
			public Java_Punctuation atSign = new Java_Punctuation('@');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Java_Variable var;
			public  NOSPACE;
		}

		public static void translate(Java_Annotation annotation)
		{
			throw new Exception("Need to implement");
		}
	}

}
