// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Collections.Generic;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 6, 2011

namespace com.eagle.programmar.PLI
{

	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using EagleInterpreter = com.eagle.interpret.EagleInterpreter;
	using EagleRunnable = com.eagle.interpret.EagleRunnable;
	using AssignMetrics = com.eagle.metrics.AssignMetrics;
	using PLI_StatementOrComment = com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
	using PLI_PercentStatement = com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
	using PLI_Comment = com.eagle.programmar.PLI.Terminals.PLI_Comment;
	using AbstractToken = com.eagle.tokens.AbstractToken;
	using TokenChooser = com.eagle.tokens.TokenChooser;
	using TokenList = com.eagle.tokens.TokenList;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using EagleGenerator = com.eagle.transform.EagleGenerator;
	using TypeEnum = com.eagle.transform.EagleGenerator.TypeEnum;
	using EagleTransformableProgram = com.eagle.transform.EagleTransformableProgram;
	using EagleTransformer = com.eagle.transform.EagleTransformer;

	public class PLI_Program : AbstractLanguage, EagleRunnable, EagleTransformableProgram
	{
		public const string PLI = "PL/I";

		public PLI_Program() : base(PLI, new PLI_Syntax())
		{
		}

		public override string DocRoot
		{
			get
			{
				return "http://publibfp.boulder.ibm.com/cgi-bin/bookmgr/BOOKS/IBM3L101/";
			}
		}

		// Components of a PL/I Program
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @S(10) @OPT TokenList<PLI_Element> elements;
		public  OPT;

		public class PLI_Element : TokenChooser
		{
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Comment XXcomment;
			public PLI_Comment XXcomment;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_DeclareGeneric XXdeclareGeneric;
			public PLI_DeclareGeneric XXdeclareGeneric;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Procedure XXprocedure;
			public PLI_Procedure XXprocedure;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_Declaration XXdeclaration;
			public PLI_Declaration XXdeclaration;
// JAVA TO C# CONVERTER TASK: Most Java annotations will not have direct .NET equivalent attributes:
// ORIGINAL LINE: public @CHOICE PLI_PercentStatement XXpercentStmt;
			public PLI_PercentStatement XXpercentStmt;
		}

		public override void interpret(EagleInterpreter interpreter)
		{
			// First pass, just collect all the FUNCTION definitions
			foreach (PLI_Element element in elements._elements)
			{
				AbstractToken which = element.getWhich();
				if (which is PLI_Procedure)
				{
					PLI_Procedure proc = (PLI_Procedure) which;
					interpreter.addFunction(proc.id1.getValue(), proc);

					// Look for procs inside the outer proc
					foreach (PLI_StatementOrComment stmt1 in proc.statements._elements)
					{
						AbstractToken which2 = stmt1.getWhich();
						if (which2 is PLI_Statement)
						{
							PLI_Statement stmt2 = (PLI_Statement) which2;
							AbstractToken which3 = stmt2.getWhich();
							if (which3 is PLI_Procedure)
							{
								PLI_Procedure proc3 = (PLI_Procedure) which3;
								interpreter.addFunction(proc3.id1.getValue(), proc3);
							}
						}
					}
				}
			}

			// Second pass, execute the program
			foreach (PLI_Element element in elements._elements)
			{
				interpreter.tryToInterpret(element);
			}
		}

		public override AbstractLanguage transformProgram(EagleTransformer transformer, EagleGenerator<AbstractStatement, AbstractExpression, AbstractVariable, AbstractType> generator)
		{
			// First pass, transform all the inner procedures
			foreach (PLI_Element element in elements._elements)
			{
				AbstractToken which3 = element.getWhich();
				if (which3 is PLI_Procedure)
				{
					PLI_Procedure mainProc = (PLI_Procedure) which3;

					// Look for procs inside the outer proc
					foreach (PLI_StatementOrComment stmt2 in mainProc.statements._elements)
					{
						AbstractToken which4 = stmt2.getWhich();
						if (which4 is PLI_Statement)
						{
							PLI_Statement stmt4 = (PLI_Statement) which4;
							AbstractToken which5 = stmt4.getWhich();
							if (which5 is PLI_Procedure)
							{
								PLI_Procedure proc5 = (PLI_Procedure) which5;
								proc5.transformFunction(transformer, generator);
							}
						}
					}
				}
			}

			// Second pass, collect global variables
			foreach (PLI_Element element in elements._elements)
			{
				AbstractToken which1 = element.getWhich();
				if (which1 is PLI_Procedure)
				{
					PLI_Procedure proc = (PLI_Procedure) which1;

					// Are there any global variables we need to declare?
					string scopeStr = proc.Scope.getScopeName();
					List<AssignMetrics> asgMetrics = transformer._metrics.findVarsInScope(scopeStr);
					foreach (AssignMetrics met in asgMetrics)
					{
						EagleGenerator.TypeEnum typE = met.uniqueType();
						if (typE != EagleGenerator.TypeEnum.VOID)
						{
							AbstractType abstrType = generator.transformType(typE, null, this);

							// System.err.println("****** Found var " + met._symbolName);
							AbstractStatement dataStmt = generator.newDataDeclaration(false, met._symbolName, null, abstrType, null, this);
							generator.addStatement(dataStmt, this);
						}
					}
				}
			}

			// Third pass, transform the MAIN Function definition
			foreach (PLI_Element element in elements._elements)
			{
				AbstractToken which1 = element.getWhich();
				if (which1 is PLI_Procedure)
				{
					PLI_Procedure proc = (PLI_Procedure) which1;
					// System.err.println("*** Found Main Procedure " + proc.id1.getValue());
					foreach (PLI_StatementOrComment stmtOrComment in proc.statements._elements)
					{
						AbstractToken which2 = stmtOrComment.getWhich();
						ICollection<AbstractStatement> newStmts = transformer.transformStatement(generator, which2);
						if (newStmts != null)
						{
							foreach (AbstractStatement newStmt in newStmts)
							{
								generator.addStatement(newStmt, stmtOrComment);
							}
						}
					}
				}
			}

			return generator.getTransfomedProgram();
		}
	}

}
