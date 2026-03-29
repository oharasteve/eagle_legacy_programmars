// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.Java
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using Java_Variable_Definition = com.eagle.programmar.Java.Symbols.Java_Variable_Definition;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using EagleTransformableStatementList = com.eagle.transform.EagleTransformableStatementList;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class Java_Data : TokenSequence, EagleRunnable, AbstractStatement, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE TokenList<Java_Annotation> annotation1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<Java_DataModifier> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Java_Annotation> annotation2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) Java_Type jtype;
		public Java_Type jtype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.Java.Symbols.Java_Variable_Definition id;
		public Java_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<Java_DataSubscript> subscripts;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Java_DataInitialValue initialValue;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<Java_MoreIdentifiers> moreIds;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @NOSPACE PunctuationSemicolon semicolon;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments;
		public  OPT;

		public class Java_DataSubscript : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

		public class Java_DataModifier : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_KeywordChoice modifier = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice(Java_Program.MODIFIERS);
			public Java_KeywordChoice modifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
		}

		public class Java_DataInitialValue : TokenSequence, EagleRunnable
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Java_Expression expression;
			public Java_Expression expression;

			public override void interpret(EagleInterpreter interpreter)
			{
				EagleValue value = interpreter.getEagleValue(expression);
				interpreter.pushEagleValue(value);
			}
		}

		public class Java_MoreIdentifiers : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Symbols.Java_Variable_Definition id;
			public Java_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationLeftBracket leftBracket;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationRightBracket rightBracket;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Java_DataInitialValue initialValue;
			public  OPT;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (initialValue != null && initialValue.isPresent())
			{
				EagleValue value = interpreter.getEagleValue(initialValue);
				interpreter.setSymbol(id, id.ToString(), value);
			}
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			AbstractType newType = Java_Type.findType(generator, jtype);

			string name = id.getValue();
			AbstractExpression initial = null;
			if (initialValue != null && initialValue.isPresent())
			{
				initial = transformer.transformExpression(generator, initialValue.expression);
			}
			result.Add(generator.newDataDeclaration(false, name, null, newType, initial, this));

			foreach (Java_MoreIdentifiers more in moreIds._elements)
			{
				name = more.id.getValue();
				initial = null;
				if (more.initialValue != null && more.initialValue.isPresent())
				{
					initial = transformer.transformExpression(generator, more.initialValue.expression);
				}
				result.Add(generator.newDataDeclaration(false, name, null, newType, initial, this));
			}

			return result;
		}

		// Called directly from Java_Program for static class-level data
		public virtual AbstractStatement transformStaticData(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newType = Java_Type.findType(generator, jtype);

			string name = id.getValue();
			AbstractExpression initial = null;
			if (initialValue != null && initialValue.isPresent())
			{
				initial = transformer.transformExpression(generator, initialValue.expression);
			}
			return generator.newDataDeclaration(true, name, null, newType, initial, this);
		}

		public static Java_Data newDataDeclaration(bool isStatic, string name, Java_Expression size, Java_Type type, Java_Expression initial, AbstractToken source)
		{
			if (type == null)
			{
				throw new Exception("Can't create data without a type, for " + name);
			}

			if (name.Equals("true", StringComparison.OrdinalIgnoreCase) || name.Equals("false", StringComparison.OrdinalIgnoreCase))
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

		private bool hasModifier(string which)
		{
			if (modifiers != null)
			{
				foreach (Java_DataModifier mod in modifiers._elements)
				{
					if (which.Equals(mod.modifier.getValue()))
					{
						return true;
					}
				}
			}
			return false;
		}

		public virtual void addModifier(string which)
		{
			Java_DataModifier mod = new Java_DataModifier();
			mod.modifier.setValue(which);
			if (modifiers == null)
			{
				modifiers = new TokenList<Java_DataModifier>();
				modifiers.setPresent(true);
			}
			if (!hasModifier(which))
			{
				modifiers.addToken(mod);
			}
		}
	}

}
