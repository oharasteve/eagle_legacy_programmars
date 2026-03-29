// ====================================================================================================
// Produced by the Free Edition of Java to C# Converter.
// Purchase a Premium Edition license at:
// https://www.tangiblesoftwaresolutions.com/order/order-java-to-csharp.html
// ====================================================================================================

// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2016

namespace com.eagle.programmar.CSharp.Generate_Unused
{
	public class Generate_CSharp_Expression
	{
	//		implements Generate_Eagle_Expression<CSharp_Expression, CSharp_Variable, CSharp_Type>
	//	// private Generate_CSharp _target;
	//
	//	public Generate_CSharp_Expression(Generate_CSharp target)
	//	{
	//		// _target = target;
	//	}
	//
	//	private static CSharp_Expression wrapExpression(AbstractToken piece)
	//	{
	//		CSharp_Expression expr = new CSharp_Expression();
	//		expr.setWhich(piece);
	//		return expr;
	//	}
	//	
	//	//
	//	// These are all pass-through functions. Not much logic here
	//	//
	//
	//	@Override
	//	public CSharp_Expression createBuiltIn(BuiltInEnum builtin)
	//	{
	//		CSharp_BuiltIn builtinExpr = new CSharp_BuiltIn();
	//		return builtinExpr.generateBuiltIn(builtin, builtinExpr);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createNumber(int num)
	//	{
	//		CSharp_Number number = new CSharp_Number();
	//		return wrapExpression(number.generateNumber(Integer.toString(num), null));
	//	}
	//
	//	@Override
	//	public CSharp_Expression createNumber(String txt)
	//	{
	//		CSharp_Number number = new CSharp_Number();
	//		return wrapExpression(number.generateNumber(txt, null));
	//	}
	//
	//	@Override
	//	public CSharp_Expression createHexNumber(String txt)
	//	{
	//		CSharp_HexNumber number = new CSharp_HexNumber();
	//		return wrapExpression(number.generateHexNumber(txt, null));
	//	}
	//
	//	@Override
	//	public CSharp_Expression createLiteral(String txt)
	//	{
	//		CSharp_Literal lit = new CSharp_Literal();
	//		return wrapExpression(lit.generateLiteral(txt, null));
	//	}
	//
	//	@Override
	//	public CSharp_Expression createCharacter(String txt)
	//	{
	//		CSharp_Character_Literal lit = new CSharp_Character_Literal();
	//		return wrapExpression(lit.generateCharLiteral(txt, null));
	//	}
	//
	//	@Override
	//	public CSharp_Variable createVariable(String name)
	//	{
	//		return CSharp_Variable.newVariable(name);
	//	}
	//	
	//	@Override
	//	public CSharp_Expression createVariableExpression(String name, CSharp_Expression subscrExpr)
	//	{
	//		CSharp_VariableExpression varExp = new CSharp_VariableExpression();
	//		return varExp.generateVarExpr(name, subscrExpr, subscrExpr);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createMethodCall(CSharp_Variable name, Collection<AbstractExpression> args)
	//	{
	//		CSharp_MethodInvocation methodCall = new CSharp_MethodInvocation();
	//		return methodCall.generateInvocation(name, args, null);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createNew(CSharp_Type typeName, Collection<AbstractExpression> args)
	//	{
	//		CSharp_ClassCreationExpression newInstance = new CSharp_ClassCreationExpression();
	//		return newInstance.generateCreation(typeName, args, null);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createNot(CSharp_Expression expr, AbstractToken source)
	//	{
	//		CSharp_LogicalNotExpression notExpr = new CSharp_LogicalNotExpression();
	//		return notExpr.generateLogicalNot(expr, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createParens(CSharp_Expression expr, AbstractToken source)
	//	{
	//		// Don't add parens if we don't need them
	//		AbstractToken which = expr.getWhich();
	//		if (which instanceof CSharp_ParenthesizedExpression || which instanceof CSharp_Number
	//				|| which instanceof CSharp_HexNumber || which instanceof CSharp_Literal
	//				|| which instanceof CSharp_Character_Literal || which instanceof CSharp_VariableExpression)
	//		{
	//			return expr;
	//		}
	//
	//		CSharp_ParenthesizedExpression parenExpr = new CSharp_ParenthesizedExpression();
	//		return parenExpr.generateParentheses(expr, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createNegative(NegativeEnum sign,
	//			CSharp_Expression expr, AbstractToken source)
	//	{
	//		CSharp_NegativeExpression negExp = new CSharp_NegativeExpression();
	//		return negExp.generateNegative(sign, expr, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createAdditive(CSharp_Expression left,
	//			AdditiveEnum add, CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_AdditiveExpression addExp = new CSharp_AdditiveExpression();
	//		return addExp.generateAdditive(left, add, right, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createMultiplicative(CSharp_Expression left,
	//			MultiplicativeEnum mult, CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_MultiplicativeExpression mulExp = new CSharp_MultiplicativeExpression();
	//		return mulExp.generateMultiplicative(left, mult, right, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createRelational(CSharp_Expression left,
	//			RelationalEnum relational, CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_RelationalExpression relExp = new CSharp_RelationalExpression();
	//		return relExp.generateRelational(left, relational, right, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createShift(CSharp_Expression left, ShiftEnum shift,
	//			CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_ShiftExpression shiftExpr = new CSharp_ShiftExpression();
	//		return shiftExpr.generateShift(left, shift, right, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createAnd(CSharp_Expression left,
	//			CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_LogicalAndExpression andExpr = new CSharp_LogicalAndExpression();
	//		return andExpr.generateLogicalAnd(left, right, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createOr(CSharp_Expression left,
	//			CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_LogicalOrExpression orExpr = new CSharp_LogicalOrExpression();
	//		return orExpr.generateLogicalOr(left, LogicalOrEnum.OR, right, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createXor(CSharp_Expression left,
	//			CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_LogicalOrExpression xorExpr = new CSharp_LogicalOrExpression();
	//		return xorExpr.generateLogicalOr(left, LogicalOrEnum.XOR, right, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createAssignment(String varName, AssignmentEnum asg,
	//			CSharp_Expression expr, AbstractToken source)
	//	{
	//		CSharp_VariableExpression varExp = new CSharp_VariableExpression();
	//		return varExp.generateVarExpr(varName, expr, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createPostIncrement(String varName,
	//			IncrementEnum incr, AbstractToken source)
	//	{
	//		CSharp_Variable var = CSharp_Variable.newVariable(varName);
	//		CSharp_PostIncrementExpression postExpr = new CSharp_PostIncrementExpression();
	//		return postExpr.generateIncrement(var, incr, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createPreIncrement(String varName,
	//		IncrementEnum incr, AbstractToken source)
	//	{
	//		CSharp_Variable var = CSharp_Variable.newVariable(varName);
	//		CSharp_PreIncrementExpression postExpr = new CSharp_PreIncrementExpression();
	//		return postExpr.generateIncrement(var, incr, source);
	//	}
	//
	//	@Override
	//	public CSharp_Expression createSubfield(CSharp_Expression left,
	//			CSharp_Expression right, AbstractToken source)
	//	{
	//		CSharp_SubfieldExpression fldExpr = new CSharp_SubfieldExpression();
	//		return fldExpr.generateSubfield(left, right, source);
	//	}
	}

}
