// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 29, 2016

package com.eagle.programmar.Java.Generate;

public class Generate_Java_Statement
//		implements Generate_Eagle_Statement<Java_Statement, Java_Expression, Java_Variable, Java_Type>
{
//	private Generate_Java _target;
//
//	public Generate_Java_Statement(Generate_Java target)
//	{
//		_target = target;
//	}
//
//	protected Java_Statement wrapStatement(AbstractToken token, AbstractToken source)
//	{
//		Java_Statement stmt = new Java_Statement();
//		stmt.setWhich(token);
//		stmt.setTransformationSource(source);
//		return stmt;
//	}
//
//	@Override
//	public Java_Statement createAssignment(String name, Java_Expression subscript, AssignmentEnum oper,
//			Java_Expression expression, String comment, AbstractToken source)
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
//		Java_AssignmentExpression asgExpr = new Java_AssignmentExpression();
//		asgExpr.var = _target._createExpression.createVariableExpression(name, subscript);
//		asgExpr.operator = new Java_PunctuationChoice(newOper);
//		asgExpr.expr = expression;
//
//		Java_ExpressionStatement exprStmt = new Java_ExpressionStatement();
//		Java_Expression expr = new Java_Expression();
//		expr.setWhich(asgExpr);
//		exprStmt.expr = expr;
//		exprStmt.semicolon = new PunctuationSemicolon();
//		if (comment != null) exprStmt.comment = new Java_Comment(comment);
//		return wrapStatement(exprStmt, source);
//	}
//
//	@Override
//	public Java_Statement createStatementBlock(ArrayList<Java_Statement> statements, AbstractToken source)
//	{
//		Java_StatementBlock blockStmt = new Java_StatementBlock();
//		blockStmt.statements = new TokenList<Java_StatementOrComment>();
//
//		blockStmt.leftBrace = new PunctuationLeftBrace();
//		blockStmt.rightBrace = new PunctuationRightBrace();
//		for (AbstractToken statement : statements)
//		{
//			Java_StatementOrComment entry = new Java_StatementOrComment();
//			entry.setWhich(statement);
//			entry.setTransformationSource(statement.getTransformationSource());
//			blockStmt.statements.addToken(entry);
//		}
//
//		return wrapStatement(blockStmt, source);
//	}
//
//	@Override
//	public Java_Statement createFunctionCall(Java_Expression expression, AbstractToken source)
//	{
//		Java_ExpressionStatement exprStmt = new Java_ExpressionStatement();
//
//		AbstractToken which = expression.getWhich();
//		if (which instanceof Java_VariableExpression)
//		{
//			Java_VariableExpression var = (Java_VariableExpression) which;
//
//			Java_MethodInvocation fnCall = new Java_MethodInvocation();
//			fnCall.methodName = var.variable;
//			fnCall.leftParen = new PunctuationLeftParen();
//			fnCall.rightParen = new PunctuationRightParen();
//
//			Java_Expression newExpr = new Java_Expression();
//			newExpr.setWhich(fnCall);
//			exprStmt.expr = newExpr;
//		}
//		else if (which instanceof Java_MethodInvocation)
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
//	public Java_Statement createData(PRIVACY privacy, int qual, int seq, String varName, TYPES type, String userType,
//			Java_Expression expr, String comment, AbstractToken source)
//	{
//		Java_Data data = Generate_Java_Data_Statement.createData(_target, privacy, qual, seq, varName, type, userType,
//				expr, comment, source);
//		return wrapStatement(data, source);
//	}
//
//	@Override
//	public Java_Statement createData(PRIVACY privacy, int qual, int seq, String varName, String typeName,
//			Java_Expression expr, String comment, AbstractToken source)
//	{
//		Java_Data data = Generate_Java_Data_Statement.createData(_target, privacy, qual, seq, varName, typeName, expr,
//				comment, source);
//		return wrapStatement(data, source);
//	}
//
//	@Override
//	public Java_Statement createIfStatement1(Java_Expression condition,
//			Java_Statement ifTrue, Java_Statement ifFalse, AbstractToken source)
//	{
//		Java_IfStatement ifStmt = new Java_IfStatement();
//		return ifStmt.generateIfElse1(condition, ifTrue, ifFalse, source);
//	}
//	
//	@Override
//	public Java_Statement createIfStatement(Java_Expression condition,
//			ArrayList<AbstractStatement> ifTrue,
//			ArrayList<AbstractStatement> ifFalse, AbstractToken source)
//	{
//		Java_IfStatement ifStmt = new Java_IfStatement();
//		return ifStmt.generateIfElse(condition, ifTrue, ifFalse, source);
//	}
//
//	@Override
//	public Java_Statement createForLoopStatement(Java_Expression initExpression, Java_Expression condExpression,
//			Java_Expression incrExpression, Java_Statement action, AbstractToken source)
//	{
//		Java_ForStatement forStmt = new Java_ForStatement();
//		return forStmt.generateForLoop1(initExpression, condExpression, incrExpression, action, source);
//	}
//
//	@Override
//	public Java_Statement createForRangeStatement(String varName, Java_Expression fromExpression,
//			Java_Expression toExpression, Java_Expression deltaExpression, Java_Statement action, AbstractToken source)
//	{
//		Java_ForStatement forStmt = new Java_ForStatement();
//		return forStmt.generateForRange1(varName, fromExpression, toExpression,
//				deltaExpression, action, source);
//	}
//
//	@Override
//	public Java_Statement createDoUntilStatement1(Java_Expression condition,
//			Java_Statement action, AbstractToken source)
//	{
//		Java_DoWhileStatement whileStmt = new Java_DoWhileStatement();
//		return whileStmt.generateDoUntil1(condition, action, source);
//	}
//
//	@Override
//	public Java_Statement createDoUntilStatement(Java_Expression condition,
//			ArrayList<AbstractStatement> actions, AbstractToken source)
//	{
//		Java_DoWhileStatement whileStmt = new Java_DoWhileStatement();
//		return whileStmt.generateDoUntil(condition, actions, source);
//	}
//
//	@Override
//	public Java_Statement createWhileStatement1(Java_Expression condition,
//			Java_Statement action, AbstractToken source)
//	{
//		Java_WhileStatement whileStmt = new Java_WhileStatement();
//		return whileStmt.generateWhile1(condition, action, source);
//	}
//
//	@Override
//	public Java_Statement createWhileStatement(Java_Expression condition,
//			ArrayList<AbstractStatement> actions, AbstractToken source)
//	{
//		Java_WhileStatement whileStmt = new Java_WhileStatement();
//		return whileStmt.generateWhile(condition, actions, source);
//	}
//
//	@Override
//	public Java_Statement createBreakStatement(AbstractToken source)
//	{
//		Java_BreakStatement stmt = new Java_BreakStatement();
//		stmt.semicolon = new PunctuationSemicolon();
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public Java_Statement createReadLineInt(String var, AbstractToken source)
//	{
//		_target._needsScanner = true;
//		Java_ExpressionStatement stmt = new Java_ExpressionStatement();
//		// String line = var + " = Integer.parseInt(System.console().readLine());"; //
//		// Doesn't work when stdin is redirected
//		String line = var + " = _scanner.nextInt();";
//		if (!_target.parseLine(stmt, line)) return null;
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public Java_Statement createReadLine(AbstractToken source)
//	{
//		_target._needsScanner = true;
//		Java_ExpressionStatement stmt = new Java_ExpressionStatement();
//		String line = "_scanner.nextLine();";
//		if (!_target.parseLine(stmt, line)) return null;
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public Java_Statement createQuitStatement(AbstractToken source)
//	{
//		Java_ExpressionStatement stmt = new Java_ExpressionStatement();
//		String line = "System.exit(0);";
//		if (!_target.parseLine(stmt, line)) return null;
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public Java_Statement createCloseStatement(String id, AbstractToken source)
//	{
//		Java_ExpressionStatement stmt = new Java_ExpressionStatement();
//		String line = id + ".close();";
//		if (!_target.parseLine(stmt, line)) return null;
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public Java_Statement createRewriteStatement(String id, String fname, AbstractToken source)
//	{
//		String rewrite = id + " = new java.io.PrintWriter(new java.io.FileWriter(\"" + fname + "\"));";
//		String errmsg = "\"Failed trying to write to '" + fname + "'\"";
//		String line = "try { " + rewrite + " } catch (java.io.IOException ex) { throw new RuntimeException(" + errmsg
//				+ ", ex); }";
//		Java_TryStatement stmt = new Java_TryStatement();
//		if (!_target.parseLine(stmt, line)) return null;
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public Java_Statement createReturnStatement(Java_Expression expr, AbstractToken source)
//	{
//		Java_ReturnStatement stmt = new Java_ReturnStatement();
//		stmt.expression = expr;
//		stmt.expression.setPresent(true);
//		stmt.semicolon = new PunctuationSemicolon();
//		stmt.setTransformationSource(source);
//		return wrapStatement(stmt, source);
//	}
//
//	@Override
//	public Java_Statement createPrintStatement1(Java_Expression line,
//			boolean newline, AbstractToken source)
//	{
//		Java_PrintStatement prtStmt = new Java_PrintStatement();
//		return prtStmt.generatePrint1(line, newline, source);
//	}
//
//	@Override
//	public Java_Statement createPrintStatement(ArrayList<AbstractExpression> pieces,
//			boolean newline, AbstractToken source)
//	{
//		Java_PrintStatement prtStmt = new Java_PrintStatement();
//		return prtStmt.generatePrint(pieces, newline, source);
//	}
//
//	@Override
//	public Java_Statement getCurrentDatetime(String varName, AbstractToken source)
//	{
//		Java_Expression expr = new Java_Expression();
//		String line = "System.currentTimeMillis()";
//		if (!_target.parseLine(expr, line)) return null;
//		return createAssignment(varName, null, AssignmentEnum.EQUALS, expr, null, source);
//	}
}
