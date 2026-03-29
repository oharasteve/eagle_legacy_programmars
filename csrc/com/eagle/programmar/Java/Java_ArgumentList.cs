// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

namespace com.eagle.programmar.Java
{

	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class Java_ArgumentList : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) Java_Expression arg;
		public Java_Expression arg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Java_MoreArguments> moreArgs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @CURIOUS("Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
		public  OPT;

		public class Java_MoreArguments : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationComma comma;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Java_Expression arg;
			public Java_Expression arg;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comment2;
			public  OPT;
		}

		public static Java_ArgumentList createArgumentList(List<Java_Expression> args)
		{
			if (args == null || args.Count == 0)
			{
				return null;
			}

			Java_ArgumentList argList = new Java_ArgumentList();

			bool first = true;
			foreach (AbstractExpression arg0 in args)
			{
				Java_Expression arg = (Java_Expression) arg0;

				if (first)
				{
					first = false;
					argList.arg = arg;
				}
				else
				{
					Java_MoreArguments more = new Java_MoreArguments();
					more.comma = new PunctuationComma();
					more.arg = arg;
					if (argList.moreArgs == null)
					{
						argList.moreArgs = new TokenList<Java_MoreArguments>();
					}
					argList.moreArgs.addToken(more);
				}
			}
			return argList;
		}
	}

}
