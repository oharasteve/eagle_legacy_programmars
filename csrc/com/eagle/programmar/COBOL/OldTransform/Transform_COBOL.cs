// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

using System.Text;

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2010

namespace com.eagle.programmar.COBOL.OldTransform
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using Old_Generate_Eagle = com.eagle.oldGenerate.Old_Generate_Eagle;
	using COBOL_Expression = com.eagle.programmar.COBOL.COBOL_Expression;
	using COBOL_Program_Complete = com.eagle.programmar.COBOL.COBOL_Program_Complete;
	using COBOL_Statement = com.eagle.programmar.COBOL.COBOL_Statement;
	using COBOL_Paragraph_Definition = com.eagle.programmar.COBOL.Symbols.COBOL_Paragraph_Definition;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using Transform_Eagle = com.eagle.transform.Transform_Eagle;

	public class Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> : Transform_Eagle where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		private void InitializeInstanceFields()
		{
			_transCobolStmt = new Transform_COBOL_Statement<Lang, Cls, Stmt, Meth, Expr, Var, Type>(this);
			_transCobolExpr = new Transform_COBOL_Expression<Lang, Cls, Stmt, Meth, Expr, Var, Type>(this);
			_transCobolData = new Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type>(this);
		}

		public Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> _target;

		// Source side
		public Transform_COBOL_Statement<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transCobolStmt;
		public Transform_COBOL_Expression<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transCobolExpr;
		public Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type> _transCobolData;

		public Transform_COBOL(Old_Generate_Eagle<Lang, Cls, Stmt, Meth, Expr, Var, Type> target)
		{
			InitializeInstanceFields();
			_target = target;
		}

		public override void transformFromXML(AbstractLanguage pgm, string sourceName, string targetName)
		{
			COBOL_Program_Complete program = (COBOL_Program_Complete) pgm;

			// Add in a main program
			_target.createEmptyClass(targetName);

			Transform_COBOL_Identification<Lang, Cls, Stmt, Meth, Expr, Var, Type> transId = new Transform_COBOL_Identification<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
			transId.transformIdentificationDivision(this, program);

			Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type> transData = new Transform_COBOL_Data<Lang, Cls, Stmt, Meth, Expr, Var, Type>(this);
			transData.transformDataDivision(program);

			Transform_COBOL_Procedure<Lang, Cls, Stmt, Meth, Expr, Var, Type> transProc = new Transform_COBOL_Procedure<Lang, Cls, Stmt, Meth, Expr, Var, Type>();
			transProc.transformProcedureDivision(this, program);

			// Wow, this is pretty nasty!
			COBOL_Paragraph_Definition source = program.procedureDiv.sections.first().paragraphs.first().paragraphHeaders.first().paragraphName;
			string firstPara = source.ToString();
			_target.addMain(sourceName, firstPara);
		}

		// Change "FIND-FIXED-ACCT" to "findFixedAcct"
		public static string fixName(string name)
		{
			if (name.IndexOf('-') < 0)
			{
				return name;
			}

			StringBuilder sb = new StringBuilder();

			char firstCh = name[0];
			if (char.IsDigit(firstCh))
			{
				sb.Append('_');
			}

			bool foundDash = false;
			foreach (char ch in name.ToCharArray())
			{
				if (ch == '-')
				{
					foundDash = true;
				}
				else
				{
					if (foundDash)
					{
						sb.Append(char.ToUpper(ch));
						foundDash = false;
					}
					else
					{
						sb.Append(char.ToLower(ch));
					}
				}
			}
			return sb.ToString();
		}

		public override Expr transformExpression(AbstractExpression expr)
		{
			Expr newExpr = _transCobolExpr.transformExpression((COBOL_Expression) expr);
			return newExpr;
		}

		public override Stmt transformStatement(AbstractStatement stmt)
		{
			Stmt newStmt = _transCobolStmt.transformStatement((COBOL_Statement) stmt);
			return newStmt;
		}
	}

}
