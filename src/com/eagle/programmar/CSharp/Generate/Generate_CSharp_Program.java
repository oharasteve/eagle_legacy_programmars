// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2016

package com.eagle.programmar.CSharp.Generate;

public class Generate_CSharp_Program
//		implements Generate_Eagle_Program<CSharp_Program, CSharp_Class, CSharp_Statement>
{
//	@SuppressWarnings("unused")
//	private Generate_CSharp _target;
//
//	public Generate_CSharp_Program(Generate_CSharp target)
//	{
//		_target = target;
//	}
//
//	@Override
//	public void addProgramComment(CSharp_Program pgm, String comment, AbstractToken source)
//	{
//		String commentString = comment;
//		if (comment != null && !comment.startsWith("//") && !comment.startsWith("/*")) commentString = "// " + comment;
//
//		CSharp_Comment comm = new CSharp_Comment(commentString, true);
//		comm.setTransformationSource(source);
//
//		if (pgm.comments1 == null) pgm.comments1 = new TokenList<CSharp_Comment>();
//		pgm.comments1.addToken(comm);
//		comm.setTransformationSource(source);
//	}
//
//	@Override
//	public void addImport(CSharp_Program pgm, String importString, boolean useStar, AbstractToken source)
//	{
//		CSharp_Using using = new CSharp_Using();
//		using.semicolon = new PunctuationSemicolon();
//
//		String[] pieces = importString.split("\\.");
//		boolean first = true;
//		for (String piece : pieces)
//		{
//			CSharp_Identifier id = new CSharp_Identifier();
//			id.setValue(piece);
//
//			if (first)
//			{
//				first = false;
//				using.id = id;
//			}
//			else
//			{
//				if (using.moreIds == null) using.moreIds = new TokenList<CSharp_MoreUsing>();
//				CSharp_MoreUsing more = new CSharp_MoreUsing();
//				more.id = id;
//				more.dot = new PunctuationPeriod();
//				using.moreIds.addToken(more);
//			}
//		}
//
//		CSharp_NamespaceOrClassEntry entry = new CSharp_NamespaceOrClassEntry();
//		entry.setWhich(using);
//		entry.setTransformationSource(source);
//		if (pgm.myClasses == null) pgm.myClasses = new TokenList<CSharp_NamespaceOrClassEntry>();
//		pgm.myClasses.addToken(entry);
//	}
//
//	@Override
//	public void setPackage(CSharp_Program pgm, String pkgName, AbstractToken source)
//	{
//		CSharp_Namespace namespace = new CSharp_Namespace();
//		namespace.setTransformationSource(source);
//		namespace.moreIds = new TokenList<CSharp_MoreNamespaceId>();
//		namespace.leftBrace = new PunctuationLeftBrace();
//		namespace.elems = new TokenList<CSharp_ProgramElems>();
//		namespace.rightBrace = new PunctuationRightBrace();
//		namespace.setTransformationSource(source);
//
//		String[] pieces = pkgName.split("\\.");
//		boolean first = true;
//		for (String piece : pieces)
//		{
//			CSharp_Identifier id = new CSharp_Identifier();
//			id.setValue(piece);
//
//			if (first)
//			{
//				first = false;
//				namespace.id = id;
//			}
//			else
//			{
//				CSharp_MoreNamespaceId more = new CSharp_MoreNamespaceId();
//				more.id = id;
//				more.dot = new PunctuationPeriod();
//				namespace.moreIds.addToken(more);
//			}
//		}
//
//		CSharp_NamespaceOrClassEntry entry = new CSharp_NamespaceOrClassEntry();
//		entry.setWhich(namespace);
//		if (pgm.myClasses == null) pgm.myClasses = new TokenList<CSharp_NamespaceOrClassEntry>();
//		pgm.myClasses.addToken(entry);
//	}
//
//	@Override
//	public void addClass(CSharp_Program pgm, CSharp_Class cls)
//	{
//		if (pgm.myClasses != null && pgm.myClasses.size() > 0)
//		{
//			CSharp_NamespaceOrClassEntry last = pgm.myClasses._elements.get(pgm.myClasses.size() - 1);
//			if (last.getWhich() instanceof CSharp_Namespace)
//			{
//				CSharp_Namespace ns = (CSharp_Namespace) last.getWhich();
//				if (ns.elems == null)
//				{
//					ns.elems = new TokenList<CSharp_ProgramElems>();
//				}
//				CSharp_ProgramElems elem = new CSharp_ProgramElems();
//				elem.setWhich(cls);
//				ns.elems.addToken(elem);
//			}
//		}
//	}
//
//	@Override
//	public void addProgramStatement(CSharp_Program pgm, CSharp_Statement statement)
//	{
//		throw new RuntimeException("need to implement");
//	}
}