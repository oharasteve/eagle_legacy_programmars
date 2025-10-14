// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jun 13, 2011

package com.eagle.programmar.PLI;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleArray;
import com.eagle.math.EagleValue;
import com.eagle.programmar.PLI.Symbols.PLI_Identifier_Reference;
import com.eagle.programmar.PLI.Symbols.PLI_Variable_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_KeywordChoice;
import com.eagle.programmar.PLI.Terminals.PLI_Level;
import com.eagle.programmar.PLI.Terminals.PLI_Number;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.tokens.punctuation.PunctuationStar;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.AssignmentEnum;
import com.eagle.transform.EagleGenerator.SubscriptEnum;
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class PLI_Declaration extends TokenSequence
		implements EagleRunnable, EagleTransformableStatement
{
	public @S(10) @OPT TokenList<PLI_Comment> commentList;

	public @S(20) PLI_KeywordChoice DECLARE = new PLI_KeywordChoice("DCL", "DECLARE");
	public @S(30) SeparatedList<PLI_Declare_Item, PunctuationComma> items;
	public @S(40) PunctuationSemicolon semicolon;

	public static class PLI_Declare_Item extends TokenSequence
	{
		public @S(10) @OPT PLI_Level level;
		public @S(20) PLI_Declare_Variables declareVariables;
		public @S(30) @OPT PLI_Declare_Size declareSize;
		public @S(40) @OPT PLI_Type type1;
		public @S(50) @OPT PLI_KeywordChoice options =
				new PLI_KeywordChoice("BUILTIN", "CONTROLLED", "EXTERNAL", "NONASSIGNABLE", "OPTIONAL");
		public @S(60) @OPT PLI_Declare_Character character1;
		public @S(70) @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
		public @S(80) @OPT PLI_Type type2;
		public @S(90) @OPT PLI_Declare_Initial initial;
		public @S(100) @OPT PLI_Declare_Character character2;
	}

	public static class PLI_Declare_Variables extends TokenChooser
	{
		public @CHOICE PLI_Variable_Definition XXvarDecl;

		public @CHOICE static class PLI_Identifier_List extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) PLI_Variable_Definition varDecl;
			public @S(30) @OPT PLI_Declare_Size size;
			public @S(40) @OPT PLI_Type type;
			public @S(50) @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
			public @S(60) @OPT PLI_Declare_Initial initial;
			public @S(70) @OPT TokenList<PLI_More_Identifier_List> moreIdentifiers;
			public @S(80) PunctuationRightParen rightParen;

			public static class PLI_More_Identifier_List extends TokenSequence
			{
				public @S(10) PunctuationComma comma;
				public @S(20) PLI_Variable_Definition varDecl;
				public @S(30) @OPT PLI_Declare_Size size;
				public @S(40) @OPT PLI_Type type;
				public @S(50) @OPT PLI_Keyword STATIC = new PLI_Keyword("STATIC");
				public @S(60) @OPT PLI_Declare_Initial initial;
			}
		}
	}

	public static class PLI_Declare_Initial extends TokenSequence
	{
		public @S(10) PLI_KeywordChoice INITIAL = new PLI_KeywordChoice("INITIAL", "INIT");
		public @S(20) PunctuationLeftParen leftParen;
		public @S(30) SeparatedList<PLI_Expression, PunctuationComma> exprs;
		public @S(40) PunctuationRightParen rightParen;
	}

	public static class PLI_Declare_Size extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) SeparatedList<PLI_Declare_Size_OneDimension, PunctuationComma> dims;
		public @S(30) PunctuationRightParen rightParen;

		public static class PLI_Declare_Size_OneDimension extends TokenChooser
		{
			public @CHOICE static class PLI_ParenStar extends TokenSequence
			{
				public @S(10) PunctuationStar star;
			}

			public @CHOICE static class PLI_Declare_Array extends TokenSequence
			{
				public @S(10) PLI_Expression exprFrom;
				public @S(20) PunctuationColon colon;
				public @S(30) PLI_Expression exprTo;
			}

			public @CHOICE static class PLI_Declare_Bounds_Array extends TokenSequence
			{
				public @S(10) PLI_Keyword LBOUND = new PLI_Keyword("LBOUND");
				public @S(20) PunctuationLeftParen leftParen1;
				public @S(30) PLI_Identifier_Reference var1;
				public @S(40) @OPT PLI_Declare_Array_Dim dim1;
				public @S(50) PunctuationRightParen rightParen1;
				public @S(60) PunctuationColon colon;
				public @S(70) PLI_Keyword HBOUND = new PLI_Keyword("HBOUND");
				public @S(80) PunctuationLeftParen leftParen2;
				public @S(90) PLI_Identifier_Reference var2;
				public @S(100) @OPT PLI_Declare_Array_Dim dim2;
				public @S(110) PunctuationRightParen rightParen2;

				public static class PLI_Declare_Array_Dim extends TokenSequence
				{
					public @S(10) PunctuationComma comma;
					public @S(20) PLI_Number num;
				}
			}
		}
	}

	public static class PLI_Declare_Character extends TokenSequence
	{
		public @S(10) PLI_KeywordChoice CHARACTER = new PLI_KeywordChoice(new String[] {
				"CHAR", "CHARACTER"
		});
		public @S(20) @OPT PLI_Declare_Character_Size size;
		public @S(30) @OPT PLI_Keyword VARYING = new PLI_Keyword("VARYING");

		public static class PLI_Declare_Character_Size extends TokenSequence
		{
			public @S(10) PunctuationLeftParen leftParen;
			public @S(20) PLI_Expression expr;
			public @S(30) PunctuationRightParen rightParen;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (int i = 0; i < items.getPrimaryCount(); i++)
		{
			PLI_Declare_Item item = items.getPrimaryElement(i);
			if (item.initial != null && item.initial.isPresent())
			{
				AbstractToken token = item.declareVariables.getWhich();
				if (token instanceof PLI_Variable_Definition)
				{
					PLI_Variable_Definition id = (PLI_Variable_Definition) token;
					if (item.initial.exprs.getPrimaryCount() > 1)
					{
						EagleArray array =  new EagleArray();
						for (int j = 0; j < item.initial.exprs.getPrimaryCount(); j++)
						{
							EagleValue val = interpreter.getEagleValue(item.initial.exprs.getPrimaryElement(j));
							array.addValue(val);
						}
						interpreter.setSymbol(id, id.toString(), array);
					}
					else
					{
						EagleValue val = interpreter.getEagleValue(item.initial.exprs.first());
						interpreter.setSymbol(id, id.toString(), val);
					}
				}
			}
		}
	}
	
	@Override
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		for (int i = 0; i < items.getPrimaryCount(); i++)
		{
			PLI_Declare_Item item = items.getPrimaryElement(i);
			AbstractToken token = item.declareVariables.getWhich();
			if (token instanceof PLI_Variable_Definition)
			{
				PLI_Variable_Definition id = (PLI_Variable_Definition) token;
				String varName = id.getValue();
				if (varName.equals("true") || varName.equals("false"))
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
						ArrayList<AbstractExpression> exprs = new ArrayList<AbstractExpression>();
						for (int j = 0; j < numValues; j++)
						{
							exprs.add(transformer.transformExpression(generator,
									item.initial.exprs.getPrimaryElement(i)));
						}
						newVal = generator.newArrayExpression(exprs, item.initial);
					}
					else
					{
						newVal = transformer.transformExpression(generator,
								item.initial.exprs.first());
					}

					AbstractExpression asgExpr = generator.newAssignmentExpression(varName,
							SubscriptEnum.FIRST_IS_ZERO, null, AssignmentEnum.EQUALS, newVal, item.initial);
					return generator.newExpressionStatement(asgExpr, item.initial);
				}
			}
		}
		
		return null;
	}
}
