// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.Java
{
	using Java_Identifier_Reference = com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using Java_Punctuation = com.eagle.programmar.Java.Terminals.Java_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Java_Type : TokenSequence, AbstractType
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Java_Comment comment;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Java_TypeName typeName;
		public Java_TypeName typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT @NOSPACE Java_GenericType genericType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT @NOSPACE TokenList<Java_ArrayType> arrayTypes;
		public  OPT;

		public class Java_ArrayType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE PunctuationRightBracket rightBracket;
			public  NOSPACE;
		}

		public class Java_GenericType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation lessThan = new com.eagle.programmar.Java.Terminals.Java_Punctuation('<');
			public Java_Punctuation lessThan = new Java_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE Java_Type subType1;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_Punctuation emptySubscript = new com.eagle.programmar.Java.Terminals.Java_Punctuation("[]");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Java_MoreTypes> moreType;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @NOSPACE Java_Punctuation greaterThan = new com.eagle.programmar.Java.Terminals.Java_Punctuation('>');
			public  NOSPACE;
		}

		public class Java_MoreTypes : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
			public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) Java_Type subType2;
			public Java_Type subType2;
		}

		public class Java_IdList : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Symbols.Java_Identifier_Reference typeName;
			public Java_Identifier_Reference typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_ExtendsType extendsType;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<Java_MoreIds> moreIds;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Java_ExtendsMultiple multiple;
			public  OPT;

			public class Java_MoreIds : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationPeriod dot;
				public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE Java_TypeName nextId;
				public  NOSPACE;
			}

			public class Java_ExtendsMultiple : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation ampersand = new com.eagle.programmar.Java.Terminals.Java_Punctuation('&');
				public Java_Punctuation ampersand = new Java_Punctuation('&');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Java.Symbols.Java_Identifier_Reference typeName;
				public Java_Identifier_Reference typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_ExtendsType extendsType;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Java_MoreIds> moreIds;
				public  OPT;
			}
		}

		// Delay finding this one until after looking for [] and <>
		public class Java_TypeName : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST Java_KeywordChoice XXprimitive = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("void", "boolean", "byte", "short", "int", "long", "char", "float", "double", "String", "class");
			public Java_KeywordChoice XXprimitive = new Java_KeywordChoice("void", "boolean", "byte", "short", "int", "long", "char", "float", "double", "String", "class");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_IdList XXidList;
			public Java_IdList XXidList;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Java_GenericTypeQuestion extends com.eagle.tokens.TokenSequence
			public class Java_GenericTypeQuestion : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Punctuation question = new com.eagle.programmar.Java.Terminals.Java_Punctuation('?');
				public Java_Punctuation question = new Java_Punctuation('?');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_ExtendsType extendsType;
				public  OPT;
			}
		}

		public class Java_ExtendsType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_KeywordChoice EXTENDS = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("extends", "super");
			public Java_KeywordChoice EXTENDS = new Java_KeywordChoice("extends", "super");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Java.Symbols.Java_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> typeName;
			public SeparatedList<Java_Identifier_Reference, PunctuationPeriod> typeName;
		}

		// Convert "double" to a Java_Type representing a double
		public static Java_Type newPrimitiveType(string name)
		{
			Java_Type type = new Java_Type();
			type.typeName = new Java_TypeName();
			type.typeName.setWhich(new Java_KeywordChoice(name));
			return type;
		}

		// Convert "foo" to a Java_Type representing the user class foo
		public static Java_Type newIdentifierType(string name)
		{
			Java_Type type = new Java_Type();
			type.typeName = new Java_TypeName();
			Java_IdList ids = new Java_IdList();
			ids.typeName = new Java_Identifier_Reference();
			ids.typeName.setValue(name);
			type.typeName.setWhich(ids);
			return type;
		}

		public static Java_Type transformType(EagleGenerator.TypeEnum type, string typeName, AbstractToken source)
		{
			if (type == null)
			{
				return null;
			}

			switch (type)
			{
			case BOOLEAN:
				return newPrimitiveType("boolean");
			case INTEGER:
				return newPrimitiveType("int");
			case DOUBLE:
				return newPrimitiveType("double");
			case CHAR:
			case STRING:
				return newPrimitiveType("String");
			case ARRAY:
				return transformTypeArray(EagleGenerator.TypeEnum.STRING);
			case HASH:
				return transformTypeHash(EagleGenerator.TypeEnum.STRING);
			case VOID:
				return newPrimitiveType("void");
			case OTHER:
				return newIdentifierType(typeName);
			default:
				throw new Exception("Can't transform type: " + type);
			}
		}

		public static Java_Type transformTypeArray(EagleGenerator.TypeEnum type)
		{
			Java_ArrayType array = new Java_ArrayType();
			array.leftBracket = new PunctuationLeftBracket();
			array.rightBracket = new PunctuationRightBracket();
			Java_Type newType = Java_Type.transformType(type, null, null);
			newType.arrayTypes = new TokenList<Java_ArrayType>();
			newType.arrayTypes.addToken(array);
			newType.arrayTypes.setPresent(true);
			return newType;
		}

		public static Java_Type transformTypeHash(EagleGenerator.TypeEnum type)
		{
			Java_Type newType = new Java_Type();

			Java_IdList idList = new Java_IdList();
			idList.typeName = new Java_Identifier_Reference();
			idList.typeName.setValue("java.util.HashMap");
			newType.typeName = new Java_TypeName();
			newType.typeName.setWhich(idList);

			newType.genericType = new Java_GenericType();
			newType.genericType.setPresent(true);
			newType.genericType.subType1 = newPrimitiveType("Integer");
			newType.genericType.subType1.setPresent(true);
			newType.genericType.moreType = new TokenList<Java_MoreTypes>();
			newType.genericType.moreType.setPresent(true);

			Java_MoreTypes more = new Java_MoreTypes();
			more.comma = new PunctuationComma();
			more.subType2 = newPrimitiveType("String");
			newType.genericType.moreType.addToken(more);

			return newType;
		}

		public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, Java_Type type)
		{
			EagleGenerator.TypeEnum newType;
			if (type.arrayTypes != null && type.arrayTypes.size() > 0)
			{
				newType = EagleGenerator.TypeEnum.ARRAY;
			}
			else
			{
				Java_KeywordChoice typeNameKW = (Java_KeywordChoice) type.typeName.getWhich();
				switch (typeNameKW.getValue().ToLower())
				{
				case "boolean":
					newType = EagleGenerator.TypeEnum.BOOLEAN;
					break;
				case "int":
					newType = EagleGenerator.TypeEnum.INTEGER;
					break;
				case "double":
					newType = EagleGenerator.TypeEnum.DOUBLE;
					break;
				case "string":
					newType = EagleGenerator.TypeEnum.STRING;
					break;
				case "void":
					newType = EagleGenerator.TypeEnum.VOID;
					break;
				default:
					newType = EagleGenerator.TypeEnum.OTHER;
					break;
				}
			}

			return generator.transformType(newType, null, null);
		}
	}

}
