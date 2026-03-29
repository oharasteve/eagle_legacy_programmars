// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

namespace com.eagle.programmar.PLI
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleArray = com.eagle.math.EagleArray;
	using EagleValue = com.eagle.math.EagleValue;
	using PLI_Identifier_Reference = com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
	using PLI_Variable_Definition = com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
	using PLI_Comment = com.eagle.programmar.PLI.Terminals.PLI_Comment;
	using PLI_Keyword = com.eagle.programmar.PLI.Terminals.PLI_Keyword;
	using PLI_KeywordChoice = com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
	using PLI_Level = com.eagle.programmar.PLI.Terminals.PLI_Level;
	using PLI_Number = com.eagle.programmar.PLI.Terminals.PLI_Number;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PunctuationStar = com.eagle.tokens.punctuation.PunctuationStar;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;
	using SubscriptEnum = com.eagle.transform.EagleGenerator.SubscriptEnum;
	using EagleTransformableStatement = com.eagle.transform.EagleTransformableStatement;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PLI_Declaration : TokenSequence, EagleRunnable, EagleTransformableStatement
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<com.eagle.programmar.PLI.Terminals.PLI_Comment> commentList;
		public  OPT;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice DECLARE = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("DCL", "DECLARE");
		public PLI_KeywordChoice DECLARE = new PLI_KeywordChoice("DCL", "DECLARE");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<PLI_Declare_Item, com.eagle.tokens.punctuation.PunctuationComma> items;
		public SeparatedList<PLI_Declare_Item, PunctuationComma> items;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public PunctuationSemicolon semicolon;

		public class PLI_Declare_Item : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT PLI_Level level;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PLI_Declare_Variables declareVariables;
			public PLI_Declare_Variables declareVariables;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_Declare_Size declareSize;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_Type type1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PLI_KeywordChoice options = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("BUILTIN", "CONTROLLED", "EXTERNAL", "NONASSIGNABLE", "OPTIONAL");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_Declare_Character character1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PLI_Keyword STATIC = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("STATIC");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT PLI_Type type2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT PLI_Declare_Initial initial;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT PLI_Declare_Character character2;
			public  OPT;
		}

		public class PLI_Declare_Variables : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Variable_Definition XXvarDecl;
			public PLI_Variable_Definition XXvarDecl;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_Identifier_List extends com.eagle.tokens.TokenSequence
			public class PLI_Identifier_List : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition varDecl;
				public PLI_Variable_Definition varDecl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_Declare_Size size;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_Type type;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PLI_Keyword STATIC = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("STATIC");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_Declare_Initial initial;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT TokenList<PLI_More_Identifier_List> moreIdentifiers;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;

				public class PLI_More_Identifier_List : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
					public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition varDecl;
					public PLI_Variable_Definition varDecl;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_Declare_Size size;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_Type type;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT PLI_Keyword STATIC = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("STATIC");
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PLI_Declare_Initial initial;
					public  OPT;
				}
			}
		}

		public class PLI_Declare_Initial : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice INITIAL = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice("INITIAL", "INIT");
			public PLI_KeywordChoice INITIAL = new PLI_KeywordChoice("INITIAL", "INIT");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<PLI_Expression, com.eagle.tokens.punctuation.PunctuationComma> exprs;
			public SeparatedList<PLI_Expression, PunctuationComma> exprs;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

		public class PLI_Declare_Size : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<PLI_Declare_Size_OneDimension, com.eagle.tokens.punctuation.PunctuationComma> dims;
			public SeparatedList<PLI_Declare_Size_OneDimension, PunctuationComma> dims;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;

			public class PLI_Declare_Size_OneDimension : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_ParenStar extends com.eagle.tokens.TokenSequence
				public class PLI_ParenStar : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationStar star;
					public PunctuationStar star;
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_Declare_Array extends com.eagle.tokens.TokenSequence
				public class PLI_Declare_Array : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) PLI_Expression exprFrom;
					public PLI_Expression exprFrom;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationColon colon;
					public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) PLI_Expression exprTo;
					public PLI_Expression exprTo;
				}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class PLI_Declare_Bounds_Array extends com.eagle.tokens.TokenSequence
				public class PLI_Declare_Bounds_Array : TokenSequence
				{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_Keyword LBOUND = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("LBOUND");
					public PLI_Keyword LBOUND = new PLI_Keyword("LBOUND");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen1;
					public PunctuationLeftParen leftParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference var1;
					public PLI_Identifier_Reference var1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PLI_Declare_Array_Dim dim1;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.tokens.punctuation.PunctuationRightParen rightParen1;
					public PunctuationRightParen rightParen1;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationColon colon;
					public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) com.eagle.programmar.PLI.Terminals.PLI_Keyword HBOUND = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("HBOUND");
					public PLI_Keyword HBOUND = new PLI_Keyword("HBOUND");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen2;
					public PunctuationLeftParen leftParen2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference var2;
					public PLI_Identifier_Reference var2;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT PLI_Declare_Array_Dim dim2;
					public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) com.eagle.tokens.punctuation.PunctuationRightParen rightParen2;
					public PunctuationRightParen rightParen2;

					public class PLI_Declare_Array_Dim : TokenSequence
					{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
						public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.PLI.Terminals.PLI_Number num;
						public PLI_Number num;
					}
				}
			}
		}

		public class PLI_Declare_Character : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice CHARACTER = new com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice(new String[] { "CHAR", "CHARACTER" });
			public PLI_KeywordChoice CHARACTER = new PLI_KeywordChoice(new string[] {"CHAR", "CHARACTER"});
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT PLI_Declare_Character_Size size;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PLI_Keyword VARYING = new com.eagle.programmar.PLI.Terminals.PLI_Keyword("VARYING");
			public  OPT;

			public class PLI_Declare_Character_Size : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
				public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) PLI_Expression expr;
				public PLI_Expression expr;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
				public PunctuationRightParen rightParen;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			for (int i = 0; i < items.getPrimaryCount(); i++)
			{
				PLI_Declare_Item item = items.getPrimaryElement(i);
				if (item.initial != null && item.initial.isPresent())
				{
					AbstractToken token = item.declareVariables.getWhich();
					if (token is PLI_Variable_Definition)
					{
						PLI_Variable_Definition id = (PLI_Variable_Definition) token;
						if (item.initial.exprs.getPrimaryCount() > 1)
						{
							EagleArray array = new EagleArray();
							for (int j = 0; j < item.initial.exprs.getPrimaryCount(); j++)
							{
								EagleValue val = interpreter.getEagleValue(item.initial.exprs.getPrimaryElement(j));
								array.addValue(val);
							}
							interpreter.setSymbol(id, id.ToString(), array);
						}
						else
						{
							EagleValue val = interpreter.getEagleValue(item.initial.exprs.first());
							interpreter.setSymbol(id, id.ToString(), val);
						}
					}
				}
			}
		}

		public override AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			for (int i = 0; i < items.getPrimaryCount(); i++)
			{
				PLI_Declare_Item item = items.getPrimaryElement(i);
				AbstractToken token = item.declareVariables.getWhich();
				if (token is PLI_Variable_Definition)
				{
					PLI_Variable_Definition id = (PLI_Variable_Definition) token;
					string varName = id.getValue();
					if (varName.Equals("true") || varName.Equals("false"))
					{
						// Sorry, cannot redefine true or false
						continue;
					}
					AbstractExpression newVal;
					if (item.initial != null && item.initial.isPresent())
					{
						int numValues = item.initial.exprs.getPrimaryCount();
						if (numValues > 1)
						{
							List<AbstractExpression> exprs = new List<AbstractExpression>();
							for (int j = 0; j < numValues; j++)
							{
								exprs.Add(transformer.transformExpression(generator, item.initial.exprs.getPrimaryElement(j)));
							}
							newVal = generator.newArrayExpression(exprs, item.initial);
						}
						else
						{
							newVal = transformer.transformExpression(generator, item.initial.exprs.first());
						}

						AbstractExpression asgExpr = generator.newAssignmentExpression(varName, EagleGenerator.SubscriptEnum.FIRST_IS_ZERO, null, EagleGenerator.AssignmentEnum.EQUALS, newVal, item.initial);
						return generator.newExpressionStatement(asgExpr, item.initial);
					}
				}
			}

			return null;
		}
	}

}
