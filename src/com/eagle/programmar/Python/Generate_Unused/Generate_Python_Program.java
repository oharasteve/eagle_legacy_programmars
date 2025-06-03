// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, May 17, 2017

package com.eagle.programmar.Python.Generate_Unused;

public class Generate_Python_Program
//		implements Generate_Eagle_Program<Python_Program, Python_ClassDeclaration, Python_Statement>
{
//	private Generate_Python_Statement _creator = new Generate_Python_Statement(null);
//
//	@Override
//	public void addProgramComment(Python_Program pgm, String comment, AbstractToken source)
//	{
//		Python_Statement stmt = _creator.createCommentStatement(comment, source);
//		if (pgm.entries == null) pgm.entries = new TokenList<Python_Statement>();
//		pgm.entries.addToken(stmt);
//	}
//
//	@Override
//	public void addImport(Python_Program pgm, String imp, boolean useStar, AbstractToken source)
//	{
//		Python_Statement importStmt = _creator.createImportStatement(imp, source);
//		pgm.entries.insert(0, importStmt);
//	}
//
//	@Override
//	public void setPackage(Python_Program pgm, String pkg, AbstractToken source)
//	{
//		throw new RuntimeException("need to implement");
//	}
//
//	@Override
//	public void addClass(Python_Program pgm, Python_ClassDeclaration cls)
//	{
//		Python2_Simple_Statement simple = new Python2_Simple_Statement();
//		simple.setWhich(cls);
//		Python_SameLineStatement statementList = new Python_SameLineStatement();
//		statementList.statements = new SeparatedList<Python_Simple_Statement, PunctuationSemicolon>();
//		statementList.statements.addPrimaryElement(simple);
//		Python_Statement stmt = new Python_Statement();
//		stmt.statementOrComment = new Python_StatementOrComment();
//		stmt.statementOrComment.setWhich(statementList);
//
//		if (pgm.entries == null) pgm.entries = new TokenList<Python_Statement>();
//		pgm.entries.addToken(stmt);
//	}
//
//	@Override
//	public void addProgramStatement(Python_Program pgm, Python_Statement statement)
//	{
//		if (pgm.entries == null) pgm.entries = new TokenList<Python_Statement>();
//		pgm.entries.addToken(statement);
//	}
}
