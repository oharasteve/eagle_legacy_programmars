// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.math.EagleValue;
import com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
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
import com.eagle.transform.EagleTransformableStatement;
import com.eagle.transform.EagleTransformer;

public class Java_Data extends TokenSequence
		implements EagleRunnable, AbstractStatement, EagleTransformableStatement
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

	public static class Java_DataModifier extends TokenChooser
	{
		public @CHOICE Java_KeywordChoice XXmodifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
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
	public AbstractStatement transformStatement(EagleTransformer transformer, EagleGenerator generator)
	{
		AbstractExpression initial = null;
		if (initialValue != null && initialValue.isPresent())
		{
			initial = transformer.transformExpression(generator, initialValue.expression);
		}
		
		String name = id.getValue();
		AbstractType newType = Java_Type.findType(generator, jtype);
		AbstractStatement stmt = generator.newDataDeclaration(name, null, newType, initial, this);
		return stmt;
	}
	
	public static Java_Data newDataDeclaration(String name, Java_Expression size, Java_Type type,
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
}
