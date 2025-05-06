// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2010

package com.eagle.programmar.CSharp;

import com.eagle.generate.EagleGenerator.PrivacyEnum;
import com.eagle.interpret.EagleInterpreter;
import com.eagle.interpret.EagleRunnable;
import com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
import com.eagle.programmar.CSharp.Directives.CSharp_PragmaDirective;
import com.eagle.programmar.CSharp.Directives.CSharp_RegionDirective;
import com.eagle.programmar.CSharp.Symbols.CSharp_Class_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Identifier_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.interfaces.AbstractClass;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Class extends TokenSequence implements EagleRunnable, AbstractClass
{
	public @S(10) @OPT @NEWLINE TokenList<CSharp_AnnotationOrComment> annotationsOrComments;
	public @S(20) @OPT TokenList<CSharp_ClassModifier> modifiers;
	public @S(30) CSharp_KeywordChoice classOrInterface = new CSharp_KeywordChoice("class", "interface", "struct");
	public @S(40) CSharp_Class_Definition className;
	public @S(50) @OPT CSharp_GenericType genericType;
	public @S(60) @OPT CSharp_ExtendsOrImplements extendsOrImplements;
	public @S(70) @OPT @NEWLINE TokenList<CSharp_Comment> comments1;
	public @S(80) @INDENT PunctuationLeftBrace leftBrace;
	public @S(90) @OPT TokenList<CSharp_ClassElement> elements;
	public @S(100) @OUTDENT PunctuationRightBrace rightBrace;
	public @S(110) @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

	public static class CSharp_AnnotationOrComment extends TokenChooser
	{
		public @CHOICE CSharp_Annotation XXannotation;
		public @CHOICE CSharp_Comment XXcomment;
	}

	public static class CSharp_ClassModifier extends TokenSequence
	{
		public @S(10) CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}

	public static class CSharp_DotIdentifier extends TokenSequence
	{
		public @S(10) PunctuationPeriod dot;
		public @S(20) CSharp_Identifier id;
	}

	public static class CSharp_ExtendsOrImplements extends TokenSequence
	{
		public @S(10) PunctuationColon colon;
		public @S(20) @OPT CSharp_NamespaceQualifer namespaceQualifier;
		public @S(30) SeparatedList<CSharp_Identifier_Reference, PunctuationPeriod> className;
		public @S(40) @OPT CSharp_GenericType genericType;
		public @S(50) @OPT CSharp_GenericWhere where;
		public @S(60) @OPT TokenList<CSharp_MoreImplements> moreImpl;

		public static class CSharp_NamespaceQualifer extends TokenSequence
		{
			public @S(10) CSharp_Identifier_Reference nameSpace;
			public @S(20) CSharp_Punctuation colonColon = new CSharp_Punctuation("::");
		}

		public static class CSharp_MoreImplements extends TokenSequence
		{
			public @S(10) PunctuationComma comma;
			public @S(20) SeparatedList<CSharp_Identifier_Reference, PunctuationPeriod> className;
			public @S(30) @OPT CSharp_GenericType genericType;
			public @S(40) @OPT CSharp_GenericWhere where;
		}

		public static class CSharp_GenericWhere extends TokenSequence
		{
			public @S(10) CSharp_Keyword WHERE = new CSharp_Keyword("where");
			public @S(20) CSharp_Identifier_Reference id;
			public @S(30) PunctuationColon colon;
			public @S(40) CSharp_Type type;
		}
	}

	public static class CSharp_ClassElement extends TokenChooser
	{
		public @CHOICE @NEWLINE CSharp_Comment XXcomment;

		public @CHOICE @NEWLINE CSharp_Property XXproperty;
		public @CHOICE @NEWLINE CSharp_Constructor XXconstructor;
		public @FIRST @NEWLINE CSharp_Method XXmethod;
		public @LAST @NEWLINE CSharp_Statement XXstatement;
		public @CHOICE @NEWLINE CSharp_SubscriptOperator XXsubscriptOperator;
		public @CHOICE @NEWLINE CSharp_Operator XXoperator;

		public @CHOICE @NEWLINE CSharp_RegionDirective XXregionDirective;
		public @CHOICE @NEWLINE CSharp_PragmaDirective XXpragmaDirective;

		public @CHOICE static class CSharp_StaticStatement extends TokenSequence
		{
			public @S(10) CSharp_Keyword STATIC = new CSharp_Keyword("static");
			public @S(20) @NEWLINE CSharp_Statement statement;
		}
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		for (CSharp_ClassElement element : elements._elements)
		{
			interpreter.tryToInterpret(element);
		}
	}
	
	public void newCSharpClass(PrivacyEnum privacy, String cName)
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
			throw new RuntimeException("Can't handle privacy: " + privacy);
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
	
	public void addMethod(CSharp_Method method)
	{
		CSharp_ClassElement element = new CSharp_ClassElement();
		element.setWhich(method);
		this.elements.addToken(element);
	}
	
	
	public void addComment(CSharp_Comment comment)
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
