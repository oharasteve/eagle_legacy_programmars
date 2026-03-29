// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2015

namespace com.eagle.programmar.AWK
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using AWK_Comment = com.eagle.programmar.AWK.Terminals.AWK_Comment;
	using AWK_EndOfLine = com.eagle.programmar.AWK.Terminals.AWK_EndOfLine;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using TokenSequence = com.eagle.tokens.TokenSequence;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class AWK_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string AWK = "AWK";

		public AWK_Program() : base(AWK, new AWK_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "https://www.gnu.org/software/gawk/manual/gawk.html";
			}
		}

// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.tokens.TokenList<AWK_Element> elements;
		public TokenList<AWK_Element> elements;

		public class AWK_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Command XXcommand;
			public AWK_Command XXcommand;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @FIRST AWK_CommentLine XXcomment;
			public AWK_CommentLine XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE AWK_Function XXfunction;
			public AWK_Function XXfunction;
		}

		public class AWK_CommentLine : TokenSequence
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) com.eagle.programmar.AWK.Terminals.AWK_Comment comment;
			public AWK_Comment comment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(20) com.eagle.programmar.AWK.Terminals.AWK_EndOfLine eoln;
			public AWK_EndOfLine eoln;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (AWK_Element element in elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is AWK_Function)
				{
					AWK_Function fn = (AWK_Function) which;
					interpreter.addFunction(fn.id.getValue(), fn);
				}
			}

			// Second pass, execute the program
			foreach (AWK_Element element in elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is AWK_Command)
				{
					AWK_Command cmd = (AWK_Command) which;
					interpreter.tryToInterpret(cmd.action);
				}
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the Function definitions
			foreach (AWK_Element elt in elements._elements)
			{
				AbstractToken which = elt.getWhich();
				if (which is AWK_Function)
				{
					AWK_Function func = (AWK_Function) which;
					// System.err.println("****** Found func " + func.id.getValue());
					func.transformFunction(transformer, generator);
				}
			}

			// Are there any global variables we need to declare?
			string scopeStr = this._currentLine + "-" + this._endLine;
			List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
			foreach (AssignMetrics met in asgMetrics)
			{
				EagleGenerator.TypeEnum typE = met.uniqueType();
				if (typE != EagleGenerator.TypeEnum.VOID)
				{
					AbstractType abstrType = generator.transformType(typE, null, this);

					AbstractExpression initExpr = null;
					if (typE == EagleGenerator.TypeEnum.HASH)
					{
						// Need to create an empty hashmap
						initExpr = generator.newClassCreation(abstrType, null, this);
					}

					// System.err.println("****** Found var " + met._symbolName);
					AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, initExpr, this);
					generator.addStatement(dataStmt, this);
				}
			}

			// Second pass, transform all the data and logic
			foreach (AWK_Element elt in elements._elements)
			{
				AbstractToken which2 = elt.getWhich();
				if (which2 is AWK_Command)
				{
					AWK_Command cmd = (AWK_Command) which2;
					List<AbstractStatement> stmts2 = transformer.transformStatement(generator, cmd.action);
					foreach (AbstractStatement stmt2 in stmts2)
					{
						generator.addStatement(stmt2, cmd);
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}
