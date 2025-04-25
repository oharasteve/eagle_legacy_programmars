// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2016

package com.eagle.programmar.CSharp.Generate;

public class Generate_CSharp_Statement
//		implements Generate_Eagle_Statement<CSharp_Statement, CSharp_Expression,
//				CSharp_Variable, CSharp_Type>
{
//	private Generate_CSharp _target;
//
//	public Generate_CSharp_Statement(Generate_CSharp target)
//	{
//		_target = target;
//	}
//
//	protected CSharp_Statement wrapStatement(AbstractToken token, AbstractToken source)
//	{
//		CSharp_Statement stmt = new CSharp_Statement();
//		stmt.setWhich(token);
//		stmt.setTransformationSource(source);
//		return stmt;
//	}
//
//	@Override
//	public CSharp_Statement createAssignment(String name, CSharp_Expression subscript, AssignmentEnum oper,
//			CSharp_Expression expression, String comment, AbstractToken source)
//	{
//		String newOper;
//		switch (oper)
//		{
//		case EQUALS:
//			newOper = "=";
//			break;
//		case PLUS_EQUALS:
//			newOper = "+=";
//			break;
//		case MINUS_EQUALS:
//			newOper = "-=";
//			break;
//		case TIMES_EQUALS:
//			newOper = "*=";
//			break;
//		case DIVIDE_EQUALS:
//			newOper = "/=";
//			break;
//		default:
//			throw new RuntimeException("Unexpected assigment operator: " + oper.toString());
//		}
//
//		CSharp_AssignmentExpression asgExpr = new CSharp_AssignmentExpression();
//		asgExpr.var = _target._createExpression.createVariableExpression(name, subscript);
//		asgExpr.operator = new CSharp_PunctuationChoice(newOper);
//		asgExpr.expr = expression;
//
//		CSharp_ExpressionStatement exprStmt = new CSharp_ExpressionStatement();
//		CSharp_Expression expr = new CSharp_Expression();
//		expr.setWhich(asgExpr);
//		exprStmt.expr = expr;
//		exprStmt.semicolon = new PunctuationSemicolon();
//		if (comment != null) exprStmt.comment = new CSharp_Comment(comment);
//		return wrapStatement(exprStmt, source);
//	}
//
//	@Override
//	public CSharp_Statement createStatementBlock(ArrayList<CSharp_Statement> statements, AbstractToken source)
//	{
//		CSharp_StatementBlock blockStmt = new CSharp_StatementBlock();
//		blockStmt.statements = new TokenList<CSharp_StatementOrComment>();
//
//		blockStmt.leftBrace = new PunctuationLeftBrace();
//		blockStmt.rightBrace = new PunctuationRightBrace();
//		for (AbstractToken statement : statements)
//		{
//			CSharp_StatementOrComment entry = new CSharp_StatementOrComment();
//			entry.setWhich(statement);
//			entry.setTransformationSource(statement.getTransformationSource());
//			blockStmt.statements.addToken(entry);
//		}
//
//		return wrapStatement(blockStmt, source);
//	}
//
//	@Override
//	public CSharp_Statement createFunctionCall(CSharp_Expression expression, AbstractToken source)
//	{
//		CSharp_ExpressionStatement exprStmt = new CSharp_ExpressionStatement();
//
//		AbstractToken which = expression.getWhich();
//		if (which instanceof CSharp_VariableExpression)
//		{
//			CSharp_VariableExpression var = (CSharp_VariableExpression) which;
//
//			CSharp_MethodInvocation fnCall = new CSharp_MethodInvocation();
//			fnCall.methodName = new CSharp_Variable();
//			fnCall.methodName.firstId = var.variable.firstId;
//			fnCall.leftParen = new PunctuationLeftParen();
//			fnCall.rightParen = new PunctuationRightParen();
//
//			CSharp_Expression newExpr = new CSharp_Expression();
//			newExpr.setWhich(fnCall);
//			exprStmt.expr = newExpr;
//		}
//		else if (which instanceof CSharp_MethodInvocation)
//		{
//			exprStmt.expr = expression;
//		}
//		else
//			throw new RuntimeException("Can't handle " + which + " yet.");
//
//		exprStmt.semicolon = new PunctuationSemicolon();
//		return wrapStatement(exprStmt, source);
//	}
//
//	@Override
//	public CSharp_Statement createData(PRIVACY privacy, int qual, int seq,
//			String varName, TYPES type, String userType,
//			CSharp_Expression expr, String comment, AbstractToken source)
//	{
//		CSharp_Data data = Generate_CSharp_Data_Statement.createData(_target,
//				privacy, qual, seq, varName, type,
//				userType, expr, comment, source);
//		return wrapStatement(data, source);
//	}
//
//	@Override
//	public CSharp_Statement createData(PRIVACY privacy, int qual, int seq, String varName, String typeName,
//			CSharp_Expression expr, String comment, AbstractToken source)
//	{
//		CSharp_Data data = Generate_CSharp_Data_Statement.createData(_target, privacy, qual, seq, varName, typeName,
//				expr, comment, source);
//		return wrapStatement(data, source);
//	}
//
//	@Override
//	public CSharp_Statement createIfStatement1(CSharp_Expression condition,
//			CSharp_Statement ifTrue, CSharp_Statement ifFalse, AbstractToken source)
//	{
//		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
//		return ifStmt.generateIfElse1((CSharp_Expression) condition,
//				(CSharp_Statement) ifTrue, (CSharp_Statement) ifFalse, source);
//	}
//
//	@Override
//	public CSharp_Statement createIfStatement(CSharp_Expression condition,
//			ArrayList<AbstractStatement> ifTrue,
//			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
//	{
//		CSharp_IfStatement ifStmt = new CSharp_IfStatement();
//		return ifStmt.generateIfElse((CSharp_Expression) condition, ifTrue, ifFalse, source);
//	}
//
//	@Override
//	public CSharp_Statement createForLoopStatement(CSharp_Expression initExpression, CSharp_Expression condExpression,
//			CSharp_Expression incrExpression, CSharp_Statement action, AbstractToken source)
//	{
//		CSharp_ForStatement forStmt = new CSharp_ForStatement();
//		return forStmt.generateForLoop1(initExpression, condExpression, incrExpression, action, source);
//	}
//
//	@Override
//	public CSharp_Statement createForRangeStatement(String varName, CSharp_Expression fromExpression,
//			CSharp_Expression toExpression, CSharp_Expression deltaExpression, CSharp_Statement action, AbstractToken source)
//	{
//		CSharp_ForStatement forStmt = new CSharp_ForStatement();
//		return forStmt.generateForRange1(varName, fromExpression, toExpression,
//				deltaExpression, action, source);
//	}
//
//	@Override
//	public CSharp_Statement createDoUntilStatement1(CSharp_Expression condition,
//			CSharp_Statement action, AbstractToken source)
//	{
//		CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
//		return doStmt.generateDoUntil1(condition, action, source);
//	}
//
//	@Override
//	public CSharp_Statement createDoUntilStatement(CSharp_Expression condition,
//			ArrayList<AbstractStatement> actions, AbstractToken source)
//	{
//		CSharp_DoWhileStatement doStmt = new CSharp_DoWhileStatement();
//		return doStmt.generateDoUntil(condition, actions, source);
//	}
//
//	@Override
//	public CSharp_Statement createWhileStatement1(CSharp_Expression condition,
//			CSharp_Statement action, AbstractToken source)
//	{
//		CSharp_WhileStatement whileStmt = new CSharp_WhileStatement();
//		return whileStmt.generateWhile1(condition, action, source);
//	}
//
//	@Override
//	public CSharp_Statement createWhileStatement(CSharp_Expression condition,
//			ArrayList<AbstractStatement> actions, AbstractToken source)
//	{
//		CSharp_WhileStatement whileStmt = new CSharp_WhileStatement();
//		return whileStmt.generateWhile(condition, actions, source);
//	}
//
//	@Override
//	public CSharp_Statement createBreakStatement(AbstractToken source)
//	{
//		CSharp_BreakStatement stmt = new CSharp_BreakStatement();
//		stmt.semicolon = new PunctuationSemicolon();
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public CSharp_Statement createReadLineInt(String var, AbstractToken source)
//	{
//		CSharp_ExpressionStatement stmt = new CSharp_ExpressionStatement();
//		String line = var + " = int.Parse(System.Console.ReadLine());";
//		if (_target.parseLine(stmt, line)) return wrapStatement(stmt, source);
//		throw new RuntimeException("Unable to parse " + line);
//	}
//
//	@Override
//	public CSharp_Statement createReadLine(AbstractToken source)
//	{
//		CSharp_ExpressionStatement stmt = new CSharp_ExpressionStatement();
//		String line = "System.Console.ReadLine();";
//		if (_target.parseLine(stmt, line)) return wrapStatement(stmt, source);
//		throw new RuntimeException("Unable to parse " + line);
//	}
//
//	@Override
//	public CSharp_Statement createQuitStatement(AbstractToken source)
//	{
//		CSharp_Statement stmt = new CSharp_Statement();
//		String line = "System.Environment.Exit(0);";
//		if (_target.parseLine(stmt, line)) return stmt;
//		throw new RuntimeException("Unable to parse " + line);
//	}
//
//	@Override
//	public CSharp_Statement createCloseStatement(String id, AbstractToken source)
//	{
//		CSharp_ExpressionStatement stmt = new CSharp_ExpressionStatement();
//		String line = id + ".close();";
//		if (_target.parseLine(stmt, line)) return wrapStatement(stmt, source);
//		throw new RuntimeException("Unable to parse " + line);
//	}
//
//	@Override
//	public CSharp_Statement createRewriteStatement(String id, String fname, AbstractToken source)
//	{
//		String rewrite = id + " = new java.io.PrintWriter(new java.io.FileWriter(\"" + fname + "\"));";
//		String errmsg = "\"Failed trying to write to '" + fname + "'\"";
//		String line = "try { " + rewrite + " } catch (java.io.IOException ex) { throw new RuntimeException(" + errmsg
//				+ ", ex); }";
//		CSharp_TryStatement stmt = new CSharp_TryStatement();
//		if (_target.parseLine(stmt, line)) return wrapStatement(stmt, source);
//		throw new RuntimeException("Unable to parse " + line);
//	}
//
//	@Override
//	public CSharp_Statement createReturnStatement(CSharp_Expression expr, AbstractToken source)
//	{
//		CSharp_ReturnStatement stmt = new CSharp_ReturnStatement();
//		stmt.expression = expr;
//		stmt.expression.setPresent(true);
//		stmt.semicolon = new PunctuationSemicolon();
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public CSharp_Statement createPrintStatement1(CSharp_Expression line,
//			boolean newline, AbstractToken source)
//	{
//		CSharp_PrintStatement prtStmt = new CSharp_PrintStatement();
//		return prtStmt.generatePrint1(line, newline, source);
//	}
//
//	@Override
//	public CSharp_Statement createPrintStatement(ArrayList<AbstractExpression> pieces,
//			boolean newline, AbstractToken source)
//	{
//		CSharp_PrintStatement prtStmt = new CSharp_PrintStatement();
//		return prtStmt.generatePrint(pieces, newline, source);
//	}
//
//	@Override
//	public CSharp_Statement getCurrentDatetime(String varName, AbstractToken source)
//	{
//		CSharp_Expression expr = new CSharp_Expression();
//		String line = "System.DateTime.Now.TotalMilliseconds";
//		if (_target.parseLine(expr, line))
//		{
//			return createAssignment(varName, null, AssignmentEnum.EQUALS, expr, null, source);
//		}
//		throw new RuntimeException("Unable to parse " + line);
//	}
}
