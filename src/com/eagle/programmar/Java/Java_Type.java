// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.transform.EagleGenerator;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Java_Type extends TokenSequence implements AbstractType
{
	public @S(10) @OPT Java_Comment comment;
	public @S(20) Java_TypeName typeName;
	public @S(30) @OPT @NOSPACE Java_GenericType genericType;
	public @S(40) @OPT @NOSPACE TokenList<Java_ArrayType> arrayTypes;

	public static class Java_ArrayType extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
		public @S(20) @NOSPACE PunctuationRightBracket rightBracket;
	}

	public static class Java_GenericType extends TokenSequence
	{
		public @S(10) Java_Punctuation lessThan = new Java_Punctuation('<');
		public @S(20) @OPT @NOSPACE Java_Type subType1;
		public @S(30) @OPT Java_Punctuation emptySubscript = new Java_Punctuation("[]");
		public @S(40) @OPT TokenList<Java_MoreTypes> moreType;
		public @S(50) @NOSPACE Java_Punctuation greaterThan = new Java_Punctuation('>');
	}

	public static class Java_MoreTypes extends TokenSequence
	{
		public @S(10) PunctuationComma comma;
		public @S(20) Java_Type subType2;
	}

	public static class Java_IdList extends TokenSequence
	{
		public @S(10) Java_Identifier_Reference typeName;
		public @S(20) @OPT Java_ExtendsType extendsType;
		public @S(30) @OPT TokenList<Java_MoreIds> moreIds;
		public @S(40) @OPT Java_ExtendsMultiple multiple;

		public static class Java_MoreIds extends TokenSequence
		{
			public @S(10) @NOSPACE PunctuationPeriod dot;
			public @S(20) @NOSPACE Java_TypeName nextId;
		}

		public static class Java_ExtendsMultiple extends TokenSequence
		{
			public @S(10) Java_Punctuation ampersand = new Java_Punctuation('&');
			public @S(20) Java_Identifier_Reference typeName;
			public @S(30) @OPT Java_ExtendsType extendsType;
			public @S(40) @OPT TokenList<Java_MoreIds> moreIds;
		}
	}

	// Delay finding this one until after looking for [] and <>
	public static class Java_TypeName extends TokenChooser
	{
		public @FIRST Java_KeywordChoice XXprimitive = new Java_KeywordChoice(
				"void", "boolean", "byte", "short", "int",
				"long", "char", "float", "double", "String", "class");

		public @CHOICE Java_IdList XXidList;

		public @CHOICE static class Java_GenericTypeQuestion extends TokenSequence
		{
			public @S(10) Java_Punctuation question = new Java_Punctuation('?');
			public @S(20) @OPT Java_ExtendsType extendsType;
		}
	}

	public static class Java_ExtendsType extends TokenSequence
	{
		public @S(10) Java_KeywordChoice EXTENDS = new Java_KeywordChoice("extends", "super");
		public @S(20) SeparatedList<Java_Identifier_Reference, PunctuationPeriod> typeName;
	}
	
	// Convert "double" to a Java_Type representing a double
	public static Java_Type newPrimitiveType(String name)
	{
		Java_Type type = new Java_Type();
		type.typeName = new Java_TypeName();
		type.typeName.setWhich(new Java_KeywordChoice(name));
		return type;
	}
	
	// Convert "foo" to a Java_Type representing the user class foo
	public static Java_Type newIdentifierType(String name)
	{
		Java_Type type = new Java_Type();
		type.typeName = new Java_TypeName();
		Java_IdList ids = new Java_IdList();
		ids.typeName = new Java_Identifier_Reference();
		ids.typeName.setValue(name);
		type.typeName.setWhich(ids);
		return type;
	}

	public static Java_Type transformType(TypeEnum type,
			String typeName, AbstractToken source)
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
		case STRING:
			return newPrimitiveType("String");
		case STRING_ARRAY:
			return transformTypeArray(TypeEnum.STRING);
		case STRING_HASH:
			return transformTypeHash(TypeEnum.STRING);
		case VOID:
			return newPrimitiveType("void");
		case OTHER:
			return newIdentifierType(typeName);
		default:
			throw new RuntimeException("Can't transform type: " + type);
		}
	}

	public static Java_Type transformTypeArray(TypeEnum type)
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

	public static Java_Type transformTypeHash(TypeEnum type)
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

	public static AbstractType findType(EagleGenerator generator, Java_Type type)
	{
		TypeEnum newType;
		if (type.arrayTypes != null && type.arrayTypes.size() > 0)
		{
			newType = TypeEnum.STRING_ARRAY;
		}
		else
		{
			Java_KeywordChoice typeNameKW = (Java_KeywordChoice) type.typeName.getWhich();
			switch (typeNameKW.getValue().toLowerCase())
			{
			case "boolean":
				newType = TypeEnum.BOOLEAN;
				break;
			case "int":
				newType = TypeEnum.INTEGER;
				break;
			case "string":
				newType = TypeEnum.STRING;
				break;
			case "void":
				newType = TypeEnum.VOID;
				break;
			default:
				newType = TypeEnum.OTHER;
				break;
			}
		}
		
		return generator.transformType(newType, null, null);
	}
}
