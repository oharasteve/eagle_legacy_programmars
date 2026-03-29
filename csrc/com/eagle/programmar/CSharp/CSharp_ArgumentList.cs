// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

namespace com.eagle.programmar.CSharp
{

	using CSharp_ArgumentOut = com.eagle.programmar.CSharp.CSharp_Argument.CSharp_ArgumentOut;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;

	public class CSharp_ArgumentList : TokenSequence
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CSharp_Argument arg;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CSharp_MoreArguments> moreArgs;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @CURIOUS("Extra comma") com.eagle.tokens.punctuation.PunctuationComma comma;
		public  OPT;

		public class CSharp_MoreArguments : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationComma comma;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_Argument arg;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments;
			public  OPT;
		}

		public static CSharp_ArgumentList createArgumentList(List<CSharp_Expression> args)
		{
			if (args == null || args.Count == 0)
			{
				return null;
			}

			CSharp_ArgumentList argList = new CSharp_ArgumentList();
			bool first = true;
			foreach (CSharp_Expression arg0 in args)
			{
				CSharp_Argument arg = new CSharp_Argument();
				CSharp_ArgumentOut @out = new CSharp_ArgumentOut();
				@out.arg = arg0;
				arg.setWhich(@out);

				if (first)
				{
					first = false;
					argList.arg = arg;
					argList.arg.setPresent(true);
				}
				else
				{
					CSharp_MoreArguments more = new CSharp_MoreArguments();
					more.comma = new PunctuationComma();
					more.arg = arg;
					more.arg.setPresent(true);
					if (argList.moreArgs == null)
					{
						argList.moreArgs = new TokenList<CSharp_MoreArguments>();
						argList.moreArgs.setPresent(true);
					}
					argList.moreArgs.addToken(more);
				}
			}
			return argList;
		}
	}

}
