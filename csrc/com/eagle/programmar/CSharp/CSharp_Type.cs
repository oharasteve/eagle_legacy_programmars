// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.CSharp
{
	using CSharp_Identifier_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
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

	public class CSharp_Type : TokenSequence, AbstractType
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) CSharp_TypeName typeName;
		public CSharp_TypeName typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_GenericType genericType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CSharp_ArrayType> arrayTypes;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CSharp_Punctuation questionMark = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation("?");
		public  OPT; // Nullable

		public class CSharp_ArrayType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE PunctuationComma comma;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
			public  NOSPACE;
		}

		public class CSharp_GenericType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation lessThan = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('<');
			public CSharp_Punctuation lessThan = new CSharp_Punctuation('<');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE SeparatedList<CSharp_Type, com.eagle.tokens.punctuation.PunctuationComma> subType;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE CSharp_Punctuation greaterThan = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('>');
			public  NOSPACE;
		}

		public class CSharp_MoreIds : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationPeriod dot;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @NOSPACE CSharp_TypeName nextId;
			public  NOSPACE;
		}

		public class CSharp_NamespaceId : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference namespace;
			public CSharp_Identifier_Reference @namespace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation colonColon = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation("::");
			public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		}

		public class CSharp_IdList : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CSharp_NamespaceId namespaceId;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference typeName;
			public CSharp_Identifier_Reference typeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CSharp_ExtendsType extendsType;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<CSharp_MoreIds> moreIds;
			public  OPT;
		}

		// Delay finding this one until after looking for [] and <>
		public class CSharp_TypeName : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST CSharp_KeywordChoice XXprimitive = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("auto", "bool", "boolean", "byte", "char", "class", "decimal", "double", "float", "int", "long", "object", "sbyte", "short", "string", "String", "ulong", "ushort", "void");
			public CSharp_KeywordChoice XXprimitive = new CSharp_KeywordChoice("auto", "bool", "boolean", "byte", "char", "class", "decimal", "double", "float", "int", "long", "object", "sbyte", "short", "string", "String", "ulong", "ushort", "void");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_IdList XXidList;
			public CSharp_IdList XXidList;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSharp_GenericTypeQuestion extends com.eagle.tokens.TokenSequence
			public class CSharp_GenericTypeQuestion : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation question = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation('?');
				public CSharp_Punctuation question = new CSharp_Punctuation('?');
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_ExtendsType extendsType;
				public  OPT;
			}
		}

		public class CSharp_ExtendsType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword EXTENDS = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("extends");
			public CSharp_Keyword EXTENDS = new CSharp_Keyword("extends");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference typeName;
			public CSharp_Identifier_Reference typeName;
		}

		// Convert "double" to a CSharp_Type representing a double
		public static CSharp_Type newPrimitiveType(string name)
		{
			CSharp_Type type = new CSharp_Type();
			type.typeName = new CSharp_TypeName();
			type.typeName.setWhich(new CSharp_KeywordChoice(name));
			return type;
		}

		// Convert "foo" to a CSharp_Type representing the user class foo
		public static CSharp_Type newIdentifierType(string name)
		{
			CSharp_Type type = new CSharp_Type();
			type.typeName = new CSharp_TypeName();
			CSharp_IdList ids = new CSharp_IdList();
			ids.typeName = new CSharp_Identifier_Reference();
			ids.typeName.setValue(name);
			type.typeName.setWhich(ids);
			return type;
		}

		public static CSharp_Type transformType(EagleGenerator.TypeEnum type, string typeName, AbstractToken source)
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
				return newPrimitiveType("int");
			case DOUBLE:
				return newPrimitiveType("double");
			case CHAR:
			case STRING:
				return newPrimitiveType("string");
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

		public static CSharp_Type transformTypeArray(EagleGenerator.TypeEnum type)
		{
			CSharp_ArrayType array = new CSharp_ArrayType();
			array.leftBracket = new PunctuationLeftBracket();
			array.rightBracket = new PunctuationRightBracket();
			CSharp_Type newType = CSharp_Type.transformType(type, null, null);
			newType.arrayTypes = new TokenList<CSharp_ArrayType>();
			newType.arrayTypes.addToken(array);
			newType.arrayTypes.setPresent(true);
			return newType;
		}

		public static CSharp_Type transformTypeHash(EagleGenerator.TypeEnum type)
		{
			CSharp_Type newType = new CSharp_Type();

			CSharp_IdList idList = new CSharp_IdList();
			idList.typeName = new CSharp_Identifier_Reference();
			idList.typeName.setValue("System.Collections.Generic.Dictionary");
			newType.typeName = new CSharp_TypeName();
			newType.typeName.setWhich(idList);

			newType.genericType = new CSharp_GenericType();
			newType.genericType.setPresent(true);
			newType.genericType.subType = new SeparatedList<CSharp_Type, PunctuationComma>();
			newType.genericType.subType.addPrimaryElement(newPrimitiveType("int"));
			newType.genericType.subType.addSecondaryElement(new PunctuationComma());
			newType.genericType.subType.addPrimaryElement(newPrimitiveType("string"));

			return newType;
		}

		public static AbstractType findType(EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator, CSharp_Type type)
		{
			EagleGenerator.TypeEnum newType;
			if (type.arrayTypes != null && type.arrayTypes.size() > 0)
			{
				newType = EagleGenerator.TypeEnum.ARRAY;
			}
			else
			{
				CSharp_KeywordChoice typeNameKW = (CSharp_KeywordChoice) type.typeName.getWhich();
				switch (typeNameKW.getValue().ToLower())
				{
				case "bool":
					newType = EagleGenerator.TypeEnum.BOOLEAN;
					break;
				case "int":
					newType = EagleGenerator.TypeEnum.INTEGER;
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
