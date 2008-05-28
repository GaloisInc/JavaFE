// -*- mode: java -*-
/* Copyright 2000, 2001, Compaq Computer Corporation */

/* IF THIS IS A JAVA FILE, DO NOT EDIT IT!  

   Most Java files in this directory which are part of the Javafe AST
   are automatically generated using the astgen comment (see
   ESCTools/Javafe/astgen) from the input file 'hierarchy.h'.  If you
   wish to modify AST classes or introduce new ones, modify
   'hierarchy.j.'
 */

package javafe.ast;

import javafe.util.Assert;
import javafe.util.Location;
import javafe.util.ErrorSet;
import javafe.tc.TagConstants;

// Convention: unless otherwise noted, integer fields named "loc" refer
// to the location of the first character of the syntactic unit


public class ClassDecl extends TypeDecl
{
  public TypeName superClass;



  /** Set the parent pointer of the <code>TypeDeclElem</code>s
    inside the <code>this</code>. */
  private void postMake() {
    for(int i = 0; i < elems.size(); i++)
      elems.elementAt(i).setParent(this);
  }


// Generated boilerplate constructors:

  //@ ensures this.modifiers == modifiers;
  //@ ensures this.pmodifiers == pmodifiers;
  //@ ensures this.id == id;
  //@ ensures this.superInterfaces == superInterfaces;
  //@ ensures this.tmodifiers == tmodifiers;
  //@ ensures this.elems == elems;
  //@ ensures this.loc == loc;
  //@ ensures this.locId == locId;
  //@ ensures this.locOpenBrace == locOpenBrace;
  //@ ensures this.locCloseBrace == locCloseBrace;
  //@ ensures this.superClass == superClass;
  protected ClassDecl(int modifiers, ModifierPragmaVec pmodifiers, /*@ non_null @*/ Identifier id, /*@ non_null @*/ TypeNameVec superInterfaces, TypeModifierPragmaVec tmodifiers, /*@ non_null @*/ TypeDeclElemVec elems, int loc, int locId, int locOpenBrace, int locCloseBrace, TypeName superClass) {
     super(modifiers, pmodifiers, id, superInterfaces, tmodifiers, elems, loc, locId, locOpenBrace, locCloseBrace);
     this.superClass = superClass;
  }

// Generated boilerplate methods:

  public final int childCount() {
     int sz = 0;
     if (this.pmodifiers != null) sz += this.pmodifiers.size();
     if (this.superInterfaces != null) sz += this.superInterfaces.size();
     if (this.tmodifiers != null) sz += this.tmodifiers.size();
     if (this.elems != null) sz += this.elems.size();
     return sz + 2;
  }

  public final /*@ non_null */ Object childAt(int index) {
          /*throws IndexOutOfBoundsException*/
     if (index < 0)
        throw new IndexOutOfBoundsException("AST child index " + index);
     int indexPre = index;

     int sz;

     sz = (this.pmodifiers == null ? 0 : this.pmodifiers.size());
     if (0 <= index && index < sz)
        return this.pmodifiers.elementAt(index);
     else index -= sz;

     if (index == 0) return this.id;
     else index--;

     sz = (this.superInterfaces == null ? 0 : this.superInterfaces.size());
     if (0 <= index && index < sz)
        return this.superInterfaces.elementAt(index);
     else index -= sz;

     sz = (this.tmodifiers == null ? 0 : this.tmodifiers.size());
     if (0 <= index && index < sz)
        return this.tmodifiers.elementAt(index);
     else index -= sz;

     sz = (this.elems == null ? 0 : this.elems.size());
     if (0 <= index && index < sz)
        return this.elems.elementAt(index);
     else index -= sz;

     if (index == 0) return this.superClass;
     else index--;

     throw new IndexOutOfBoundsException("AST child index " + indexPre);
  }   //@ nowarn Exception;

  public final /*@non_null*/String toString() {
     return "[ClassDecl"
        + " modifiers = " + this.modifiers
        + " pmodifiers = " + this.pmodifiers
        + " id = " + this.id
        + " superInterfaces = " + this.superInterfaces
        + " tmodifiers = " + this.tmodifiers
        + " elems = " + this.elems
        + " loc = " + this.loc
        + " locId = " + this.locId
        + " locOpenBrace = " + this.locOpenBrace
        + " locCloseBrace = " + this.locCloseBrace
        + " superClass = " + this.superClass
        + "]";
  }

  public final int getTag() {
     return TagConstants.CLASSDECL;
  }

  public final void accept(/*@ non_null */ Visitor v) { v.visitClassDecl(this); }

  public final /*@ non_null */ Object accept(/*@ non_null */ VisitorArgResult v, Object o) {return v.visitClassDecl(this, o); }

  public void check() {
     super.check();
     if (this.pmodifiers != null)
        for(int i = 0; i < this.pmodifiers.size(); i++)
           this.pmodifiers.elementAt(i).check();
     if (this.id == null) throw new RuntimeException();
     for(int i = 0; i < this.superInterfaces.size(); i++)
        this.superInterfaces.elementAt(i).check();
     if (this.tmodifiers != null)
        for(int i = 0; i < this.tmodifiers.size(); i++)
           this.tmodifiers.elementAt(i).check();
     if (this.elems == null) throw new RuntimeException();
     if (this.superClass != null)
        this.superClass.check();
  }

  //@ requires loc != javafe.util.Location.NULL;
  //@ requires locId != javafe.util.Location.NULL;
  //@ requires locOpenBrace != javafe.util.Location.NULL;
  //@ requires locCloseBrace != javafe.util.Location.NULL;
  //@ ensures \result != null;
  public static /*@ non_null */ ClassDecl make(int modifiers, ModifierPragmaVec pmodifiers, /*@ non_null @*/ Identifier id, /*@ non_null @*/ TypeNameVec superInterfaces, TypeModifierPragmaVec tmodifiers, /*@ non_null @*/ TypeDeclElemVec elems, int loc, int locId, int locOpenBrace, int locCloseBrace, TypeName superClass) {
     ClassDecl result = new ClassDecl(modifiers, pmodifiers, id, superInterfaces, tmodifiers, elems, loc, locId, locOpenBrace, locCloseBrace, superClass);
     result.postMake();
     return result;
  }
}
