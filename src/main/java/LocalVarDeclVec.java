/**
 ** LocalVarDeclVec: A Vector of LocalVarDecl's. <p>
 **
 ** The interface to this class follows java.util.Vector, where
 ** appropriate. <p>
 **
 **
 ** This class was automatically created from the template
 ** javafe.util/_<tt>TYPE</tt>_Vec.j.<p>, i.e.
 ** $Id: LocalVarDeclVec.j 1928 2006-05-19 12:43:10Z chalin $
 **
 ** DO NOT EDIT!!<p>
 **/

/*
 * File: LocalVarDeclVec.java <p>
 *
 * This file is a template for a vector of X.  It is filled in using
 * sed (e.g, see the Makefile for javafe.ast).  The following
 * replacements need to be done:<p>
 *
 * LocalVarDecl should be globally replaced with the simple name of the
 *		element type<p>
 * javafe.ast should be globally replaced with the package the resulting
 *		Vector type should live in<p>
 * javafe.ast should be globally replaced with the package the
 *		element type belongs to<p>
 *
 * The resulting type will have name LocalVarDeclVec.<p>
 * 
 * Example: to make a vector of java.lang.Integer's that lives in
 * javafe.ast, substitute Integer, javafe.ast, and java.lang
 * respectively.<p>
 *
 *
 * By Default, LocalVarDeclVec's may not contain nulls.  If the ability to
 * hold nulls is desired, remove all lines with "// @@@@" on them.<p>
 */


package javafe.ast;


import java.util.Vector;
import javafe.util.StackVector;

import javafe.ast.LocalVarDecl;


public class  LocalVarDeclVec {

    /***************************************************
     *                                                 *
     * Instance fields:				       *
     *                                                 *
     ***************************************************/

    private /*@ spec_public non_null*/ LocalVarDecl[] elements;
    //@ invariant (\forall int i; (0<=i && i<count) ==> elements[i]!=null);
    //@ invariant \typeof(elements) == \type(LocalVarDecl[]);

    //@ invariant elements.owner == this;

    /*@spec_public*/ private int count;
    //@ invariant 0 <= count;
    //@ invariant count <= elements.length;


    /***************************************************
     *                                                 *
     * Private constructors:			       *
     *                                                 *
     ***************************************************/

    //@ requires els!=null;
    //@ requires \nonnullelements(els);				// @@@@
    //@ ensures count == els.length;
    //@ pure
    private LocalVarDeclVec(LocalVarDecl[] els) {
	this.count = els.length;
	this.elements = new LocalVarDecl[count];
	//@ set elements.owner = this;

	System.arraycopy(els,0, elements,0, count);
    }

    //@ requires cnt >= 0;
    //@ ensures this.elements.length >= cnt;
    //@ pure
    private LocalVarDeclVec(int cnt) {
	this.elements = new LocalVarDecl[(cnt == 0 ? 2 : cnt)];
	//@ set elements.owner = this;

	this.count = 0;
    }


    /***************************************************
     *                                                 *
     * Public maker methods:			       *
     *                                                 *
     ***************************************************/

    //@ ensures \result!=null;
    //@ ensures \fresh(\result);
    //@ pure
    public static LocalVarDeclVec make() { 
	return new LocalVarDeclVec(0);
    }

    //@ requires count >= 0;
    //@ ensures \result!=null;
    //@ ensures \fresh(\result);
    public static LocalVarDeclVec make(int count) { 
	return new LocalVarDeclVec(count);
    }

    //@ requires vec!=null;
    //@ requires vec.elementType <: \type(LocalVarDecl);
    //@ requires !vec.containsNull;
    //@ ensures \result!=null;
    //@ ensures \fresh(\result);
    public static LocalVarDeclVec make(Vector vec) {
	int sz = vec.size();
	LocalVarDeclVec result = new LocalVarDeclVec(sz);
	result.count = sz;
	vec.copyInto(result.elements);
	return result;
    }

    //@ requires els!=null;
    //@ requires \nonnullelements(els);
    //@ ensures \result!=null;
    //@ ensures \result.count == els.length;
    //@ ensures \fresh(\result);
    public static LocalVarDeclVec make(LocalVarDecl[] els) {
	return new LocalVarDeclVec(els);
    }

    //
    //@ requires s.vectorCount>1;
    //@ requires s.elementType <: \type(LocalVarDecl);
    //@ ensures \result!=null;
    //@ ensures \result.count == (\old(s.elementCount) - \old(s.currentStackBottom));
    // These are from pop() on s:
    //@ modifies s.vectorCount;
    //@ ensures s.vectorCount == \old(s.vectorCount)-1;
    //@ modifies s.elementCount, s.currentStackBottom;
    public static LocalVarDeclVec popFromStackVector(/*@non_null*/ StackVector s) {
	// Creates a new LocalVarDeclVec from top stuff in StackVector
	int sz = s.size();
	LocalVarDeclVec r = new LocalVarDeclVec(sz);
	s.copyInto(r.elements);
	r.count = sz;
	s.pop();
	return r;
    }


    /***************************************************
     *                                                 *
     * Other methods:				       *
     *                                                 *
     ***************************************************/

    //@ requires 0<=index && index<count;
    //@ ensures \result!=null;					// @@@@
    //@ pure
    public final LocalVarDecl elementAt(int index)
	  /*throws ArrayIndexOutOfBoundsException*/ {
	if (index < 0 || index >= count)
	    throw new ArrayIndexOutOfBoundsException(index);

	javafe.util.Assert.notNull(elements[index]);		// @@@@
	return elements[index];
    }

    //@ requires 0<=index && index<count;
    //@ requires x!=null;					// @@@@
    public final void setElementAt(LocalVarDecl x, int  index) 
	/*throws ArrayIndexOutOfBoundsException*/ {
	if (index < 0 || index >= count)
	    throw new ArrayIndexOutOfBoundsException(index);
	elements[index] = x;
    }

    //@ ensures \result==count;
    //@ pure
    public final int size() { return count; }

    public final /*@non_null*/String toString() {
	StringBuffer b = new StringBuffer();
	b.append("{LocalVarDeclVec");
	for(int i = 0; i < count; i++) {
	    b.append(" ");
	    if (elements[i]==null)
		b.append("null");
	    else
		b.append(elements[i].toString());
	}
	b.append('}');
	return b.toString();
    }

  //@ ensures \result!=null;
  //@ ensures \nonnullelements(\result);			// @@@@
  //@ pure
  public final LocalVarDecl[] toArray() {
      int ct = count;
      LocalVarDecl[] b = new LocalVarDecl[ ct ];
      for(int i = 0; i < ct; i++) {
	  b[i]=elements[i];
      }
      return b;
  }

  //@ ensures \result!=null;
  //@ pure
  public final LocalVarDeclVec copy() {
    LocalVarDeclVec result = new LocalVarDeclVec(count);
    result.count = count;
    System.arraycopy(elements,0,result.elements,0,count);
    return result;
  }

  //@ requires x!=null;						// @@@@
  public boolean contains(LocalVarDecl x) {
    for(int i = 0; i < count; i++) {
      if( elements[i] == x ) return true;
    }
    return false;
  }

  //@ requires x!=null;						// @@@@
  //@ modifies count,elements;
  //@ ensures count==\old(count)+1;
  public final void addElement(LocalVarDecl x) {
    if( count == elements.length ) {
      LocalVarDecl[] newElements = new LocalVarDecl[ 2*(elements.length==0 ?
					      2 : elements.length) ];
      //@ set newElements.owner = this;

      System.arraycopy(elements, 0, newElements, 0, elements.length );
      elements = newElements;
    }
    elements[count++]=x;
  }

  //@ requires x!=null;						// @@@@
  //@ modifies count;
  public final boolean removeElement(LocalVarDecl x) {
    for( int i=0; i<count; i++ ) {
      if( elements[i] == x ) {
	  int ct=count;
	for( int j=i+1; j<ct; j++ ) 
	  elements[j-1]=elements[j];
	count--;
	return true;
      }
    }
    return false;
  }

    //@ requires 0<=i && i<count;
    //@ modifies count;
    public final void removeElementAt(int i) {
	int ct=count;
        for (int j=i+1; j<ct; j++) {
            elements[j-1]=elements[j];
	}	    
        count--;
    }

    //@ requires count>0;
    //@ modifies count;
    //@ ensures count==\old(count)-1;
    //@ ensures \result!=null;
    public final LocalVarDecl pop() {
        count--;
        LocalVarDecl result = elements[count];
	elements[count] = null;
	return result;
    }


    //@ modifies count;
    //@ ensures count==0;
    public final void removeAllElements() {
	count = 0;
    }


  //@ requires 0 <= index && index <= this.count;
  //@ requires obj!=null;
  //@ modifies count,elements;
  //@ ensures count==\old(count)+1;
  public final void insertElementAt(LocalVarDecl obj, int index) {
    if( count == elements.length ) {
      LocalVarDecl[] newElements = new LocalVarDecl[ 2*(elements.length==0 ?
					      2 : elements.length) ];
      //@ set newElements.owner = this;
      System.arraycopy(elements, 0, newElements, 0, elements.length );
      elements = newElements;
    }
    int ct=count;
    //-@ loop_predicate i!=ct ==> elements[ct] != null, i<= ct; // FIXME - what are the semantics
    for( int i=count; i>index; i--) 
      elements[i]=elements[i-1];
    elements[index]=obj;
    count++;
  }

  //@ requires vec!=null;
  //@ modifies count,elements;
  //@ ensures count==\old(count)+vec.count;
  public final void append(LocalVarDeclVec vec) {
      //-@ loop_predicate count == \old(count)+i, i <= vec.count; // FIXME - what are the semantics
    for( int i=0; i<vec.size(); i++)
      addElement( vec.elementAt(i) );
  }
}

/* Copyright 2000, 2001, Compaq Computer Corporation */
