// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractType;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.transform.EagleGenerator.TypeEnum;

public class Python_Type extends TokenChooser implements AbstractType
{
	public @LAST Python_Literal XXtypeName;
	public @CHOICE Python_Punctuation XXellipsis = new Python_Punctuation("...");

	public @CHOICE static class Python_TypeParens extends TokenSequence
	{
		public @S(10) PunctuationLeftParen leftParen;
		public @S(20) @OPT Python_TypeList typeList;
		public @S(30) PunctuationRightParen rightParen;
	}

	public @CHOICE static class Python_TypeBrackets extends TokenSequence
	{
		public @S(10) PunctuationLeftBracket leftBracket;
		public @S(20) @OPT Python_TypeList typeList;
		public @S(30) PunctuationRightBracket rightBracket;
	}

	public @CHOICE Python_KeywordChoice XXPRIMITIVES = new Python_KeywordChoice(
			"Any", "None", "bool", "bytes", "float", "int", "object", "str", "Text");

	public @FIRST static class Python_MetaClass extends TokenSequence
	{
		public @S(10) Python_Keyword METEACLASS = new Python_Keyword("metaclass");
		public @S(20) PunctuationEquals equals;
		public @S(30) Python_Variable metaclass;
	}

	public @FIRST static class Python_StructuredType extends TokenSequence
	{
		public @S(10) @OPT Python_TypeTyping typing;
		public @S(20) Python_KeywordChoice TUPLE = new Python_KeywordChoice("Awaitable", "awaitable", "Callable",
				"callable", "Coroutine", "coroutine", "DefaultDict", "defaultdict", "Dict", "dict", "Generic",
				"generic", "Iterable", "iterable", "Iterator", "iterator", "List", "list", "Map", "map", "Mapping",
				"mapping", "MutableMapping", "mutablemapping", "Optional", "optional", "Sequence", "sequence", "Set",
				"set", "Tuple", "tuple", "Type", "type", "Union", "union");
		public @S(30) Python_TypeBrackets typeList;

		public static class Python_TypeTyping extends TokenSequence
		{
			public @S(10) Python_Keyword TYPING = new Python_Keyword("typing");
			public @S(20) PunctuationPeriod dot;
		}
	}

	public @CHOICE static class Python_Regular_Class extends TokenSequence
	{
		public @S(10) SeparatedList<Python_TypeName, PunctuationPeriod> superClass;

		public static class Python_TypeName extends TokenChooser
		{
			public @CHOICE Python_Keyword XXSELF = new Python_Keyword("self");
			public @CHOICE Python_Identifier_Reference XXid;
		}
	}
	
	// Convert "double" to a Python_Type representing a double
	public static Python_Type newPrimitiveType(String name)
	{
		Python_Type type = new Python_Type();
		type.setWhich(new Python_KeywordChoice(name));
		return type;
	}

	// Convert "foo" to a Python_Type representing the user class foo
	public static Python_Type newIdentifierType(String name)
	{
		Python_Type type = new Python_Type();
		Python_Literal lit = new Python_Literal();
		lit.setValue(name);
		type.setWhich(lit);
		return type;
	}

	public static Python_Type transformType(TypeEnum type,
			String typeName, AbstractToken source)
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
			return newPrimitiveType("float");
		case CHAR:
		case STRING:
			return newPrimitiveType("str");
		case STRING_ARRAY:
			return newPrimitiveType("list"); // actually list[str]
		case STRING_HASH:
			return newPrimitiveType("dict"); // actually dict[str]
		case VOID:
			return newPrimitiveType("None");
		case OTHER:
			return newIdentifierType(typeName);
		default:
			throw new RuntimeException("Can't transform type: " + type);
		}
	}
}
