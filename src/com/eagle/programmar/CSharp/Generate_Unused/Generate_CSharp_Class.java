// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2016

package com.eagle.programmar.CSharp.Generate_Unused;

public class Generate_CSharp_Class
//		implements Generate_Eagle_Class<CSharp_Class, CSharp_Method, CSharp_Statement, CSharp_Type>
{
//	public Generate_CSharp_Class(Generate_CSharp target)
//	{
//	}
//
//	private static void addClassModifier(CSharp_Class cls, String mod)
//	{
//		if (cls.modifiers == null)
//		{
//			cls.modifiers = new TokenList<CSharp_ClassModifier>();
//			cls.modifiers.setPresent(true);
//		}
//		CSharp_ClassModifier modifier = new CSharp_ClassModifier();
//		modifier.modifier.setValue(mod);
//		cls.modifiers.addToken(modifier);
//	}
//	
//	private static void addClassPrivacy(CSharp_Class cls, PRIVACY privacy)
//	{
//		switch (privacy)
//		{
//		case PUBLIC:
//			addClassModifier(cls, "public");
//			break;
//		case PRIVATE:
//			addClassModifier(cls, "private");
//			break;
//		case PROTECTED:
//			addClassModifier(cls, "protected");
//			addClassModifier(cls, "internal");
//			break;
//		case PACKAGE:
//			break;
//		default:
//			throw new RuntimeException("Can't handle class privacy: " + privacy);
//		}
//	}
//	
//	@Override
//	public CSharp_Class createNewClass(PRIVACY privacy, String className)
//	{
//		CSharp_Class cls = new CSharp_Class();
//		addClassPrivacy(cls, privacy);
//		
//		cls.className = new CSharp_Class_Definition();
//		cls.className.setValue(className);
//		cls.leftBrace = new PunctuationLeftBrace();
//		cls.rightBrace = new PunctuationRightBrace();
//		return cls;
//	}
//	
//	@Override
//	public void addClassComment(CSharp_Class parentClass, String comment, AbstractToken source)
//	{
//		CSharp_Comment comm = new CSharp_Comment("// " + comment, true);
//
//		CSharp_ClassElement elt = new CSharp_ClassElement();
//		elt.setWhich(comm);
//		elt.setTransformationSource(source);
//
//		if (parentClass.elements == null) parentClass.elements = new TokenList<CSharp_ClassElement>();
//		parentClass.elements.addToken(elt);
//	}
//
//	@Override
//	public void addClassData(CSharp_Class parentClass, CSharp_Statement dataStmt)
//	{
//		CSharp_ClassElement elt = new CSharp_ClassElement();
//		elt.setWhich(dataStmt);
//
//		if (parentClass.elements == null) parentClass.elements = new TokenList<CSharp_ClassElement>();
//		parentClass.elements.addToken(elt);
//	}
//
//	@Override
//	public void addMethod(CSharp_Class parentClass, CSharp_Method method)
//	{
//		CSharp_ClassElement elt = new CSharp_ClassElement();
//		elt.setWhich(method);
//
//		if (parentClass.elements == null) parentClass.elements = new TokenList<CSharp_ClassElement>();
//		parentClass.elements.addToken(elt);
//	}
//
//	private static void addConstructorModifier(CSharp_Constructor cons, String mod)
//	{
//		if (cons.modifiers == null)
//		{
//			cons.modifiers = new TokenList<CSharp_MethodModifier>();
//			cons.modifiers.setPresent(true);
//		}
//		CSharp_MethodModifier modifier = new CSharp_MethodModifier();
//		modifier.modifier.setValue(mod);
//		cons.modifiers.addToken(modifier);
//	}
//	
//	@Override
//	public void addConstructor(CSharp_Class parentClass, String className, Collection<AbstractExpression> args,
//			AbstractToken source)
//	{
//		CSharp_Constructor cons = new CSharp_Constructor();
//		cons.setTransformationSource(source);
//		cons.constructorName = new CSharp_Current_Class_Reference();
//		cons.constructorName.setValue(className);
//		cons.leftParen = new PunctuationLeftParen();
//		cons.rightParen = new PunctuationRightParen();
//
//		// Always set public
//		addConstructorModifier(cons, "public");
//
//		// Create the constructor body
//		cons.body = new CSharp_MethodBody();
//		CSharp_MethodImplementation impl = new CSharp_MethodImplementation();
//		impl.block = new CSharp_StatementBlock();
//		impl.block.leftBrace = new PunctuationLeftBrace();
//		impl.block.rightBrace = new PunctuationRightBrace();
//		cons.body.setWhich(impl);
//
//		// If any args are given, create a call to super() with them
//		if (args != null && args.size() > 0)
//		{
//			CSharp_MethodInvocation func = new CSharp_MethodInvocation();
//			func.methodName.firstId = CSharp_Variable.newVariable("super").firstId;
//			func.leftParen = new PunctuationLeftParen();
//			func.argList = Generate_CSharp.createArgumentList(args);
//			func.argList.setPresent(true);
//			func.rightParen = new PunctuationRightParen();
//
//			CSharp_ExpressionStatement exprStmt = new CSharp_ExpressionStatement();
//			exprStmt.expr = new CSharp_Expression();
//			exprStmt.expr.setWhich(func);
//			exprStmt.semicolon = new PunctuationSemicolon();
//
//			CSharp_Statement stmt = new CSharp_Statement();
//			stmt.setWhich(exprStmt);
//
//			CSharp_StatementOrComment stmtOrComment = new CSharp_StatementOrComment();
//			stmtOrComment.setWhich(stmt);
//			if (impl.block.statements == null) impl.block.statements = new TokenList<CSharp_StatementOrComment>();
//			impl.block.statements.addToken(stmtOrComment);
//		}
//
//		// Add it to the parent class
//		CSharp_ClassElement elt = new CSharp_ClassElement();
//		elt.setWhich(cons);
//
//		if (parentClass.elements == null) parentClass.elements = new TokenList<CSharp_ClassElement>();
//		parentClass.elements.addToken(elt);
//	}
//
//	@Override
//	public CSharp_Class addInnerClass(PRIVACY privacy, CSharp_Class parentClass, String className,
//			CLASS_QUALIFIERS qual, AbstractToken source)
//	{
//		// Create the new inner class
//		CSharp_Class newClass = new CSharp_Class();
//		newClass.className = new CSharp_Class_Definition();
//		newClass.className.setValue(className);
//		newClass.setTransformationSource(source);
//
//		// Set public or private
//		addClassPrivacy(newClass, privacy);
//
//		// If parentClass is null, that means it is the main class
//		if (parentClass != null)
//		{
//			// Make sure inner classes are static
//			addClassModifier(newClass, "static");
//		}
//
//		newClass.leftBrace = new PunctuationLeftBrace();
//		newClass.rightBrace = new PunctuationRightBrace();
//
//		CSharp_Statement stmt = new CSharp_Statement();
//		stmt.setWhich(newClass);
//		CSharp_ClassElement elt = new CSharp_ClassElement();
//		elt.setWhich(stmt);
//
//		if (parentClass != null)
//		{
//			if (parentClass.elements == null) parentClass.elements = new TokenList<CSharp_ClassElement>();
//			parentClass.elements.addToken(elt);
//		}
//
//		return newClass;
//	}
//
//	@Override
//	public void setClassExtends(CSharp_Class parentClass, String extendsClass)
//	{
//		parentClass.extendsOrImplements = new CSharp_ExtendsOrImplements();
//		parentClass.extendsOrImplements.setPresent(true);
//
//		parentClass.extendsOrImplements.className = new SeparatedList<CSharp_Identifier_Reference, PunctuationPeriod>();
//		CSharp_Identifier_Reference id = new CSharp_Identifier_Reference();
//		id.setValue(extendsClass);
//		parentClass.extendsOrImplements.className.addPrimaryElement(id);
//	}
//
//	@Override
//	public CSharp_Class addInnerDataClass(CSharp_Class parentClass, String className, TYPES type, AbstractToken source)
//	{
//		// String newType = CSharp_Transform.getTypeString(type);
//
//		// Create the new inner class
//		CSharp_Class newClass = new CSharp_Class();
//		newClass.className = new CSharp_Class_Definition();
//		newClass.className.setValue(className);
//		newClass.setTransformationSource(source);
//
//		// Add it to the outer class
//		CSharp_Statement newStmt = new CSharp_Statement();
//		newStmt.setWhich(newClass);
//		CSharp_ClassElement elt = new CSharp_ClassElement();
//		elt.setWhich(newStmt);
//		if (parentClass.elements == null) parentClass.elements = new TokenList<CSharp_ClassElement>();
//		parentClass.elements.addToken(elt);
//
//		return newClass;
//	}
//
//	@Override
//	public CSharp_Type createType(String name)
//	{
//		CSharp_IdList idList = new CSharp_IdList();
//		idList.typeName = new CSharp_Identifier_Reference();
//		idList.typeName.setValue(name);
//
//		CSharp_Type type = new CSharp_Type();
//		type.typeName = new CSharp_TypeName();
//		type.typeName.setWhich(idList);
//		return type;
//	}
//
//	@Override
//	public void finalize(CSharp_Class cls)
//	{
//		// Nothing to do here
//	}
}
