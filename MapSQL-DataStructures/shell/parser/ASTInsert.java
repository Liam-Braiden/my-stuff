/* Generated By:JJTree: Do not edit this line. ASTInsert.java Version 4.3 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package mapsql.shell.parser;

public
class ASTInsert extends SimpleNode {
  public ASTInsert(int id) {
    super(id);
  }

  public ASTInsert(MapSQL p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(MapSQLVisitor visitor, Object data) {
    return visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=5a4b305877858ca7051096cfa7456547 (do not edit this line) */