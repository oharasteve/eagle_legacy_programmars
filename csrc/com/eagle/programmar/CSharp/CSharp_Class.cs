// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

namespace com.eagle.programmar.CSharp
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using CSharp_GenericType = com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
	using CSharp_PragmaDirective = com.eagle.programmar.CSharp.Directives.CSharp_PragmaDirective;
	using CSharp_RegionDirective = com.eagle.programmar.CSharp.Directives.CSharp_RegionDirective;
	using CSharp_Class_Definition = com.eagle.programmar.CSharp.Symbols.CSharp_Class_Definition;
	using CSharp_Identifier_Reference = com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_Identifier = com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PrivacyEnum = com.eagle.transform.EagleGenerator.PrivacyEnum;

	public class CSharp_Class : TokenSequence, EagleRunnable, AbstractClass
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @NEWLINE TokenList<CSharp_AnnotationOrComment> annotationsOrComments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CSharp_ClassModifier> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice classOrInterface = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("class", "interface", "struct");
		public CSharp_KeywordChoice classOrInterface = new CSharp_KeywordChoice("class", "interface", "struct");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CSharp.Symbols.CSharp_Class_Definition className;
		public CSharp_Class_Definition className;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CSharp_GenericType genericType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT CSharp_ExtendsOrImplements extendsOrImplements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT @NEWLINE TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments1;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT TokenList<CSharp_ClassElement> elements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public  OPT;

		public class CSharp_AnnotationOrComment : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Annotation XXannotation;
			public CSharp_Annotation XXannotation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_Comment XXcomment;
			public CSharp_Comment XXcomment;
		}

		public class CSharp_ClassModifier : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice modifier = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
			public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
		}

		public class CSharp_DotIdentifier : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationPeriod dot;
			public PunctuationPeriod dot;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_Identifier id;
			public CSharp_Identifier id;
		}

		public class CSharp_ExtendsOrImplements : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_NamespaceQualifer namespaceQualifier;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> className;
			public SeparatedList<CSharp_Identifier_Reference, PunctuationPeriod> className;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CSharp_GenericType genericType;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CSharp_GenericWhere where;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT TokenList<CSharp_MoreImplements> moreImpl;
			public  OPT;

			public class CSharp_NamespaceQualifer : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference nameSpace;
				public CSharp_Identifier_Reference nameSpace;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation colonColon = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation("::");
				public CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
			}

			public class CSharp_MoreImplements : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> className;
				public SeparatedList<CSharp_Identifier_Reference, PunctuationPeriod> className;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT CSharp_GenericType genericType;
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CSharp_GenericWhere where;
				public  OPT;
			}

			public class CSharp_GenericWhere : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword WHERE = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("where");
				public CSharp_Keyword WHERE = new CSharp_Keyword("where");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference id;
				public CSharp_Identifier_Reference id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon;
				public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) CSharp_Type type;
				public CSharp_Type type;
			}
		}

		public class CSharp_ClassElement : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST @NEWLINE CSharp_Method XXmethod;
			public  NEWLINE;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Comment XXcomment;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Constructor XXconstructor;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Operator XXoperator;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_PragmaDirective XXpragmaDirective;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_Property XXproperty;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_RegionDirective XXregionDirective;
			public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE CSharp_SubscriptOperator XXsubscriptOperator;
			public  NEWLINE;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST @NEWLINE CSharp_Statement XXstatement;
			public  NEWLINE;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			foreach (CSharp_ClassElement element in elements._elements)
			{
				interpreter.tryToInterpret(element);
			}
		}

		public virtual void newCSharpClass(PrivacyEnum privacy, string cName)
		{
			this.modifiers = new TokenList<CSharp_ClassModifier>();
			CSharp_ClassModifier modifier = new CSharp_ClassModifier();
			switch (privacy)
			{
			case PUBLIC:
				modifier.modifier = new CSharp_KeywordChoice("public");
				break;
			case PRIVATE:
				modifier.modifier = new CSharp_KeywordChoice("private");
				break;
			default:
				throw new Exception("Can't handle privacy: " + privacy);
			}
			this.modifiers.addToken(modifier);

			this.className = new CSharp_Class_Definition();
			this.className.setValue(cName);

			this.classOrInterface = new CSharp_KeywordChoice("class");
			this.elements = new TokenList<CSharp_ClassElement>();
			this.elements.setPresent(true);
			this.leftBrace = new PunctuationLeftBrace();
			this.rightBrace = new PunctuationRightBrace();
		}

		public virtual void addMethod(CSharp_Method method)
		{
			CSharp_ClassElement element = new CSharp_ClassElement();
			element.setWhich(method);
			this.elements.addToken(element);
		}

		public virtual void addComment(CSharp_Comment comment)
		{
			if (this.annotationsOrComments == null)
			{
				this.annotationsOrComments = new TokenList<CSharp_AnnotationOrComment>();
			}
			CSharp_AnnotationOrComment annot = new CSharp_AnnotationOrComment();
			annot.setWhich(comment);
			this.annotationsOrComments.addToken(annot);
		}
	}

}
