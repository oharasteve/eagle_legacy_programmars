// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2013

package com.eagle.symbols;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import com.eagle.core.EagleSyntax;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.DefinitionInterface;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.ReferenceInterface;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.utils.EagleUtilities;

public abstract class Eagle_Resolve_References
{
	protected boolean _trace = false;

	protected void connectReferencesOutside(EagleSyntax syntax, EagleScope scope, AbstractToken token,
			Class<? extends DefinitionInterface> definitionClass,
			Class<? extends ReferenceInterface> referenceClass,
			Class<? extends AbstractToken> outsideClass)
	{
		ArrayList<AbstractToken> defs = findAllInstancesOutside(token, definitionClass, outsideClass);
		connectRefs(syntax, scope, token, defs, referenceClass);
	}
	
	protected void connectReferences(EagleSyntax syntax, EagleScope scope, AbstractToken token,
			Class<? extends DefinitionInterface> definitionClass,
			Class<? extends ReferenceInterface> referenceClass)
	{
		ArrayList<AbstractToken> defs = findAllInstances(token, definitionClass);
		connectRefs(syntax, scope, token, defs, referenceClass);
	}

	protected void connectRefs(EagleSyntax syntax, EagleScope scope, AbstractToken token,
			ArrayList<AbstractToken> defs, Class<? extends ReferenceInterface> referenceClass)
	{
		ArrayList<AbstractToken> refs = findAllInstances(token, referenceClass);
		
		for (AbstractToken definition : defs)
		{
			DefinitionInterface def = (DefinitionInterface) definition;
			scope.addSymbol(def);
			String defName = def.toString();
			
			for (AbstractToken reference : refs)
			{
				ReferenceInterface ref = (ReferenceInterface) reference;
				
				if (syntax._isCaseSensitive) if (! defName.equals(ref.toString())) continue;
				if (!syntax._isCaseSensitive) if (! defName.equalsIgnoreCase(ref.toString())) continue;
				
				ref.setDefinition(def);
				def.addReference(ref);
			}
		}
	}
	
	// Convenience routine that allocates the array for you ....
	protected ArrayList<AbstractToken> findAllInstancesOutside(AbstractToken token, Class<?> targetClass, Class<? extends AbstractToken> outsideClass)
	{
		ArrayList<AbstractToken> tokenList = new ArrayList<AbstractToken>();
		findEm(tokenList, token, false, targetClass, outsideClass);
		return tokenList;
	}

	// Convenience routine that allocates the array for you ....
	protected ArrayList<AbstractToken> findAllInstances(AbstractToken token, Class<?> targetClass)
	{
		ArrayList<AbstractToken> tokenList = new ArrayList<AbstractToken>();
		findEm(tokenList, token, false, targetClass, null);
		return tokenList;
	}

	// You have to create the TokenList yourself, in advance
	protected void findAllInstances(ArrayList<AbstractToken> tokenList, AbstractToken token, Class<?> targetClass)
	{
		findEm(tokenList, token, false, targetClass, null);
	}
	
	// Careful, recursive
	private void findEm(ArrayList<AbstractToken> tokenList, AbstractToken token, boolean optional,
			Class<?> targetClass, Class<? extends AbstractToken> outsideClass)
	{
		if (token == null) return;
		if (optional && ! token.isPresent()) return;
		
		Class<?> cls = token.getClass();
		
		// Add it into the result
		if (targetClass.isAssignableFrom(cls))
		{
			//System.out.println("*** Found an instance of " + targetClass.getName());
			tokenList.add(token);
		}
		
		if (token instanceof TerminalToken)
		{
			// Nuthin' else to do
		}
		else if (token instanceof TokenChooser)
		{
			AbstractToken child = ((TokenChooser) token).getWhich();
			findEm(tokenList, child, optional, targetClass, outsideClass);
		}
		else
		{
			// Don't go inside Functions when looking for Global variables ....
			if (outsideClass != null)
			{
				//System.out.println("** Checking outside=" + outsideClass.getName() + "  token=" + token.getClass().getName());
				if (outsideClass.isAssignableFrom(token.getClass()))
				{
					//System.out.println("***** Skipping outside=" + outsideClass.getName() + "  token=" + token);
					return;	// Don't go inside this one
				}
			}

			// See what the Token contains
			Field[] fields = cls.getFields();
			for (Field fld : fields)
			{
				String name = fld.getName();
				if (name.startsWith("this$")) continue;		// Skip inner class junk

				boolean opt = fld.getAnnotation(TokenSequence.OPT.class) != null;
				boolean skip = fld.isAnnotationPresent(AbstractToken.SKIP.class);
				if (skip) continue;

				Object obj = null;
				try
				{
					obj = fld.get(token);
				}
				catch (Exception ex)
				{
					throw new RuntimeException("Unable to access field " + name + " in " + token.getClass().getName());
				}
				
				if (obj instanceof TokenList<?>)
				{
					TokenList<?> values = (TokenList<?>) obj;
					for (Object val : values._elements)
					{
						findEm(tokenList, (AbstractToken) val, opt, targetClass, outsideClass);
					}
					continue;
				}
				
				// Get the child instance
				if ( ! (obj instanceof AbstractToken)) continue;	// Ignore junk in class definition
				AbstractToken child = (AbstractToken) obj;
				
				// Recurse
				findEm(tokenList, child, opt, targetClass, outsideClass);
			}
		}
	}
	
	public void printCrossReference(PrintStream out, AbstractToken token)
	{
		ArrayList<AbstractToken> definitions = new ArrayList<AbstractToken>();
		findAllInstances(definitions, token, DefinitionInterface.class);
		if (definitions.size() == 0)
		{
			out.println("No definitions found.");
			return;
		}
		
		out.println();
		out.println(" Line  Definition            Type       References");
		out.println("=====  ====================  =========  =====================");
		for (AbstractToken defToken : definitions)
		{
			DefinitionInterface def = (DefinitionInterface) defToken;
			out.print(EagleUtilities.rj(def.getStartLine()+1, 5) + "  " +
					EagleUtilities.lj(def.toString(), 20) + "  " +
					EagleUtilities.lj(def.getType().getName(), 9) + "  ");
			
			Collection<ReferenceInterface> refList = def.listReferences();
			if (refList == null)
			{
				out.print("(none)");
			}
			else
			{
				for (ReferenceInterface ref : refList)
				{
					out.print(EagleUtilities.rj(ref.getStartLine()+1, 5));
				}
			}
			
			out.println();
		}
		out.println();
	}
}
