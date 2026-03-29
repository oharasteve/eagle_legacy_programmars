// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System;
using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

namespace com.eagle.programmar.CSharp
{

	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using ArgumentsMetrics = com.eagle.metrics.ArgumentsMetrics;
	using CallMetrics = com.eagle.metrics.CallMetrics;
	using ReturnMetrics = com.eagle.metrics.ReturnMetrics;
	using CSharp_GenericType = com.eagle.programmar.CSharp.CSharp_Type.CSharp_GenericType;
	using CSharp_StatementBlock = com.eagle.programmar.CSharp.Statements.CSharp_StatementBlock;
	using CSharp_Method_Definition = com.eagle.programmar.CSharp.Symbols.CSharp_Method_Definition;
	using CSharp_Type_Definition = com.eagle.programmar.CSharp.Symbols.CSharp_Type_Definition;
	using CSharp_Variable_Definition = com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
	using CSharp_Comment = com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
	using CSharp_Keyword = com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
	using CSharp_KeywordChoice = com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
	using CSharp_Punctuation = com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
	using EagleScope = com.eagle.scope.EagleScope;
	using EagleScopeInterface = com.eagle.scope.EagleScope.EagleScopeInterface;
	using AbstractFunction = com.eagle.tokens.AbstractFunction;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using SeparatedList = com.eagle.tokens.SeparatedList;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using PunctuationColon = com.eagle.tokens.punctuation.PunctuationColon;
	using PunctuationComma = com.eagle.tokens.punctuation.PunctuationComma;
	using PunctuationEquals = com.eagle.tokens.punctuation.PunctuationEquals;
	using PunctuationLeftBrace = com.eagle.tokens.punctuation.PunctuationLeftBrace;
	using PunctuationLeftParen = com.eagle.tokens.punctuation.PunctuationLeftParen;
	using PunctuationRightBrace = com.eagle.tokens.punctuation.PunctuationRightBrace;
	using PunctuationRightParen = com.eagle.tokens.punctuation.PunctuationRightParen;
	using PunctuationSemicolon = com.eagle.tokens.punctuation.PunctuationSemicolon;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using PrivacyEnum = com.eagle.transform.EagleGenerator.PrivacyEnum;
	using StaticEnum = com.eagle.transform.EagleGenerator.StaticEnum;
	using EagleTransformableFunction = com.eagle.transform.EagleTransformableFunction;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class CSharp_Method : TokenSequence, AbstractMethod, AbstractFunction, EagleRunnable, EagleScope.EagleScopeInterface, EagleTransformableFunction
	{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT @BLANKLINE TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comments;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT TokenList<CSharp_Annotation> annotation;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @OPT TokenList<CSharp_MethodModifier> modifiers;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT TokenList<com.eagle.programmar.CSharp.Terminals.CSharp_Comment> comment2;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) CSharp_Type returnType;
		public CSharp_Type returnType;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT CSharp_Keyword GLOBAL = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("global");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(70) @OPT CSharp_Punctuation colon2 = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation("::");
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(80) com.eagle.programmar.CSharp.Symbols.CSharp_Method_Definition id;
		public CSharp_Method_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(90) @OPT CSharp_GenericType generic;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(100) @OPT CSharp_MethodParameters parameters;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(110) @OPT TokenList<CSharp_MethodWhere> where;
		public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(120) @NEWLINE CSharp_MethodBody body;
		public  NEWLINE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(130) @OPT @CURIOUS("Extra semicolon") com.eagle.tokens.punctuation.PunctuationSemicolon semicolon;
		public  OPT;

		public class CSharp_MethodParameters : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @NOSPACE PunctuationLeftParen leftParen;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT @NOSPACE SeparatedList<CSharp_MethodParameter, com.eagle.tokens.punctuation.PunctuationComma> params;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) @NOSPACE PunctuationRightParen rightParen;
			public  NOSPACE;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) @OPT CSharp_Comment comment3;
			public  OPT;
		}

		public class CSharp_MethodModifier : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice modifier = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
			public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
		}

		public class CSharp_MethodParameter : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT CSharp_Annotation annotation;
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_KeywordChoice passBy = new com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice("ref", "out", "this", "params");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CSharp_Type cstype;
			public CSharp_Type cstype;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition id;
			public CSharp_Variable_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(50) @OPT CSharp_Punctuation emptySubscript = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation("[]");
			public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(60) @OPT CSharp_MethodParamDefault defValue;
			public  OPT;

			public class CSharp_MethodParamDefault : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.punctuation.PunctuationEquals equals;
				public PunctuationEquals equals;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) CSharp_Expression value;
				public CSharp_Expression value;
			}
		}

		public class CSharp_MethodWhere : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Keyword WHERE = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("where");
			public CSharp_Keyword WHERE = new CSharp_Keyword("where");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.CSharp.Symbols.CSharp_Type_Definition id;
			public CSharp_Type_Definition id;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) com.eagle.tokens.punctuation.PunctuationColon colon;
			public PunctuationColon colon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(40) CSharp_Type type;
			public CSharp_Type type;
		}

		public class CSharp_MethodBody : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PunctuationSemicolon XXsemicolon;
			public PunctuationSemicolon XXsemicolon;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE CSharp_MethodImplementation XXimplementation;
			public CSharp_MethodImplementation XXimplementation;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE static class CSharp_MethodLambda extends com.eagle.tokens.TokenSequence
			public class CSharp_MethodLambda : TokenSequence
			{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation equalsGreater = new com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation("=>");
				public CSharp_Punctuation equalsGreater = new CSharp_Punctuation("=>");
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) @OPT CSharp_Keyword REF = new com.eagle.programmar.CSharp.Terminals.CSharp_Keyword("ref");
				public  OPT;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(30) CSharp_Expression returnValue;
				public CSharp_Expression returnValue;
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP CallMetrics _callMetrics = null;
		public CallMetrics _callMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ArgumentsMetrics _argumentsMetrics = null;
		public ArgumentsMetrics _argumentsMetrics = null;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @SKIP ReturnMetrics _returnMetrics = null;
		public ReturnMetrics _returnMetrics = null;

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: private @SKIP EagleScope _scope = new com.eagle.scope.EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);
		private EagleScope _scope = new EagleScope(this, CSharp_Syntax.IS_CASE_SENSITIVE);

		public override EagleScope Scope
		{
			get
			{
				return _scope;
			}
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			if (_callMetrics == null)
			{
				_callMetrics = new CallMetrics(interpreter._metrics, id.getValue(), id);
			}
			if (_argumentsMetrics == null)
			{
				_argumentsMetrics = new ArgumentsMetrics(interpreter._metrics, id.getValue(), id);
			}
			if (_returnMetrics == null)
			{
				_returnMetrics = new ReturnMetrics(interpreter._metrics, id.getValue(), id);
			}

			// Nothing to do here. Only run methods when they are called / invoked.
			// Exception is 'Main'
			if (id.getValue().Equals("Main"))
			{
				interpreter.callingFunction("main", this);
				AbstractToken which = body.getWhich();
				if (which is CSharp_MethodImplementation)
				{
					CSharp_MethodImplementation impl = (CSharp_MethodImplementation) which;
					foreach (CSharp_StatementOrComment stmt in impl.block.statements._elements)
					{
						interpreter.tryToInterpret(stmt);
					}
				}
				interpreter.completedFunction("main", this);
			}
		}

		public override void transformFunction(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			AbstractType newReturnType = CSharp_Type.findType(generator, returnType);

			string newName = id.getValue();
			if (VERBOSE)
			{
				Console.WriteLine("** Found CSharp method " + newName);
			}
			if (newName.Equals("Main"))
			{
				newName = generator.mainName();
			}

			generator.addMethod(newReturnType, newName, this);
			generator.setMethodName(newName);

			if (parameters != null && parameters.isPresent())
			{
				int nParams = parameters.@params.getPrimaryCount();
				for (int i = 0; i < nParams; i++)
				{
					CSharp_MethodParameter param = parameters.@params.getPrimaryElement(i);
					AbstractType paramType = CSharp_Type.findType(generator, param.cstype);
					generator.addMethodParameter(paramType, param.id.getValue());
				}
			}

			if (!(body.getWhich() is CSharp_MethodImplementation))
			{
				throw new Exception("Methods need an implementation");
			}

			CSharp_MethodImplementation impl = (CSharp_MethodImplementation) body.getWhich();
			List<AbstractStatement> newStmts = new List<AbstractStatement>();
			foreach (CSharp_StatementOrComment javaStmt in impl.block.statements._elements)
			{
				if (javaStmt.getWhich() is CSharp_Statement)
				{
					CSharp_Statement stmt1 = (CSharp_Statement) javaStmt.getWhich();
					List<AbstractStatement> stmts2 = transformer.transformStatement(generator, stmt1.getWhich());
					if (stmts2 != null)
					{
						foreach (AbstractStatement stmt2 in stmts2)
						{
							newStmts.Add(stmt2);
						}
					}
				}
			}

			AbstractStatement newBlock = generator.newBlockStatement(newStmts, impl);
			generator.addStatement(newBlock, impl);
			generator.doneMethod();
		}

		public virtual void newCSharpMethod(EagleGenerator.PrivacyEnum privacy, EagleGenerator.StaticEnum isStatic, CSharp_Type retType, string mName)
		{
			this.modifiers = new TokenList<CSharp_MethodModifier>();

			CSharp_MethodModifier modifier1 = new CSharp_MethodModifier();
			switch (privacy)
			{
			case PUBLIC:
				modifier1.modifier = new CSharp_KeywordChoice("public");
				break;
			case PRIVATE:
			case NONE:
				modifier1.modifier = new CSharp_KeywordChoice("private");
				break;
			default:
				throw new Exception("Can't handle privacy: " + privacy);
			}
			this.modifiers.addToken(modifier1);

			switch (isStatic)
			{
			case NONE:
				break;
			case STATIC:
				CSharp_MethodModifier modifier2 = new CSharp_MethodModifier();
				modifier2.modifier = new CSharp_KeywordChoice("static");
				this.modifiers.addToken(modifier2);
				break;
			default:
				throw new Exception("Can't handle static: " + isStatic);
			}

			if (retType == null)
			{
				this.returnType = CSharp_Type.newPrimitiveType("void");
			}
			else
			{
				this.returnType = retType;
			}

			this.parameters = new CSharp_MethodParameters();
			this.parameters.setPresent(true);
			this.parameters.leftParen = new PunctuationLeftParen();
			this.parameters.@params = new SeparatedList<CSharp_MethodParameter, PunctuationComma>();
			this.parameters.rightParen = new PunctuationRightParen();

			this.body = new CSharp_MethodBody();
			CSharp_MethodImplementation impl = new CSharp_MethodImplementation();
			impl.block = new CSharp_StatementBlock();
			impl.block.leftBrace = new PunctuationLeftBrace();
			impl.block.statements = new TokenList<CSharp_StatementOrComment>();
			impl.block.rightBrace = new PunctuationRightBrace();
			this.body.setWhich(impl);

			this.id = new CSharp_Method_Definition();
			this.id.setValue(mName);
		}

		public virtual void addMethodParameter(AbstractType type, string name)
		{
			CSharp_MethodParameter param = new CSharp_MethodParameter();
			param.setPresent(true);
			param.id = new CSharp_Variable_Definition();
			param.id.setValue(name);
			param.cstype = (CSharp_Type) type;

			if (this.parameters.@params.size() > 0)
			{
				this.parameters.@params.addSecondaryElement(new PunctuationComma());
			}
			this.parameters.@params.addPrimaryElement(param);
		}

		public virtual void addComment(CSharp_Comment comment)
		{
			if (this.comments == null)
			{
				this.comments = new TokenList<CSharp_Comment>();
			}
			this.comments.addToken(comment);
		}
	}

}
