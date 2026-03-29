// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Apr 1, 2020

namespace com.eagle.programmar.Rust
{
	using Rust_Keyword = com.eagle.programmar.Rust.Terminals.Rust_Keyword;
	using Rust_KeywordChoice = com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice;
	using Rust_Punctuation = com.eagle.programmar.Rust.Terminals.Rust_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Rust_Type : TokenChooser, AbstractType
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Rust_TypePrimitive extends com.eagle.tokens.TokenSequence
		public class Rust_TypePrimitive : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Rust_TypePrimitiveStatic typeStatic;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice PRIMITIVE = new com.eagle.programmar.Rust.Terminals.Rust_KeywordChoice("bool", "f64", "isize", "i32", "str", "&str", "String", "usize", "u32", "HashMap");
			public Rust_KeywordChoice PRIMITIVE = new Rust_KeywordChoice("bool", "f64", "isize", "i32", "str", "&str", "String", "usize", "u32", "HashMap");

			public class Rust_TypePrimitiveStatic : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Punctuation ampersand = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("&");
				public Rust_Punctuation ampersand = new Rust_Punctuation("&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_Punctuation quote = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("'");
				public Rust_Punctuation quote = new Rust_Punctuation("'");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Terminals.Rust_Keyword STATIC = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("static");
				public Rust_Keyword STATIC = new Rust_Keyword("static");
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Rust_TypeArray extends com.eagle.tokens.TokenSequence
		public class Rust_TypeArray : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Rust.Terminals.Rust_Punctuation ampersand = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("&");
			public Rust_Punctuation ampersand = new Rust_Punctuation("&");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Rust.Terminals.Rust_Punctuation quote = new com.eagle.programmar.Rust.Terminals.Rust_Punctuation("'");
			public Rust_Punctuation quote = new Rust_Punctuation("'");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Rust.Terminals.Rust_Keyword STATIC = new com.eagle.programmar.Rust.Terminals.Rust_Keyword("static");
			public Rust_Keyword STATIC = new Rust_Keyword("static");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) Rust_Type subType;
			public Rust_Type subType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

	//	public static class Rust_GenericType extends TokenSequence
	//	{
	//		public @S(10) Rust_Punctuation lessThan = new Rust_Punctuation('<');
	//		public @S(20) @OPT @NOSPACE Rust_Type subType1;
	//		public @S(30) @OPT TokenList<Rust_MoreTypes> moreType;
	//		public @S(40) @NOSPACE Rust_Punctuation greaterThan = new Rust_Punctuation('>');
	//	}
	//
	//	public static class Rust_MoreTypes extends TokenSequence
	//	{
	//		public @S(10) PunctuationComma comma;
	//		public @S(20) Rust_Type subType2;
	//	}

		public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, string typeName)
		{
			EagleGenerator.TypeEnum newType;
			switch (typeName)
			{
			case "bool":
				newType = EagleGenerator.TypeEnum.BOOLEAN;
				break;
			case "i32":
			case "isize":
			case "u32":
			case "usize":
				newType = EagleGenerator.TypeEnum.INTEGER;
				break;
			case "f64":
				newType = EagleGenerator.TypeEnum.DOUBLE;
				break;
			case "str":
			case "String":
				newType = EagleGenerator.TypeEnum.STRING;
				break;
			case "HashMap":
				newType = EagleGenerator.TypeEnum.HASH;
				break;
			default:
				newType = EagleGenerator.TypeEnum.OTHER;
				break;
			}
			return generator.transformType(newType, null, null);
		}

		// Convert "i32" to a Rust_Type representing an integer
		public static Rust_Type newPrimitiveType(string name)
		{
			Rust_Type type = new Rust_Type();
			Rust_TypePrimitive primitive = new Rust_TypePrimitive();
			primitive.PRIMITIVE.setValue(name);
			type.setWhich(primitive);
			return type;
		}

		public static Rust_Type transformType(EagleGenerator.TypeEnum type, string typeName, AbstractToken source)
		{
			if (type == null)
			{
				return null;
			}

			switch (type)
			{
			case BOOLEAN:
				return newPrimitiveType("bool");
			case INTEGER:
				return newPrimitiveType("i32");
			case DOUBLE:
				return newPrimitiveType("f64");
			case CHAR:
			case STRING:
				return newPrimitiveType("&str");
			case ARRAY:
				return transformTypeArray(EagleGenerator.TypeEnum.STRING);
			case HASH:
				return newPrimitiveType("HashMap");
			case VOID:
				return null;
			default:
				throw new Exception("Can't transform type: " + type);
			}
		}

		public static Rust_Type transformTypeArray(EagleGenerator.TypeEnum type)
		{
			Rust_TypeArray array = new Rust_TypeArray();
			array.leftBracket = new PunctuationLeftBracket();
			array.rightBracket = new PunctuationRightBracket();

			Rust_Type newType = new Rust_Type();
			newType.setWhich(array);
			return newType;
		}
	}
}
