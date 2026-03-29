// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2024

namespace com.eagle.programmar.Python.Expressions
{

	using Python_Expression = com.eagle.programmar.Python.Python_Expression;
	using Python_Generator = com.eagle.programmar.Python.Python_Generator;
	using Python_List = com.eagle.programmar.Python.Python_List;
	using Python_MoreListItem = com.eagle.programmar.Python.Python_List.Python_MoreListItem;
	using Python_Multiline_Syntax = com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
	using Python_Comment = com.eagle.programmar.Python.Terminals.Python_Comment;
	using Python_EndOfLine = com.eagle.programmar.Python.Terminals.Python_EndOfLine;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using PrimaryOperator = com.eagle.tokens.PrimaryOperator;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;

	public class Python_Brackets : PrimaryOperator
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
		public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_EndOfLine eoln1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.Python.Terminals.Python_Comment> comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Python_EndOfLine eoln2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT @NOSPACE @SYNTAX(com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax.class) com.eagle.programmar.Python.Python_List list;
		public @OPT @SYNTAX(typeof(Python_Multiline_Syntax)) Python_List list;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @NOSPACE PunctuationRightBracket rightBracket;
		public @NOSPACE PunctuationRightBracket rightBracket;

		public static Python_Expression generateArray(List<AbstractExpression> exprs, AbstractToken source)
		{
			Python_Brackets brack = new Python_Brackets();
			brack.leftBracket = new PunctuationLeftBracket();
			brack.rightBracket = new PunctuationRightBracket();
			brack.list = new Python_List();
			brack.list.setPresent(true);

			for (int i = 0; i < exprs.size(); i++)
			{
				if (i == 0)
				{
					brack.list.expr = (Python_Expression) exprs.get(0);
				}
				else
				{
					if (brack.list.moreItems == null)
					{
						brack.list.moreItems = new TokenList<Python_List.Python_MoreListItem>();
						brack.list.moreItems.setPresent(true);
					}
					Python_List.Python_MoreListItem more = new Python_List.Python_MoreListItem();
					more.comma = new PunctuationComma();
					more.expr = (Python_Expression) exprs.get(i);
					brack.list.moreItems.addToken(more);
				}
			}

			brack.setTransformationSource(source);
			return Python_Generator.wrapExpression(brack);
		}
	}

}
