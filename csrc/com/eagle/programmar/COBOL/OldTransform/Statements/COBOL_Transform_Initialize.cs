// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 2, 2011

namespace com.eagle.programmar.COBOL.OldTransform.Statements
{
	using AbstractLanguage = com.eagle.core.AbstractLanguage;
	using com.eagle.programmar.COBOL.OldTransform;
	using COBOL_InitializeStatement = com.eagle.programmar.COBOL.Statements.COBOL_InitializeStatement;
	using AbstractClass = com.eagle.tokens.interfaces.AbstractClass;
	using AbstractExpression = com.eagle.tokens.interfaces.AbstractExpression;
	using AbstractMethod = com.eagle.tokens.interfaces.AbstractMethod;
	using AbstractStatement = com.eagle.tokens.interfaces.AbstractStatement;
	using AbstractType = com.eagle.tokens.interfaces.AbstractType;
	using AbstractVariable = com.eagle.tokens.interfaces.AbstractVariable;
	using AssignmentEnum = com.eagle.transform.EagleGenerator.AssignmentEnum;

	public class COBOL_Transform_Initialize<Lang, Cls, Stmt, Meth, Expr, Var, Type> where Lang : com.eagle.core.AbstractLanguage where Cls : com.eagle.tokens.interfaces.AbstractClass where Stmt : com.eagle.tokens.interfaces.AbstractStatement where Meth : com.eagle.tokens.interfaces.AbstractMethod where Expr : com.eagle.tokens.interfaces.AbstractExpression where Var : com.eagle.tokens.interfaces.AbstractVariable where Type : com.eagle.tokens.interfaces.AbstractType
	{
		public virtual Stmt transform(Transform_COBOL<Lang, Cls, Stmt, Meth, Expr, Var, Type> trans, COBOL_InitializeStatement initializeStatement)
		{
			Expr zero = trans._target._createExpression.createNumber(0);
			string varName = trans._transCobolData.getFullVariableName(initializeStatement.what, null);
			Stmt asgStatement = trans._target._createStatement.createAssignment(varName, null, AssignmentEnum.EQUALS, zero, null, initializeStatement);
			return asgStatement;
		}
	}

}
