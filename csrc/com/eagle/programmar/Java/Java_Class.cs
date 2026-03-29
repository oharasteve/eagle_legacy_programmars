// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

namespace com.eagle.programmar.Java
{
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using Java_Constructor = com.eagle.programmar.Java.Java_Method.Java_Constructor;
	using Java_GenericType = com.eagle.programmar.Java.Java_Type.Java_GenericType;
	using Java_Class_Definition = com.eagle.programmar.Java.Symbols.Java_Class_Definition;
	using Java_Identifier_Reference = com.eagle.programmar.Java.Symbols.Java_Identifier_Reference;
	using Java_Comment = com.eagle.programmar.Java.Terminals.Java_Comment;
	using Java_Keyword = com.eagle.programmar.Java.Terminals.Java_Keyword;
	using Java_KeywordChoice = com.eagle.programmar.Java.Terminals.Java_KeywordChoice;
	using Java_Punctuation = com.eagle.programmar.Java.Terminals.Java_Punctuation;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationPeriod = com.eagle.tokens.punctuation.PunctuationPeriod;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using PrivacyEnum = com.eagle.transform.EagleGenerator.PrivacyEnum;

	public class Java_Class : TokenSequence, EagleRunnable, AbstractClass
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @BLANKLINE Java_ClassModifierList modifierList;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @CURIOUS("Extra at sign") com.eagle.programmar.Java.Terminals.Java_Punctuation atSign = new com.eagle.programmar.Java.Terminals.Java_Punctuation('@');
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.programmar.Java.Terminals.Java_KeywordChoice classOrInterface = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice("class", "interface");
		public Java_KeywordChoice classOrInterface = new Java_KeywordChoice("class", "interface");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.Java.Symbols.Java_Class_Definition className;
		public Java_Class_Definition className;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT Java_GenericType genericType;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT Java_ClassExtends jextends;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT Java_ClassImplements jimplements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) @OPT TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @INDENT PunctuationLeftBrace leftBrace;
		public  INDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT TokenList<Java_ClassElement> elements;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OUTDENT PunctuationRightBrace rightBrace;
		public  OUTDENT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @OPT @NEWLINE TokenList<com.eagle.programmar.Java.Terminals.Java_Comment> comments3;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @CURIOUS("Extra semicolon") @OPT PunctuationSemicolon semicolon;
		public @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

		public static class Java_ClassModifierList extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<Java_ClassModifier> modifiers;
			public TokenList<Java_ClassModifier> modifiers;
		}

		public static class Java_ClassModifier extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST @NEWLINE Java_Comment XXcomment;
			public @NEWLINE Java_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_Annotation XXannotation;
			public Java_Annotation XXannotation;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE Java_KeywordChoice XXmodifier = new com.eagle.programmar.Java.Terminals.Java_KeywordChoice(Java_Program.MODIFIERS);
			public Java_KeywordChoice XXmodifier = new Java_KeywordChoice(Java_Program.MODIFIERS);
		}

		public static class Java_ClassExtends extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword EXTENDS = new com.eagle.programmar.Java.Terminals.Java_Keyword("extends");
			public Java_Keyword EXTENDS = new Java_Keyword("extends");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Java.Symbols.Java_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> classNames;
			public SeparatedList<Java_Identifier_Reference, PunctuationPeriod> classNames;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_GenericType genericType;
			public @OPT Java_GenericType genericType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Java_MoreExtends> moreExtend;
			public @OPT TokenList<Java_MoreExtends> moreExtend;

			public static class Java_MoreExtends extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Java.Symbols.Java_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> classNames;
				public SeparatedList<Java_Identifier_Reference, PunctuationPeriod> classNames;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_GenericType genericType;
				public @OPT Java_GenericType genericType;
			}
		}

		public static class Java_ClassImplements extends TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.Java.Terminals.Java_Keyword IMPLEMENTS = new com.eagle.programmar.Java.Terminals.Java_Keyword("implements");
			public Java_Keyword IMPLEMENTS = new Java_Keyword("implements");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.tokens.SeparatedList<com.eagle.programmar.Java.Symbols.Java_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> classNames;
			public SeparatedList<Java_Identifier_Reference, PunctuationPeriod> classNames;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT Java_GenericType genericType;
			public @OPT Java_GenericType genericType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<Java_MoreImplements> moreImpl;
			public @OPT TokenList<Java_MoreImplements> moreImpl;

			public static class Java_MoreImplements extends TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationComma comma;
				public PunctuationComma comma;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT Java_Comment comment;
				public @OPT Java_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.SeparatedList<com.eagle.programmar.Java.Symbols.Java_Identifier_Reference, com.eagle.tokens.punctuation.PunctuationPeriod> classNames;
				public SeparatedList<Java_Identifier_Reference, PunctuationPeriod> classNames;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT Java_GenericType genericType;
				public @OPT Java_GenericType genericType;
			}
		}

		public static class Java_ClassElement extends TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST @NEWLINE Java_Comment XXcomment;
			public @NEWLINE Java_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @NEWLINE Java_Method XXmethod;
			public @NEWLINE Java_Method XXmethod;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST @NEWLINE Java_Constructor XXconstructor;
			public @NEWLINE Java_Constructor XXconstructor;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE @CURIOUS(value = "Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon XXsemicolon;
			public @CURIOUS(value = "Extra semicolon") PunctuationSemicolon XXsemicolon;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @LAST Java_Statement XXstatement;
			public Java_Statement XXstatement;
		}

		public void interpret(EagleInterpreter interpreter)
		{
			foreach (Java_ClassElement element in elements._elements)
			{
				interpreter.tryToInterpret(element);
			}
		}

		public void newJavaClass(PrivacyEnum privacy, string cName)
		{
			this.setPresent(true);
			this.modifierList = new Java_ClassModifierList();
			this.modifierList.setPresent(true);
			this.modifierList.modifiers = new TokenList<Java_ClassModifier>();
			Java_ClassModifier modifier = new Java_ClassModifier();
			switch (privacy)
			{
			case PUBLIC:
				modifier.setWhich(new Java_KeywordChoice("public"));
				break;
			case PRIVATE:
				modifier.setWhich(new Java_KeywordChoice("private"));
				break;
			default:
				throw new Exception("Can't handle privacy: " + privacy);
			}
			this.modifierList.modifiers.addToken(modifier);

			this.className = new Java_Class_Definition();
			this.className.setValue(cName);

			this.classOrInterface = new Java_KeywordChoice("class");
			this.elements = new TokenList<Java_ClassElement>();
			this.elements.setPresent(true);
			this.leftBrace = new PunctuationLeftBrace();
			this.rightBrace = new PunctuationRightBrace();
		}

		public void addMethod(Java_Method method)
		{
			Java_ClassElement element = new Java_ClassElement();
			element.setWhich(method);
			this.elements.addToken(element);
		}

		public void addComment(Java_Comment comment)
		{
			if (this.comments3 == null)
			{
				this.comments3 = new TokenList<Java_Comment>();
			}
			this.comments3.addToken(comment);
		}
	}

}
