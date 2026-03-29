// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.CSharp
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using EagleValue = com.eagle.math.EagleValue;
	using CSharp_Variable_Definition = com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
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

	public class CSharp_Data : TokenSequence, EagleRunnable, EagleTransformableStatementList
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NEWLINE CSharp_DataBeforeSemicolon dataBody;
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationSemicolon semicolon;
		public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments;
		public  OPT;

		public class CSharp_DataBeforeSemicolon : TokenSequence, EagleRunnable
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE TokenList<CSharp_Annotation> annotation1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CSharp_DataModifier> modifiers;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CSharp_Annotation> annotation2;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) CSharp_Type type;
			public CSharp_Type type;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition id;
			public CSharp_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT PunctuationLeftBracket leftBracket;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT PunctuationRightBracket rightBracket;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT CSharp_DataInitialValue initialValue;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT TokenList<CSharp_MoreIdentifiers> moreIds;
			public  OPT;

			public override void interpret(EagleInterpreter interpreter)
			{
				if (initialValue != null && initialValue.isPresent())
				{
					EagleValue value = interpreter.getEagleValue(initialValue.expression);
					interpreter.setSymbol(id, id.ToString(), value);
				}
			}
		}

		public class CSharp_DataModifier : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice modifier = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
			public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
		}

		public class CSharp_DataInitialValue : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSharp_Expression expression;
			public CSharp_Expression expression;
		}

		public class CSharp_MoreIdentifiers : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition id;
			public CSharp_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT PunctuationLeftBracket leftBracket;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT PunctuationRightBracket rightBracket;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CSharp_DataInitialValue initialValue;
			public  OPT;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			interpreter.tryToInterpret(dataBody);
		}

		public override List<AbstractStatement> transformStatement(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			List<AbstractStatement> result = new List<AbstractStatement>();
			AbstractType newType = CSharp_Type.findType(generator, dataBody.type);

			string name = dataBody.id.getValue();
			AbstractExpression initial = null;
			if (dataBody.initialValue != null && dataBody.initialValue.isPresent())
			{
				initial = transformer.transformExpression(generator, dataBody.initialValue.expression);
			}
			result.Add(generator.newDataDeclaration(false, name, null, newType, initial, this));

			foreach (CSharp_MoreIdentifiers more in dataBody.moreIds._elements)
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

		// Called directly from CSharp_Program for static class-level data
		public virtual AbstractStatement transformStaticData(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newType = CSharp_Type.findType(generator, dataBody.type);

			string name = dataBody.id.getValue();
			AbstractExpression initial = null;
			if (dataBody.initialValue != null && dataBody.initialValue.isPresent())
			{
				initial = transformer.transformExpression(generator, dataBody.initialValue.expression);
			}
			return generator.newDataDeclaration(true, name, null, newType, initial, dataBody);
		}

		public static CSharp_Data newDataDeclaration(bool isStatic, string name, CSharp_Expression size, CSharp_Type type, CSharp_Expression initial, AbstractToken source)
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

			CSharp_Data data = new CSharp_Data();
			data.dataBody = new CSharp_DataBeforeSemicolon();
			data.semicolon = new PunctuationSemicolon();

			// Set data name and type
			data.dataBody.id = new CSharp_Variable_Definition();
			data.dataBody.id.setValue(name);
			data.dataBody.type = type;

			if (isStatic)
			{
				data.addModifier("static");
			}

			// Set the initial value, if any
			if (initial != null)
			{
				CSharp_DataInitialValue init = new CSharp_DataInitialValue();
				init.setPresent(true);
				init.equals = new PunctuationEquals();
				init.expression = initial;
				data.dataBody.initialValue = init;
				data.dataBody.initialValue.setPresent(true);
			}

			data.setTransformationSource(source);
			return data;
		}

		private bool hasModifier(string which)
		{
			if (dataBody.modifiers != null)
			{
				foreach (CSharp_DataModifier mod in dataBody.modifiers._elements)
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
			CSharp_DataModifier mod = new CSharp_DataModifier();
			mod.modifier.setValue(which);
			if (dataBody.modifiers == null)
			{
				dataBody.modifiers = new TokenList<CSharp_DataModifier>();
				dataBody.modifiers.setPresent(true);
			}
			if (!hasModifier(which))
			{
				dataBody.modifiers.addToken(mod);
			}
		}
	}

}
