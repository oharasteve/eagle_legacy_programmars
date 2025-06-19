// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
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
import com.eagle.transform.EagleGenerator.TypeEnum;

public class CSharp_Type extends TokenSequence implements AbstractType
{
	public @S(10) CSharp_TypeName typeName;
	public @S(20) @OPT CSharp_GenericType genericType;
	public @S(30) @OPT TokenList<CSharp_ArrayType> arrayTypes;
	public @S(40) @OPT CSharp_Punctuation questionMark = new CSharp_Punctuation("?"); // Nullable

	public static class CSharp_ArrayType extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationLeftBracket leftBracket;
		public @S(20) @OPT @NOSPACE PunctuationComma comma;
		public @S(30) @NOSPACE PunctuationRightBracket rightBracket;
	}

	public static class CSharp_GenericType extends TokenSequence
	{
		public @S(10) CSharp_Punctuation lessThan = new CSharp_Punctuation('<');
		public @S(20) @OPT @NOSPACE SeparatedList<CSharp_Type, PunctuationComma> subType;
		public @S(30) @NOSPACE CSharp_Punctuation greaterThan = new CSharp_Punctuation('>');
	}

	public static class CSharp_MoreIds extends TokenSequence
	{
		public @S(10) @NOSPACE PunctuationPeriod dot;
		public @S(20) @NOSPACE CSharp_TypeName nextId;
	}

	public static class CSharp_NamespaceId extends TokenSequence
	{
		public @S(10) CSharp_Identifier_Reference namespace;
		public @S(20) CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
	}

	public static class CSharp_IdList extends TokenSequence
	{
		public @S(10) @OPT CSharp_NamespaceId namespaceId;
		public @S(20) CSharp_Identifier_Reference typeName;
		public @S(30) @OPT CSharp_ExtendsType extendsType;
		public @S(40) @OPT TokenList<CSharp_MoreIds> moreIds;
	}

	// Delay finding this one until after looking for [] and <>
	public static class CSharp_TypeName extends TokenChooser
	{
		public @FIRST CSharp_KeywordChoice XXprimitive = new CSharp_KeywordChoice(
				"auto", "bool", "boolean", "byte", "char", "class", "decimal", "double",
				"float", "int", "long", "object", "sbyte", "short", "string", "String",
				"ulong", "ushort", "void");

		public @CHOICE CSharp_IdList XXidList;

		public @CHOICE static class CSharp_GenericTypeQuestion extends TokenSequence
		{
			public @S(10) CSharp_Punctuation question = new CSharp_Punctuation('?');
			public @S(20) @OPT CSharp_ExtendsType extendsType;
		}
	}

	public static class CSharp_ExtendsType extends TokenSequence
	{
		public @S(10) CSharp_Keyword EXTENDS = new CSharp_Keyword("extends");
		public @S(20) CSharp_Identifier_Reference typeName;
	}
	
	// Convert "double" to a CSharp_Type representing a double
	public static CSharp_Type newPrimitiveType(String name)
	{
		CSharp_Type type = new CSharp_Type();
		type.typeName = new CSharp_TypeName();
		type.typeName.setWhich(new CSharp_KeywordChoice(name));
		return type;
	}
	
	// Convert "foo" to a CSharp_Type representing the user class foo
	public static CSharp_Type newIdentifierType(String name)
	{
		CSharp_Type type = new CSharp_Type();
		type.typeName = new CSharp_TypeName();
		CSharp_IdList ids = new CSharp_IdList();
		ids.typeName = new CSharp_Identifier_Reference();
		ids.typeName.setValue(name);
		type.typeName.setWhich(ids);
		return type;
	}
	
	public static CSharp_Type transformType(boolean isArray, TypeEnum type, String typeName, AbstractToken source)
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
		case STRING:
			return newPrimitiveType("string");
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
	
	public static CSharp_Type transformTypeArray(TypeEnum type)
	{
		CSharp_ArrayType array = new CSharp_ArrayType();
		array.leftBracket = new PunctuationLeftBracket();
		array.rightBracket = new PunctuationRightBracket();
		CSharp_Type newType = CSharp_Type.transformType(true, type, null, null);
		newType.arrayTypes = new TokenList<CSharp_ArrayType>();
		newType.arrayTypes.addToken(array);
		newType.arrayTypes.setPresent(true);
		return newType;
	}
	
	public static CSharp_Type transformTypeHash(TypeEnum type)
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
}
