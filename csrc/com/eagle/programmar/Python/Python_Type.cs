// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

namespace com.eagle.programmar.Python
{
	using Python_Identifier_Reference = com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
	using Python_Keyword = com.eagle.programmar.Python.Terminals.Python_Keyword;
	using Python_KeywordChoice = com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
	using Python_Literal = com.eagle.programmar.Python.Terminals.Python_Literal;
	using Python_Punctuation = com.eagle.programmar.Python.Terminals.Python_Punctuation;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBracket = com.eagle.tokens.punctuation.PunctuationLeftBracket;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBracket = com.eagle.tokens.punctuation.PunctuationRightBracket;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;

	public class Python_Type : TokenChooser, AbstractType
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Python_Literal XXtypeName;
		public Python_Literal XXtypeName;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Punctuation XXellipsis = new com.eagle.programmar.Python.Terminals.Python_Punctuation("...");
		public Python_Punctuation XXellipsis = new Python_Punctuation("...");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_TypeParens extends com.eagle.tokens.TokenSequence
		public class Python_TypeParens : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftParen leftParen;
			public PunctuationLeftParen leftParen;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_TypeList typeList;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightParen rightParen;
			public PunctuationRightParen rightParen;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_TypeBrackets extends com.eagle.tokens.TokenSequence
		public class Python_TypeBrackets : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationLeftBracket leftBracket;
			public PunctuationLeftBracket leftBracket;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Python_TypeList typeList;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationRightBracket rightBracket;
			public PunctuationRightBracket rightBracket;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_KeywordChoice XXPRIMITIVES = new com.eagle.programmar.Python.Terminals.Python_KeywordChoice("Any", "None", "bool", "bytes", "float", "int", "object", "str", "Text");
		public Python_KeywordChoice XXPRIMITIVES = new Python_KeywordChoice("Any", "None", "bool", "bytes", "float", "int", "object", "str", "Text");

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Python_MetaClass extends com.eagle.tokens.TokenSequence
		public class Python_MetaClass : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword METEACLASS = new com.eagle.programmar.Python.Terminals.Python_Keyword("metaclass");
			public Python_Keyword METEACLASS = new Python_Keyword("metaclass");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationEquals equals;
			public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Python_Variable metaclass;
			public Python_Variable metaclass;
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST static class Python_StructuredType extends com.eagle.tokens.TokenSequence
		public class Python_StructuredType : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT Python_TypeTyping typing;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.Python.Terminals.Python_KeywordChoice TUPLE = new com.eagle.programmar.Python.Terminals.Python_KeywordChoice("Awaitable", "awaitable", "Callable", "callable", "Coroutine", "coroutine", "DefaultDict", "defaultdict", "Dict", "dict", "Generic", "generic", "Iterable", "iterable", "Iterator", "iterator", "List", "list", "Map", "map", "Mapping", "mapping", "MutableMapping", "mutablemapping", "Optional", "optional", "Sequence", "sequence", "Set", "set", "Tuple", "tuple", "Type", "type", "Union", "union");
			public Python_KeywordChoice TUPLE = new Python_KeywordChoice("Awaitable", "awaitable", "Callable", "callable", "Coroutine", "coroutine", "DefaultDict", "defaultdict", "Dict", "dict", "Generic", "generic", "Iterable", "iterable", "Iterator", "iterator", "List", "list", "Map", "map", "Mapping", "mapping", "MutableMapping", "mutablemapping", "Optional", "optional", "Sequence", "sequence", "Set", "set", "Tuple", "tuple", "Type", "type", "Union", "union");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) Python_TypeBrackets typeList;
			public Python_TypeBrackets typeList;

			public class Python_TypeTyping : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Python.Terminals.Python_Keyword TYPING = new com.eagle.programmar.Python.Terminals.Python_Keyword("typing");
				public Python_Keyword TYPING = new Python_Keyword("typing");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.punctuation.PunctuationPeriod dot;
				public PunctuationPeriod dot;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class Python_Regular_Class extends com.eagle.tokens.TokenSequence
		public class Python_Regular_Class : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.SeparatedList<Python_TypeName, com.eagle.tokens.punctuation.PunctuationPeriod> superClass;
			public SeparatedList<Python_TypeName, PunctuationPeriod> superClass;

			public class Python_TypeName : TokenChooser
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Keyword XXSELF = new com.eagle.programmar.Python.Terminals.Python_Keyword("self");
				public Python_Keyword XXSELF = new Python_Keyword("self");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Python_Identifier_Reference XXid;
				public Python_Identifier_Reference XXid;
			}
		}

		// Convert "double" to a Python_Type representing a double
		public static Python_Type newPrimitiveType(string name)
		{
			Python_Type type = new Python_Type();
			type.setWhich(new Python_KeywordChoice(name));
			return type;
		}

		// Convert "foo" to a Python_Type representing the user class foo
		public static Python_Type newIdentifierType(string name)
		{
			Python_Type type = new Python_Type();
			Python_Literal lit = new Python_Literal();
			lit.setValue(name);
			type.setWhich(lit);
			return type;
		}

		public static Python_Type transformType(TypeEnum type, string typeName, AbstractToken source)
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
			case ARRAY:
				return newPrimitiveType("list"); // actually list[str]
			case HASH:
				return newPrimitiveType("dict"); // actually dict[str]
			case VOID:
				return newPrimitiveType("None");
			case OTHER:
				return newIdentifierType(typeName);
			default:
				throw new Exception("Can't transform type: " + type);
			}
		}
	}

}
