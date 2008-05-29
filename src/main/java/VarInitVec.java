/**
 ** VarInitVec: A Vector of VarInit's. <p>
 **
 ** The interface to this class follows java.util.Vector, where
 ** appropriate. <p>
 **
 **
 ** This class was automatically created from the template
 ** javafe.util/_<tt>TYPE</tt>_Vec.j.<p>, i.e.
 ** $Id: VarInitVec.j 1928 2006-05-19 12:43:10Z chalin $
 **
 ** DO NOT EDIT!!<p>
 **/

/*
 * File: VarInitVec.java <p>
 *
 * This file is a template for a vector of X.  It is filled in using
 * sed (e.g, see the Makefile for javafe.ast).  The following
 * replacements need to be done:<p>
 *
 * VarInit should be globally replaced with the simple name of the
 *		element type<p>
 * javafe.ast should be globally replaced with the package the resulting
 *		Vector type should live in<p>
 * javafe.ast should be globally replaced with the package the
 *		element type belongs to<p>
 *
 * The resulting type will have name VarInitVec.<p>
 * 
 * Example: to make a vector of java.lang.Integer's that lives in
 * javafe.ast, substitute Integer, javafe.ast, and java.lang
 * respectively.<p>
 *
 *
 * By Default, VarInitVec's may not contain nulls.  If the ability to
 * hold nulls is desired, remove all lines with "// @@@@" on them.<p>
 */


package javafe.ast;


import java.util.Vector;
import javafe.util.StackVector;

import javafe.ast.VarInit;


public class  VarInitVec {

    /***************************************************
     *                                                 *
     * Instance fields:				       *
     *                                                 *
     ***************************************************/

    private /*@ spec_public non_null*/ VarInit[] elements;
    //@ invariant (\forall int i; (0<=i && i<count) ==> elements[i]!=null);
    //@ invariant \typeof(elements) == \type(VarInit[]);

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
    private VarInitVec(VarInit[] els) {
	this.count = els.length;
	this.elements = new VarInit[count];
	//@ set elements.owner = this;

	System.arraycopy(els,0, elements,0, count);
    }

    //@ requires cnt >= 0;
    //@ ensures this.elements.length >= cnt;
    //@ pure
    private VarInitVec(int cnt) {
	this.elements = new VarInit[(cnt == 0 ? 2 : cnt)];
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
    public static VarInitVec make() { 
	return new VarInitVec(0);
    }

    //@ requires count >= 0;
    //@ ensures \result!=null;
    //@ ensures \fresh(\result);
    public static VarInitVec make(int count) { 
	return new VarInitVec(count);
    }

    //@ requires vec!=null;
    //@ requires vec.elementType <: \type(VarInit);
    //@ requires !vec.containsNull;
    //@ ensures \result!=null;
    //@ ensures \fresh(\result);
    public static VarInitVec make(Vector vec) {
	int sz = vec.size();
	VarInitVec result = new VarInitVec(sz);
	result.count = sz;
	vec.copyInto(result.elements);
	return result;
    }

    //@ requires els!=null;
    //@ requires \nonnullelements(els);
    //@ ensures \result!=null;
    //@ ensures \result.count == els.length;
    //@ ensures \fresh(\result);
    public static VarInitVec make(VarInit[] els) {
	return new VarInitVec(els);
    }

    //
    //@ requires s.vectorCount>1;
    //@ requires s.elementType <: \type(VarInit);
    //@ ensures \result!=null;
    //@ ensures \result.count == (\old(s.elementCount) - \old(s.currentStackBottom));
    // These are from pop() on s:
    //@ modifies s.vectorCount;
    //@ ensures s.vectorCount == \old(s.vectorCount)-1;
    //@ modifies s.elementCount, s.currentStackBottom;
    public static VarInitVec popFromStackVector(/*@non_null*/ StackVector s) {
	// Creates a new VarInitVec from top stuff in StackVector
	int sz = s.size();
	VarInitVec r = new VarInitVec(sz);
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
    public final VarInit elementAt(int index)
	  /*throws ArrayIndexOutOfBoundsException*/ {
	if (index < 0 || index >= count)
	    throw new ArrayIndexOutOfBoundsException(index);

	javafe.util.Assert.notNull(elements[index]);		// @@@@
	return elements[index];
    }

    //@ requires 0<=index && index<count;
    //@ requires x!=null;					// @@@@
    public final void setElementAt(VarInit x, int  index) 
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
	b.append("{VarInitVec");
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
  public final VarInit[] toArray() {
      int ct = count;
      VarInit[] b = new VarInit[ ct ];
      for(int i = 0; i < ct; i++) {
	  b[i]=elements[i];
      }
      return b;
  }

  //@ ensures \result!=null;
  //@ pure
  public final VarInitVec copy() {
    VarInitVec result = new VarInitVec(count);
    result.count = count;
    System.arraycopy(elements,0,result.elements,0,count);
    return result;
  }

  //@ requires x!=null;						// @@@@
  public boolean contains(VarInit x) {
    for(int i = 0; i < count; i++) {
      if( elements[i] == x ) return true;
    }
    return false;
  }

  //@ requires x!=null;						// @@@@
  //@ modifies count,elements;
  //@ ensures count==\old(count)+1;
  public final void addElement(VarInit x) {
    if( count == elements.length ) {
      VarInit[] newElements = new VarInit[ 2*(elements.length==0 ?
					      2 : elements.length) ];
      //@ set newElements.owner = this;

      System.arraycopy(elements, 0, newElements, 0, elements.length );
      elements = newElements;
    }
    elements[count++]=x;
  }

  //@ requires x!=null;						// @@@@
  //@ modifies count;
  public final boolean removeElement(VarInit x) {
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
    public final VarInit pop() {
        count--;
        VarInit result = elements[count];
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
  public final void insertElementAt(VarInit obj, int index) {
    if( count == elements.length ) {
      VarInit[] newElements = new VarInit[ 2*(elements.length==0 ?
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
  public final void append(VarInitVec vec) {
      //-@ loop_predicate count == \old(count)+i, i <= vec.count; // FIXME - what are the semantics
    for( int i=0; i<vec.size(); i++)
      addElement( vec.elementAt(i) );
  }
}

/* Copyright 2000, 2001, Compaq Computer Corporation */