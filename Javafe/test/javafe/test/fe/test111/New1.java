/**
 ** These files test the use of new instance expressions' enclosing
 ** instance pointers -- both with and without explicit enclosing
 ** instance pointers.
 **/


class Top {
    // no explicit enclosing instance ptr...
    Top foo(int x) {
	return new Top();       // normal 1.0 case
    }

    int TopLevel;    // attempt to block seeing TopLevel...
    
    static class TopLevel {
	// ...

	// explicit enclosing instance ptr...
	TopLevel bar(char q) {
	    return q.new TopLevel(); // error! not a reference type!
	}

	// continued in New2.java...
    }
}
class Top2 {
    static class TopLevel {}
}
