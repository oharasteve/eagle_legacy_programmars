// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import java.util.ArrayList;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractExpression;
import com.eagle.tokens.interfaces.AbstractStatement;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationSemicolon;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleTransformableStatementList;
import com.eagle.transform.EagleTransformer;

public class Java_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatementList
{
	public @S(10) @OPT @NEWLINE TokenList<Java_Annotation> annotation1;
	public @S(20) @OPT TokenList<Java_DataModifier> modifiers;
	public @S(30) @OPT TokenList<Java_Annotation> annotation2;
	public @S(40) Java_Type jtype;
	public @S(50) Java_Variable_Definition id;
	public @S(60) @OPT TokenList<Java_DataSubscript> subscripts;
	public @S(70) @OPT Java_DataInitialValue initialValue;
	public @S(80) @OPT TokenList<Java_MoreIdentifiers> moreIds;
	public @S(90) @NOSPACE PunctuationSemicolon semicolon;
	public @S(100) @OPT TokenList<Java_Comment> comments;

	public static class Java_DataSubscript extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) PunctuationRightBracket rightBracket;
	}

	public static class Java_DataModifier extends TokenSequence
	{
		public @S(10) Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
	}

	public static class Java_DataInitialValue extends TokenSequence implements EagleRunnable
	{
		public @S(10) PunctuationEquals equals;
		public @S(20) Java_Expression expression;

		@Override
		public void interpret(EagleInterpreter interpreter)
		{
			EagleValue value = interpreter.getEagleValue(expression);
			interpreter.pushEagleValue(value);
		}
	}

	public static class Java_MoreIdentifiers extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Java_Variable_Definition id;
		public @S(30) @OPT PunctuationLeftBracket leftBracket;
		public @S(40) @OPT PunctuationRightBracket rightBracket;
		public @S(50) @OPT Java_DataInitialValue initialValue;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (initialValue != null && initialValue.isPresent())
		{
			EagleValue value = interpreter.getEagleValue(initialValue);
			interpreter.setSymbol(id, id.toString(), value);
		}
	}

	@Override
	public ArrayList<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		ArrayList<AbstractStatement> result = new ArrayList<AbstractStatement>();
		AbstractType newType = Java_Type.findType(generator, jtype);
		
		String name = id.getValue();
		AbstractExpression initial = null;
		if (initialValue != null && initialValue.isPresent())
		{
			initial = transformer.transformExpression(generator, initialValue.expression);
		}
		result.add(generator.newDataDeclaration(false, name, null, newType, initial, this));
		
		for (Java_MoreIdentifiers more : moreIds._elements)
		{
			name = more.id.getValue();
			initial = null;
			if (more.initialValue != null && more.initialValue.isPresent())
			{
				initial = transformer.transformExpression(generator, more.initialValue.expression);
			}
			result.add(generator.newDataDeclaration(false, name, null, newType, initial, this));
		}
		
		return result;
	}
	
	// Called directly from Java_Program for static class-level data
	public AbstractStatement transformStaticData(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractType newType = Java_Type.findType(generator, jtype);
		
		String name = id.getValue();
		AbstractExpression initial = null;
		if (initialValue != null && initialValue.isPresent())
		{
			initial = transformer.transformExpression(generator, initialValue.expression);
		}
		return generator.newDataDeclaration(true, name, null, newType, initial, this);
	}
	
	public static Java_Data newDataDeclaration(boolean isStatic, String name, Java_Expression size, Java_Type type,
			Java_Expression initial, AbstractToken source)
	{
		if (type == null)
		{
			throw new RuntimeException("Can't create data without a type, for " + name);
		}
		
		if (name.equalsIgnoreCase("true") || name.equalsIgnoreCase("false"))
		{
			// Sorry, cannot redefine true or false
			return null;
		}
		
		Java_Data data = new Java_Data();
		data.semicolon = new PunctuationSemicolon();
		
		// Set data name and type
		data.id = new Java_Variable_Definition();
		data.id.setValue(name);
		data.jtype = type;

		if (isStatic)
		{
			data.addModifier("static");
		}
		
		// Set the initial value, if any
		if (initial != null)
		{
			Java_DataInitialValue init = new Java_DataInitialValue();
			init.setPresent(true);
			init.equals = new PunctuationEquals();
			init.expression = initial;
			data.initialValue = init;
			data.initialValue.setPresent(true);
		}

		data.setTransformationSource(source);
		return data;
	}

	private boolean hasModifier(String which)
	{
		if (modifiers != null)
		{
			for (Java_DataModifier mod : modifiers._elements)
			{
				if (which.equals(mod.modifier.getValue()))
				{
					return true;
				}
			}
		}
		return false;
	}

	public void addModifier(String which)
	{
		Java_DataModifier mod = new Java_DataModifier();
		mod.modifier.setValue(which);
		if (modifiers == null)
		{
			modifiers = new TokenList<Java_DataModifier>();
			modifiers.setPresent(true);
		}
		if (! hasModifier(which))
		{
			modifiers.addToken(mod);
		}
	}
}
