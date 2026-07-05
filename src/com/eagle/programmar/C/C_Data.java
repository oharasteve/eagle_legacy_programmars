// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import java.util.ArrayList;

import com.eagle.generate.EagleGenerator;
import com.eagle.generate.StaticEnum;
import com.eagle.generate.TypeEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.C.C_Function.C_FunctionAsm;
import com.eagle.programmar.C.Symbols.C_Variable_Definition;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Types.C_FunctionPointer;
import com.eagle.programmar.C.Types.C_TypePrimitive.C_TypeStar;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.interfaces.AbstractVariable;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class C_Data extends TokenChooser
{
	public @CHOICE C_FunctionPointer XXfunctionPointer;

	public @CHOICE static class C_RegularData extends TokenSequence
			implements EagleRunnable, AbstractStatement,
					EagleTransformableStatementList
	{
		public @S(10) @OPT TokenList<C_DataModifiers> modifiers1;
		public @S(20) C_Type ctype;
		public @S(30) @OPT TokenList<C_DataModifiers> modifiers2;
		public @S(40) @OPT TokenList<C_Comment> comments1;
		public @S(50) C_Variable_Definition id;
		public @S(60) @OPT TokenList<C_Subscript> subscripts;
		public @S(70) @OPT C_DataInitialValue initialValue;
		public @S(80) @OPT TokenList<C_MoreIdentifiers> moreIds;
		public @S(90) @OPT C_FunctionAttributes attributes;  // I know, it is data here
		public @S(100) @OPT C_FunctionAsm asm;
		public @S(110) PunctuationSemicolon semicolon;
		public @S(120) @OPT TokenList<C_Comment> comments2;

		public static class C_MoreIdentifiers extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) @OPT TokenList<C_TypeStar> stars;
			public @S(30) C_Variable_Definition id;
			public @S(40) @OPT TokenList<C_Subscript> subscripts;
			public @S(50) @OPT C_DataInitialValue initialValue;
		}

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			if (initialValue != null && initialValue.isPresent())
			{
				EagleValue value = interpreter.getEagleValue(initialValue.expression);
				interpreter.setSymbol(id, id.toString(), value);
			}
		}

		@Override
		public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer,
				EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
			TypeEnum argType2 = ctype.findType();

			if (subscripts != null && subscripts.size() == 1)
			{
				if (argType2 == TypeEnum.CHAR)
				{
					// If we have char xx[] then it is a string
					argType2 = TypeEnum.STRING;
				}
				else if (argType2 == TypeEnum.STRING)
				{
					// If we have char *xx[] then it is a string array
					argType2 = TypeEnum.ARRAY;
				}
			}

			AbstractType newType = generator.transformType(argType2, null, this);

			String name = id.getValue();
			AbstractExpression initial = null;
			if (initialValue != null && initialValue.isPresent())
			{
				initial = transformer.transformExpression(generator, initialValue.expression);
			}
			int asgs = transformer._metrics.countAssignments(name, null);
			StaticEnum isConst = StaticEnum.NONE;
			if (asgs == 1) isConst = StaticEnum.CONST;			
			result.add(generator.newDataDeclaration(isConst, name, null, newType, initial, this));

			for (C_MoreIdentifiers more : moreIds._elements)
			{
				name = more.id.getValue();
				initial = null;
				if (more.initialValue != null && more.initialValue.isPresent())
				{
					initial = transformer.transformExpression(generator, more.initialValue.expression);
				}
				asgs = transformer._metrics.countAssignments(name, null);
				isConst = StaticEnum.NONE;
				if (asgs == 1) isConst = StaticEnum.CONST;			
				result.add(generator.newDataDeclaration(isConst, name, null, newType, initial, this));
			}

			return result;
		}
	}
}
